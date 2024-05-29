package ru.siebel.spring.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import ru.siebel.spring.springdata.entity.Author;
import ru.siebel.spring.springdata.entity.AuthorSummary;
import ru.siebel.spring.springdata.entity.Book;
import ru.siebel.spring.springdata.repository.BookRepository;
import ru.siebel.spring.springdata.service.AuthorService;
import ru.siebel.spring.springdata.service.BookService;
import ru.siebel.spring.springdata.service.TransactionalService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class SpringdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringdataApplication.class, args);
    }

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TransactionalService transactionalService;
    @Autowired
    private BookService bookService;

    @Bean
    @Profile("!test")
    public CommandLineRunner demoCommandLineRunner() {
        return args -> {

            System.out.println("Running.....");

            List<Book> books = bookService.findBookByAuthorNameAndAuthorAge("Креативный", 25);
            System.out.println("Результат работы метода findBookByAuthorNameAndAuthorAge() " + books);

            List<AuthorSummary> summary = authorService.getSummary("Креативный");
            System.out.println("Результат getSummary() " + summary);

            authorService.setNewAge(42, "Позитивный");

            List<Author> name = authorService.findByFirstName("Позитивный");
            System.out.println("Резульат findByFirstName() " + name);

            transactionalService.transactionMethod();
        };
    }
}
