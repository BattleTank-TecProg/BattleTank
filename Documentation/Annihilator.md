# Class:
```java
/**Class to create objects Anniquilator type, which are contained values ​​and methods for this purpose
*/

public class Annihilator

```

## Methods:

```java
/**This is a constructor method. X, y and z are coordinates for construction of the annihilator in the space.
* @param double - x
* @param double - y
* @param double - y
* @param int - angle
*/
public Annihilator(double x, double y, double z, int angle);
```


```java
/**This method is responsible to create the polygons of the body of annihilator.*/
private void makeBody();
```


```java
/**This method is responsible to create polygons for the tank turret.*/
private void makeTurret()
```


```java
/**This method is responsible for updating the annihilator in the scenario, according to the actions in the game.*/
public void update()
```

```java
/**This method is responsible for drawing the body annihilator.*/
public void draw()
```

```java
/**This method is responsible for specifying the loss of life of annihilator.
* @param int - damagePoint
*/
public void damage(int damagePoint)
```


```java
/**This method is responsible for return the 2D boundary of the annihilator.
* @return Rectangle2D - boundary2D
*/
public Rectangle2D getBoundary2D();
```

## Declarations:

```java
/**Total angle that the body has rotated from the initial position. (in the x-z plane).*/

private int bodyAngle
```

```java
/**Total angle that the turret has rotated from the initial position. (in the x-z plane).*/

private int turretAngle
```

```java
/**Degrees the tank body has rotated in a frame.*/

private int bodyAngleDelta
```

```java
/**Degrees the tank body has rotated in a frame.*/

private int turretAngleDelta
```

```java
/**The position index of the tank in the grid map.*/

private int position
```

```java
/**Position that you want to get arround.*/

private int desiredPosition
```

```java
/**Time needed before a weapon can be fired again.*/

private int coolDownShell
```

```java
/**Time needed before a weapon can be fired again.*/

private int coolDownRocket
```

```java
/**Angle between player tank and turret centre.*/

private int targetAngle
```

```java
/**Angle between a target location and body centre.*/

private int targetAngleBody
```

```java
/**TargetAngleBody of the previous frame.*/

private int previousTargetAngleBody
```

```java
/**A count down for death after hp = 0.*/

private int countDownToDeath
```

```java
/**Represent the time that medium tank has been in stuck status.*/

private int stuckCount
```

```java
/**Distance from player tank.*/

private double distance
```

```java
/**Go foward.*/

private boolean forward
```

```java
/**The aim of the Tank moves to the right.*/

private boolean aimRight
```

```java
/**The aim of the Tank moves to the left.*/

private boolean aimLeft
```

```java
/** Shoots shell.*/

private boolean firingShell
```

```java
/**Shoots Rocket.*/

private boolean firingRocket
```

```java
/**It indicates whether the previous square is visible.*/

private boolean isVisiblePreviousFrame
```

```java
/**This active there is an enemy activation artifice.*/

public boolean active
```

```java
/**It indicates whether this clear to shot.*/

private boolean clearToShoot
```

```java
/**The centre of the body in camera coordinate.*/

private Vector bodyCenter
```

```java
/**Polygons for tank turret.*/

private polygon3D turret [ ]
```

```java
/**The shadow of tank body.*/

private polygon3D shadowBody
```

```java
/**The shadow of tank turret.*/

private polygon3D shadowTurret
```

```java
/**The centre of the turret (pivot point for rotation).*/

private Vector turretCenter
```

```java
/**Polygons for tank body.*/

private polygon3D body [ ]
```

```java
/** smoke tail will be visible if the tank's health is dropped to half.*/

private Smoke Smoke
```
