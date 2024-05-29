package ru.siebel.spring.springdata.entity;

import lombok.Data;

@Data
public class AuthorSummary {
    private final String firstName;
    private final int age;
}
