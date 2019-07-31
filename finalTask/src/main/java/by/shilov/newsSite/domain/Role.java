package by.shilov.newsSite.domain;

public enum Role {
    ADMINISTRATOR("administrator"),
    WRITER("writer"),
    VISITOR("visitor");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Role getRoleByString(String name){
        for (Role role : Role.values()){
            if (name.equals(role.getName())){
                return role;
            }
        }
        return null;
    }

}
