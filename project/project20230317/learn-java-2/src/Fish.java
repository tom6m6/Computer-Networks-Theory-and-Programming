import java.util.*;

public class Fish implements Animal, Comparable{
    public void eat(){
        System.out.println("Fish eats");
    }
    public void travel(){
        System.out.println("Fish travels");
    }
    public void move(){
        System.out.println("Fish moves");
    }
    // for task2
    int size;
    public Fish(){
        Random r = new Random();
        this.size = r.nextInt(100);
    }
    void print(){
        System.out.print(this.size + " < ");
    }

    // implement Comparable interface


    public static void main(String args[]){
        // create an ArrayList to store Fish objects
        Fish[] fishList = new Fish[10];
        // add 10 Fish objects to the list
        for(int i=0; i<10; i++){
            fishList[i] = new Fish();
        }
        Arrays.sort(fishList);
        // sort the list using Collections.sort method
        //Collections.sort(fishList);
        // print each object in the sorted list using print method
        for(Fish fish : fishList){
            fish.print();
        }
    }

    @Override
    public int compareTo(Object other) {
        Fish fish = (Fish)other;
        return this.size - fish.size;
    }
}
