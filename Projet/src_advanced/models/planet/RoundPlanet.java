package models.planet;

import java.io.Serializable;

import models.GameObject;
import models.shape.Circle;
import models.spaceship.BasicSpaceshipType;

/**
 * Round planet. The basic planet with basic spaceship type.
 * 
 * inherits from Planet

 * @author meryl
 *
 *@see GameObject
 *@see Planet
 * @see BasicSpaceshipType
 * @see Circle
 */
public class RoundPlanet extends Planet implements Serializable{

	/**
	 * The round planet constructor calling its parent constructor then setting planet shape.
	 * 
	 * @see Planet#planetGenerator()
	 * @see Planet#Planet(models.spaceship.SpaceshipType)
	 */
	public RoundPlanet() {
		super(new BasicSpaceshipType());
		this.planetShape = new Circle(this.getX(), this.getY(), this.width);
		this.circonstrictRadius = this.width /2;
	}
	
}
