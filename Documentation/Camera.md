# Class:

```java
 public class Camera
```
* This class is responsible for the camera for the game.

## Methods:

```java
public Camera();
```

* This is a constructor method, which init camera with default values.

```java
public void update();
```

* This method is responsible for updating the camera in the scenario, according to the actions in the game.

## Declarations:

```java
public static Vector position
```

* This vector represents the position of the camera (third person view).

```java
public static Vector absolutePosition
```

* This vector represents the absolute position of the camera.

```java
public Vector thirdPersonDisplacement
```

* The vector represents the displacement for creating third person effect.

```java
public static Vector viewDirection
```

* The vector represents the direction of the view.

```java
public static int XZ_angle
```

* The angle that camera has rotated from the default view direction, this angle is 315 degrees.

```java
public static int YZ_angle
```

* The angle that camera has rotated from the default view direction, this angle is 315 degrees.

```java
public static final Rectangle screen
```

* This object is a large rectangle that represents the area of the game on the screen.

```java
public static boolean restart
```

* Variable that indicates whether the camera is positioned at the starting position.

```java
public int flyThroughTimer
```

* This variable holds the flight time of the camera.
