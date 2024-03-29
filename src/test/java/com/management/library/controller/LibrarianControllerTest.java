package com.management.library.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.library.domain.Book;
import com.management.library.service.LibrarianService;

@RunWith(SpringRunner.class)
@WebMvcTest(LibrarianController.class)
public class LibrarianControllerTest {

	@MockBean
	private LibrarianService librarianService;

	private JacksonTester<Book> jsonList;

	private JacksonTester<Map> jsonMap;

	private JacksonTester<List<Book>> jsonResultBookList;

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void testGetAddBook() throws IOException, Exception {
		Book checkBook = new Book("EMP_6953_2019", "Hibernate", "Java", "IGH Pubications", "English", 1232);

		when(librarianService.addBooks(Mockito.any(Book.class))).thenReturn(checkBook);

		MockHttpServletResponse response = mockMvc.perform(post("/librarian/books")
				.contentType(MediaType.APPLICATION_JSON).content(jsonList.write(checkBook).getJson())).andReturn()
				.getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		assertThat(response.getContentAsString())
				.isEqualTo(jsonList
						.write(new Book(checkBook.getISBN(), checkBook.getTitle(), checkBook.getSubject(),
								checkBook.getPublisher(), checkBook.getLanguage(), checkBook.getNumberOfPages()))
						.getJson());
	}

	@Test
	public void testGetAllBooks() throws Exception {
		Book book1 = new Book("EMP_6953_2019", "Hibernate", "Java", "IGH Pubications", "English", 1232);
		Book book2 = new Book("EMP_6153_2019", "JPA", "Java", "Pearson Pubications", "English", 532);
		List<Book> bookList = new ArrayList<>();
		bookList.add(book1);
		bookList.add(book2);

		when(librarianService.getAllBooks()).thenReturn(bookList);

		MockHttpServletResponse response = mockMvc.perform(get("/librarian/books")).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		assertThat(response.getContentAsString()).isEqualTo(jsonResultBookList.write(bookList).getJson());
	}

	@Test
	public void testGetBookByISBN() throws Exception {
		Book book1 = new Book("EMP_6953_2019", "Hibernate", "Java", "IGH Pubications", "English", 1232);
		String isbn = book1.getISBN();
		when(librarianService.getBookByISBN(isbn)).thenReturn(book1);
		MockHttpServletResponse response = mockMvc.perform(get("/librarian/books/{isbn}", "EMP_6953_2019")).andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		assertThat(response.getContentAsString()).isEqualTo(jsonList.write(book1).getJson());
	}

	@Test
	public void testUpdateBook() throws Exception {

		Book book1 = new Book("EMP_6953_2019", "Hibernate", "Java", "IGH Pubications", "English", 1232);
		String isbn = book1.getISBN();
		Book bookDetails = new Book("EMP_6953_2019", "Hibernate with JPA", "Java", "IGH Pubications", "English", 1532);
		when(librarianService.updateBook(isbn, bookDetails)).thenReturn(bookDetails);
		MockHttpServletResponse response = mockMvc.perform(put("/librarian/books/{isbn}", "EMP_6953_2019")
				.contentType(MediaType.APPLICATION_JSON).content(jsonList.write(bookDetails).getJson())).andReturn()
				.getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonList
				.write(new Book(bookDetails.getISBN(), bookDetails.getTitle(), bookDetails.getSubject(),
						bookDetails.getPublisher(), bookDetails.getLanguage(), bookDetails.getNumberOfPages()))
				.getJson());
	}

	@Test
	public void testDeleteBook() throws IOException, Exception {
		Book book1 = new Book("EMP_6953_2019", "Hibernate", "Java", "IGH Pubications", "English", 1232);
		String isbn = book1.getISBN();
		Map<String, Boolean> value = new HashMap<>();
		value.put("Deleted", Boolean.TRUE);
		when(librarianService.deleteBook(isbn)).thenReturn(value);
		MockHttpServletResponse response = mockMvc.perform(delete("/librarian/books/{isbn}", isbn)
				.contentType(MediaType.APPLICATION_JSON).content(jsonList.write(book1).getJson())).andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

		assertThat(response.getContentAsString()).isEqualTo(jsonMap.write(value).getJson());
	}
}
