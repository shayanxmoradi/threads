import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SharedResource {
    private final List<Integer> sharedList = new ArrayList<>();
    private boolean evenTurn = true;

    public synchronized void addEvenNumbers(List<Integer> evens) throws InterruptedException {
        for (int number : evens) {
            while (!evenTurn) {
                wait();
            }
            sharedList.add(number);
            evenTurn = false;
            notify();
        }
    }

    public synchronized void addOddNumbers(List<Integer> odds) throws InterruptedException {
        for (int number : odds) {
            while (evenTurn) {
                wait();
            }
            sharedList.add(number);
            evenTurn = true;
            notify();
        }
    }

    public List<Integer> getSharedList() {
        return sharedList;
    }
}

class EvenThread extends Thread {
    private final SharedResource sharedResource;
    private final List<Integer> evens;

    public EvenThread(SharedResource sharedResource, List<Integer> evens) {
        this.sharedResource = sharedResource;
        this.evens = evens;
    }

    @Override
    public void run() {
        try {
            sharedResource.addEvenNumbers(evens);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class OddThread extends Thread {
    private final SharedResource sharedResource;
    private final List<Integer> odds;

    public OddThread(SharedResource sharedResource, List<Integer> odds) {
        this.sharedResource = sharedResource;
        this.odds = odds;
    }

    @Override
    public void run() {
        try {
            sharedResource.addOddNumbers(odds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();

        for (int i = 0; i <= number; i++) {
            if (i % 2 == 0) {
                evens.add(i);
            } else {
                odds.add(i);
            }
        }

        SharedResource sharedResource = new SharedResource();

        EvenThread evenThread = new EvenThread(sharedResource, evens);
        OddThread oddThread = new OddThread(sharedResource, odds);

        evenThread.start();
        oddThread.start();

        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Combined list: " + sharedResource.getSharedList());
    }
}