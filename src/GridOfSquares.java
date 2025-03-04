import java.awt.*;
import javax.swing.JPanel;

public class GridOfSquares {

    public final static int rows = 8;

}

class GridPanel extends JPanel {

    // array to store the color of the squares we'll render later
    Color[][] colors = new Color[GridOfSquares.rows][GridOfSquares.rows];

    // initialize the grid to all black
    public GridPanel() {
        for (int i = 0; i < GridOfSquares.rows; i++) {
            for (int j = 0; j < GridOfSquares.rows; j++) {
                colors[i][j] = Color.BLACK;
            }
        }

    }


    public void resetColors() {
        for (int i = 0; i < GridOfSquares.rows; i++) {
            for (int j = 0; j < GridOfSquares.rows; j++) {
                colors[i][j] = Color.BLACK;
            }
        }
    }

    public void setTargetSquare(int x, int y, Color color) {
        colors[x][y] = color;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // don't need
        int sizeLength = getWidth();

        // set size of squares in grid
        int squareSize = sizeLength / GridOfSquares.rows;

        // draw grid of squares
        for (int i = 0; i < GridOfSquares.rows; i++) {
            for (int j = 0; j < GridOfSquares.rows; j++) {

                // draw rectangle
                g.setColor(colors[i][j]);
                g.fillRect(i * squareSize, j * squareSize, squareSize, squareSize);

                // draw outline
                g.setColor(colors[i][j]);
                g.drawRect(i * squareSize, j * squareSize, squareSize, squareSize);

            }
        }
    }
}