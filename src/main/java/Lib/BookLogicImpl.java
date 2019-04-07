package Lib;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookLogicImpl implements BookLogic {
	private BookDao bookDao;
	private PublisherDao publisherDao;

	public BookLogicImpl(BookDao bookDao, PublisherDao publisherDao) {
		this.bookDao = bookDao;
		this.publisherDao = publisherDao;
	}

	@Override
	@Transactional
	public Book CreateBook(Book book) throws UndifineBookException {
		if (!(book.getISBN().length() >= 10) && (book.getISBN().length() <= 13)) {
			throw new UndifineBookException("Illegal ISBN: " + (book.getISBN()));
		}
		if (!(book.getRating() >= 0 && book.getRating() <= 5)) {
			throw new UndifineBookException("Illegal rating: " + (book.getRating()));
		}
		this.publisherDao.save(book.getPublisher());
		return this.bookDao.save(book);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Book> getBookByISBN(String ISBN) {
		return this.bookDao.findById(ISBN);
	}

	@Override
	@Transactional
	public void removeAllBooks() {
		this.bookDao.deleteAll();
	}

}
