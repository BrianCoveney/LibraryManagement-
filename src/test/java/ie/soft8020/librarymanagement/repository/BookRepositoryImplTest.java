package ie.soft8020.librarymanagement.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ie.soft8020.librarymanagement.LibraryManagementApplication;
import ie.soft8020.librarymanagement.domain.Book;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LibraryManagementApplication.class, webEnvironment= SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("default")
@Rollback
public class BookRepositoryImplTest {
	
	@Autowired
	IBookRepository repo;
	
	private static int DEFAULT_DB_SIZE = 10;
	private static int ID = 2;

	@Test
	public void testGet() {
		Book book = repo.get(ID);
		assertThat(book.getTitle(), equalTo("Effective Java"));
	}

	@Test
	public void testFindAll() {
		List<Book> books = repo.findAll();
		assertThat(books, hasSize(DEFAULT_DB_SIZE));
	}
	
	@Test
	@Transactional 
	public void testRemove() {
		// @Transactional at method level and @Roolback at the class level,
		// will ensure that the database rolls back at the end of testing.
		Book book = repo.get(ID);
		repo.remove(book);
		List<Book> books = repo.findAll();
		assertThat(books, hasSize(DEFAULT_DB_SIZE -1));
	}
	
	@Test
	@Transactional
	public void testSave() {
		// Our database has rolled back to its default size, 
		// due to the @Transactional and @Roolback annotations.
		List<Book> books = repo.findAll();
		assertThat(books, hasSize(DEFAULT_DB_SIZE));
		
		Book book = new Book();
		book.setTitle("My New Book");
		
		repo.save(book);
		List<Book> newBooks = repo.findAll();
		assertThat(newBooks, hasSize(DEFAULT_DB_SIZE + 1));
	}

}
