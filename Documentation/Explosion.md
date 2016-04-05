# Class:

```java
public class Explosion
```

* This class is responsible for represent the explosions, which happen while the game is played.

## Methods:

```java
public Explosion(double x, double y, double z, double size)
```

* This is a constructor method. X, y and z are coordinates for the explosion and the size is the diameter of explosion.

```java
public void uptade()
```

* This method is responsible for updating the explosion in the scenario, according to the actions in the game.

```java
public void draw()
```

* This method is responsible for draw the explosion on the scenario.

```java
public rectangle2D getBoundary2D()
```

* This method is responsible for return the limits of the explosion.

## Declarations:

```java
public int frameIndex
```
* The square indicate the index.

```java
public int auraIndex
```
* Defines the size of the aura.

```java
public int damage
```
* Amount of damage he can tear the opponent.

```java
public int type
```
* Type of explosion 0 = normal and 1 = plasma.

```java
public int groundZero
```
* Indicates the starting point of explosion.

```java
public boolean explicitDrawing
```
* Enable this boolean if this explosion has be to drawn explicitly

```java
double size
```
* Size of the explosion.

```java
public polygon3D explosionAura
```
* Define the aura explosion
