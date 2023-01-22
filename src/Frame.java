import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Frame extends JFrame {
    JPanel[][] panels = null;
    int[][] coordinate = new int[50][50];
    int headX, headY, appleX, appleY, length;
    char direction = 'u';
    Frame() {
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true);

        this.setLayout(null);
        length = 1;

        //Create panels
        panels = new JPanel[50][50];
        for(int x = 0; x < panels.length; x++) {
            for(int y = 0; y < panels[x].length; y++) {
                panels[x][y] = new JPanel();
                panels[x][y].setBackground(Color.orange);
                panels[x][y].setBounds(x*10,y*10,10,10);
                this.getContentPane().add(panels[x][y]);
            }
        }
        setApple();
    }

    public void rebuild() {
        clearFrame();
        length = 1;
        for(int x = 0; x < panels.length; x++)
            for(int y = 0; y < panels[x].length; y++) {
                this.getContentPane().add(panels[x][y]);
            }
        coordinate = new int[50][50];
        this.revalidate();
        this.repaint();
    }
    public void delay(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setHead(int x, int y) {
        headX = x;
        headY = y;
        coordinate[x][y] = length;
    }

    public void setApple() {
        Random rand = new Random();
        boolean cont = true;
        while(cont) {
            appleX = 5 + rand.nextInt(40);
            appleY = 5 + rand.nextInt(40);
            if(coordinate[appleX][appleY] == 0)
                cont = false;
        }
        panels[appleX][appleY].setBackground(Color.red);
    }


    public void updateScreen() {
        for(int x = 0; x < panels.length; x++) {
            for (int y = 0; y < panels[x].length; y++) {
                if(coordinate[x][y] != 0)
                    panels[x][y].setBackground(Color.black);
                else if(x == appleX && y == appleY)
                    panels[x][y].setBackground(Color.red);
                else
                    panels[x][y].setBackground(Color.orange);
            }
        }
        this.repaint();
    }

    public void moveUp() {
        headY--;
        for(int x = 0; x < coordinate.length; x++) {
            for (int y = 0; y < coordinate[x].length; y++) {
                if(coordinate[x][y] != 0)
                    coordinate[x][y]--;
            }
        }
        setHead(headX, headY);
    }

    public void moveDown() {
        headY++;
        for(int x = 0; x < coordinate.length; x++) {
            for (int y = 0; y < coordinate[x].length; y++) {
                if(coordinate[x][y] != 0)
                    coordinate[x][y]--;
            }
        }
        setHead(headX, headY);
    }

    public void moveRight() {
        headX++;
        for(int x = 0; x < coordinate.length; x++) {
            for (int y = 0; y < coordinate[x].length; y++) {
                if(coordinate[x][y] != 0)
                    coordinate[x][y]--;
            }
        }
        setHead(headX, headY);
    }

    public void moveLeft() {
        headX--;
        for(int x = 0; x < coordinate.length; x++) {
            for (int y = 0; y < coordinate[x].length; y++) {
                if(coordinate[x][y] != 0)
                    coordinate[x][y]--;
            }
        }
        setHead(headX, headY);
    }

    public void runGame() {
        length = 1;
        boolean setApple = false;
        while(true) {
            try {
                if (direction == 'u') {
                    if(coordinate[headX][headY-1] != 0) {
                        System.out.println("Dead");
                        break;
                    }
                    moveUp();
                }
                else if (direction == 'd') {
                    if(coordinate[headX][headY+1] != 0) {
                        System.out.println("Dead");
                        break;
                    }
                    moveDown();
                }
                else if (direction == 'l') {
                    if(coordinate[headX-1][headY] != 0) {
                        System.out.println("Dead");
                        break;
                    }
                    moveLeft();
                }
                else if (direction == 'r') {
                    if(coordinate[headX+1][headY] != 0) {
                        System.out.println("Dead");
                        break;
                    }
                    moveRight();
                }
                if(headX == appleX && headY == appleY) {
                    length++;
                    setApple = true;
                    panels[appleX][appleY].setBackground(Color.orange);
                    //setApple();
                }
                this.updateScreen();
                if(setApple) {
                    setApple();
                    setApple = false;
                }
                delay(150);
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Dead");
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void addInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'w')
                    direction = 'u';
                else if(e.getKeyChar() == 's')
                    direction = 'd';
                else if(e.getKeyChar() == 'a')
                    direction = 'l';
                else if(e.getKeyChar() == 'd')
                    direction = 'r';
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    public void clearFrame() {
        this.getContentPane().removeAll();
    }

    public void showScore() {
        clearFrame();
        JLabel label = new JLabel("Score: " + length);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        JPanel panel  = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBounds(0, 0, 500, 500);
        panel.setBackground(Color.red);
        panel.add(label);
        this.getContentPane().add(panel);
        this.revalidate();
        this.repaint();
        System.out.println(length);
    }
}

//TODO Fix better move method, collect right, left, up, down in the same method
//TODO Better check if head out of bounds, then die
//TODO Fix score
//TODO Fix apple spawn bug

//TODO Add borderLayout and resizeable window