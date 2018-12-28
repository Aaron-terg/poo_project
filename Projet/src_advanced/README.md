#Projet POO



Welcome on the game 

Instruction manual
--

##Rules 


- The objective of this game is to conquer the universe. You will start with one planet picked randomly between a set of planet. this planet, your planet, will be colored in red and start producing spaceships.

- The number written on the planet is the number of ships you've got. This number will be incremented every second while you possess the planet. 

- There are other planets around you, your mission, conquer them! Send your ships on them to destroy their base and possess it.

- Stay up to date and manage your troop during the battle thanks to the user interface
  * The first line written on the left top is the percentage of ships to send from your planet.
  * The second one is the number of players still active in the game.
  * The last one is the number of fleet flying through space. 

- Your objective is to send your ships on the other planets and more especially on the other player's planet. You gain a planet when you destroy all the ships on it.

- If one of your planet is in danger, think about recharging them with ship by sharing the ships of one of your other planets.

- When an enemy  ship enter in your planet, this planet will lose as many spaceship as the attack  power of the spaceship. Despite the fact an ally spaceship only increments by one the reserve.

- You win when there is no more player in the game to stand before you.


##Controls

Before getting to the  front, here is some useful informations to control your fleets.

###Troop movement

When you want to send flying some spaceship use the percentage at the top left of the screen with the key E or A (A will increase the number of ships to send while E will decrease it).
  
    Press The A key or the E key to increase or decrease of 5% respectively


The troop movement is done in three step:

 1. Select your planet or spacefleet with a LEFT click of your mouse. The selected planet will have bigger outline.
    - The spacefleet can be selected easily with a box create while drag and release the mouse
 2. Choose your percentage (you can do switch order with the first one)
 3. Select the planet you want to send your fleet to. There is two way to do so:
    -  attack or recharge with a drag and release.
    - if the planet is yours, you can press the CTRL key while LEFT clicking on the planet.

 /!\   The drag'n release start from the departure planet to the arriving one. 

###Mouse features
####In-game features

LEFT Mouse pressing
--
three cases:
 1. The click is perform over one of your planet: select this planet, highlight it and create a new line with the center of the planet as origin. 
 2. The click is perform over one your spaceship: select the spacefleet it belong to, highlight it and create a new line with the selected spaceship position as origin.
 3. The click is perform everywhere else: create a selection box to make easier the selection of a spacefleet.
 
 
RIGHT Mouse click
--
 - if the CTRL key is pressed it display information of the planet (debugging purposes: only in console)
 - 



###Keyboard features

####In-game features

A/E key: increase/decrease the percentage by 5
--

Digit key: change spaceship type of a planet
--
pressing 1, 2 or 3 While selecting one of your planet will change the spaceship type it produce.
 1. Basic spaceship: no special stats.
 2. Strong spaceship: really slow ship but compense with a huge attack power. 
 3. Fast spaceship: can get a planet at the other side of the universe in no time but have weak attack power.
 
--- 

####General features
CTRL + S: Save your game
--
 You can save your game by pushing the "S" key while holding the CTRL key.

CTRL + P: Load your previously saved game
--
 You can load your game by pushing the p key while holding the CTRL key.

P or ESCAPE: become a time master
--
 You can pause the game by pressing the P key to rethink your strategies and ESCAPE key to resume the game.

CTRL + Q: Rage quit
--
 quit the game by pushing the Q key plus the CTRL key... I know you want to.
 
CTRL + R: Start a new game
--
 start a new game while playing.
 
##The user interface

There is four differents splashscreens for the user to perform specific action independant of the game.

####Main menu

when you start the applications you come across the main menu. You can either choose to start a new game or load a previous save game. If you choose the loading option, the game will start automatically where you saved.

####Setting menu
if you choose the new game option you will be redirected to the setting menu. 
 - adding / removing player: click on the plus square for adding a new player or click on the colored square to remove one player.
 
    /!\ the game will not start if there are less planets than player or less than 2 players. there can be up to 4 player.
 
 - adding / removing planets: click on the plus button to add planet or the minus button to remove one.
 
     /!\ for some reasons, in the advanced version when there is more than 10 planets the program can crash once in a while.
     
 - Uncheck the human player checkbox if you want to start a game with only AI.
 
 Press start to launch the game when you have done.
 
 
####contextual menu
 
During the game you can set the game in pause mode (P key). During that time, you will have an UI displaying three options:
 1. Resume: resume the game where you paused it <=> CTRL + ESCAPE.
 2. Restart: Return to the setting menu != CTRL + R : return to the main menu
 3. Save: Save the current state of the game and stored it in a file. <=> CTRL + S

	 /!\ your previous data will be lost.
 
The last UI appear at the end of the game if there is only one player left. Here you can start a new game or quit the applications.




