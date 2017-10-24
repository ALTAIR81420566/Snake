package application.models;
import java.util.ArrayList;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Snake extends Animal {
	
	private boolean isAlife = true;
	
	public static enum Direction {RIGHT, LEFT, UP, DOWN};
	private Direction direction = Direction.RIGHT;
	
	private ArrayList<Circle> body =  new ArrayList<>();
	private ArrayList<Rectangle> food =  new ArrayList<>();

	private double borderTop;
	private double borderRight;
	private double borderBottom;
	private double borderLeft;
	
	private double lastX = 0;
	private double lastY = 0;
	
	public Snake(int size, double borderBottom, double borderRight, ArrayList<Rectangle> food) {
		this.borderBottom = borderBottom;
		this.borderRight = borderRight;
		this.borderTop = 0;
		this.borderLeft = 0;
		this.food = food;
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
		for(Rectangle frog : food) {
			if(body.get(0).getBoundsInParent().intersects(frog.getBoundsInParent())) {
				body.add(1 , new Circle(lastX, lastY, 10, Paint.valueOf("YELLOW")));
				body.get(0).setCenterX(frog.getX());
				body.get(0).setCenterY(frog.getY() + 30);			
			}
		}
		
	}
	

	@Override
	public void move() {
	
			if(this.direction == Direction.RIGHT) {
				 lastX = body.get(0).getCenterX();
				 lastY = body.get(0).getCenterY();
				 
				 if(body.get(0).getCenterX() >= (borderRight - 15)) {
					 body.get(0).setCenterX(15);
				 }else {
					 body.get(0).setCenterX(lastX + 30);
				 }
		
			}else if(this.direction == Direction.LEFT) {
				 lastX = body.get(0).getCenterX();
				 lastY = body.get(0).getCenterY();
				 
				 if(body.get(0).getCenterX() <= (borderLeft + 15)) {
					 body.get(0).setCenterX(borderRight - 15);
				 }else {
					 body.get(0).setCenterX(lastX - 30);
				 }
		
			}else if(this.direction == Direction.UP) {
				 lastX = body.get(0).getCenterX();
				 lastY = body.get(0).getCenterY();
				 
				 if(body.get(0).getCenterY() <= (borderTop + 15)) {
					 body.get(0).setCenterY(borderBottom - 15);
				 }else {
					 body.get(0).setCenterY(lastY - 30);
				 }
		
			}else if(this.direction == Direction.DOWN) {
				 lastX = body.get(0).getCenterX();
				 lastY = body.get(0).getCenterY();
				 
				 if(body.get(0).getCenterY() >= (borderBottom - 15)) {
					 body.get(0).setCenterY(borderTop + 15);
				 }else {
					 body.get(0).setCenterY(lastY + 30);
				 }
	
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
			eat();
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
