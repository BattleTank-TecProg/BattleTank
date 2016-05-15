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
```

```java
/**This method is responsibly of Construct polygons for a fence of energy.*/

public void makePolygons()
```

```java
/**These declarations are for construct polygons for this model.*/

Declarations of makePolygons():
vector v[]
polygons = new polygon3D[2]
```

```java
/**This method is responsible for return the 2D boundary of the fence.
*
* @return boundary2D
*/

public rectangle2D getBoundary2D()
```

```java
/**This method is responsible for updating the fence in the scenario, according to the actions in the game.*/

public void update()
```

```java
/**This method is responsible to eliminate the fence of the scenery.*/

public void destroy()
```


```java
/**This method is responsible for drawing the fence.*/

public void draw()
```


## Declarations:

```java
/**Returns the block health length value.*/

static final double LENGTH
```

```java
/**This Constant represents the block health height value.*/

static final double HEIGHT
```

```java
/**This Constant represents the health block width value.*/

static final double WIDTH
```

```java
/**This Constant represents the fence orientation horizontal*/

static final int HORIZONTAL = 1;
```

```java
/**The polygons of the model.*/

private Polygon3D polygons[];
```

```java
/**The polygons of the model.*/

private Polygon3D polygons[ ].
```

```java
/**The fence orientation*/

public int orientation;
```
