
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class SnakeGame {

    static int foodX = GridOfSquares.rows / 2;
    static int foodY = GridOfSquares.rows / 2;
    // default starting spot
    static int headX = GridOfSquares.rows / 2;
    static int headY = GridOfSquares.rows / 2;
    static char keyInput = 'w';
    static char lastValidInput = 'w';
    Snake snake;
    JFrame frame;
    GridPanel panel;

    public SnakeGame() {

        snake = new Snake();
        frame = new JFrame("Jake Snake");
        panel = new GridPanel();
        // set up the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(panel);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                keyInput = e.getKeyChar();
            }
        });
        frame.setVisible(true);
    }


    public void drawSnake() {
        // TODO stop food from spawning inside snake
        SnakeCell temp = snake.head;
        while (temp != null) {
            panel.setTargetSquare(temp.xPosition, temp.yposition, Color.green);
            temp = temp.next;
        }
    }

    // TODO stop food from spawning inside snake
    public void drawFood() {
        panel.setTargetSquare(foodX, foodY, Color.RED);
    }

    public void render() {
        panel.resetColors();

        drawFood();
        drawSnake();
        panel.repaint();
    }

    // NO
    public void newFood() {
        foodX = (int) (Math.random() * GridOfSquares.rows - 1);
        foodY = (int) (Math.random() * GridOfSquares.rows - 1);
        while (snake.checkCollision(foodX, foodY)) {
            System.out.println("FOOD COLLISION");
            newFood();
        }
    }

    public static boolean checkFood() {
        return headX == foodX && headY == foodY;
    }

    // TODO prevent moving opposite of previous direction
    public static void move(char key) {
        switch (key) {
            case 'w':
                if (lastValidInput == 's') {
                    move(lastValidInput);
                    break;
                }
                headY--;
                lastValidInput = key;
                break;
            case 'a':
                if (lastValidInput == 'd') {
                    move(lastValidInput);
                    break;
                }
                headX--;
                lastValidInput = key;
                break;
            case 's':
                if (lastValidInput == 'w') {
                    move(lastValidInput);
                    break;
                }
                headY++;
                lastValidInput = key;
                break;
            case 'd':
                if (lastValidInput == 'a') {
                    move(lastValidInput);
                    break;
                }
                headX++;
                lastValidInput = key;
                break;
            default:
                move(lastValidInput);
                break;
        }
    }
}