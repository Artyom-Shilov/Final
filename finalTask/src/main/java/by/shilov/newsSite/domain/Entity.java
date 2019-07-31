package by.shilov.newsSite.domain;

public abstract class Entity {
    private Integer id;

    public Entity(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
