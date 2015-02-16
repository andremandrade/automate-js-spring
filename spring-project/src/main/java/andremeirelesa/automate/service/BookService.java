package andremeirelesa.automate.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import andremeirelesa.automate.domain.Book;

@Service
public class BookService {

	// Dummy repository
	private Map<Integer, Book> bookRepository = new HashMap<Integer, Book>();
	private int index = 0;

	public Collection<Book> getBooks() {
		return bookRepository.values();
	}

	public Book saveBook(Book newBook) {
		newBook.setId(nextIndex());
		bookRepository.put(newBook.getId(), newBook);
		return newBook;
	}

	public Book updateBook(Book book, int id) throws Exception {
		if (!bookRepository.containsKey(id))
			throw new Exception("Not Found");
		bookRepository.put(id, book);
		return book;
	}

	public void deleteBook(int id) throws Exception {
		if (!bookRepository.containsKey(id))
			throw new Exception("Not Found");
		bookRepository.remove(id);
	}

	private Integer nextIndex() {
		return ++index;
	}
}
