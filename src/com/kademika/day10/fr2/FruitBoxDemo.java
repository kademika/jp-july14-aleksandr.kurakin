package com.kademika.day10.fr2;

import java.util.List;

/**
 * Created by kurakinaleksandr on 28.06.14.
 */
public class FruitBoxDemo {
    public static void main (String [] args){
//        FruitBox<Apple> apples = new FruitBox<>();
//        FruitBox<Banana> bananas = new FruitBox<>();
        FruitBox<Fruit> fruits = new FruitBox<>();
        Apple ap1 = new Apple();
        ap1.setPrice(12);
        fruits.add(ap1);
        Apple ap2 = new Apple();
        ap2.setPrice(10);
        fruits.add(ap2);
        Apple ap3 = new Apple();
        ap3.setPrice(14);
        fruits.add(ap3);
        Apple ap4 = new Apple();
        ap4.setPrice(8);
        fruits.add(ap4);

        Banana bn1 = new Banana();
        bn1.setPrice(2);
        fruits.add(bn1);
        Banana bn2 = new Banana();
        bn2.setPrice(9);
        fruits.add(bn2);
        Banana bn3 = new Banana();
        bn3.setPrice(18);
        fruits.add(bn3);
        Banana bn4 = new Banana();
        bn4.setPrice(1);
        fruits.add(bn4);

        List<Fruit> fruitBox = fruits.getFruits();
        for (int l = 0; l<fruitBox.size(); l++){
            System.out.println(fruitBox.get(l).toString());
        }
        System.out.println("And now sorting");
        fruits.sortByPrice();
        System.out.println("After sorting");
        List<Fruit> fruitBox1 = fruits.getFruits();
        for (int l = 0; l<fruitBox1.size(); l++){
            System.out.println(fruitBox1.get(l).toString());


        }
    }
}
