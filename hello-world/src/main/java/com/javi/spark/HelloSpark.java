package com.javi.spark;


import static spark.Spark.*;

public class HelloSpark {

	public static void main(String[] args) {
		get("/hello", (request, response) -> "Hello Javi!");
	}

}
