package via.pro3.retrieval;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import via.pro3.retrieval.service.DataSource;
import via.pro3.retrieval.service.DatabaseSource;
import via.pro3.retrieval.service.RetrievalService;

public class ServerMain
{
  public static void main(String[] args)
  {
    try
    {
      // Make server
      var source = new DatabaseSource();
      var retrievalService = new RetrievalService(source);
      Server server = ServerBuilder
          .forPort(8089)
          .addService(retrievalService)
          .build();
      // Start server
      server.start();
      System.out.println("Server started, listening on " + server.getPort());
      server.awaitTermination();
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }
}
