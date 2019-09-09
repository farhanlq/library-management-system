package com.management.library.controller;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
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
	private JacksonTester<List<Book>> jsonList;
	@Autowired
	private MockMvc mockMvc;
	private Book book;

	@Before
	public void setUp() throws Exception {
		JacksonTester.initFields(this, new ObjectMapper());
		book = new Book("EMP_6953_2019", "Hibernate", "Java", "IGH Pubications", "English", 1232);
	}

	@Test
	public void testGetAddBook() {
		Book checkBook = new Book("EMP_6953_2019", "Hibernate", "Java", "IGH Pubications", "English", 1232);
		when(librarianService.addBooks(Mockito.any(Book.class))).thenReturn(book);
		// MockHttpServletResponse response =
		// mockMvc.perform(post("/librarian/books").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(jsonList.write())).
	}

}
