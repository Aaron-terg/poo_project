package Models;

import java.io.Serializable;

public class GameObject implements Serializable{
	protected double x, y;
	protected double height, width;
	public static GameObject selected = null;
	
	public GameObject() {
		this.x = 0;
		this.y = 0;
		this.height = 0;
		this.width = 0;
	}
	
	public GameObject(double x, double y, double height, double width) {
		this.x = x - width/2;
		this.y = y - height/2;
		this.height = height;
		this.width = width;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void isSelected() {
		selected = this;
	}
	
	public boolean isInside(double x, double y) {
		
		return x <= (this.x + this.width) && x >= (this.x - this.width)
				 && y >= (this.y - this.height) && y <= (this.y + this.height);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x :" + x + ", y: "+ y
				+ ", height: "+ height +", width: " + width +"\n"
				+ ", object selected :" + selected + "\n";
	}
}
