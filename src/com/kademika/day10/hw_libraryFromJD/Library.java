package com.kademika.day10.hw_libraryFromJD;

public class Library {
	Book book;
	
	
	int x = 0;
	int y = 0;
	int z = 0;
	
	Book[][][][] lib = new Book[6][26][26][50];

	public Library() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < 26; k++) {
					for (int n = 0; n < 50; n++)
						lib[i][j][k][n] = null;
				}
			}
		}
	}

	public void getBookByAutor(String autor) {
		String atr = autor.substring(0, 1);

		int aTr = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(atr);

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < 50; k++) {
					if (lib[i][aTr][j][k] != null
							&& lib[i][aTr][j][k].getAutor().equals(autor)) {

						lib[i][aTr][j][k].getBookInfo();

					}
				}
			}
		}
//		System.out.println("We did not found the book");
	}

	public void getBooksByName(String name) {

		int nM = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(name.substring(0, 1));

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 26; j++) {
				for (int k = 0; k < 50; k++) {
					
					if (lib[i][j][nM][k] != null
							&& lib[i][j][nM][k].getName().equals(name)) {
						System.out.println("We have found the book");
						lib[i][j][nM][k].getBookInfo();

					}
				}
			}
		}
//		System.out.println("We did not found the book");
	}

	public void getBooksByGenre(BookGenre bookGenre) {

		int gG = bookGenre.getId();
//		int genreCount = 0;
//		while (genreCount < 5){	
		for (int x=0;x < 26; x++) {
			for (int y=0; y < 26; y++) {
				for (int z=0; z < 50; z++) {
					if (lib[gG][x][y][z] != null && lib[gG][x][y][z].getBookGenre().equals(bookGenre)) {
//						if (genreCount ==5){
//							break;
//							} 
					
							lib[gG][x][y][z].getBookInfo();
//							genreCount++;	
						}
					
				}
//				if (genreCount ==5){
//					break;
//				}
			}
//			if (genreCount ==5){
//				break;
//			}
		}
		}
//		System.out.println("We did not found the book");
	
	
	public void dropCounter(){
		x = 0;
		y = 0;
		z = 0;
	}

	void insertBook(Book object) {

		int nN = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(object.getName().substring(0, 1));
		int aA = ("ABCDEFGHIJKLMNOPQRSTUVWXYZ").indexOf(object.getAutor().substring(0, 1));
		int gG = object.getBookGenre().getId();
		for (int i = 0; i < 50; i++) {
			if (lib[gG][aA][nN][i] == null) {

				lib[gG][aA][nN][i] = object;
				break;
			}
		}
	}
}
