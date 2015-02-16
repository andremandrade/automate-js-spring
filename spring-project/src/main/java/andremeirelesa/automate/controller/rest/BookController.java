package andremeirelesa.automate.controller.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import andremeirelesa.automate.domain.Book;
import andremeirelesa.automate.service.BookService;

@Controller
@RequestMapping("/rest/v0/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Book>> getBooks() {
		return new ResponseEntity<Collection<Book>>(bookService.getBooks(),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Book> saveBook(@RequestBody Book newBook) {
		return new ResponseEntity<Book>(bookService.saveBook(newBook),
				HttpStatus.CREATED);
	}

	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@PathVariable(value="id") int id, @RequestBody Book book) {
		try {
			return new ResponseEntity<Book>(bookService.updateBook(book, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteBook(@PathVariable(value="id") int id) {
		try {
			bookService.deleteBook(id);
			return new ResponseEntity<Book>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}

}
