package client;

import com.pm.grpc.orders.Order;
import com.pm.grpc.orders.OrderRequest;
import com.pm.grpc.orders.OrderResponse;
import com.pm.grpc.orders.OrderServiceGrpc;
import io.grpc.Channel;

import java.util.List;

public class OrderClient  {

    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;

    public OrderClient(Channel channel){
    orderServiceBlockingStub = OrderServiceGrpc.newBlockingStub(channel);
  }

  public List<Order> getUsersDetails(int userId) {
    OrderRequest orderRequest = OrderRequest.newBuilder().setUserId(userId).build();

    OrderResponse orderResponse = orderServiceBlockingStub.getOrdersByUserId(orderRequest);
    return orderResponse.getOrdersList();
    }
}
