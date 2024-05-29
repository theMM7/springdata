package ru.siebel.spring.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.siebel.spring.springdata.entity.Author;
import ru.siebel.spring.springdata.entity.AuthorSummary;
import ru.siebel.spring.springdata.repository.AuthorRepository;

import java.util.List;

@Component
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void save(Author author) {
        authorRepository.save(author);
    }

    public List<AuthorSummary> getSummary(String name) {
        return authorRepository.findSummaryByFirstName(name);
    }

    public List<Author> findByFirstName(String firstName) {
        return authorRepository.findByFirstName(firstName);
    }

    @Transactional
    public void setNewAge(int newAge, String name) {
        authorRepository.setNewAgeByName(newAge, name);
    }
}
