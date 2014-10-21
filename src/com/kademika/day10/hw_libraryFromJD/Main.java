package com.kademika.day10.hw_libraryFromJD;

public class Main {
	// Name, Author, Genre

	public static void main(String[] args) {
		Library lib = new Library();
		lib.insertBook(new Book("Aed rose", "Bevi Magnem", BookGenre.FANTASY));
		lib.insertBook(new Book("Aed lkhj", "Bevi gfd", BookGenre.FANTASY));
		lib.insertBook(new Book("Aed sdgsg", "Bevi sgsdgh", BookGenre.FANTASY));
		lib.insertBook(new Book("Aed sdfasf", "Bevi jk", BookGenre.FANTASY));
		lib.insertBook(new Book("Aed erwe", "Bevi eryeye", BookGenre.FANTASY));
		lib.insertBook(new Book("Aed rere", "Bevi Magnem", BookGenre.FANTASY));
		lib.insertBook(new Book("Aed ladskjhj", "Bevi gfd", BookGenre.FANTASY));
		lib.insertBook(new Book("Aed sdgvxsg", "Bevi sgsdgh", BookGenre.FANTASY));
		lib.insertBook(new Book("Aed sdfafdghdfsf", "Bevi jk", BookGenre.FANTASY));
		lib.insertBook(new Book("Aed erwsdfe", "Bevi eryeye", BookGenre.FANTASY));
		
		lib.getBooksByName("Aed sdgsg");
		System.out.println(" ");
		lib.getBookByAutor("Bevi eryeye");
		System.out.println(" ");
		lib.getBooksByGenre(BookGenre.FANTASY);
		System.out.println(" ");
		lib.getBooksByGenre(BookGenre.FANTASY);
		lib.dropCounter();
		lib.getBooksByGenre(BookGenre.FANTASY);
		System.out.println(" ");
		lib.getBooksByGenre(BookGenre.FANTASY);
		
	}

}
