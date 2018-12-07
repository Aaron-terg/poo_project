package Models;

public class GameObject {
	protected double x, y;
	protected double height, width;
	
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

	public boolean isInside(double x, double y) {
		
		return x <= (this.x + this.width) && x >= (this.x - this.width)
				 && y >= (this.y - this.height) && y <= (this.y + this.height);
	}
}