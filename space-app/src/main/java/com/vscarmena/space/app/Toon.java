package com.vscarmena.space.app;

import java.util.Objects;

public class Toon {

    private Long id;
    private String name;

    protected Toon() {}

    public Toon(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toon toon = (Toon) o;
        return id.equals(toon.id) && name.equals(toon.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Toon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
