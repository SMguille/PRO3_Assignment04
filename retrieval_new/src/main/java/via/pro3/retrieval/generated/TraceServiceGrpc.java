package via.pro3.retrieval.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class TraceServiceGrpc {

  private TraceServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "TraceService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<via.pro3.retrieval.generated.GetAnimalsByProductRequest,
      via.pro3.retrieval.generated.GetAnimalsByProductResponse> getGetAnimalsByProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAnimalsByProduct",
      requestType = via.pro3.retrieval.generated.GetAnimalsByProductRequest.class,
      responseType = via.pro3.retrieval.generated.GetAnimalsByProductResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.pro3.retrieval.generated.GetAnimalsByProductRequest,
      via.pro3.retrieval.generated.GetAnimalsByProductResponse> getGetAnimalsByProductMethod() {
    io.grpc.MethodDescriptor<via.pro3.retrieval.generated.GetAnimalsByProductRequest, via.pro3.retrieval.generated.GetAnimalsByProductResponse> getGetAnimalsByProductMethod;
    if ((getGetAnimalsByProductMethod = TraceServiceGrpc.getGetAnimalsByProductMethod) == null) {
      synchronized (TraceServiceGrpc.class) {
        if ((getGetAnimalsByProductMethod = TraceServiceGrpc.getGetAnimalsByProductMethod) == null) {
          TraceServiceGrpc.getGetAnimalsByProductMethod = getGetAnimalsByProductMethod =
              io.grpc.MethodDescriptor.<via.pro3.retrieval.generated.GetAnimalsByProductRequest, via.pro3.retrieval.generated.GetAnimalsByProductResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAnimalsByProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.retrieval.generated.GetAnimalsByProductRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.retrieval.generated.GetAnimalsByProductResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TraceServiceMethodDescriptorSupplier("GetAnimalsByProduct"))
              .build();
        }
      }
    }
    return getGetAnimalsByProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<via.pro3.retrieval.generated.GetProductsByAnimalRequest,
      via.pro3.retrieval.generated.GetProductsByAnimalResponse> getGetProductsByAnimalMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetProductsByAnimal",
      requestType = via.pro3.retrieval.generated.GetProductsByAnimalRequest.class,
      responseType = via.pro3.retrieval.generated.GetProductsByAnimalResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.pro3.retrieval.generated.GetProductsByAnimalRequest,
      via.pro3.retrieval.generated.GetProductsByAnimalResponse> getGetProductsByAnimalMethod() {
    io.grpc.MethodDescriptor<via.pro3.retrieval.generated.GetProductsByAnimalRequest, via.pro3.retrieval.generated.GetProductsByAnimalResponse> getGetProductsByAnimalMethod;
    if ((getGetProductsByAnimalMethod = TraceServiceGrpc.getGetProductsByAnimalMethod) == null) {
      synchronized (TraceServiceGrpc.class) {
        if ((getGetProductsByAnimalMethod = TraceServiceGrpc.getGetProductsByAnimalMethod) == null) {
          TraceServiceGrpc.getGetProductsByAnimalMethod = getGetProductsByAnimalMethod =
              io.grpc.MethodDescriptor.<via.pro3.retrieval.generated.GetProductsByAnimalRequest, via.pro3.retrieval.generated.GetProductsByAnimalResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetProductsByAnimal"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.retrieval.generated.GetProductsByAnimalRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.retrieval.generated.GetProductsByAnimalResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TraceServiceMethodDescriptorSupplier("GetProductsByAnimal"))
              .build();
        }
      }
    }
    return getGetProductsByAnimalMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TraceServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TraceServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TraceServiceStub>() {
        @java.lang.Override
        public TraceServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TraceServiceStub(channel, callOptions);
        }
      };
    return TraceServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static TraceServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TraceServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TraceServiceBlockingV2Stub>() {
        @java.lang.Override
        public TraceServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TraceServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return TraceServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TraceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TraceServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TraceServiceBlockingStub>() {
        @java.lang.Override
        public TraceServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TraceServiceBlockingStub(channel, callOptions);
        }
      };
    return TraceServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TraceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TraceServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TraceServiceFutureStub>() {
        @java.lang.Override
        public TraceServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TraceServiceFutureStub(channel, callOptions);
        }
      };
    return TraceServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getAnimalsByProduct(via.pro3.retrieval.generated.GetAnimalsByProductRequest request,
        io.grpc.stub.StreamObserver<via.pro3.retrieval.generated.GetAnimalsByProductResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAnimalsByProductMethod(), responseObserver);
    }

    /**
     */
    default void getProductsByAnimal(via.pro3.retrieval.generated.GetProductsByAnimalRequest request,
        io.grpc.stub.StreamObserver<via.pro3.retrieval.generated.GetProductsByAnimalResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetProductsByAnimalMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TraceService.
   */
  public static abstract class TraceServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TraceServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TraceService.
   */
  public static final class TraceServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TraceServiceStub> {
    private TraceServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TraceServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TraceServiceStub(channel, callOptions);
    }

    /**
     */
    public void getAnimalsByProduct(via.pro3.retrieval.generated.GetAnimalsByProductRequest request,
        io.grpc.stub.StreamObserver<via.pro3.retrieval.generated.GetAnimalsByProductResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAnimalsByProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProductsByAnimal(via.pro3.retrieval.generated.GetProductsByAnimalRequest request,
        io.grpc.stub.StreamObserver<via.pro3.retrieval.generated.GetProductsByAnimalResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetProductsByAnimalMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TraceService.
   */
  public static final class TraceServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<TraceServiceBlockingV2Stub> {
    private TraceServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TraceServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TraceServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public via.pro3.retrieval.generated.GetAnimalsByProductResponse getAnimalsByProduct(via.pro3.retrieval.generated.GetAnimalsByProductRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetAnimalsByProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.pro3.retrieval.generated.GetProductsByAnimalResponse getProductsByAnimal(via.pro3.retrieval.generated.GetProductsByAnimalRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetProductsByAnimalMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service TraceService.
   */
  public static final class TraceServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TraceServiceBlockingStub> {
    private TraceServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TraceServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TraceServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public via.pro3.retrieval.generated.GetAnimalsByProductResponse getAnimalsByProduct(via.pro3.retrieval.generated.GetAnimalsByProductRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAnimalsByProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.pro3.retrieval.generated.GetProductsByAnimalResponse getProductsByAnimal(via.pro3.retrieval.generated.GetProductsByAnimalRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetProductsByAnimalMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TraceService.
   */
  public static final class TraceServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TraceServiceFutureStub> {
    private TraceServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TraceServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TraceServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.pro3.retrieval.generated.GetAnimalsByProductResponse> getAnimalsByProduct(
        via.pro3.retrieval.generated.GetAnimalsByProductRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAnimalsByProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.pro3.retrieval.generated.GetProductsByAnimalResponse> getProductsByAnimal(
        via.pro3.retrieval.generated.GetProductsByAnimalRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetProductsByAnimalMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ANIMALS_BY_PRODUCT = 0;
  private static final int METHODID_GET_PRODUCTS_BY_ANIMAL = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ANIMALS_BY_PRODUCT:
          serviceImpl.getAnimalsByProduct((via.pro3.retrieval.generated.GetAnimalsByProductRequest) request,
              (io.grpc.stub.StreamObserver<via.pro3.retrieval.generated.GetAnimalsByProductResponse>) responseObserver);
          break;
        case METHODID_GET_PRODUCTS_BY_ANIMAL:
          serviceImpl.getProductsByAnimal((via.pro3.retrieval.generated.GetProductsByAnimalRequest) request,
              (io.grpc.stub.StreamObserver<via.pro3.retrieval.generated.GetProductsByAnimalResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetAnimalsByProductMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              via.pro3.retrieval.generated.GetAnimalsByProductRequest,
              via.pro3.retrieval.generated.GetAnimalsByProductResponse>(
                service, METHODID_GET_ANIMALS_BY_PRODUCT)))
        .addMethod(
          getGetProductsByAnimalMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              via.pro3.retrieval.generated.GetProductsByAnimalRequest,
              via.pro3.retrieval.generated.GetProductsByAnimalResponse>(
                service, METHODID_GET_PRODUCTS_BY_ANIMAL)))
        .build();
  }

  private static abstract class TraceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TraceServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return via.pro3.retrieval.generated.Trace.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TraceService");
    }
  }

  private static final class TraceServiceFileDescriptorSupplier
      extends TraceServiceBaseDescriptorSupplier {
    TraceServiceFileDescriptorSupplier() {}
  }

  private static final class TraceServiceMethodDescriptorSupplier
      extends TraceServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    TraceServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TraceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TraceServiceFileDescriptorSupplier())
              .addMethod(getGetAnimalsByProductMethod())
              .addMethod(getGetProductsByAnimalMethod())
              .build();
        }
      }
    }
    return result;
  }
}
