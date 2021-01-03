package com.ihason.learn.learnretrofit.server;

import lombok.Data;

@Data
public class ResourceDTO {

    private Long id;
    private String name;

    public ResourceDTO() {
    }

    public ResourceDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ResourceDTO of(Long id, String name) {
        return new ResourceDTO(id, name);
    }
}
