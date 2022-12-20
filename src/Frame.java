import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

public class Frame extends JFrame {
    JPanel[][] panels = null;
    int headX, headY;
    Frame() {
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,500);
        this.setVisible(true);

        this.setLayout(null);

        //Create panels
        panels = new JPanel[50][50];
        for(int x = 0; x < panels.length; x++) {
            for(int y = 0; y < panels[x].length; y++) {
                panels[x][y] = new JPanel();
                panels[x][y].setBackground(Color.orange);
                panels[x][y].setBounds(x*10,y*10,10,10);
                this.add(panels[x][y]);
                this.repaint();
            }
        }
    }
    public void delay(int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setHead(int x, int y) {
        panels[x][y].setBackground(Color.black);
        headX = x;
        headY = y;
        this.repaint();
    }

    public void moveUp() {
        panels[headX][headY].setBackground(Color.orange);
        panels[headX][headY-1].setBackground(Color.black);
        headY--;
        this.repaint();
    }

    public void moveDown() {
        panels[headX][headY].setBackground(Color.orange);
        panels[headX][headY+1].setBackground(Color.black);
        headY++;
        this.repaint();
    }

    public void moveRight() {
        panels[headX][headY].setBackground(Color.orange);
        panels[headX+1][headY].setBackground(Color.black);
        headX++;
        this.repaint();
    }

    public void moveLeft() {
        panels[headX][headY].setBackground(Color.orange);
        panels[headX-1][headY].setBackground(Color.black);
        headX--;
        this.repaint();
    }

    public void addInput() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'w')
                    moveUp();
                else if(e.getKeyChar() == 's')
                    moveDown();
                else if(e.getKeyChar() == 'a')
                    moveLeft();
                else if(e.getKeyChar() == 'd')
                    moveRight();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }
}

//TODO Add check if head out of bounds, then die