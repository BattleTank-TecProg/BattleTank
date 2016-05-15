# Class:

```java
/**This class gathers all the features of enemies turrets.*/

public class GunTurret
```

## Methods:

```java
/**This is a constructor method, which is responsible for initializing the turret and has as parameters the coordinates and the angle of the turret.
* @param double - x
* @param double - y
* @param double - z
* @param int - angle
*/

public GunTurret(double x, double y, double z, int angle)
```  
```java
/**These declaration define the centre point of this model(also the centre point of tank body)*/

Declarations of GunTurret():
start = new vector(x,y,z);
iDirection = new vector(0.7,0,0);
jDirection = new vector(0,0.7,0);
kDirection = new vector(0,0,0.7);
```

```java
/**This method is responsible for create polygons for gun turret lower part.*/

public void makeBody()
```
```java
/**These Declarations Construct polygons for this model.*/

Declarations of makeBody():
vector v[]
body = new polygon3D[5]
```

```java
/**This method is responsible for create polygons for gun turret upper part.*/

private void makeTurret()
```

```java
/**This method is responsible for upgrading the turret in the game scenario according to the actions in the game.*/

public void uptade()
```

```java
/**This method has the function to calculate the distance between the tanks of the player and enemies , and also invoke enemy tanks next.*/

private void processAI()
```

```java
/**This method is responsible for draw the body of turret , and draw the smoke when attacked.*/

public void draw()
```

```java
/**This method is responsible for return the limits of the explosion.*/

public rectangle2D getBoundary2D()
```

```java
/**This method intended to withdraw when the turret attacked life points, and its input parameter the value of the damage.
* @param int - damagePoint
*/

public void damage(int damagePoint)
```

## Declarations:

```java
/**Polygons for gunTurret body.*/

private polygon3D body [ ]
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
/**The body focuses in the coordinate of the camera.*/

private Vector bodyCenter
```

```java  
/**The turret center (pivot point for rotation).*/

private Vector turretCenter
```

```java
/**Total angle that the turret has rotated from the initial position. (in the x-z plane).*/

private int turretAngle
```

```java
/**The target angle with respect to body center.*/

private int targetAngle
```

```java
/**Degrees the  gun turret has rotated in a frame.*/

private int turretAngleDelta
```

```java
/**GunTurret moves to the Right.*/

private boolean aimRight
```

```java
/**GunTurret moves to the left.*/

private boolean aimLeft
```

```java
/**GunTurret shoot.*/

private boolean firing
```

```java
/**Time needed before a weapon can be fired again.*/

private int coolDown
```

```java
/**Current coolDown.*/

private int currentCoolDown
```

```java
/**The position index of the turret  in the grid map.*/

private int position
```

```java
/**A smoke tail.*/

private Smoke Smoke
```

```java
/**Whether the gun turret is visible in the previous frame.*/

private boolean isVisiblePreviousFrame
```

```java
/**Temporary vector which will be used for vector arithmetic.*/

private Vector tempVector1
```

```java
/**Temporary vectors which will be used for vector arithmetic.*/

private Vector tempVector2
```

```java
/**An AI flag  indicates whether it has engaged with player tank*/

private boolean engaged
```

```java
/**Distance from player tank.*/

private double distance
```

```java
/**An AI flag indicates whether there is a type 2 obstacle between gunTurret and player tank.*/

private boolean clearToShoot
```
