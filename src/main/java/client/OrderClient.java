package client;

import com.pm.grpc.orders.OrderServiceGrpc;

import java.nio.channels.Channel;

public class OrderClient  {

    private OrderServiceGrpc.OrderServiceBlockingStub orderServiceBlockingStub;

    public OrderClient(Channel channel){

    }
}
