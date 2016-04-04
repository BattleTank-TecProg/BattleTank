# Class:

```java
 public class Annihilator
```
* This class gathers all the features of the annihilator enemy.

## Methods:

```java
public Annihilator(double x, double y, double z, int angle);
```
* This is a builder method. X, y and z are coordinates for construction of the annihilator in the space.

```java
private void makeBody();
```
* This method is responsible to create the polygons of the body of annihilator.

```java
private void makeTurret()
```

* This method is responsible to create polygons for the tank turret.

```java
public void update()
```

* This method is responsible for updating the annihilator in the scenario, according to the actions in the game.

```java
public void draw()
```
* This method is responsible for drawing the body annihilator.

```java
public void damage(int damagePoint)
```

* This method is responsible for specifying the loss of life of annihilator.

```java
public Rectangle2D getBoundary2D();
```

* This method is responsible for return the 2D boundary of the annihilator.

## Variables:
