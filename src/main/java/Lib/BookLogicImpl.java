package Lib;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort.Direction;


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

	@Override
	@Transactional(readOnly=true)
	public List<Book> getAllBooks(int page, int size) {
		return
		this.bookDao
		.findAll(
			PageRequest.of(
					page, 
					size, 
					Direction.ASC, 
					"ISBN"))
		.getContent();
	}

}
