class Chef {
    private int platosDisponibles = 0;
    
    public synchronized void producirPlatos(int cantidad, String tipoPlato) {
        System.out.println("Chef está produciendo " + cantidad + " platos de " + tipoPlato);
        platosDisponibles += cantidad;
        notifyAll(); // Notifica a los hilos en espera que hay platos disponibles
    }
    
    public synchronized void realizarPedido(int cantidad, String tipoPlato) {
        while (platosDisponibles < cantidad) {
            try {
                System.out.println("Mesero esperando platos de " + tipoPlato);
                wait(); // Espera hasta que haya suficientes platos disponibles
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupción durante la espera del pedido.");
            }
        }
        
        System.out.println("Mesero está sirviendo " + cantidad + " platos de " + tipoPlato);
        platosDisponibles -= cantidad;
    }
}

class Mesero implements Runnable {
    private Chef chef;
    private int cantidad;
    private String tipoPlato;
    
    public Mesero(Chef chef, int cantidad, String tipoPlato) {
        this.chef = chef;
        this.cantidad = cantidad;
        this.tipoPlato = tipoPlato;
    }
    
    @Override
    public void run() {
        chef.realizarPedido(cantidad, tipoPlato);
    }
}

public class Main {
    public static void main(String[] args) {
        Chef chef = new Chef();
        
        // Crear hilos productores (chefs)
        Thread chef1 = new Thread(() -> chef.producirPlatos(5, "Pizza"));
        Thread chef2 = new Thread(() -> chef.producirPlatos(3, "Sushi"));
        
        // Crear hilos consumidores (meseros)
        Thread mesero1 = new Thread(new Mesero(chef, 2, "Pizza"));
        Thread mesero2 = new Thread(new Mesero(chef, 4, "Sushi"));
        
        // Iniciar hilos
        chef1.start();
        chef2.start();
        mesero1.start();
        mesero2.start();
        
        // Esperar a que los hilos terminen
        try {
            chef1.join();
            chef2.join();
            mesero1.join();
            mesero2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupción durante la ejecución de hilos.");
        }
        
        System.out.println("Finalización de la simulación.");
    }
}
