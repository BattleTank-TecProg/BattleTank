# Class:

```java
public class Helix
```
* A partial system that create the helix of the railgun


## Methods:

```java
public Helix(vector centre, int angle)
```

* Construct the helix of railgun in the game

```java
public rectangle2D getBoundary2D()
```

* Return the 2D boundary of this model

```java
public void update()
```

* Update the numbers of helix that as available

```java
public void draw()
```

* Draw the helix when was shotted

## Declarations:

```java
public Vector particles[]
```

* This vector keeps the particles .

```java
public Vector directions[]
```

* This vector stores the particle directions.

```java
public int colors[]
```

* This vector keeps the colors of the particles.

```java
public int ALPHA=0xFF000000
```

* Alpha mask

```java
public vector temp1
```

* Temp vector

```java
public Vector temp2
```

* Temp vector

```java
static final double LENGHT = 0.1
```

* Static variable to indicate length , and to border.


```java
static final double HEIGHT = 0.25
```

* Static variable to indicate height , and to border.


```java
static final double WIDTH = 0.1
```

* Static variable to indicate width , and to border.
