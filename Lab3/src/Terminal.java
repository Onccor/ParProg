import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Terminal {

    static Semaphore lock = new Semaphore(1);

    private static class User implements Runnable {

        public int id;

        public User(int id) {
            this.id = id;
        }

        public void useTerminal() {
            try {
                lock.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("User " + id + " use terminal");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("User " + id + " release terminal ");
            lock.release();
        }

        @Override
        public void run() {
            while (true) {
                useTerminal();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private static class SystemProcess implements Runnable {

        public void useTerminal() {
            try {
                lock.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("System Process use terminal");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("System Process release terminal");
            lock.release();
        }

        @Override
        public void run() {
            while (true) {
                useTerminal();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Terminal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public static void main(String[] args) {
        Thread sys = new Thread(new SystemProcess(), "systemProcess");
        sys.start();
        for (int i = 0; i < 10; i++) {
            
            new Thread(new User(i), "user" + i).start();
        }
    }
}