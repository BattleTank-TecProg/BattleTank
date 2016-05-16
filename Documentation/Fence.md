# Class:

```java
/**This class is responsible for the methods and attributes of energy fence.*/

public class Fence
```

## Methods:

```java
/**This is a constructor method, which receives as parameters the coordinates x, y and z and the orientation of Fence.
* @param double - x
* @param double - y
* @param double - x
* @param int - orientation
*/
public Fence(double x, double y, double z, int orientation)

/**
 * This method return a polygon referring to polygons.
 * @return polygons
 */
public Polygon3D[] getPolygons()

/**
 * This method set a polygon referring to polygons.
 * @param polygons
 */
public void setPolygons(Polygon3D[] polygons)

/**
 * This method return a orientation referring to object.
 * @return orientation
 */
public int getOrientation()

/**
 * This method set a orientation referring to object.
 * @param orientation
 */
public void setOrientation(int orientation)

/**
 * This method return a lenght referring to object.
 * @return LENGHT
 */
public static double getLenght()

/**
 * This method return a height referring to object.
 * @return HEIGHT
 */
public static double getHeight()

/**
 * This method return a width referring to object.
 * @return WIDTH
 */
public static double getWidth()
/**
 * This method return a vertical referring to object.
 * @return VERTICAL
 */
public static int getVertical()

/**
 * This method return a horizontal referring to object.
 * @return HORIZONTAL
 */
public static int getHorizontal()

/**This method is responsibly of Construct polygons for a fence of energy.*/
public void makePolygons()
```

### Declarations of makePolygons

```java
/**These declarations are for construct polygons for this model.*/
vector v[]
polygons = new polygon3D[2]
```

```java
/**This method is responsible for return the 2D boundary of the fence.
* @return boundary2D
*/

public rectangle2D getBoundary2D()

/**
 * This method is responsible for updating the fence in the scenario,
 * according to the actions in the game.
 */
public void update()

/**This method is responsible to eliminate the fence of the scenery.*/

public void destroy()

/**This method is responsible for drawing the fence.*/

public void draw()
```


## Declarations:
```java
/** Returns the block health length value. */
private static final double LENGHT

/** This Constant represents the block health height value. */
private static final double HEIGHT

/** This Constant represents the health block width value. */
private static final double WIDTH

/** This Constant represents the fence orientation vertical */
private static final int VERTICAL

/** This Constant represents the fence orientation horizontal */
private static final int HORIZONTAL

/** The polygons of the model. */
private Polygon3D polygons[]

/** The fence orientation */
private int orientation
```
