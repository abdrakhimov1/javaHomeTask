import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class FixedThreadPool implements ThreadPool {
    private final Queue<Runnable> tasks = new LinkedList<>();
    private final Collection<Thread> threads;

    public FixedThreadPool(int numberOfThreads) {
        this.threads = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++
             ) {
            threads.add(new Thread(new TasksSolver()));
        }
    }

    private class TasksSolver implements Runnable {
        @Override
        public void run() {
            try {
                Runnable task;
                while (true) {
                    synchronized (tasks) {
                        while (tasks.isEmpty())
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
