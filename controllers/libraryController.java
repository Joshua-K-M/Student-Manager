package com.example.library2222;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
@Controller
public class libraryController {
	@Autowired
	Book3Repo repo;
	@Autowired
	Book3Service serve;
	@Autowired
	Member3Service vice ;
	@Autowired
	Member3Repo vile;
	@Autowired
	Borrow3Service servo;
	@GetMapping("/librarian")
	public String welcomePage(Model model) {
		model.addAttribute("come","here");
		return "welcomePage";	}
	
	@GetMapping("/registerNewMember")
	public String registerNewMember() {
		return "registerNewMember";	}
	
	@PostMapping("/register")
	public String register(@RequestParam("name") String name, @RequestParam("Email") String email) {
	    Member3 member = new Member3();
	    member.setName(name);
	    member.setEmail(email);
	    vile.save(member);
	    return "redirect:/librarian"; 
	}

	@RequestMapping("/addBook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book3());
		return "addBook";	}
	
    @PostMapping("/saveBook")   
    public String receiveAndSaveBook(@ModelAttribute("book") Book3 incomingBook) {
    	System.out.println("data reached  "+incomingBook.getTitle() );
        repo.save(incomingBook);
        return "redirect:/addBook"; 
    }


	@RequestMapping("/updateBook")
	public String updateBook() {
		return "updateBook";	}
	
	@Transactional
	@PostMapping("/update")
	public String update(RedirectAttributes redirectAttributes, @RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("bookID") Long bookID,@RequestParam("category") Category category,@RequestParam("isAvailable") Boolean isAvailable){
	   try {
	   Book3 book = repo.findById(bookID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BOOK WITH SUCH ID NOT FOUND"));
	   book.setAuthor(author);
	   book.setCategory(category);
	   book.setTitle(title);
	   book.setIsAvailable(isAvailable);
	   repo.save(book);
	    return "redirect:/librarian"; 
	   } catch(Exception e){
		   redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		   return "redirect:/updateBook";
	   }
	}
	@RequestMapping("/delete")
	public String delete() {
		return "delete";	}
	
	@PostMapping("/deleting")
	public String deleteBook(@RequestParam("bookID") int id, Model model) {
		Long jk = (long) id; 
	    serve.deleteBookByID(jk);
	    System.out.println("Deleting book with ID: " + id);
	    return "redirect:/librarian"; 
	}
	
	@RequestMapping("/viewAllBooks")
	public String viewAllBooks(Model model) {
		List<Book3> books = repo.findAll();
		model.addAttribute("books", books);
		return "viewAllBooks";	}
	
	@GetMapping("/viewBorrowedBooks")
	public String viewBorrowedBooks(Model model) {
		List<Member3> members = vile.findAll();
		StringBuilder allNames = new StringBuilder();
    	for(Member3 member: members) {
  
    		if (member.getBook1id() != null && member.getBook1id() != 0) {
    			Long mem = member.getBook1id();
    			Book3 boo = new Book3();
    			boo = repo.findById(mem).orElse(null);
    			if(boo != null) {
    				allNames.append(boo.getTitle()+" "+"BY:"+" "+member.getName()+" "+"BORROW DATE:"+"  "+String.valueOf(boo.getBorrowDate())+" "+"RETURN DATE:"+"  "+String.valueOf(boo.getReturnDate())).append("\n");
    				allNames.append("<br><br>");
    			}
    		}
    		else if (member.getBook2id() != null && member.getBook2id() != 0) {
    			Long mem = member.getBook2id();
    			Book3 boo = new Book3();
    			boo = repo.findById(mem).orElse(null);
    			if(boo != null) {
    				allNames.append(boo.getTitle()+" "+"BY:"+" "+member.getName()+" "+"BORROW DATE:"+"  "+String.valueOf(boo.getBorrowDate())+" "+"RETURN DATE:"+"  "+String.valueOf(boo.getReturnDate())).append("\n");
    				allNames.append("<br><br>");
    			}
    		}
    		else if (member.getBook3id() != null && member.getBook3id() != 0) {
    			Long mem = member.getBook3id();
    			Book3 boo = new Book3();
    			boo = repo.findById(mem).orElse(null);
    			if(boo != null) {
    				allNames.append(boo.getTitle()+" "+"BY:"+" "+member.getName()+" "+"BORROW DATE:  "+String.valueOf(boo.getBorrowDate())+" "+"RETURN DATE:"+"  "+String.valueOf(boo.getReturnDate())).append("\n");
    				allNames.append("<br><br>");
    			}
    		}
    	
    	}
    	model.addAttribute("Borrowed", allNames.toString());
		return "viewBorrowedBooks";	}
	
	@GetMapping("/viewBorrowed")
	public String viewBorrowed(Model model) {
		List<Member3> members = vile.findAll();
		StringBuilder allNames = new StringBuilder();
    	for(Member3 member: members) {
  
    		if (member.getBook1id() != null && member.getBook1id() != 0) {
    			Long mem = member.getBook1id();
    			Book3 boo = new Book3();
    			boo = repo.findById(mem).orElse(null);
    			if(boo != null) {
    				allNames.append(boo.getTitle()).append("\n");
    				allNames.append("<br><br>");
    			}
    		}
    		else if (member.getBook2id() != null && member.getBook2id() != 0) {
    			Long mem = member.getBook2id();
    			Book3 boo = new Book3();
    			boo = repo.findById(mem).orElse(null);
    			if(boo != null) {
    				allNames.append(boo.getTitle()).append("\n");
    				allNames.append("<br><br>");
    			}
    		}
    		else if (member.getBook3id() != null && member.getBook3id() != 0) {
    			Long mem = member.getBook3id();
    			Book3 boo = new Book3();
    			boo = repo.findById(mem).orElse(null);
    			if(boo != null) {
    				allNames.append(boo.getTitle()).append("\n");
    				allNames.append("<br><br>");
    			}
    		}
 
    	}
    	model.addAttribute("orrowed", allNames.toString());
		return "viewBorrowed";
	}
	
	@GetMapping("/viewAllMembers")
	public String viewAllMembers(Model model) {
		List<Member3> members = vile.findAll();
		StringBuilder allNames = new StringBuilder();
    	for(Member3 member: members) {
    		allNames.append(member.getId()+"  "+ member.getName()).append("\n");
    		allNames.append("<br><br>");
    	}
    	model.addAttribute("members", allNames.toString());
		return "viewAllMembers";
	}
	
	@GetMapping("/member")
	public String welcomeMemberPage(Model model) {
		model.addAttribute("come","here");
		return "welcomeMemberPage";	}
	
	
	@GetMapping("/searchTitle")
    public String searchTitle(){
    	return "searchTitle";
    
	}
	
	@PostMapping("/searchByTitle")
	public String searchTitle(HttpServletRequest request, Model model) {
	    String enteredTitle = request.getParameter("bookTitle");
	    StringBuilder allNames = new StringBuilder();
	    List<Book3> all = new ArrayList(); 
    	all = repo.findAll();
    	List<Book3> categor = new ArrayList();
    	for(Book3 books: all) {
    		if (books.getTitle() != null && books.getTitle().equals(enteredTitle)) {
    			allNames.append(books.getId()+"      "+books.getTitle()+"       "+books.getCategory()).append("\n");
				allNames.append("<br><br>");
    		}
    	}
    	model.addAttribute("enteredTitle", allNames.toString());
	    return "searchByTitle";}
	
	@GetMapping("/searchAuthor")
	public String searchAuthor() {
		return "searchAuthor";
	}
	
	@PostMapping("/searchByAuthor")
	public String searchAuthor(HttpServletRequest request, Model model) {
	    String enteredTitle = request.getParameter("bookAuthor");
	    StringBuilder allNames = new StringBuilder();
	    List<Book3> all = new ArrayList(); 
    	all = repo.findAll();
    	List<Book3> categor = new ArrayList();
    	for(Book3 books: all) {
    		if (books.getTitle() != null && books.getAuthor().equals(enteredTitle)) {
    			allNames.append(books.getId()+"      "+books.getTitle()+"       "+books.getCategory()).append("\n");
				allNames.append("<br><br>");
    		}
    	}
    	model.addAttribute("enteredAuthor", allNames.toString());
	    return "searchByAuthor";}
	
	@GetMapping("/searchCategory")
	public String searchCategory() {
		return "searchCategory";
	}
	
	@PostMapping("/searchByCategory")
	public String searchCategory(@RequestParam("category") Category category, Model model) {
	   
	    StringBuilder allNames = new StringBuilder();
	    List<Book3> all = new ArrayList();
	    all = repo.findByCategory(category);

    	for(Book3 books: all) {
    		allNames.append(books.getId()+"      "+books.getTitle()+"       "+books.getCategory()).append("\n");
			allNames.append("<br><br>");
    	}
    	model.addAttribute("enteredCategory", allNames.toString());
	    return "searchByCategory";}
	
	@GetMapping("/borrowBook")
	public String borrowBook() {
		return "borrowBook";
	}
	
    @PostMapping("/borrowing")
    public String borrowing(Model model, @RequestParam("bookID") Long bookID, @RequestParam("memberID") Long memberID, @RequestParam("borrowDate") String borrowDate, @RequestParam("returnDate") String returnDate){
    	

	
		Member3 member = vile.findById(memberID).orElse(null);
		Book3 book = repo.findById(bookID).orElse(null);

	if(book.getIsAvailable() == false) {
		return "borrowNotAvailable";
	}
	if(member == null) {
		return "memberNotAvailable";
	}
	 if(member.getBook1id() == null) {
		member.setBook1id(bookID);
		book.setIsAvailable(false);
		book.setBorrowDate(borrowDate);
		book.setReturnDate(returnDate);
	}
	else if(member.getBook2id() == null) {
		member.setBook2id(bookID);
		book.setIsAvailable(false);
		book.setBorrowDate(borrowDate);
		book.setReturnDate(returnDate);
	}
	else if(member.getBook3id() == null) {
		member.setBook3id(bookID);
		book.setIsAvailable(false);
		book.setBorrowDate(borrowDate);
		book.setReturnDate(returnDate);
	}
	
	
	String title = book.getTitle();
	model.addAttribute("title", title);
	repo.save(book);
	vile.save(member);
    
	return "borrowDone";
    }
    
    @GetMapping("/returnBook")
    public String returnBook() {
    	return "returnBook";
    }
    @PostMapping("/returning")
    public String returning(Model model, @RequestParam("bookID") Long bookID, @RequestParam("memberID") Long memberID) {
    	 Book3 book = repo.findById(bookID).orElse(null);		 
		 Member3 member = vile.findById(memberID).orElse(null);
			     
		 if(book.getIsAvailable() == null) {
				return "borrowNotAvailable";
			}
			if(member == null) {
				return "memberNotAvailable";
			}
			    
			    if (member.getBook1id() == bookID) {
			    	member.setBook1id(0L);
			    	book.setIsAvailable(true);
			    	book.setReturnDate(null);
			    	book.setBorrowDate(null);
			    }
			    else if (member.getBook2id() == bookID) {
			    	member.setBook2id(0L);
			    	book.setIsAvailable(true);
			    	book.setReturnDate(null);
			    	book.setBorrowDate(null);
			    }
			    else if (member.getBook3id() == bookID) {
			    	member.setBook3id(0L);
			    	book.setIsAvailable(true);
			    	book.setReturnDate(null);
			    	book.setBorrowDate(null);
			    }
			    
			    String title = book.getTitle();
				model.addAttribute("title", title);
				repo.save(book);
				vile.save(member);
				
			    return "returning";
    }
    
    @GetMapping("/viewBorrowedByMember")
    public String viewBorrowedByMember() {
    	return "viewBorrowedByMember";
    }
    @PostMapping("/viewing")
    public String viewing(Model model, @RequestParam("memberID") Long memberID) {
    	Book3 book = new Book3();
		Book3 booki = new Book3();
		Book3 booko = new Book3();
		
		
		Member3 member = vile.findById(memberID).orElse(null);
		if(member == null) {
			return "memberNotAvailable";
		}
		StringBuilder allNames = new StringBuilder();
		Long book1 = member.getBook1id();
		Long book2 = member.getBook2id();
		Long book3 = member.getBook3id();
		
		if(book1 != null && book1 != 0) {
			book = repo.findById(book1).orElse(null);
			allNames.append(book.getTitle());
			allNames.append("<br><br>");
		}
		if(book2 != null && book2 != 0) {
			booki = repo.findById(book1).orElse(null);
			allNames.append(booki.getTitle());
			allNames.append("<br><br>");
		}
		if(book3 != null && book3 != 0) {
			booko = repo.findById(book1).orElse(null);
			allNames.append(booko.getTitle());
			allNames.append("<br><br>");
		}
		model.addAttribute("books", allNames.toString());
	    return "viewing";
    }
}




