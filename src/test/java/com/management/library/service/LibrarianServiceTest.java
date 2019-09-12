package com.management.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
		//assertThat(librarianRepository).isNotNull();

	}

}
