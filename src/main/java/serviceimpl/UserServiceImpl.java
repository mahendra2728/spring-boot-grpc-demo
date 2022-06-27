package serviceimpl;

import client.OrderClient;
import com.pm.grpc.orders.Order;
import com.pm.grpc.users.UserRequest;
import com.pm.grpc.users.UserResponse;
import com.pm.grpc.users.UserServiceGrpc;
import dto.UserDtoResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUserDetails(UserRequest request, StreamObserver<UserResponse> responseObserver) throws InterruptedException {

        UserDtoResponse dto =new UserDtoResponse();
        dto.setUserId(request.getUserId());
        dto.setName("Mahendra");
        dto.setUsername("mahi2728");
        dto.setAge(27);

    // Add channel config here to call Order Service
    ManagedChannel channel =
        ManagedChannelBuilder.forTarget("localhost:50002").usePlaintext().build();

    OrderClient orderClient = new OrderClient(channel);
    List<Order> orders = orderClient.getUsersDetails(Integer.valueOf(dto.getUserId()));
    List<com.pm.grpc.users.Order> orders2 = getOrders(orders);
    channel.awaitTermination(3, TimeUnit.SECONDS);

    UserResponse userResponse =
        UserResponse.newBuilder()
            .setUserId(dto.getUserId())
            .setName(dto.getName())
            .setUsername(dto.getUsername())
            .setAge(dto.getAge())
            .addAllOrders(orders2)
            .build();

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
  }

  private List<com.pm.grpc.users.Order> getOrders(List<Order> orders) {
    List<com.pm.grpc.users.Order> orders2 =
        orders.stream()
            .map(
                orderDto -> {
                  return com.pm.grpc.users.Order.newBuilder()
                      .setOrderId(orderDto.getOrderId())
                      .setUserId(orderDto.getUserId())
                      .setProductName(orderDto.getProductName())
                      .setDescription(orderDto.getDescription())
                      .setTotalPrice(orderDto.getTotalPrice())
                      .build();
                })
            .collect(Collectors.toList());
    return orders2;
    }
}
