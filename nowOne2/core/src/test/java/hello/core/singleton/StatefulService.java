package hello.core.singleton;

import java.sql.SQLOutput;

public class StatefulService {

    private int price;

    public void order(String name, int price){
        System.out.println("name= " + name + " price= "+price);
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
