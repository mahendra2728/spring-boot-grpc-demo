package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import serviceimpl.UserServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class UserServer {

  private static final Logger logger = Logger.getLogger(UserServer.class.getName());
  private static Server server;

  public static void main(String[] args) throws InterruptedException, IOException {
      startServer();
      waitTermination();
  }

  public static void startServer() throws IOException {
      int port=50001;
      server = ServerBuilder.forPort(port)
              .addService(new UserServiceImpl())
              .build()
              .start();
      logger.info("server started on port 50001");
   }

   public static void stopServer() throws InterruptedException {
      if (server!=null){
          server.awaitTermination(30, TimeUnit.SECONDS);
      }
   }
   public static void waitTermination() throws InterruptedException {
       if (server!=null){
           server.awaitTermination();
       }
   }
}
