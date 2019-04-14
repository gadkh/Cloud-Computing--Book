package Lib;

import java.util.List;
import java.util.Optional;

public interface BookLogic {
	public Book CreateBook(Book book) throws UndifineBookException;
	public Optional<Book> getBookByISBN(String ISBN);
	public List<Book> getAllBooks(int page, int size);
	public void removeAllBooks();
}
