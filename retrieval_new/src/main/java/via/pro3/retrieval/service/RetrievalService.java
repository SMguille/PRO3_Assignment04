package via.pro3.retrieval.service;

import io.grpc.stub.StreamObserver;
import via.pro3.retrieval.generated.*;

public class RetrievalService extends TraceServiceGrpc.TraceServiceImplBase
{
  private int[] dummy = new int[]{1,2,3};
  @Override public void getAnimalsByProduct(GetAnimalsByProductRequest request,
      StreamObserver<GetAnimalsByProductResponse> responseObserver)
  {
    System.out.println("Received request >>> " + request.toString());
    // Make builder
    var responseBuilder = GetAnimalsByProductResponse.newBuilder();
    // Populate
    for(var i : dummy)
      responseBuilder.addAnimals(AnimalRef.newBuilder().setId(i).build());
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
    for(var i : dummy)
      responseBuilder.addProducts(ProductRef.newBuilder().setId(i).build());
    // Build
    var responseText = responseBuilder.build();
    responseObserver.onNext(responseText);
    responseObserver.onCompleted();
  }
}
