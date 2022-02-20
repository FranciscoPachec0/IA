To compile you must execute the command javac *.java and then the command java Use. If you want to use the tests from the tests directory just
use the command java Use < testN.txt where the N is replaced by the test number (you may have to copy the tests to the src folder). 

This version of the program has a few extra options throughout, not only to make the program more 
complete and versatile program, but also for the purpose of performing some tests that were necessary during its grant and debugging.

In question 1 there are 2 options to randomly create points or directly pass the points that we want to use.

In question 2 there are also 2 options, use the nearest neighbor from the first point inserted/originated or from a randomly
or from a randomly decided point.

In question 3 you find the printout of the lines that intersect.

In question 4 there are 4 options 1 that correspond to each line of question 4:
 1 - Solve by choosing the candidate that reduces the perimeter the most until there are no more intersections
 2 - Always solves the first candidate until there are no more intersections
 3 - Find the candidate that has the fewest intersections and solve that first candidate, repeating the process until there are no more intersections
 4 - Search for a line that has intersections and choose one of the lines that intersects it, randomly, solving it

In question 5 it solves the polygon using Simulated Annealing.

Question 6 has only a very initial part of the implementation.

Translated with www.DeepL.com/Translator (free version)