# Sieve
Visualize on Android how the sieve algorithm finds prime numbers.

20170521
On a timer, the app queries the sieve engine for the next number and its classification, and colors the tile (a type of View) for that number accordingly. There is one issue: if the tile is not in the view it cannot be colored correctly because a look up of the tile for the number in the grid view returns null. In addition, if invisible tiles are at the top, incorrect tiles may be colored. While the second issue can be solved by applying an offset, I have not found an elegant approach to address the first issue.
