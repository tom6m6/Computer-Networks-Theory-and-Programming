import java.util.*;
public class Test {
    public static void main(String[] args) {
        ArrayList<Color> list = new ArrayList<>();
        for(int i=1;i<=3;i++){
            Collections.addAll(list, Color.values());
        }
        Random r = new Random(1234567);
        Collections.shuffle(list, r);
        for(int i=0;i<list.size();i++){
            Color c = list.get(i);
            // TODO
            switch (c){
                case RED:
                    System.out.print(Color.RED.type+" ");
                    break;
                case GREEN:
                    System.out.print(Color.GREEN.type+" ");
                    break;
                case BLUE:
                    System.out.print(Color.BLUE.type+" ");
                    break;
            }
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