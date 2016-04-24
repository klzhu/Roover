This program navigates a robotic hoover through a room and cleans up dirt patches 
if the hoover is plcaed on the patch.

In order to run, open a terminal window in the project folder and enter the command:
java -jar periscopechallenge.jar input.txt

input.txt is not hard coded into the program, so you can change the argument you pass in.

Decision Choices:
I originally coded this project by using an actual game board. However, because we only need 
to make sure the roover is moving within the board and track the roover position and how many
dirt patches it has cleaned up, I decided to forgo the board and just use a hashset to store 
where the dirt patches are. The hashset stores the XY coordinate of the dirt patch as a string,
and if the roover position matches that XY coordinate, I remove the dirt patch from the hashset.

This theoretically saves reduces the space complexity of the program. However, in the case where
there is a dirt patch on every spot of the game board, the space complexity will still be O(NM)
where N is the number of columns and M is the number of rows.

The code is broken up into 3 parts:
ChallengeDriver.java is the main program that takes in the file name and creates an instance of the
Roover class.

Roover.java is where all the logic for navigating the roover and tracking the roover position 
and how many dirt patches has been cleaned up happens.

RooverTest.java contains unit tests for various input files I tested.