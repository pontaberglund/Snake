public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.addInput();
        while(true) {
            frame.setHead(25,25);
            frame.runGame();
            frame.showScore();
            frame.delay(5000);
            frame.rebuild();
        }
    }
}