# Class:

```java
public class Fence
```

* This class is responsible for the methods and attributes of energy fence.

## Methods:

```java
public Fence(double x, double y, double z, int orientation)
```

* This is a constructor method, which receives as parameters the coordinates x, y and z and the orientation of Fence.

```java
public void makePolygons()
```

* This class is responsibly of Construct polygons for a fence of energy.

```java
public rectangle2D getBoundary2D()
```

* This method is responsible for return the 2D boundary of the fence.

```java
public void update()
```

* This method is responsible for updating the fence in the scenario, according to the actions in the game.

```java
public void destory()
```

* This method is responsible to eliminate the fence of the scenery.

```java
public void draw()
```
* This method is responsible for drawing the fence.

## Declaretions:
```java
static final double LENGHT
```
* Returns the health block lenght value.

```java
static final double HEIGHT
```
* This Constant represents the health block height value.

```java
static final double WIDTH
```
* This Constant represents the health block width value.

```java
private Polygon3D polygons[ ].
```
* The polygons of the model.

```java
public int orientation
```
* The orientation is 0 = vertical   1 = horizontal.
