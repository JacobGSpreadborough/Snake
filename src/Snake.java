public class Snake {
    public int score;
    public SnakeCell head;
    public SnakeCell tail;

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
        // increase length if food was eaten
        if (ateFood) {
            score++;
        }
        else{
            SnakeCell temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            temp.next = null;
        }
    }

    public boolean checkCollision(int x, int y) {
        // check against body
        SnakeCell temp = head.next;
        while (temp != null) {
            if (temp.xPosition == x && temp.yposition == y) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public boolean checkWalls() {
        // check against walls
        return (head.xPosition > GridOfSquares.rows - 1)
                || (head.yposition > GridOfSquares.rows - 1)
                || (head.xPosition < 0)
                || (head.yposition < 0);
    }

}