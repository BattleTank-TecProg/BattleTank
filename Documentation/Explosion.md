# Class:

```java
/** This class is responsible for represent the explosions, which happen while the game is played.*/

public class Explosion
```

## Methods:

```java

/**
 * This method is responsible for updating the explosion in the scenario,
 * according to the actions in the game.
 */
public void update()

/**
 * This method is responsible for return the limits of the explosion.
 * @return boundary2D
 */
public Rectangle2D getBoundary2D()

/**
 * This method return the size.
 * @return size
 */
public double getSize()

/**
 * This method set the size.
 * @param size
 */
public void setSize(double size)

/**
 * This method return the index of sprite.
 * @return spriteIndex
 */
public int getSpriteIndex()

/**
 * This method set the index of sprite.
 * @param spriteIndex
 */
public void setSpriteIndex(int spriteIndex)

/**
 * This method return the index of frame.
 * @return frameIndex
 */
public int getFrameIndex()

/**
 * This method set the index of frame.
 * @param frameIndex
 */
public void setFrameIndex(int frameIndex)

/**
 * This method return the index of frame.
 * @return auraIndex
 */
public int getAuraIndex()

/**
 * This method set the index of frame.
 * @param auraIndex
 */
public void setAuraIndex(int auraIndex)

/**
 * This method return the index of damege.
 * @return damage
 */
public int getDamage()

/**
 * This method return the index of damege.
 * @param damage
 */
public void setDamage(int damage)

/**
 * This method return the type.
 * @return type
 */
public int getType()

/**
 * This method set the type.
 * @param type
 */
public void setType(int type)

/**
 * This method return the index of ground zero.
 * @return groundZero
 */
public int getGroundZero()

/**
 * This method set the index of ground zero.
 * @param groundZero
 */
public void setGroundZero(int groundZero)

/**
 * This method returns boolean if the design will be explicit or not.
 * @return explicitDrawing
 */
public boolean isExplicitDrawing()


/**
 * This method set boolean if the design will be explicit or not.
 * @param explicitDrawing
 */
public void setExplicitDrawing(boolean explicitDrawing)

/**
 * This method returns a polygon referring to the explosion of the aura.
 * @return explosionAura
 */
public Polygon3D getExplosionAura()

/**
 * This method set a polygon referring to the explosion of the aura.
 * @param explosionAura
 */
public void setExplosionAura(Polygon3D explosionAura)
```

```java
/**
* This method is responsible for draw the explosion on the scenario.
*/
public synchronized void draw()
```

##### Declarations in the draw
```java
/**This variable holds the proportion of explosion*/
double ratio;

/** This is a constructor method. X, y and z are coordinates for the explosion and the size is the diameter of explosion.
* @param double - x
* @param double - y
* @param double - z
* @param double - size
*/

public Explosion(double x, double y, double z, double size)
```
##### Declarations in the Explosion

```java
final double LENGHT

final double HEIGHT

final double WIDTH

final double XPOSITION

final double YPOSITION

final double LENGHTRECTANGLE

final double HEIGHTRECTANGLE

```

## Declarations:

```java
/** Size of the explosion.*/
private double size

/** The square indicate the index.*/
private int frameIndex

/** Defines the size of the aura.*/
private int auraIndex

/** Amount of damage he can tear the opponent.*/
private int damage

/** Type of explosion 0 = normal and 1 = plasma.*/
private int type

/** Indicates the starting point of explosion.*/
private int groundZero

/** Enable this boolean if this explosion has be to drawn explicitly*/
private boolean

/** Define the aura explosion*/
private Polygon3D explosionAura
```
