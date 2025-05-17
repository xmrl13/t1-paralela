public record Order(int id, int prepTime1, int prepTime2, int prepTime3) {
    public Order(int id) {
        this(id,
                1 + (int) (Math.random() * 5), // Tempo aleatório entre 1-3 segundos
                1 + (int) (Math.random() * 5), // Tempo aleatório entre 1-3 segundos
                1 + (int) (Math.random() * 5)  // Tempo aleatório entre 1-3 segundos
        );
    }
}