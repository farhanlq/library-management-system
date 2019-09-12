package com.management.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.management.library.domain.Book;
import com.management.library.repository.LibrarianRepository;

public class LibrarianServiceTest {

	@InjectMocks
	LibrarianService librarianService;

	@Mock
	LibrarianRepository librarianRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddBook() {
		Book addBook = new Book("EMP_6953_2019", "Hibernate", "Java", "IGH Pubications", "English", 1232);

		when(librarianRepository.save(addBook)).thenReturn(addBook);
		assertThat(librarianService.addBooks(addBook)).isEqualTo(addBook);
	}

	@Test
	public void testGetAllBooks() {
		Book addBook = new Book("EMP_6953_2019", "Hibernate", "Java", "IGH Pubications", "English", 1232);
		List<Book> bookList = new ArrayList<>();
		bookList.add(addBook);
		bookList.add(addBook);
		when(librarianRepository.findAll()).thenReturn(bookList);
		assertThat(librarianService.getAllBooks()).isEqualTo(bookList);
	}

	@Test
	public void testGetBookByISBN() {
		Book book = new Book("EMP_6953_2019", "Hibernate", "Java", "IGH Pubications", "English", 1232);
		String isbn = book.getISBN();
		when(librarianRepository.getOne(isbn)).thenReturn(book);
		assertThat(librarianService.getBookByISBN(isbn)).isEqualTo(book);

	}

	@Test
	public void testUpdateBook() {
		Book book = new Book();
		book.setISBN("EMP_6953_2019");
		book.setTitle("Hibernate");
		book.setSubject("Java");
		book.setPublisher("IGH Pubications");
		book.setLanguage("English");
		book.setNumberOfPages(1334);

		String isbn = book.getISBN();
		when(librarianRepository.getOne(isbn)).thenReturn(book);

		book.setPublisher("Pearson Publications");

		when(librarianRepository.save(book)).thenReturn(book);

		assertThat(librarianService.updateBook(isbn, book)).isEqualTo(book);

	}
	
	@Test
	public void testDeleteBook() {
		
	}
}
