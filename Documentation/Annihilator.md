# Class:
```java
/**Class to create objects Anniquilator type, which are contained values ​​and methods for this purpose
*/

public class Annihilator

```

## Methods:

```java
public Annihilator(double x, double y, double z, int angle);
```

```java
/**This is a constructor method. X, y and z are coordinates for construction of the annihilator in the space.
* @param double - x
* @param double - y
* @param double - y
* @param int - angle
*/
```

```java
private void makeBody();
```

```java
/**This method is responsible to create the polygons of the body of annihilator.*/
```

```java
private void makeTurret()
```

```java
/**This method is responsible to create polygons for the tank turret.*/
```

```java
public void update()
```

```java
/**This method is responsible for updating the annihilator in the scenario, according to the actions in the game.*/
```

```java
public void draw()
```

```java
/**This method is responsible for drawing the body annihilator.*/
```

```java
public void damage(int damagePoint)
```

```java
/**This method is responsible for specifying the loss of life of annihilator.
* @param int - damagePoint
*/
```

```java
public Rectangle2D getBoundary2D();
```

```java
/**This method is responsible for return the 2D boundary of the annihilator.
* @return Rectangle2D - boundary2D
*/
```

## Declarations:

```java
private int bodyAngle
```

```java
/**Total angle that the body has rotated from the initial position. (in the x-z plane).*/
```

```java
private int turretAngle
```

```java
/**Total angle that the turret has rotated from the initial position. (in the x-z plane).*/
```

```java
private int bodyAngleDelta
```

```java
/**Degrees the tank body has rotated in a frame.*/
```

```java
private int turretAngleDelta
```
```java
/**Degrees the tank body has rotated in a frame.*/
```

```java
private int position
```

```java
/**The position index of the tank in the grid map.*/
```

```java
private int desiredPosition
```

```java
/**Position that you want to get arround.*/
```

```java
private int coolDownShell
```

```java
/**Time needed before a weapon can be fired again.*/
```

```java
private int coolDownRocket
```

```java
/**Time needed before a weapon can be fired again.*/
```

```java
private int targetAngle
```

```java
/**Angle between player tank and turret centre.*/
```

```java
private int targetAngleBody
```

```java
/**Angle between a target location and body centre.*/
```

```java
private int previousTargetAngleBody
```

```java
/**TargetAngleBody of the previous frame.*/
```

```java
private int countDownToDeath
```

```java
/**A count down for death after hp = 0.*/
```

```java
private int stuckCount
```

```java
/**Represent the time that medium tank has been in stuck status.*/
```

```java
private double distance
```

```java
/**Distance from player tank.*/
```

```java
private boolean forward
```

```java
/**Go foward.*/
```

```java
private boolean aimRight
```

```java
/**The aim of the Tank moves to the right.*/
```

```java
private boolean aimLeft
```

```java
/**The aim of the Tank moves to the left.*/
```

```java
private boolean firingShell
```

```java
/** Shoots shell.*/
```

```java
private boolean firingRocket
```

```java
/**Shoots Rocket.*/
```

```java
private boolean isVisiblePreviousFrame
```

```java
/**It indicates whether the previous square is visible.*/
```

```java
public boolean active
```

```java
/**This active there is an enemy activation artifice.*/
```

```java
private boolean clearToShoot
```

```java
/**It indicates whether this clear to shot.*/
```

```java
private Vector bodyCenter
```
```java
/**The centre of the body in camera coordinate.*/
```

```java
private polygon3D turret [ ]
```

```java
/**Polygons for tank turret.*/
```

```java
private polygon3D shadowBody
```

```java
/**The shadow of tank body.*/
```

```java
private polygon3D shadowTurret
```

```java
/**The shadow of tank turret.*/
```

```java
private Vector turretCenter
```

```java
/**The centre of the turret (pivot point for rotation).*/
```

```java
private polygon3D body [ ]
```

```java
/**Polygons for tank body.*/
```

```java
private Smoke Smoke
```

```java
/** smoke tail will be visible if the tank's health is dropped to half.*/
```
