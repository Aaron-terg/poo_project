package models.planet;

import java.io.Serializable;

import models.shape.Circle;
import models.spaceship.BasicSpaceshipType;

public class RoundPlanet extends Planet implements Serializable{

	public RoundPlanet() {
		super(new BasicSpaceshipType());
		this.planetShape = new Circle(this.getX(), this.getY(), this.width);
		this.circonstrictRadius = this.width /2;
	}
	
}
