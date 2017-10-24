package application.models;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Frog  extends Animal{
	
	private Rectangle body;

	private double borderTop;
	private double borderRight;
	private double borderBottom;
	private double borderLeft;
	
	private ArrayList<Circle> snake;

	private boolean isAlife;
	
	public Frog(double borderBottom, double borderRight) {
		this.borderBottom = borderBottom;
		this.borderRight = borderRight;
		this.borderTop = 0;
		this.borderLeft = 0;
		
		
		Random rnd = new Random(System.currentTimeMillis());
		
		double x = rnd.nextInt((int)borderRight);
		x = x - (x % 30) + 10;
		
		double y = rnd.nextInt((int)borderBottom);
		y = y - (y % 30) + 10;
		
		body =  new Rectangle(x,y,10,10);
		body.setFill(Paint.valueOf("GREEN"));
	}
	
	public Rectangle getBody() {
		return body;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move() {
		while(isAlife) {
			move();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
