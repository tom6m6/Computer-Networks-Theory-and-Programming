class PlusMinusOne {
    volatile int num;
    public void plusOne() {
        synchronized (this) {
            this.num = this.num + 1;
            printNum();
        }


    }
    public void minusOne() {
        synchronized (this) {
            this.num = this.num - 1;
            printNum();
        }

    }
    public void printNum() {
        System.out.println("num = " + this.num);
    }
}
