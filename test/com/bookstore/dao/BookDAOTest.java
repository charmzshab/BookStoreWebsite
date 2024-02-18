package com.bookstore.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;

class BookDAOTest extends BaseDAOTest {

	private static BookDAO bookDAO;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		BaseDAOTest.setUpBeforeClass();
		bookDAO = new BookDAO(entityManager);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// Close EntityManager
		BaseDAOTest.tearDownAfterClass();
	}

	@Test
	public void testCreate() throws ParseException, IOException {
		Book existBook = new Book();

		Category newCat = new Category("Java Core");
		newCat.setCategoryId(21);
		existBook.setCategory(newCat);

		existBook.setTitle("Effective Java (3rd Edition)");
		existBook.setAuthor("Joshua Bloch");
		existBook.setDescription(
				"Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable?");
		existBook.setIsbn("0321356683");
		existBook.setPrice(38.87f);

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");

		existBook.setPublishDate(publishDate);

		String imagePath = "C:\\Users\\Hp\\Documents\\my_Servlet&Jsp_training\\E-Commerce BookStore Website\\Domain\\book\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

		existBook.setImage(imageBytes);

		Book resultBook = bookDAO.create(existBook);

//  	Assertions.assertThrows(PersistenceException.class, () -> {
//  		bookDAO.create(newBook);
//	});

		assertTrue(resultBook.getBookId() > 0);

	}

	@Test
	public void testUpdate() throws ParseException, IOException {
		Book newBook = new Book();
		newBook.setBookId(32);

		Category newCat = new Category("Data Science");
		newCat.setCategoryId(20);
		newBook.setCategory(newCat);

		newBook.setTitle("Effective Java (3rd Edition)");
		newBook.setAuthor("Joshua Bloch");
		newBook.setDescription(
				"Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable?");
		newBook.setIsbn("0321356683");
		newBook.setPrice(40.87f);

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date publishDate = dateFormat.parse("05/28/2008");

		newBook.setPublishDate(publishDate);

		String imagePath = "C:\\Users\\Hp\\Documents\\my_Servlet&Jsp_training\\E-Commerce BookStore Website\\Domain\\book\\books\\Effective Java.JPG";
		byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));

		newBook.setImage(imageBytes);

		Book resultBook = bookDAO.update(newBook);

		assertEquals(resultBook.getTitle(), "Effective Java (3rd Edition)");

	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

}
