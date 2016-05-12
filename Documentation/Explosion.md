# Class:

```java
public class Explosion
```
```java
/** This class is responsible for represent the explosions, which happen while the game is played.*/
```

## Methods:

```java
public Explosion(double x, double y, double z, double size)
```

```java
/** This is a constructor method. X, y and z are coordinates for the explosion and the size is the diameter of explosion.*/
```

```java
public void uptade()
```

```java
/** This method is responsible for updating the explosion in the scenario, according to the actions in the game.*/
```

```java
public void draw()
```

```java
/** This method is responsible for draw the explosion on the scenario.*/
```

##### Declarations in the draw():
```java
double ratio;
```

```java
/* This variable holds the proportion of explosion*/
```


```java
public rectangle2D getBoundary2D()
```

```java
/** This method is responsible for return the limits of the explosion.*/
```
## Declarations:

```java
public int frameIndex
```
```java
```
/** The square indicate the index.*/

```java
public int auraIndex
```
```java
/** Defines the size of the aura.*/
```

```java
public int damage
```
```java
/** Amount of damage he can tear the opponent.*/
```

```java
public int type
```
```java
/** Type of explosion 0 = normal and 1 = plasma.*/
```

```java
public int groundZero
```
```java
/** Indicates the starting point of explosion.*/
```

```java
public boolean explicitDrawing
```
```java
/** Enable this boolean if this explosion has be to drawn explicitly*/
```

```java
double size
```

```java
/** Size of the explosion.*/
```

```java
public polygon3D explosionAura
```
```java
/** Define the aura explosion*/
```
