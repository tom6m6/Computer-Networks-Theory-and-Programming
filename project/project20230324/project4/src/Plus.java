class Plus extends Thread {
    Plus(PlusMinus pm) {
        this.plusMinus = pm;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            plusMinus.plusOne();
        }
    }

    PlusMinus plusMinus;
}