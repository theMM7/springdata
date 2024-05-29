package ru.siebel.spring.springdata.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private BigDecimal price;
    private LocalDate publishDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Book(String title, BigDecimal price, LocalDate publishDate, Author author) {
        this.title = title;
        this.price = price;
        this.publishDate = publishDate;
        this.author = author;
    }
}
