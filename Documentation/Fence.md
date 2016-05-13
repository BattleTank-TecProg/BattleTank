# Class:

```java
/**This class is responsible for the methods and attributes of energy fence.*/

public class Fence
```

## Methods:

```java
public Fence(double x, double y, double z, int orientation)
```
```java
/**This is a constructor method, which receives as parameters the coordinates x, y and z and the orientation of Fence.
* @param double - x
* @param double - y
* @param double - x
* @param int - orientation
*/
```

```java
public void makePolygons()
```
```java
/**This class is responsibly of Construct polygons for a fence of energy.*/

Declarations of makePolygons():
vector v[]
polygons = new polygon3D[2]
```
```java
/**These declarations are for construct polygons for this model.*/
```

```java
public rectangle2D getBoundary2D()
```
```java
/**This method is responsible for return the 2D boundary of the fence.*/
```

```java
public void update()
```
```java
/**This method is responsible for updating the fence in the scenario, according to the actions in the game.*/
```

```java
public void destory()
```
```java
/**This method is responsible to eliminate the fence of the scenery.*/
```

```java
public void draw()
```
```java
/**This method is responsible for drawing the fence.*/
```

## Declarations:

```java
static final double LENGTH
```
```java
/**Returns the block health length value.*/
```

```java
static final double HEIGHT
```
```java
/**This Constant represents the block health height value.*/
```

```java
static final double WIDTH
```
```java
/**This Constant represents the health block width value.*/
```

```java
private Polygon3D polygons[ ].
```
```java
/**The polygons of the model.*/
```

```java
public int orientation
```
```java
/**The orientation is 0 = vertical   1 = horizontal.*/
```
