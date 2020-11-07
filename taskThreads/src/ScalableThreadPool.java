import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class ScalableThreadPool implements ThreadPool {

    private final Queue<Runnable> tasks = new LinkedList<>();
    private final Collection<Thread> threads;
    private final int min, max;

    public ScalableThreadPool(int min, int max) {
        this.min = min;
        this.max = max;
        this.threads = new ArrayList<>();
        for (int i = 0; i < min; i++
        ) {
            threads.add(new Thread(new ScalableTaskSolver()));
        }
    }

    private void resetThreadsToMin(){
        threads.clear();
        for (int i = 0; i < min; i++
        ) {
            threads.add(new Thread(new ScalableTaskSolver()));
        }
    }

    private class ScalableTaskSolver implements Runnable{

        @Override
        public void run() {
            try {
                Runnable task;
                while (true) {
                    synchronized (tasks) {
                        while (tasks.isEmpty())
                            resetThreadsToMin();
                            tasks.wait();
                        task = tasks.poll();
                    }
                    task.run();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start() {
        for (Thread thread : threads
        ) {
            thread.start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (tasks) {
            tasks.add(runnable);
            tasks.notify();
        }
    }
}
