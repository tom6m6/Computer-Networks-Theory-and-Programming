package dynamic_programming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        ArrayList<Color> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Collections.addAll(list, Color.values());
        }
        Random r = new Random(1234567);
        Collections.shuffle(list, r);
        for (int i = 0; i < list.size(); i++) {
            Color c = list.get(i);
            switch (c){
                case RED:
                    System.out.println(Color.RED.type);
                    break;
                case GREEN:
                    System.out.println(Color.GREEN.type);
                    break;
                case BLUE:
                    System.out.println(Color.BLUE.type);
                    break;
            }
        }
    }

    enum Color {
        RED(1),
        GREEN(2),
        BLUE(3);
        int type;

        Color(int _type) {
            this.type = _type;
        }
    }
}