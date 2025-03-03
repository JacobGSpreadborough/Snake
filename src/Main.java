public class Main {
    public static void main(String[] args) throws InterruptedException {

        SnakeGame game = new SnakeGame();

        // GAME LOOP

        game.render();

        while (true) {

            if (game.snake.score == GridOfSquares.rows * GridOfSquares.rows) {
                System.out.println("You Win!");
                break;
            }

            SnakeGame.move(SnakeGame.keyInput);

            game.snake.updateBody(SnakeGame.headX, SnakeGame.headY, SnakeGame.checkFood());
            if (game.snake.checkCollision(SnakeGame.headX, SnakeGame.headY) || game.snake.checkWalls()) {
                // high score doesn't work
                System.out.println("You Lost!");
                System.out.println("Score: " + game.snake.score);
                break;
            }
            game.render();

            if (SnakeGame.checkFood()) {
                game.newFood();
                String title = "Score: " + game.snake.score;
                game.frame.setTitle(title);
            }

            Thread.sleep(200);
        }
    }
}