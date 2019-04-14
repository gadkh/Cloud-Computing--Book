package Lib;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Books")
public class Book {
	private String ISBN;
	private String title;
	private String[] authors;
	private Publisher publisher;
	private int publishedYear;
	private int rating;

	public Book() {
		super();
	}

	public Book(String iSBN, String title, String[] authors, Publisher publisher, int publishedYear, int rating) {
		super();
		ISBN = iSBN;
		this.title = title;
		this.authors = authors;
		this.publisher = publisher;
		this.publishedYear = publishedYear;
		this.rating = rating;
	}

	@Id
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

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public int getPublishedYear() {
		return publishedYear;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
