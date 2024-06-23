package starvation;

class HighPriorityThread extends Thread {
    private final SharedResource sharedResource;

    public HighPriorityThread(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
        this.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void run() {
        while (true) {
            sharedResource.accessResource("HighPriorityThread");
        }
    }
}
