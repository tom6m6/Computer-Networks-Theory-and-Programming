public class Battle implements Runnable {
    private BattleObject bo1;
    private BattleObject bo2;
    public Battle(BattleObject bo1, BattleObject bo2) {
        this.bo1 = bo1;
        this.bo2 = bo2;
    }
    // TODO
    @Override
    public void run(){
        while(!bo2.isDestoryed()){
            bo1.attackHero(bo2);
            if(bo1.isDestoryed()){
                break;
            }
        }
    }

}
