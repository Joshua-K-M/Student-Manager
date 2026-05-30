package com.example.library;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.library2222.Book3;
import com.example.library2222.Book3Repo;
import com.example.library2222.Member3;
import com.example.library2222.Member3Repo;
import com.example.library2222.Member3Service;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
public class member3ServiceTest {
	
	@Mock
	private Member3Repo Mrepo;
	@Mock
	private Book3Repo Brepo;
	
	private Member3Service member3Service;
	private MockedStatic<JOptionPane> mockedJOptionPane;
	
	@BeforeEach
	void setUp() {
		member3Service = new Member3Service(Mrepo, Brepo);
		mockedJOptionPane = Mockito.mockStatic(JOptionPane.class);
	}
	
	@AfterEach
	void tearDown() {
		mockedJOptionPane.close();
	}
	
	@Test
	void testAddMember_Success() {
		Member3 mockMember = new Member3();
		mockMember.setName("Alice Green");
		
		assertDoesNotThrow(() -> member3Service.addMember(mockMember));
		verify(Mrepo, times(1)).save(mockMember);
	}
	
	@Test
	void testViewBorrowedBooksByMember_Success() {
		Long targetId = 42L;
		Member3 mockMember = new Member3();
		mockMember.setName("Charlie Brown");
		mockMember.setBook1id(101L);
		
		when(Mrepo.findById(targetId)).thenReturn(Optional.of(mockMember));
		assertDoesNotThrow(() -> member3Service.viewBorrowedBooksByMember(targetId));
		
		mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(eq(null), contains("101")));
		
	}
	
	@Test
	void testViewAllMembers_Success() {
		Member3 member1 = new Member3();
		member1.setName("User One");
		member1.setBook1id(0L);
		
		List<Member3> allMembers = new ArrayList<>();
		allMembers.add(member1);
		
		when(Mrepo.findAll()).thenReturn(allMembers);
		
		assertDoesNotThrow(() -> member3Service.viewAllMembers());
		
		mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(any(), any()));
	}
	
	@Test
	void testViewBorrowedBooks_Success() {
		Member3 activeMember = new Member3();
		activeMember.setName("David Miller");
		activeMember.setBook1id(202L);
		
		Book3 borrowedBook = new Book3();
		borrowedBook.setTitle("Effective Java");
		
		borrowedBook.setBorrowDate("2026-05-10");
		borrowedBook.setReturnDate("2026-06-10");
		
		List<Member3> allMembers = new ArrayList<>();
		when(Mrepo.findAll()).thenReturn(allMembers);
		
		assertDoesNotThrow(() -> member3Service.viewBorrowedBooks());
		mockedJOptionPane.verify(() -> JOptionPane.showMessageDialog(any(), any()));
	}

}


