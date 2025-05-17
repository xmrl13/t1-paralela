public class Main {

    public static final int TOTAL_CLIENTS = 10;
    public static final int TOTAL_KITCHENS = 10;

    public static void main(String[] args) {
        for (int i = 0; i < TOTAL_CLIENTS; i++) {
            Client client = new Client("Client - " + (i+1));
            client.start();
        }
        for (int i = 0; i < TOTAL_KITCHENS; i++) {
            Kitchen kitchen = new Kitchen("Kitchen - " + (i+1));
            kitchen.start();
        }
    }
}
