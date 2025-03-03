public class Snake {
    int length;
    int[][] body = new int[2][GridOfSquares.rows*GridOfSquares.rows];

    public Snake() {
        // default length and starting position
        length = 2;
        body[0][0] = GridOfSquares.rows/2;
        body[1][0] = GridOfSquares.rows/2;
        body[0][1] = GridOfSquares.rows/2;
        body[1][1] = (GridOfSquares.rows/2) + 1;
    }

    public void updateBody(int newX, int newY, boolean ateFood) {
        // adds another cell if food was eaten, if not the last cell gets overridden and disappears
        length += (ateFood) ? 1:0;
        // iterate through the list backwards and shift all the values
        // would it be better to fill the array from the end and iterate backwards when drawing the snake
        for (int i = length; i > 0; i--) {
            body[0][i] = body[0][i-1];
            body[1][i] = body[1][i-1];
        }
        // set last item to 0
        body[0][0] = newX;
        body[1][0] = newY;
    }


    public boolean checkCollision(int x, int y) {
        // check against body
        for (int i = 1; i < length; i++) {
            if(body[0][x] == body[0][i]
                    && body[1][y] == body[1][i]) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWalls() {
        // check against walls
        return (body[0][0] > GridOfSquares.rows - 1)
                || (body[1][0] > GridOfSquares.rows - 1)
                || (body[0][0] < 0)
                || (body[1][0] < 0);
    }

}