package com.management.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.management.library.domain.Book;

@Repository
public interface LibrarianRepository extends JpaRepository<Book, String> {

}
