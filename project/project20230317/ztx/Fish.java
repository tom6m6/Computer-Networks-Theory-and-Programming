package dynamic_programming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Fish implements Animal, Comparable<Fish> {

    int size;

    public Fish(){
        Random r = new Random();
        this.size = r.nextInt(100);
    }

    void print(){
        System.out.print(this.size + " < ");
    }

    @Override
    public void eat() {
        System.out.println("Fish eats");
    }

    @Override
    public void travel() {
        System.out.println("Fish travels");
    }

    public void move(){
        System.out.println("Fish moves");
    }

    @Override
    public int compareTo(Fish o) {
        return this.size - o.size;
    }

    public static void main(String[] args){
        Fish[] fishes = new Fish[10];
        for(int i=0;i<10;i++)
            fishes[i] = new Fish();
        Arrays.sort(fishes);
        for(int i=0;i<10;i++)
            fishes[i].print();
    }
}


















