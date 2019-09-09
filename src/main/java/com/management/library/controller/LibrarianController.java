package com.management.library.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.library.domain.Book;
import com.management.library.service.LibrarianService;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

	private static final Logger log = LoggerFactory.getLogger(LibrarianController.class);

	@Autowired
	LibrarianService librarianService;

	@PostMapping("/books")
	public ResponseEntity<Book> addBooks(@RequestBody Book book) {
		log.info("Entering addBooks() method ");
		librarianService.addBooks(book);
		log.info("Exiting addBooks() method ");
		return ResponseEntity.ok(book);
	}

	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return librarianService.getAllBooks();
	}

	@GetMapping("/books/{isbn}")
	public ResponseEntity<Book> getBookByISBN(@PathVariable String isbn) {
		Book book = librarianService.getBookByISBN(isbn);
		return ResponseEntity.ok(book);

	}

}
