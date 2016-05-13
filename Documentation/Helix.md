# Class:

```java
/**A partial system that create the helix of the railgun*/

public class Helix
```

## Methods:

```java
public Helix(vector centre, int angle)
```
```java
/**Construct the helix of railgun in the game
* @param Vector - centre
* @param int - angle
*/
```

```java
public rectangle2D getBoundary2D()
```
```java
/**Return the 2D boundary of this model*/
```

```java
public void update()
```
```java
/**Update the numbers of helix that as available*/
```

```java
public void draw()
```
```java
/**Draw the helix when was shotted*/
```

## Declarations:

```java
public Vector particles[]
```
```java
/**This vector keeps the particles .*/
```

```java
public Vector directions[]
```
```java
/**This vector stores the particle directions.*/
```

```java
public int colors[]
```
```java
/**This vector keeps the colors of the particles.*/
```

```java
public int ALPHA
```
```java
/**Alpha mask*/
```

```java
public vector temp1
```
```java
/**Temp vector*/
```

```java
public Vector temp2
```
```java
/**Temp vector*/
```

```java
static final double LENGHT
```
```java
/**Static variable to indicate length , and to border.*/
```

```java
static final double HEIGHT = 0.25
```
```java
/**Static variable to indicate height , and to border.*/
```

```java
static final double WIDTH = 0.1
```
```java
/**Static variable to indicate width , and to border.*/
```
