import java.util.Scanner;


public class Point {

	private double x, y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getX(){
		return x;
	}
	public void setX(double x){
		this.x = x;
	}
	public double getY(){
		return y;
	}
	public void setY(double y){
		this.y = y;
	}
	
	
	public double dFrom0(){
		return Math.sqrt(getX()*getX() + getY()*getY());
	}
	
	
	public boolean inQuadrant(){
		if(dFrom0() < 1) return true;		
		return false;
	}
	
	
	public static Point randPoint(){
		return new Point(Math.random(), Math.random());
	}
	
	
	public static Point[] randPointArray(int n){
		Point[] points = new Point[n];
		
		for(int i = 0; i < n; i++){
			points[i] = randPoint();
		}
		return points;
	}
	
	
}
