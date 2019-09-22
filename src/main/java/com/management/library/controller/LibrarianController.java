package com.management.library.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		log.info("Entering getAllBooks() method ");
		List<Book> books = librarianService.getAllBooks();
		log.info("Exiting getAllBooks() method ");
		return books;
	}

	@GetMapping("/books/{isbn}")
	public ResponseEntity<Book> getBookByISBN(@PathVariable String isbn) {
		log.info("Entering getBookByISBN() method ");
		Book book = librarianService.getBookByISBN(isbn);
		log.info("Exiting getBookByISBN() method ");
		return ResponseEntity.ok(book);

	}

	@PutMapping("/books/{isbn}")
	public ResponseEntity<Book> updateBook(@PathVariable String isbn, @Valid @RequestBody Book bookDetails) {
		log.info("Entering updateBook() method ");
		Book updatedBook = librarianService.updateBook(isbn, bookDetails);
		log.info("Exiting updateBook() method ");
		return ResponseEntity.ok(updatedBook);
	}

	@DeleteMapping("/books/{isbn}")
	public ResponseEntity<Map> deleteBook(@PathVariable String isbn) {
		log.info("Entering deleteBook() method ");
		Map<String, Boolean> result = librarianService.deleteBook(isbn);
		log.info("Exiting deleteBook() method ");
		return ResponseEntity.ok(result);

	}

}
