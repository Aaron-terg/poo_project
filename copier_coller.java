
//								Iterator<Planet> planetIt = universe.getPlanets().iterator();
//								Planet planetDest = spacefleet.getDestination();
//								if(planetDest == null)
//									break;
//								while(planetIt.hasNext()) {
//									
//									Planet planet = planetIt.next();
//									
//									if(planet.getPlanetShape().intersects(spaceshipType.getSpaceshipShape())) {
//										
//										if(planet.equals(planetDest)) {
//											planet.spaceShipEnter(spaceshipType);
//											iterator.remove();
//											break;
//										}
//										else {
//											//spaceshipType.get_around(planet);
//											spaceshipType.goTo(planetDest);
//											spaceshipType.render(gc);
//										}
//									}else {
//										spaceshipType.goTo(planetDest);
//										spaceshipType.render(gc);
//									}
//								}


//								double length = Math.abs(spaceShip.getSpaceshipShape().getY()[0]-spaceShip.getSpaceshipShape().getY()[1]);
//								if(!(obstacle.getIs_destination()) &&!(player.getTerritory().contains(obstacle))&&
//									(Math.abs(obstacle.getPlanetShape().distPoint(spaceShip.getSpaceshipShape().getX()[j], spaceShip.getSpaceshipShape().getY()[i]))<=obstacle.getPlanetShape().getRadius()+length+20)){
//									//spaceShip.get_around(obstacle);
//								}
public void goTo(Planet destination) {
		double dist_x = this.getX()-destination.getX();
		double dist_y = this.getY()-destination.getY();
		double dist = Math.sqrt((dist_x*dist_x)+(dist_y*dist_y));
		double new_pos_x = dist_x/dist;
		double new_pos_y = dist_y /dist;
		this.newPosition(new_pos_x, new_pos_y);
	}