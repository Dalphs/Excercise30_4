import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static int sum = 0;
    public static int syncSum = 0;

    public static void main(String[] args) {
        ArrayList<Runnable> runnables = new ArrayList<>();
        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService syncedPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    sum++;
                }
            };
            pool.execute(runnable);
        }
        pool.shutdown();


        runnables.clear();

        for (int i = 0; i < 1000; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    addSynchronized();
                }
            };
            syncedPool.execute(runnable);
        }

        syncedPool.shutdown();

        System.out.println(syncSum);
        System.out.println(syncSum);
        System.out.println(syncSum);

    }

    public static synchronized void addSynchronized(){
        syncSum++;
    }

}
