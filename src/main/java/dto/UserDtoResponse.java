package dto;

public class UserDtoResponse {

    private String userId;
    private String username;
    private String name;
    private int age;

    public UserDtoResponse(){}

    public UserDtoResponse(String userId, String username, String name, int age) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
