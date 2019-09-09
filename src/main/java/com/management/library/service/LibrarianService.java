package com.management.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.library.domain.Book;
import com.management.library.repository.LibrarianRepository;

@Service
public class LibrarianService {

	@Autowired
	LibrarianRepository librarianRepository;

	public void addBooks(Book book) {
		librarianRepository.save(book);
	}

	public List<Book> getAllBooks() {
		return librarianRepository.findAll();
	}

	public Book getBookByISBN(String isbn) {
		return librarianRepository.getOne(isbn);

	}

}
