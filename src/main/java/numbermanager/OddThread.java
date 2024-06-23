package numbermanager;

import java.util.List;

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
