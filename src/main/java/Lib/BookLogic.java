package Lib;

import java.util.Optional;

public interface BookLogic {
	public Book CreateBook(Book book);
	public Optional<Book> getBookByISBN(String ISBN);
	public void removeAllBooks();
}
