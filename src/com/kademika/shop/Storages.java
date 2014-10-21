package com.kademika.shop;

import com.kademika.shop.constants.Name;
// Storages hranit infomatciu o tovarah, pokupatel'ah i pokupkah
public class Storages {
	Bird[][] brdStrg;
	
	Purchase[] prchsStrg;
	public Storages() {
		brdStrg = new Bird[Name.values().length][100];
		
		prchsStrg = new Purchase[1000];
	}
	// rabotaem s pticami (tovar)
	// dibavlenie novoi pticy v hranilische
	public void insertBird (Bird bird){
		for (int i = 0; i<100; i++){
			if (brdStrg[bird.getName().getId()] [i] == null){
				brdStrg[bird.getName().getId()] [i] = bird;
				break;
			}
		}
	}
	// vynimanie pticy iz hranilischa 
	public Bird getBird(Name name) {
		Bird brd = null;
		if (brdStrg[name.getId()][0] == null) {
			System.out.println("Sorry, we have no this birds anymore =(");// insert Exception here, 
		}
		for (int i = 0; i < 100; i++) {
			if (brdStrg[name.getId()][i] == null) {
				brd = brdStrg[name.getId()][i - 1];
				brdStrg[name.getId()][i - 1] = null;
				break;
			}
		}
		return brd;
	}
	//rabotaem s pokupatelem, zdes' est vozmognost' tol'ko dobavleniya
	public void insertPurchase(Purchase prchs){
		for (int i = 0; i<1000; i++){
			if (prchsStrg [i] == null){
				prchsStrg [i] = prchs;
				break;
			}
		}
	}
	public Bird birdInStorage(Name name, int number){
		final Bird brd = brdStrg[name.getId()][number]; // is it correct?
		return brd;
	}
	
	public Purchase purchaseInStorage(int number){
		final Purchase prchs = prchsStrg[number]; // is it correct?
		return prchs;
	}
}
