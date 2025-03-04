public class Main {

	public static char[] movements = { 'w', 'a', 's', 'd' };
	public static double[] inputs;

	public static void main(String[] args) throws InterruptedException {

		// input nodes are all the squares on the screen
		// hidden layer is 16 because it's a nice number
		// 4 outputs are WASD
		int[] layerSizes = { GridOfSquares.area, 16, 4 };
		inputs = new double[GridOfSquares.area];
		NeuralNet snakeAI = new NeuralNet(layerSizes);
		SnakeGame game = new SnakeGame();

		// GAME LOOP

		game.render();

		while (true) {

			if (game.snake.score == GridOfSquares.area) {
				System.out.println("You Win!");
				break;
			}

			SnakeGame.keyInput = movements[snakeAI.Classify(inputs)];
			System.out.println(SnakeGame.keyInput);

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