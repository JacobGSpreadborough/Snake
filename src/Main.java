public class Main {
    public static void main(String[] args) throws InterruptedException {

        SnakeGame game = new SnakeGame();

        // GAME LOOP

        game.render();

        while (true) {

            if (game.snake.length == GridOfSquares.rows * GridOfSquares.rows) {
                System.out.println("You Win!");
                break;
            }

            SnakeGame.move(SnakeGame.keyInput);

            game.snake.updateBody(SnakeGame.headX, SnakeGame.headY, SnakeGame.checkFood());
            if (game.snake.checkCollision(0, 0) || game.snake.checkWalls()) {
                // high score doesn't work
                System.out.println("You Lost!");
                System.out.println("Score: " + (game.snake.length - 2));
                break;
            }
            game.render();

            if (SnakeGame.checkFood()) {
                game.newFood();
                String title = "Score: " + (game.snake.length - 2);
                game.frame.setTitle(title);
            }

            Thread.sleep(200);
        }
    }
}