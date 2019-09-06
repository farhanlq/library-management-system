package com.management.library.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@PostMapping
	public ResponseEntity<Book> addBooks(@RequestBody Book book) {
		log.info("Entering addBooks method ");
		librarianService.addBooks(book);
		log.info("Exiting addBooks method ");
		return ResponseEntity.ok(book);
		

	}
}
