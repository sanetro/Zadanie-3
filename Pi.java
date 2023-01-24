import java.util.concurrent.*;

public class Pi {    
    static long inRangeCircle = 0;
    static long totalPoints = 1_200_000_000;
    static int numThreads = 10;

    public static void main(String[] args){
        ExecutorService exe = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            exe.execute(new PiThread());
        }
        exe.shutdown();
        while(!exe.isTerminated()) {}

        double pi = 4.0 * (double) inRangeCircle / (double) totalPoints;
        System.out.println("Pi = " + pi + "\n" +
                            "Threads = " + numThreads + "\n" +
                            "Points = " + totalPoints + "\n");
    }

    static class PiThread implements Runnable {
        public void run() 
        {
            for (long i = 0; i < totalPoints / numThreads; i++) 
            {
                double x = Math.random();
                double y = Math.random();
                if (x * x + y * y <= 1)
                    inRangeCircle++;
            }
        }
    }
}


