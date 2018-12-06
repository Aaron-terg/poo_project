package Models.Spaceship;

import Models.Player;

public class BasicSpaceshipType extends SpaceshipType {
	
	public BasicSpaceshipType() {
		super();
		this.speed = 2;
		//this.spaceshipShape = new Polygon();
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
