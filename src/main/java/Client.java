public class Client extends Thread {

    public Client(String name) {
        super(name);
    }

    private void send() {
        int order = OrderQueueManager.addOrder();

        if (order == -1) {
            System.out.println("Shutting down thread: " + getName());
            Thread.currentThread().interrupt();
            return;
        }

        if (order == 0) {
            return;
        }

        System.out.println(getName() + " created Order #" + order);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                send();
                Thread.sleep(500 + (int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted and terminated.");
            Thread.currentThread().interrupt();
        }
    }
}