import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Frame frame = new Frame();
        frame.setHead(25,25);
        for(int i = 0; i < 10; i++) {
            frame.moveUp();
            frame.delay(500);
        }
        frame.addInput();
    }
}