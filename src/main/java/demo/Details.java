package demo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Details {
	private String details;
	private String author;

	public Details() {
	}

	public Details(String details, String author) {
		super();
		this.details = details;
		this.author = author;
	}

	@Column(name="MESSAGE_DETAILS")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Details [details=" + details + ", author=" + author + "]";
	}

}
