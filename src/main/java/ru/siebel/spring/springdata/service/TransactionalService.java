package ru.siebel.spring.springdata.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.siebel.spring.springdata.entity.Author;
import ru.siebel.spring.springdata.entity.AuthorSummary;
import ru.siebel.spring.springdata.entity.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionalService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final ValidateService validateService;

    public void wrapperTransactionMethod() {
        transactionMethod(); //не будет работать транзакция
    }

    @Transactional
    public void transactionMethod() {
        Author author = new Author();
        author.setName("Новый автор");
        author.setAge(50);
        authorService.save(author);

        BigDecimal value;
        try {
            validateService.validate("1");
            value = BigDecimal.valueOf(100);
        } catch (IllegalStateException e) {
            System.out.println("Произошла не критичная ошибка " + e);
            value = BigDecimal.ONE;
        }

        bookService.save(new Book("Новая", value, LocalDate.now(), author));

        List<AuthorSummary> summaries = authorService.getSummary("Новый автор");

        System.out.println("Результат getSummary() в транзакции " + summaries);
    }

    @Transactional
    private void privateTransactionalMethod() {
        //не будет работать транзакция
    }
}
