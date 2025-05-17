public class Kitchen extends Thread {

    public Kitchen(String name) {
        super(name);
    }

    private void getOrder(){
        OrderQueueManager.getOrder();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                getOrder();
                Thread.sleep(500 + (int) (Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " was interrupted and terminated.");
            Thread.currentThread().interrupt();
        }
    }
}

