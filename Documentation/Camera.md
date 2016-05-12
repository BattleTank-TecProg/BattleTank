# Class:

```java
 public class Camera
```

```java
/** This class is responsible for the camera for the game.*/
```

## Methods:

```java
public Camera();
```

```java
/** This is a constructor method, which init camera with default values.*/
```

```java
public void update();
```

```java
/** This method is responsible for updating the camera in the scenario, according to the actions in the game.*/
```

## Declarations:

```java
public static Vector position
```

```java
/** This vector represents the position of the camera (third person view).*/
```

```java
public static Vector absolutePosition
```

```java
/** This vector represents the absolute position of the camera*/
```

```java
public Vector thirdPersonDisplacement
```

```java
/** The vector represents the displacement for creating third person effect.*/
```

```java
public static Vector viewDirection
```

```java
/** The vector represents the direction of the view.*/
```

```java
public static int XZ_angle
```

```java
/** The angle that camera has rotated from the default view direction, this angle is 315 degrees.*/
```

```java
public static int YZ_angle
```

```java
/** The angle that camera has rotated from the default view direction, this angle is 315 degrees.*/
```

```java
public static final Rectangle screen
```

```java
/** This object is a large rectangle that represents the area of the game on the screen.*/
```

```java
public static boolean restart
```

```java
/** Variable that indicates whether the camera is positioned at the starting position.*/
```

```java
public int flyThroughTimer
```
```java
/** This variable holds the flight time of the camera.*/
```
