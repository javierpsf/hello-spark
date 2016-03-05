package com.javi.spark;


import static spark.Spark.get;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.javi.spark.model.Book;

public class HelloSpark {

	private static Map<String, Book> books = new HashMap<String, Book>();
	
	public static void main(String[] args) {
		
		final Random random = new Random();
		
		get("/hello", (request, response) -> "Hello Javi!");
		
		
        get("/users/:name", (request, response) -> "Selected user: " + request.params(":name"));
        
        post("/books", (request, response) -> {
            String author = request.queryParams("author");
            String title = request.queryParams("title");
            Book book = new Book(author, title);

            int id = random.nextInt(Integer.MAX_VALUE);
            books.put(String.valueOf(id), book);

            response.status(201); // 201 Created
            return id;
        });
        
        
        get("/books/:id", (request, response) -> {
            Book book = books.get(request.params(":id"));
            Boolean book2 = books.containsValue("javi");
            if (book != null) {
                return "Title: " + book.getTitle() + ", Author: " + book.getAuthor();
            } else {
                response.status(404); // 404 Not found
                return "Book not found";
            }
        });


		
	}

}
