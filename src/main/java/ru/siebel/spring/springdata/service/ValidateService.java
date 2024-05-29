package ru.siebel.spring.springdata.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ValidateService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void validate(String value) {
        if (value == null || value.length() < 2) {
            throw new IllegalStateException("Некорректное значение");
        }
    }
}
