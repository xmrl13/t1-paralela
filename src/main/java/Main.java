public class Main {

    public static final int TOTAL_CLIENTS = 10;

    public static void main(String[] args) {
        for (int i = 0; i < TOTAL_CLIENTS; i++) {
            Client client = new Client("Client - " + (i+1));
            client.start();
        }
    }
}
