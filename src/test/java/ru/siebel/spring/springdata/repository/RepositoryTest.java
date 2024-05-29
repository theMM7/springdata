package ru.siebel.spring.springdata.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.siebel.spring.springdata.entity.Author;
import ru.siebel.spring.springdata.entity.AuthorSummary;
import ru.siebel.spring.springdata.entity.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void testByContainers() {
        List<Author> authors = authorRepository.findByFirstName("Креативный");
        assertEquals(1, authors.size());
        assertEquals(25, authors.get(0).getAge());

        List<Book> bookA = bookRepository.findBookByAuthor_NameAndAuthor_Age("Креативный", 25);
        assertEquals(2, bookA.size());
    }

    @Test
    @Sql("/test.sql")
    void manualUpload() {
        List<AuthorSummary> firstName = authorRepository.findSummaryByFirstName("Новый автор");

        assertEquals(1, firstName.size());

        AuthorSummary authorSummary = firstName.get(0);

        assertEquals(27, authorSummary.getAge());
    }
}