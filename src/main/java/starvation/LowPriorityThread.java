package starvation;

class LowPriorityThread extends Thread {
    private final SharedResource sharedResource;

    public LowPriorityThread(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
        this.setPriority(Thread.MIN_PRIORITY);
    }

    @Override
    public void run() {
        while (true) {
            sharedResource.accessResource("LowPriorityThread");
        }
    }
}
