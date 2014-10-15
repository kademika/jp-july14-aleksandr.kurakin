package com.kademika.day10.hw_libraryFromJD;

public class Book {

	private String name;
	private String autor;
	private BookGenre bookGenre;

	public Book(String name, String autor, BookGenre bookGenre) {
		this.name = name;
		this.autor = autor;
		this.bookGenre = bookGenre;
	}

	public void getBookInfo() {
		System.out.println("Title: " + this.getName() + "; Autor: "
				+ this.getAutor() + "; Genre: " + this.getBookGenre());
	}

	public String getName() {
		return name;
	}

	public String getAutor() {
		return autor;
	}

	public BookGenre getBookGenre() {
		return bookGenre;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setBookGenre(BookGenre bookGenre) {
		this.bookGenre = bookGenre;
	}
}
