# Class:

```java
public class helix
```
* A partical system that resemble the double helix trail of a railgun slug


## Methods:

```java
public helix(vector centre, int angle)
```

```java
public rectangle2D getBoundary2D()
```
* Return the 2D boundary of this model

```java
public void update()
```

```java
public void draw()
```


## Declarations:
```java
public vector particles[];
```
* This vector keeps the particles .

```java
public vector directions[];
```
* This vector stores the particle directions.

```java
public int colors[];
```
* This vector keeps the colors of the particles.

```java
public int ALPHA=0xFF000000;
```
* Alpha mask


```java
public vector temp1 = new vector(0,0,0);
public vector temp2 = new vector(0,0,0);
```
* Temp vector

```java
static final double LENGHT = 0.1;
```
* Static variable to indicate length , and to border.


```java
static final double HEIGHT = 0.25;
```
* Static variable to indicate height , and to border.


```java
static final double WIDTH = 0.1;
```
* Static variable to indicate width , and to border.
