import java.util.*;
class MyThread implements Runnable {
    static int[] seeds = {1234567, 2345671, 3456712};
    MyThread(int id, Res _res) {
        Random r = new Random(seeds[id]);
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(r.nextInt(10000));
        }
        res = _res;
    }
    @Override
    public void run() {
        int maxx=Integer.MIN_VALUE;
        synchronized(res) {
            for (int i=0;i<list.size();i++) {
                if (list.get(i)>maxx) {
                    maxx=list.get(i);
                }
            }
            if (maxx>res.max_idx) {
                res.max_idx=maxx;
            }
        }
    }

    ArrayList<Integer> list;
    Res res;
}