# Class:

```java
/**A partial system that create the helix of the railgun*/

public class Helix
```

## Methods:

```java
/**Construct the helix of railgun in the game
* @param Vector - centre
* @param int - angle
*/

public Helix(vector centre, int angle)
```

```java
/**Return the 2D boundary of this model*/

public rectangle2D getBoundary2D()
```

```java
/**Update the numbers of helix that as available*/

public void update()
```

```java
/**Draw the helix when was shotted*/

public void draw()
```

## Declarations:

```java
/**This vector keeps the particles .*/

public Vector particles[]
```

```java
/**This vector stores the particle directions.*/

public Vector directions[]
```

```java
/**This vector keeps the colors of the particles.*/

public int colors[]
```

```java
/**Alpha mask*/

public int ALPHA
```

```java
/**Temporary vector which will be used for vector arithmetic.*/

public Vector temp1
```

```java
/**Temporary vector which will be used for vector arithmetic.*/

public Vector temp2
```

```java
/**Static variable to indicate length , and to border.*/

static final double LENGHT
```

```java
/**Static variable to indicate height , and to border.*/

static final double HEIGHT
```

```java
/**Static variable to indicate width , and to border.*/

static final double WIDTH
```
