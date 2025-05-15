import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Producer {

    private static final Lock queueLock = new ReentrantLock();
    private static final int TOTAL_ORDERS = 100;
    private static final Queue<Order> ORDER_QUEUE = new LinkedList<>();
    private static int ordersProduced = 0;

    private Producer() {
        throw new UnsupportedOperationException("Util class, do not initialize");
    }

    public static int add() {

        int orderId;

        queueLock.lock();

        try {
            if (ordersProduced >= TOTAL_ORDERS) {
                return -1;
            }

            if (Math.random() < 0.1) {
                System.out.println(Thread.currentThread().getName() + " Failed to create the order after acquiring the lock.");
                return 0;
            }

            orderId = ++ordersProduced;
        } finally {
            queueLock.unlock();
        }

        Order order = new Order(orderId);

        queueLock.lock();

        try {
            ORDER_QUEUE.add(order);
        } finally {
            queueLock.unlock();
        }

        return orderId;
    }
}