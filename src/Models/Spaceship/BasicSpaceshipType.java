package Models.Spaceship;

import Models.Player;
import Models.shape.Polygon;

public class BasicSpaceshipType extends SpaceshipType {
	
	public BasicSpaceshipType() {
		super();
		this.speed = 2;
		//this.spaceshipShape = new Polygon(this.x, this.y);
	}
	
	public BasicSpaceshipType(Player player) {
		this(); 
		this.player = player;
	}
	public BasicSpaceshipType(SpaceshipType spaceship) {
		super(spaceship);
	}
	
	public BasicSpaceshipType(Player player, double posX, double posY) {
		super();
		this.player = player;
		this.spaceshipShape.setPosition(posX, posY);
	}
	




	
	
}
