package serviceimpl;

import com.pm.grpc.orders.Order;
import com.pm.grpc.orders.OrderRequest;
import com.pm.grpc.orders.OrderResponse;
import com.pm.grpc.orders.OrderServiceGrpc;
import dto.OrderDto;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {


    @Override
    public void getOrdersByUserId(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
        System.out.println("userId received : "+request.getUserId());
        OrderDto order =new OrderDto();
        order.setUserId(request.getUserId());
        List<Order> orders = getOrderBasedOnUserId(order);

        OrderResponse response = OrderResponse.newBuilder()
                .addAllOrders(orders)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    private List<Order> getOrderBasedOnUserId(OrderDto order) {
        List<OrderDto> orders = Arrays.asList(
                new OrderDto(12,07,"mobiles","Electronis",12000),
                new OrderDto(14,03,"laptop","Electronis",45000),
                new OrderDto(134,02,"tables","Home Decorator",10000),
                new OrderDto(152,03,"Cloths","Life Style",3000),
                new OrderDto(53,07,"TV Unit","Electronis",15000)
                );
    return orders.stream()
        .filter(userDto -> userDto.getUserId() == order.getUserId())
        .map(
            dto -> {
              return Order.newBuilder()
                  .setOrderId(dto.getOrderId())
                  .setUserId(dto.getUserId())
                  .setProductName(dto.getProductName())
                  .setDescription(dto.getDescription())
                  .setTotalPrice(dto.getTotalPrice())
                  .build();
            })
        .collect(Collectors.toList());
    }
}
