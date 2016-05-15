# Class:

```java
/** This class is responsible for represent the explosions, which happen while the game is played.*/

public class Explosion
```

## Methods:

```java
/** This is a constructor method. X, y and z are coordinates for the explosion and the size is the diameter of explosion.
* @param double - x
* @param double - y
* @param double - z
* @param double - size
*/

public Explosion(double x, double y, double z, double size)
```

```java
/** This method is responsible for updating the explosion in the scenario, according to the actions in the game.*/

public void uptade()
```

```java
/** This method is responsible for draw the explosion on the scenario.*/

public void draw()
```

```java
/* This variable holds the proportion of explosion*/

Declarations in the draw():
double ratio;
```


```java
/** This method is responsible for return the limits of the explosion.*/

public rectangle2D getBoundary2D()
```

## Declarations:

```java
/** The square indicate the index.*/

public int frameIndex
```

```java
/** Defines the size of the aura.*/

public int auraIndex
```

```java
/** Amount of damage he can tear the opponent.*/

public int damage
```

```java
/** Type of explosion 0 = normal and 1 = plasma.*/

public int type
```

```java
/** Indicates the starting point of explosion.*/

public int groundZero
```

```java
/** Enable this boolean if this explosion has be to drawn explicitly*/

public boolean explicitDrawing
```

```java
/** Size of the explosion.*/

double size
```

```java
/** Define the aura explosion*/

public polygon3D explosionAura
```
