package com.management.library.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;

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

}
