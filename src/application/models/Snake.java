package application.models;
import java.util.ArrayList;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Snake extends Animals {
	
	private boolean isAlife = true;
	
	public static enum Direction {RIGHT, LEFT, UP, DOWN};
	
	private Direction direction = Direction.RIGHT;
	
	private ArrayList<Circle> body =  new ArrayList<>();
	
	public Snake(int size) {
		int margin = 30;	
		int lastPart = size * 30 - 15;
		Circle head =  new Circle(lastPart, 15, 15, Paint.valueOf("YELLOW"));
		body.add(head);
		for(int i = 1; i < size-1; i++) {
			Circle middle =  new Circle(lastPart - margin, 15, 10, Paint.valueOf("YELLOW"));
			lastPart = lastPart - margin;
			body.add(middle);
		}
		Circle tail =  new Circle(15, 15, 7.5, Paint.valueOf("YELLOW"));
		body.add(tail);
	}
	
	public boolean isAlife() {
		return isAlife;
	}
	
	public ArrayList<Circle> getBody(){
		return body;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return this.direction;
	}
	
	public void eat() {
		
	}
	

	@Override
	public void move() {
	
		double lastX = 0;
		double lastY = 0;

		if(this.direction == Direction.RIGHT) {
			 lastX = body.get(0).getCenterX();
			 lastY = body.get(0).getCenterY();
			 body.get(0).setCenterX(lastX + 30);
	
		}else if(this.direction == Direction.LEFT) {
			 lastX = body.get(0).getCenterX();
			 lastY = body.get(0).getCenterY();
			 body.get(0).setCenterX(lastX - 30);
	
		}else if(this.direction == Direction.UP) {
			 lastX = body.get(0).getCenterX();
			 lastY = body.get(0).getCenterY();
			 body.get(0).setCenterY(lastY - 30);
	
		}else if(this.direction == Direction.DOWN) {
			 lastX = body.get(0).getCenterX();
			 lastY = body.get(0).getCenterY();
			 body.get(0).setCenterY(lastY + 30);
	
		}
		
		for(int i = 1; i < body.size(); i++) {
			double currentX =  body.get(i).getCenterX();
			double currentY = body.get(i).getCenterY();;
			
			body.get(i).setCenterX(lastX);
			body.get(i).setCenterY(lastY);
			
			lastX = currentX;
			lastY = currentY;
			
		}
	
	}
	
	private void isSuicide() {
		for(int i = 1; i < body.size(); i++) {
			if(body.get(0).getBoundsInParent().intersects(body.get(i).getBoundsInParent())) {
				for(Circle deathPart : body) {
					deathPart.setFill(Paint.valueOf("RED"));
				}
				isAlife = false;
				break;
			}
		}
	}

	@Override
	public void run() {
		while(isAlife) {
			move();
			isSuicide();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	
}
