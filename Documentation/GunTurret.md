# Class:

```java
public class GunTurret
```

* This class gathers all the features of enemies turrets.

## Methods:

```java
public GunTurret(double x, double y, double z, int angle)
```  

* This is a constructor method, which is responsible for initializing the turret and has as parameters the coordinates and the angle of the turret.

```java
public void makeBody()
```

* This method is responsible for create polygons for gun turret lower part.

```java
private void makeTurret()
```

* This method is responsible for create polygons for gun turret upper part.

```java
public void uptade()
```

* This method is responsible for upgrading the turret in the game scenario according to the actions in the game.

```java
private void processAI()
```

* This method has the function to calculate the distance between the tanks of the player and enemies , and also invoke enemy tanks next.

```java
public void draw()
```

* This method is responsible for draw the body of turret , and draw the smoke when attacked.

```java
public rectangle2D getBoundary2D()
```

* This method is responsible for return the limits of the explosion.

```java
public void damage(int damagePoint)
```

* This method intended to withdraw when the turret attacked life points, and its input parameter the value of the damage.
## Variables:
