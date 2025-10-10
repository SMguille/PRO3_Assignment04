package via.pro3.retrieval.service;

import io.grpc.stub.StreamObserver;
import via.pro3.retrieval.generated.*;

import java.sql.SQLException;
import java.util.List;

public class RetrievalService extends TraceServiceGrpc.TraceServiceImplBase
{

  private DataSource source;

  public RetrievalService(DataSource source)
  {
    this.source = source;
  }

  @Override public void getAnimalsByProduct(GetAnimalsByProductRequest request,
      StreamObserver<GetAnimalsByProductResponse> responseObserver)
  {
    System.out.println("Received request >>> " + request.toString());
    // Make builder
    var responseBuilder = GetAnimalsByProductResponse.newBuilder();
    // Populate
    List<Integer> ids = null;
    try
    {
      ids = source.getAnimalsByProduct(request.getProductId());
    }
    catch (SQLException e)
    {
      System.out.println("Error:"+e.getMessage());
      responseObserver.onError(e);
    }
    for (var id : ids) {
      responseBuilder.addAnimals(AnimalRef.newBuilder().setId(id).build());
    }
    // Build
    var responseText = responseBuilder.build();
    responseObserver.onNext(responseText);
    responseObserver.onCompleted();
  }

  @Override public void getProductsByAnimal(GetProductsByAnimalRequest request,
      StreamObserver<GetProductsByAnimalResponse> responseObserver)
  {
    System.out.println("Received request >>> " + request.toString());
    // Make builder
    var responseBuilder = GetProductsByAnimalResponse.newBuilder();
    // Populate
    List<Integer> ids = null;
    try
    {
      ids = source.getProductsByAnimal(request.getAnimalId());
    }
    catch (SQLException e)
    {
      System.out.println("Error:"+e.getMessage());
      responseObserver.onError(e);
    }
    for (var id : ids) {
      responseBuilder.addProducts(ProductRef.newBuilder().setId(id).build());
    }
    // Build
    var responseText = responseBuilder.build();
    responseObserver.onNext(responseText);
    responseObserver.onCompleted();
  }
}
