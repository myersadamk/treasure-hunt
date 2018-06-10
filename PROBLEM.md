Thank you very much for your interest in our open Java developer position.  In
order to evaluate your programming skills we would like you to write a small
program.

Please demonstrate your Java coding abilities by completing the problem laid
out below. This challenge requires some form of input.  You are free to
implement any mechanism for feeding data into your solution; for example, using
hard coded sample data within a unit test or loading from a text file.

You should provide sufficient evidence that your solution is complete by, at a
minimum, demonstrating that it works correctly against the supplied example
data.

Your solution will be evaluated based on its clarity, effectiveness,
completeness, and style. Instructions on compilation and execution of your code
will be greatly appreciated.  You are welcome to use any version of Java that
you prefer.  Please complete the problem and send it back to me within 48 hours
of your requested start time.  If you have any questions or concerns please
contact me via e-mail at xxx@omitted.com.

*Good luck!*

## Problem  - Find the treasure fast!
A treasure map was uncovered, but the map only shows the starting location. The
path from the origin to the treasure is given as series of steps.

"Walk north for 2 hours, then ride a horse at trot speed heading east for
15 minutes..."

Multiple teams of treasure hunters have departed to follow the map's
instructions.

Your team decided, based on the fact that there are more than 1000 instructions,
that you are better off parsing the map with a computer and then just following
a straight line towards the general area of the treasure.

You made some generalizations on the speed of the modes of transportation and
came up with the following approximations:
  Walk          - 3 mph
  Run           - 6 mph
  Horse trot    - 4 mph
  Horse gallop  - 15 mph
  Elephant ride - 6 mph

Another member on the team has already written a program to translate the map
into input which is ready for your program.

Write a program to parse the following input to determine the location of the
final destination.

## Example input:
Walk, 20 mins, N
Run, 1 hour, E
Horse trot, 3 hours, NW
Elephant ride, 1 hour 30 mins, SE
Horse gallop, 20 mins, SE
Walk, 30 mins, SW
Horse trot, 1 hour 1 min, W
...

## Example output:
2029.1 miles to the north, 298893.0 miles to the east
