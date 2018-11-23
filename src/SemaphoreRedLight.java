import java.util.concurrent.Semaphore;

public class SemaphoreRedLight {
    public static void main(String[] args) {
        Semaphore semaphore= new Semaphore(1,true);
        new Thread(new ThreadTest("Pole 1",semaphore)).start();
        new Thread(new ThreadTest("Pole 2",semaphore)).start();
        new Thread(new ThreadTest("Pole 3",semaphore)).start();
        new Thread(new ThreadTest("Pole 4",semaphore)).start();
    }
}

class ThreadTest implements Runnable {
private String threadName="";
    Semaphore semaphore;

    ThreadTest(String threadName, Semaphore semaphore){
        this.threadName=threadName;
        this.semaphore=semaphore;
    }

    @Override
    public void run() {
        while (true){
            try {
                semaphore.acquire();
                System.out.println(threadName+" Green Light");
                Thread.sleep(3000);
                System.out.println(threadName+" Yellow Light");
                Thread.sleep(1000);
                System.out.println(threadName+" Red Light");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            semaphore.release();
        }
    }
}
