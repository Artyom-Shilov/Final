package by.shilov.newsSite.domain;

import java.util.Objects;

public class Mark extends Entity {
    private String name;

    public Mark() {}

    public Mark(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mark mark = (Mark) o;
        return Objects.equals(name, mark.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Mark{" +
                "name='" + name + '\'' +
                '}';
    }
}
