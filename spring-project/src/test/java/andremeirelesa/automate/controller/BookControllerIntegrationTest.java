package andremeirelesa.automate.controller;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import andremeirelesa.automate.WebApp;
import andremeirelesa.automate.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApp.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookControllerIntegrationTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/rest/v0/books");
		template = new TestRestTemplate();
	}

	@Test
	public void test1getEmptyBooks() throws Exception {
		ResponseEntity<List> responseGet = template.getForEntity(
				this.base.toURI(), List.class);
		assertEquals(HttpStatus.OK, responseGet.getStatusCode());
		assertEquals(responseGet.getBody().size(), 0);
	}

	@Test
	public void test2saveBook() throws Exception {
		// add book
		Book book = new Book();
		book.setTitle("Engracadinha");
		book.setYear(1965);
		ResponseEntity<Book> responseSave = template.postForEntity(
				this.base.toURI(), book, Book.class);
		assertEquals(HttpStatus.CREATED, responseSave.getStatusCode());
		assertEquals(responseSave.getBody().getId(), 1);

		// verify book added
		ResponseEntity<List> responseGet = template.getForEntity(
				this.base.toURI(), List.class);
		assertEquals(HttpStatus.OK, responseGet.getStatusCode());
		assertEquals(responseGet.getBody().size(), 1);
	}

	@Test
	public void test3updateBook() throws Exception {
		// update book
		Book book = new Book();
		book.setId(1);
		book.setTitle("Bonitinha mas ordinaria");
		book.setYear(1968);
		URL updateURL = new URL(this.base.toString() + "/" + 1);
		template.put(updateURL.toURI(), book);

		// verify book added
		ResponseEntity<List> responseGet = template.getForEntity(
				this.base.toURI(), List.class);
		assertEquals(HttpStatus.OK, responseGet.getStatusCode());
		assertEquals(responseGet.getBody().size(), 1);
		LinkedHashMap<String, String> bookFromGet = ((LinkedHashMap<String, String>) responseGet
				.getBody().get(0));

		assertEquals(book.getId(), bookFromGet.get("id"));
		assertEquals(book.getTitle(), bookFromGet.get("title"));
		assertEquals(book.getYear(), bookFromGet.get("year"));
	}

}
