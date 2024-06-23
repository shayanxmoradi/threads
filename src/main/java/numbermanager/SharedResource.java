package numbermanager;

import java.util.ArrayList;
import java.util.List;

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
