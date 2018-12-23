package models;

import controllers.UserInput;

/**
 * <b>An enumeration of the different game state </b>
 * <ul>
 * 		<li>RUNNING: render and update the game</li>
 * 		<li>PAUSED: pause the rendering/updating and display the pause panel</li>
 * 		<li>STARTED / LOADED: initialize and start the game. You can load a saved play with CTRL + P</li>
 * 		<li>SAVED: save the current state, frame of the game. You can save your game with CTRL + S </li>
 * 		<li>ENDED: display the end panel when their is only one player left </li>
 * </ul>
 *
 * @see UserInput#keyPressed()
 * @see Game
 * @author meryl, Virginie
 * @since src_basic
 *
 */
public enum GameState {
	INIT,
	RUNNING,
	PAUSED,
	STARTED,
	ENDED,
	SAVED,
	LOADED
}
