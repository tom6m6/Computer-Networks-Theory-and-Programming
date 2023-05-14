public class TestBattle {
    public static void main(String[] args) {
        BattleObject bo1 = new BattleObject();
        bo1.name = "Object1";
        bo1.hp = 600;
        bo1.attack = 50;
        BattleObject bo2 = new BattleObject();
        bo2.name = "Object2";
        bo2.hp = 500;
        bo2.attack = 40;
        // TODO
        /*
        Battle battle = new Battle(bo1,bo2);
        Thread thread1 = new Thread(battle,"Thread-1");
        thread1.start();
        */

        // Extend
        Battle battle1 = new Battle(bo1,bo2);
        Battle battle2 = new Battle(bo2,bo1);
        Thread thread1 = new Thread(battle1,"Thread-1");
        Thread thread2 = new Thread(battle2,"Thread-2");
        thread1.start();
        thread2.start();
    }
}