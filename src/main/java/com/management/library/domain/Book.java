package com.management.library.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
    )
	@Column(name = "ISBN")
	private String ISBN;
	@Column(name = "title")
	private String title;
	@Column(name = "subject")
	private String subject;
	@Column(name = "publisher")
	private String publisher;
	@Column(name = "language")
	private String language;
	@Column(name = "numberOfPages")
	private int numberOfPages;

	public Book() {
	}

	public Book(String iSBN, String title, String subject, String publisher, String language, int numberOfPages) {
		ISBN = iSBN;
		this.title = title;
		this.subject = subject;
		this.publisher = publisher;
		this.language = language;
		this.numberOfPages = numberOfPages;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", title=" + title + ", subject=" + subject + ", publisher=" + publisher
				+ ", language=" + language + ", numberOfPages=" + numberOfPages + "]";
	}

}
