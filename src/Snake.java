public class Snake {
    public int score;
    public SnakeCell head;
    public SnakeCell tail;
    public static final double ALIVE = -1.0;
    public static final double DEAD = 0.0;
    public static final double FOOD = 1.0;

    public Snake() {
        // default length and starting position
        score = 0;
        head = new SnakeCell(GridOfSquares.rows / 2, GridOfSquares.rows / 2);
        tail = new SnakeCell(GridOfSquares.rows / 2, (GridOfSquares.rows / 2 + 1));
        head.next = tail;
        tail.next = null;
    }

    public void updateBody(int newX, int newY, boolean ateFood) {
        // create new cell
        SnakeCell newCell = new SnakeCell(newX, newY);
        // replace head
        newCell.next = head;
        head = newCell;
        // set corresponding input to 'alive'
        NeuralNet.updateInputs(Main.inputs, head.xPosition, head.yPosition, ALIVE);
        // increase length if food was eaten
        if (ateFood) {
            score++;
        } else {
            // remove tail cell if food wasn't eaten
            SnakeCell temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            temp.next = null;
            //remove cell from inputs if no food was eaten
            NeuralNet.updateInputs(Main.inputs, temp.xPosition, temp.yPosition, DEAD);
        }
    }

    public boolean checkCollision(int x, int y) {
        // check against body
        SnakeCell temp = head.next;
        while (temp != null) {
            if (temp.xPosition == x && temp.yPosition == y) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public boolean checkWalls() {
        // check against walls
        return (head.xPosition > GridOfSquares.rows - 1)
                || (head.yPosition > GridOfSquares.rows - 1)
                || (head.xPosition < 0)
                || (head.yPosition < 0);
    }

}