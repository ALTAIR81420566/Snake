package application.controllers;

import java.util.ArrayList;
import java.util.List;

import application.models.Frog;
import application.models.Snake;
import application.models.Snake.Direction;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GameLoop{

	private Snake snake;
	private ArrayList<Rectangle> frogs =  new ArrayList<>();
	private Pane parentPane;
	
	public GameLoop(Group snakeGroup, Group frogGroup, Pane parentPane) {
		this.parentPane = parentPane;
	}
	
	public void start() {
		createListeners();
		createFrogs(3);
		createSnake(5);
		
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
	
	private void createSnake(int size) {
	
		snake = new Snake(size, parentPane.getPrefHeight(), parentPane.getPrefWidth(), frogs);

		List<Circle> body = snake.getBody();
		for( int i = 0; i < body.size(); i++) {
			parentPane.getChildren().add(body.get(i));
		}
	}
	
	private void createFrogs(int count) {
		
		for(int i = 0 ; i < count; i++) {
			Frog frog = new Frog( parentPane.getPrefHeight(), parentPane.getPrefWidth());
			frogs.add(frog.getBody());
			parentPane.getChildren().add(frog.getBody());
		}
	}


}
