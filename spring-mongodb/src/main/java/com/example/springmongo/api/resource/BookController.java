package com.example.springmongo.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmongo.api.model.Book;
import com.example.springmongo.api.repository.BookRepository;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository repository;
		
	@PostMapping("/addBook")
	public String saveBook(@RequestBody Book book) {
		repository.save(book);
		return "Added book";
	}
	
	@GetMapping("/findAllBooks")
	public List<Book> getBooks() {
		return repository.findAll();
	}
	
	@GetMapping("/findAllBooks/{id}")
	public Optional<Book> getBook(@PathVariable int id) {
		return repository.findById(id);
	}
	
	@GetMapping("/")
	public String serverStarted() {
		return "Server running";
	}	
		
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		repository.deleteById(id);
		return "book deleted with id : " + id;
	}
}
