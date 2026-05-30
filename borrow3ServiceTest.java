package com.example.library;

import static org.mockito.Mockito.when;


import java.util.Optional;

import javax.swing.JOptionPane;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import com.example.library2222.Book3;
import com.example.library2222.Book3Repo;
import com.example.library2222.Borrow3Repo;
import com.example.library2222.Borrow3Service;
import com.example.library2222.Member3;
import com.example.library2222.Member3Repo;

@ExtendWith(MockitoExtension.class)
public class borrow3ServiceTest {
	
	@Mock
	private Borrow3Repo repo;
	@Mock
	private  Member3Repo Mrepo;
	@Mock 
	private Book3Repo Brepo;
	
	private Borrow3Service borrow3Service;
	private MockedStatic<JOptionPane> mockedJOptionPane;
	
	@BeforeEach
	void setUp() {
		borrow3Service = new Borrow3Service(repo, Mrepo, Brepo);
		mockedJOptionPane = Mockito.mockStatic(JOptionPane.class);
	}
	
	@AfterEach
	void tearDown() {
		mockedJOptionPane.close();
	}
	
	@Test
	void testBorrow_Success() {
		Long memberId = 1L;
		Long bookId = 100L;
		Member3 mockMember = new Member3();
		mockMember.setName("John Doe");
		mockMember.setBook1id(0L);
		
		Book3 mockBook = new Book3();
		mockBook.setTitle("Spring Boot in Action");
		mockBook.setIsAvailable(true);
		
		when(Mrepo.findById(memberId)).thenReturn(Optional.of(mockMember));
		when(Brepo.findById(bookId)).thenReturn(Optional.of(mockBook));
		
		mockedJOptionPane.when(() -> JOptionPane.showInputDialog("ENTER BORROW DATE")).thenReturn("2026-05-25");
		mockedJOptionPane.when(() -> JOptionPane.showInputDialog("ENTER RETURN DATE")).thenReturn("2026-06-05");
		mockedJOptionPane.when(() -> JOptionPane.showOptionDialog(any(), any(), any(), anyInt(), anyInt(), any(), any(), any())).thenReturn(JOptionPane.OK_OPTION);
		
		assertDoesNotThrow(() -> borrow3Service.borrow(memberId, bookId));
		
		assertFalse(mockBook.getIsAvailable(), "The book should mark itself as unavailable");
		assertEquals(bookId, mockMember.getBook1id(), "The member should have saved the book ID into slot 1");
	}
	
	@Test 
	void testBorrow_BookAlreadyBorrowed_ThrowsException() {
		Long memberId = 1L;
		Long BookId = 100L;
		
		Member3 mockMember = new Member3();
		mockMember.setName("John Doe");
		
		Book3 mockBook = new Book3();
		mockBook.setTitle("Clean Code");
		mockBook.setIsAvailable(false);
		
		when(Mrepo.findById(memberId)).thenReturn(Optional.of(mockMember));
		when(Brepo.findById(BookId)).thenReturn(Optional.of(mockBook));
		
		assertDoesNotThrow(() -> {borrow3Service.borrow(memberId, BookId);});
	}
	
	@Test
	void testreturnBook_Succcess() {
		Long bookId = 100L;
		Long memberId = 1L;
		
		Book3 mockBook = new Book3();
		mockBook.setTitle("Test Driven Development");
		mockBook.setIsAvailable(false);
		
		Member3 mockMember = new Member3();
		mockMember.setName("Bob Smith");
		mockMember.setBook1id(bookId);
		
		when(Brepo.findById(bookId)).thenReturn(Optional.of(mockBook));
		when(Mrepo.findById(memberId)).thenReturn(Optional.of(mockMember));
		
		assertDoesNotThrow(() -> borrow3Service.returnBook(bookId, memberId));
		
		assertEquals(0L, mockMember.getBook1id(), "The borrowed book ID slot should reset to 0L");
		assertTrue(mockBook.getIsAvailable(), "The book should be available again");
	}

}
