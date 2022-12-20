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
                this.add(panels[x][y]);
            }
        }
        setApple();
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
        appleX = rand.nextInt(50);
        appleY = rand.nextInt(50);
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
        while(true) {
            try {
                if (direction == 'u')
                    moveUp();
                else if (direction == 'd')
                    moveDown();
                else if (direction == 'l')
                    moveLeft();
                else if (direction == 'r')
                    moveRight();
                if(headX == appleX && headY == appleY) {
                    length++;
                    panels[appleX][appleY].setBackground(Color.orange);
                    setApple();
                }
                this.updateScreen();
                delay(200);
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
}

//TODO Add check if head collides with tail
//TODO Better check if head out of bounds, then die