package Lib;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class BookMicroService {
	private  final String DETAILED="detailed";
	private  final String ISBN="isbn";
	private  final String TITLE="title";
	private ArrayList<Book>bookArry;
	
	public BookMicroService() {
		this.bookArry=new ArrayList<>();
	}

	@RequestMapping(
			path="/books/echo",
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	public Book createBook (@RequestBody Book book){
		this.bookArry.add(book);
		return book;
	}
	
	
	@RequestMapping(
			path="/books/byIsbn/{isbn}",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Book getSpecificBook(@PathVariable("isbn") String isbn) throws UndifineBookException {
		boolean found=false;
		Book b = null;
		for(int i=0;i<this.bookArry.size() &&found==false;i++)
		{
			b=this.bookArry.get(i);
			if(isbn.equals(b.getISBN()))
			{
				found=true;
			}
		}
		if(found==true)
		{
			return b;
		}
		else
		{
			throw new UndifineBookException("Illigale ISBN");
		}
	}
	
	@RequestMapping(
			path="/books",
			method=RequestMethod.DELETE)
		public void delete ()  {
			this.bookArry.clear();
		}
	
	@RequestMapping(
			path="/books/all",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
		public Object[] getAllBooks(
				@RequestParam(name="content", required=false, defaultValue=DETAILED) String content) throws UndifineBookException {
			if(content.equals(DETAILED))
			{
				return (Book[]) bookArry.toArray(new Book[bookArry.size()]);
			}
			else if(content.equals(ISBN)) 
			{
				return bookArry
			    .stream()
			    .map(book->book.getISBN())
			    .collect(Collectors.toList())
				.toArray(new String[0]);
			}
			else if(content.equals(TITLE))
			{
				return bookArry
					    .stream()
					    .map(book->book.getTitle())
					    .collect(Collectors.toList())
						.toArray(new String[0]);
			}
			else
			{
				throw new UndifineBookException("Illigale Key");
			}
		}
	@ExceptionHandler
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public Book handleException (UndifineBookException e) {
		e.printStackTrace();
		return new Book();
		}

}