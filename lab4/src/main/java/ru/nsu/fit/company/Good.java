package ru.nsu.fit.company;

import java.util.UUID;

public class Good {
    private final String name;
    private final UUID id;

    public Good(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
