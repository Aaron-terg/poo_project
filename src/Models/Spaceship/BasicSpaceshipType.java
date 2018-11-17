package Models.Spaceship;

public class BasicSpaceshipType implements SpaceshipType {
	private int speed;
	private int productionTime;
	
	public BasicSpaceshipType() {
		this.speed = 100;
		this.productionTime = 2;
	}
	
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public int getProductionTime() {
		// TODO Auto-generated method stub
		return this.productionTime;
	}
}
