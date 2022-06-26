package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import serviceimpl.OrderServiceImpl;
import serviceimpl.UserServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class OrderServer {

  private static final Logger logger = Logger.getLogger(OrderServer.class.getName());
  private static Server server;

  public static void main(String[] args) throws InterruptedException, IOException {
      startServer();
      blockUntilTermination();
  }

  public static void startServer() throws IOException {
      int port=50002;
      server = ServerBuilder.forPort(port)
              .addService(new OrderServiceImpl())
              .build()
              .start();
      logger.info("Order Server started on port +"+port);

      Runtime.getRuntime().addShutdownHook(new Thread(){
          @Override
          public void run() {
              try {
                  stopServer();
              } catch (InterruptedException e) {
                  logger.info("Order server stopped interrupted");
              }
          }
      });
   }

   public static void stopServer() throws InterruptedException {
      if (server!=null){
          server.awaitTermination(30, TimeUnit.SECONDS);
      }
   }
   public static void blockUntilTermination() throws InterruptedException {
       if (server!=null){
           server.awaitTermination();
       }
   }
}
