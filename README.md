# Tetris Sudoku

Tetris Sudoku
-------------------------

SP20: CMPE-133 Sec 02 -Soft Eng II: Project is to develop Tetris Sudoku, a game.

### Contributors
* Guiller Dalit
* Mashawn Hall, 
* Shruti Panchal
* Kenneth Huang

### Project Overview
* This is a game developed as a java application. Tetrisdoku is a simple game which is a blend of tetris blocks and sudoku logic. Player just needs to place one randomly generated tetris block which has numbers inside the sudoku grid which follows the sudoku logic of not placing two same numbers in either the same row, same column or the subgrid. And the score would be calculated according to the time used by the player to solve the grid.
Compiled in JAVA 11.

### Logistics
How the game has been developed and what all requirements were met during the implementation phase.
* The game has been developed as a java based application in Java 11. 
* The game has one level as of now, which is a 6 by 6 grid.
* For the UI part, user can:
  * View the instructions
  * Play the game
  * View scores
  * Quit
* After the player starts the game:
  * They can place just one tetris at a time
  * Won’t be able to overlap two tetris using drag and drop as their would be a collision check
  * Next block would be randomly generated at the bottom left corner of the screen.
* Score calculation is inversely proportional to the time passed, i.e more the time used by the player to solve the game, lesser the score for that respective player.
* For the view scores’ functionality, the player would be able to see the top five scores from the history and the scores are shown in decreasing order, that is from the highest to the lowest.

The game will follow the MVC design pattern. Further alteration and additional design patterns may be implemented. 

### Launch Sreen
![alt text](https://github.com/guiller-d/tetris-sudoku/blob/master/screenshots/st_image1.png)

### Game Screen
![alt text](https://github.com/guiller-d/tetris-sudoku/blob/master/screenshots/st_image2.png)

### Score Screen
![alt text](https://github.com/guiller-d/tetris-sudoku/blob/master/screenshots/st_image.png)


