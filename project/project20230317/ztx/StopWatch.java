package dynamic_programming;

public class StopWatch extends Watch{

    public long getElapsedTime(){
        return endTime - startTime;
    }
}
