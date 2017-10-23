package application.models;
import java.util.ArrayList;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Snake extends Animals {
	
	public static enum Direction {RIGHT, LEFT, TOP, DOWN};
	
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
	
	public ArrayList<Circle> getBody(){
		return body;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
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

	@Override
	public void run() {
		while(true) {
			
			move();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	
}
