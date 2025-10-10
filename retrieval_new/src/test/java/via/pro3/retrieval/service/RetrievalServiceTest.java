package via.pro3.retrieval.service;

import org.junit.jupiter.api.Test;
import via.pro3.retrieval.generated.*;
import io.grpc.stub.StreamObserver;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class RetrievalServiceTest {

  static class CapturingObserver<T> implements StreamObserver<T> {
    T value;
    Throwable error;
    final AtomicBoolean completed = new AtomicBoolean(false);

    @Override public void onNext(T v) { value = v; }
    @Override public void onError(Throwable t) { error = t; }
    @Override public void onCompleted() { completed.set(true); }
  }

  @Test
  void getAnimalsByProduct_success() throws Exception {
    DataSource stub = new DataSource() {
      @Override public List<Integer> getAnimalsByProduct(int productId) {
        return List.of(7, 11, 13);
      }
      @Override public List<Integer> getProductsByAnimal(int animalId) {
        throw new UnsupportedOperationException();
      }
    };

    var svc = new RetrievalService(stub);
    var req = GetAnimalsByProductRequest.newBuilder().setProductId(42).build();
    var obs = new CapturingObserver<GetAnimalsByProductResponse>();

    svc.getAnimalsByProduct(req, obs);

    assertNull(obs.error, "no error expected");
    assertTrue(obs.completed.get(), "should complete");
    assertNotNull(obs.value);
    assertEquals(
        List.of(
            AnimalRef.newBuilder().setId(7).build(),
            AnimalRef.newBuilder().setId(11).build(),
            AnimalRef.newBuilder().setId(13).build()
        ),
        obs.value.getAnimalsList()
    );
  }

  @Test
  void getAnimalsByProduct_sqlExceptionSurfacesAsOnError() throws Exception {
    DataSource stub = new DataSource() {
      @Override public List<Integer> getAnimalsByProduct(int productId) throws SQLException {
        throw new SQLException("boom");
      }
      @Override public List<Integer> getProductsByAnimal(int animalId) {
        throw new UnsupportedOperationException();
      }
    };

    var svc = new RetrievalService(stub);
    var req = GetAnimalsByProductRequest.newBuilder().setProductId(1).build();
    var obs = new CapturingObserver<GetAnimalsByProductResponse>();

    svc.getAnimalsByProduct(req, obs);

    assertNotNull(obs.error, "onError should be called");
    assertFalse(obs.completed.get(), "should not complete after onError");
    assertNull(obs.value, "no value after error");
  }

  @Test
  void getProductsByAnimal_success() {
    DataSource stub = new DataSource() {
      @Override public List<Integer> getAnimalsByProduct(int productId) {
        throw new UnsupportedOperationException();
      }
      @Override public List<Integer> getProductsByAnimal(int animalId) {
        return List.of(101, 102);
      }
    };

    var svc = new RetrievalService(stub);
    var req = GetProductsByAnimalRequest.newBuilder().setAnimalId(9).build();
    var obs = new CapturingObserver<GetProductsByAnimalResponse>();

    svc.getProductsByAnimal(req, obs);

    assertNull(obs.error);
    assertTrue(obs.completed.get());
    assertEquals(
        List.of(
            ProductRef.newBuilder().setId(101).build(),
            ProductRef.newBuilder().setId(102).build()
        ),
        obs.value.getProductsList()
    );
  }
}
