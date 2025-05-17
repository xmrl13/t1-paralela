import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderQueueManager {

    private static final Lock queueLock = new ReentrantLock();
    private static final int TOTAL_ORDERS = 10;
    private static final Queue<Order> ORDER_QUEUE = new LinkedList<>();
    private static int ordersProduced = 0;
    public static Semaphore ordersAvailable = new Semaphore(0);

    private OrderQueueManager() {
        throw new UnsupportedOperationException("Util class, do not initialize");
    }

    public static int addOrder() {

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

            Order order = new Order(orderId);

            ORDER_QUEUE.add(order);

            ordersAvailable.release();
            return orderId;

        } finally {
            queueLock.unlock();
        }
    }

    public static Order getOrder() {
        try {
            ordersAvailable.acquire();

            queueLock.lock();
            try {
                Order order = ORDER_QUEUE.poll();
                assert order != null;
                System.out.println(Thread.currentThread().getName() + " processing Order #" + order.id());
                return order;
            } finally {
                queueLock.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " was interrupted while waiting for orders.");
            Thread.currentThread().interrupt();
        }
        return null;
    }
}