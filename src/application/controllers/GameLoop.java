package application.controllers;

import java.util.List;

import application.models.Snake;
import application.models.Snake.Direction;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class GameLoop{
	private Group snakeGroup;
	private Group frogGroup;
	private Snake snake;
	private Pane parentPane;
	
	public GameLoop(Group snakeGroup, Group frogGroup, Pane parentPane) {
		this.snakeGroup = snakeGroup;
		this.frogGroup = frogGroup;
		this.parentPane = parentPane;
	}
	
	public void start() {
		createListeners();
		createSnake();
		Thread snakeThread = new Thread(snake, "Snake");
		snakeThread.start();	
	}
	
	private void createListeners() {
		parentPane.setOnMouseClicked(event ->{
        	if (event.getButton().equals(MouseButton.PRIMARY)) {
        		if(snake.getDirection().equals(Direction.RIGHT)) {
        			snake.setDirection(Direction.UP);
        		}else if(snake.getDirection().equals(Direction.UP)){
        			snake.setDirection(Direction.LEFT);
        		}else if(snake.getDirection().equals(Direction.LEFT)){
        			snake.setDirection(Direction.DOWN);
        		}else if(snake.getDirection().equals(Direction.DOWN)){
        			snake.setDirection(Direction.RIGHT);
        		}
        	}else if((event.getButton().equals(MouseButton.SECONDARY))) {
        		if(snake.getDirection().equals(Direction.RIGHT)) {
        			snake.setDirection(Direction.DOWN);
        		}else if(snake.getDirection().equals(Direction.UP)){
        			snake.setDirection(Direction.RIGHT);
        		}else if(snake.getDirection().equals(Direction.LEFT)){
        			snake.setDirection(Direction.UP);
        		}else if(snake.getDirection().equals(Direction.DOWN)){
        			snake.setDirection(Direction.LEFT);
        		}
        	}
        	
        });
	}
	
	private void createSnake() {
	
		snake = new Snake(5);

		List<Circle> body = snake.getBody();
		for( int i = 0; i < body.size(); i++) {
			snakeGroup.getChildren().add(body.get(i));
		}
	}


}
