package com.kademika.day10.fr11ServiceRepository;

@Service
public class Service1 {
    String name;
    public Service1(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    
    @InitService
    public void printName(){
        System.out.println("Service name is " + name);
    }
}
