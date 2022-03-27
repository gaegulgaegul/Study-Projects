package com.gaegul;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    @Setter
    private String name;
    @Setter
    private int age;
}
