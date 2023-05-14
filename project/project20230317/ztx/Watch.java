package dynamic_programming;

import java.util.Date;

public class Watch {

    protected long startTime;

    protected long endTime;

    void start(){
        startTime = System.currentTimeMillis();
    }

    void end(){
        endTime = System.currentTimeMillis();
    }
}
