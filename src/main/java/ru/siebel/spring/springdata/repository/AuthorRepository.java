package ru.siebel.spring.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.siebel.spring.springdata.entity.Author;
import ru.siebel.spring.springdata.entity.AuthorSummary;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT new ru.siebel.spring.springdata.entity.AuthorSummary(a.name, a.age) FROM Author a WHERE a.name = :firstName")
    List<AuthorSummary> findSummaryByFirstName(String firstName);

    @Query("FROM Author WHERE name = ?1")
    List<Author> findByFirstName(String firstName);

    @Modifying
    @Query("UPDATE Author SET age = :newAge where name = :name")
    void setNewAgeByName(@Param("newAge") int newAge, @Param("name") String name);
}
