package serviceimpl;

import com.pm.grpc.users.UserRequest;
import com.pm.grpc.users.UserResponse;
import com.pm.grpc.users.UserServiceGrpc;
import dto.UserDtoResponse;
import io.grpc.stub.StreamObserver;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getUserDetails(UserRequest request, StreamObserver<UserResponse> responseObserver) {

        UserDtoResponse dto =new UserDtoResponse();
        dto.setUserId(request.getUserId());
        dto.setName("Mahendra");
        dto.setUsername("mahi2728");
        dto.setAge(27);

        UserResponse userResponse = UserResponse.newBuilder()
                .setUserId(dto.getUserId())
                .setName(dto.getName())
                .setUsername(dto.getUsername())
                .setAge(dto.getAge())
                .build();

        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();

    }
}
