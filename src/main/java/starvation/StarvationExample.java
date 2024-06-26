package starvation;

public class StarvationExample {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        HighPriorityThread highPriorityThread = new HighPriorityThread(sharedResource);
        LowPriorityThread lowPriorityThread = new LowPriorityThread(sharedResource);

        highPriorityThread.start();
        lowPriorityThread.start();
    }
}