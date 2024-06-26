package deadlock;

public class ThreadDeadlock {


    public static void main(String[] args) throws InterruptedException {
        Bank bank1 = new Bank("shayan",1200);
        Bank bank2 = new Bank("soghra",1100);
        Bank bank3 = new Bank("ali",100);


        Thread t1 = new Thread(new SyncThread(bank1, bank2), "t1");
        Thread t2 = new Thread(new SyncThread(bank1, bank3), "t2");
        Thread t3 = new Thread(new SyncThread(bank3, bank1), "t3");

        t1.start();
        Thread.sleep(5000);
        t2.start();
        Thread.sleep(5000);
        t3.start();

    }

}

