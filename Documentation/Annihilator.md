# Class:

```java
 public class Annihilator
```
* This class gathers all the features of the annihilator enemy.

## Methods:

```java
public Annihilator(double x, double y, double z, int angle);
```
* This is a constructor method. X, y and z are coordinates for construction of the annihilator in the space.

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

## Declarations:

```java
private int bodyAngle
```
* Total angle that the body has rotated from the initial position. (in the x-z plane).

```java
private int turretAngle
```
* Total angle that the turret has rotated from the initial position. (in the x-z plane).

```java
private int bodyAngleDelta
```
* Degrees the tank body has rotated in a frame.

```java
private int turretAngleDelta
```
* Degrees the tank body has rotated in a frame.

```java
private int position
```
* The position index of the tank in the grid map.

```java
private int desiredPosition
```
* Position that you want to get arround.

```java
private int coolDownShell
```
* Time needed before a weapon can be fired again.

```java
private int coolDownRocket
```
* Time needed before a weapon can be fired again.

```java
private int targetAngle
```
* Angle between player tank and turret centre.

```java
private int targetAngleBody
```
* Angle between a target location and body centre.

```java
private int previousTargetAngleBody
```
* TargetAngleBody of the previous frame.

```java
private int countDownToDeath
```
* A count down for death after hp = 0.

```java
private int stuckCount
```
* Represent the time that medium tank has been in stuck status.

```java
private double distance
```
* Distance from player tank.

```java
private boolean forward
```
* Go foward.

```java
private boolean aimRight
```
* The aim of the Tank moves to the right.

```java
private boolean aimLeft
```
* The aim of the Tank moves to the left.

```java
private boolean firingShell
```
* Shoots shell.

```java
private boolean firingRocket
```
* Shoots Rocket.

```java
private boolean isVisiblePreviousFrame
```
* It indicates whether the previous square is visible.

```java
public boolean active
```
* This active there is an enemy activation artifice.

```java
private boolean clearToShoot
```
* It indicates whether this clear to shot.

```java
private vector bodyCenter
```
* The centre of the body in camera coordinate.

```java
private polygon3D turret [ ]
```
* Polygons for tank turret.

```java
private polygon3D shadowBody
```
* The shadow of tank body.

```java
private polygon3D shadowTurret
```
* The shadow of tank turret.

```java
private vector turretCenter
```
* The centre of the turret (pivot point for rotation).

```java
private polygon3D body [ ]
```
* Polygons for tank body.

```java
private smoke Smoke
```
* A smoke tail will be visible if the tank's health is dropped to half.
