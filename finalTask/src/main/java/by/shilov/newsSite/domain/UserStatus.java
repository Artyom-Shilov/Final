package by.shilov.newsSite.domain;

public enum UserStatus {
    UNBLOCKED("unblocked"),
    BLOCKED("blocked");

    private String name;

    UserStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static UserStatus getStatusByString(String userStatus){
        for (UserStatus status : UserStatus.values()){
            if (userStatus.equals(status.getName())){
                return status;
            }
        }
        return null;
    }
}
