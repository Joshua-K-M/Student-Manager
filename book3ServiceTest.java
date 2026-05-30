package com.example.library;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.library2222.Book3;
import com.example.library2222.Book3Repo;
import com.example.library2222.Book3Service;

public class book3ServiceTest {
	@ExtendWith(MockitoExtension.class)
	@Mock
	@Autowired
	private Book3Repo repo;
    
	@InjectMocks
	private Book3Service book3Service;
	
	private Book3 book;
	
	@BeforeEach
	void setUp() {
		book = new Book3();
		book.setTitle("THE JAVA REFERENCE");
		book.setAuthor("John Doe");
		book.setIsAvailable(true);
	}
	@Test
	void testGetAllBooks_Success() {
		List<Book3> bookList = new ArrayList<>();
		bookList.add(book);
		when(repo.findAll()).thenReturn(bookList);
		
		List<Book3> result = book3Service.getAllBooks();
		
		assertEquals(1, result.size());
		assertEquals("THE JAVA REFERENCE", result.get(0).getTitle());
		verify(repo, times(1)).findAll();
	}
	
	@Test
	void testGetBookByID_Success() {
		Long bookId = 1L;
		when(repo.findById(bookId)).thenReturn(Optional.of(book));
		
		Book3 result = book3Service.getBookByID(bookId);
		
		assertNotNull(result);
		assertEquals("THE JAVA REFERENCE", result.getTitle());
		verify(repo, times(1)).findById(bookId);
	}
	
	@Test
	void testAddBook_Success_WhenBookDoesNotExist() {
		when(repo.findAll()).thenReturn(new ArrayList<>());
		when(repo.save(book)).thenReturn(book);
		book3Service.addBook(book);
		verify(repo, times(1)).save(book);
	}
	
	
}
