package application.controllers;

import java.util.List;

import application.models.Snake;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class GameLoop implements Runnable {
	private Group snakeGroup;
	private Group frogGroup;
	private Snake snake;
	
	public GameLoop(Group snakeGroup, Group frogGroup) {
		this.snakeGroup = snakeGroup;
		this.frogGroup = frogGroup;
	}
	
	public void start() {
		createSnake();
		Thread snakeThread = new Thread(snake, "Snake");
		snakeThread.start();
			
	}
	
	private void createSnake() {
	
		snake = new Snake(4);

		List<Circle> body = snake.getBody();
		for( int i = 0; i < body.size(); i++) {
			snakeGroup.getChildren().add(body.get(i));
		}
	}

	private void updateSnake() {
		snakeGroup.getChildren().clear();
		List<Circle> body = snake.getBody();
		for( int i = 0; i < body.size(); i++) {
			snakeGroup.getChildren().add(body.get(i));
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
					updateSnake();
					Thread.sleep(300);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
}
