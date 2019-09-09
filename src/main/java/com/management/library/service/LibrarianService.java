package com.management.library.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.library.domain.Book;
import com.management.library.repository.LibrarianRepository;

@Service
public class LibrarianService {

	@Autowired
	LibrarianRepository librarianRepository;

	public Book addBooks(Book book) {
		return librarianRepository.save(book);
	}

	public List<Book> getAllBooks() {
		return librarianRepository.findAll();
	}

	public Book getBookByISBN(String isbn) {
		return librarianRepository.getOne(isbn);

	}

	public Book updateBook(String isbn, Book bookDetails) {
		Book book = librarianRepository.getOne(isbn);
		book.setTitle(bookDetails.getTitle());
		book.setSubject(bookDetails.getSubject());
		book.setPublisher(bookDetails.getPublisher());
		book.setLanguage(bookDetails.getLanguage());
		book.setNumberOfPages(bookDetails.getNumberOfPages());

		return librarianRepository.save(book);
	}

	public Map<String, Boolean> deleteBook(String isbn) {
		Book book = librarianRepository.getOne(isbn);
		librarianRepository.delete(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}

}
