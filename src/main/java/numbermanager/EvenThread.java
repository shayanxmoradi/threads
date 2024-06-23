package numbermanager;

import java.util.List;

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
