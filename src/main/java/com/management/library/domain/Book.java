package com.management.library.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Book {

	@Id
	@GenericGenerator(name = "string_based_custom_sequence", strategy = "com.management.library.generator.ISBNGenerator")
	@GeneratedValue(generator = "string_based_custom_sequence")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + numberOfPages;
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (ISBN == null) {
			if (other.ISBN != null)
				return false;
		} else if (!ISBN.equals(other.ISBN))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (numberOfPages != other.numberOfPages)
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}
