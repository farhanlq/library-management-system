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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Library Management System", description = "Operations pertaining to addin books and Users in Library Management System")
@RestController
@RequestMapping("/librarian")
public class LibrarianController {

	private static final Logger log = LoggerFactory.getLogger(LibrarianController.class);

	@Autowired
	LibrarianService librarianService;

	@ApiOperation(value = "Add a Book")
	@PostMapping("/books")
	public ResponseEntity<Book> addBooks(
			@ApiParam(value = "Book object store in database table", required = true) @Valid @RequestBody Book book) {
		log.info("Entering addBooks() method ");
		librarianService.addBooks(book);
		log.info("Exiting addBooks() method ");
		return ResponseEntity.ok(book);
	}

	@ApiOperation(value = "View a list of all books")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("/books")
	public List<Book> getAllBooks() {
		log.info("Entering getAllBooks() method ");
		List<Book> books = librarianService.getAllBooks();
		log.info("Exiting getAllBooks() method ");
		return books;
	}

	@ApiOperation(value = "Get a book by ISBN")
	@GetMapping("/books/{isbn}")
	public ResponseEntity<Book> getBookByISBN(
			@ApiParam(value = "Book ISBN for which book object will be retrieve", required = true) @PathVariable String isbn) {
		log.info("Entering getBookByISBN() method ");
		Book book = librarianService.getBookByISBN(isbn);
		log.info("Exiting getBookByISBN() method ");
		return ResponseEntity.ok(book);

	}

	@ApiOperation(value = "Update a book")
	@PutMapping("/books/{isbn}")
	public ResponseEntity<Book> updateBook(
			@ApiParam(value = "Book ISBN to update book object", required = true) @PathVariable String isbn,
			@ApiParam(value = "Updated book object") @Valid @RequestBody Book bookDetails) {
		log.info("Entering updateBook() method ");
		Book updatedBook = librarianService.updateBook(isbn, bookDetails);
		log.info("Exiting updateBook() method ");
		return ResponseEntity.ok(updatedBook);
	}

	@ApiOperation(value = "Delete a book")
	@DeleteMapping("/books/{isbn}")
	public ResponseEntity<Map> deleteBook(
			@ApiParam(value = "Book ISBN from which book object will delete from database table", required = true) @PathVariable String isbn) {
		log.info("Entering deleteBook() method ");
		Map<String, Boolean> result = librarianService.deleteBook(isbn);
		log.info("Exiting deleteBook() method ");
		return ResponseEntity.ok(result);

	}

}
