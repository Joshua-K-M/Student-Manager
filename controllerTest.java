package com.example.library2222;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.library2222.Book3;
import com.example.library2222.Book3Repo;
import com.example.library2222.Book3Service;
import com.example.library2222.Borrow3Service;
import com.example.library2222.Member3;
import com.example.library2222.Member3Repo;
import com.example.library2222.Member3Service;
import com.example.library2222.libraryController;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
@WebMvcTest(controllers = libraryController.class)
public class controllerTest {
	
	@MockitoBean
	private Book3Repo repo;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private Book3Service serve;
	
	@MockitoBean
	private Member3Service vice;
	
	@MockitoBean
	private Member3Repo vile;
	
	@MockitoBean
	private Borrow3Service servo;
	
	@Test
	public void testWelcomePage() throws Exception{
		mockMvc.perform(get("/librarian"))
		.andExpect(status().isOk())
		.andExpect(view().name("welcomePage"))
		.andExpect(model().attribute("come", "here"));
	}
	
	@Test
	public void testRegisterMember_Post() throws Exception{
		when(vile.save(any(Member3.class))).thenReturn(new Member3());
		mockMvc.perform(post("/register")
		.param("name", "John Doe")
		.param("Email", "john.doe@example.com"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/librarian"));
	}
	
	@Test
	public void testAddBookPage() throws Exception{
		mockMvc.perform(get("/addBook"))
		.andExpect(status().isOk())
		.andExpect(view().name("addBook"))
		.andExpect(model().attributeExists("book"));
	}
	
	@Test
	public void testReceiveAndSaveBook_Post() throws Exception{
		when(repo.save(any(Book3.class))).thenReturn(new Book3());
		
		mockMvc.perform(post("/saveBook")
				.flashAttr("book", new Book3()))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/addBook"));
	}
	
	@Test
	public void testViewAllBooks() throws Exception{
		when(repo.findAll()).thenReturn(new ArrayList<>());
		
		mockMvc.perform(get("/viewAllBooks"))
		.andExpect(status().isOk())
		.andExpect(view().name("viewAllBooks"))
		.andExpect(model().attributeExists("books"));
		
	}
	
	@Test
	public void testDeleteBook_Post() throws Exception{
		
		mockMvc.perform(post("/deleting")
				.param("bookID", "1"))
			    .andExpect(status().is3xxRedirection())
			    .andExpect(view().name("redirect:/librarian"));
				
	}
	
	@Test
	public void testBorrowing_Success() throws Exception{
		Member3 mockMember = new Member3();
		mockMember.setBook1id(null);
		
		Book3 mockBook = new Book3();
		mockBook.setIsAvailable(true);
		mockBook.setTitle("Sample Book");
		when(vile.findById(any(Long.class))).thenReturn(Optional.of(mockMember));
		when(repo.findById(any(Long.class))).thenReturn(Optional.of(mockBook));
		
		mockMvc.perform(post("/borrowing")
				.param("bookID", "1")
				.param("memberID", "10")
				.param("returnDate", "23")
				.param("borrowDate", "2025-05-27")) 
		        .andExpect(status().isOk())
		        .andExpect(view().name("borrowDone"));
		
				
	}

}
