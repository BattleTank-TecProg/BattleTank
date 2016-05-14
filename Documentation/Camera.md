# Class:

```java
/** This class is responsible for the camera for the game.*/

 public class Camera

```

## Methods:

```java
/** This is a constructor method, which init camera with default values.*/

public Camera();
```

```java
/** This method is responsible for updating the camera in the scenario, according to the actions in the game.*/

public void update();
```

## Declarations:

```java
/** This vector represents the position of the camera (third person view).*/

public static Vector position
```

```java
/** This vector represents the absolute position of the camera*/

public static Vector absolutePosition
```

```java
/** The vector represents the displacement for creating third person effect.*/

public Vector thirdPersonDisplacement
```

```java
/** The vector represents the direction of the view.*/

public static Vector viewDirection
```

```java
/** The angle that camera has rotated from the default view direction, this angle is 315 degrees.*/

public static int XZ_angle
```

```java
/** The angle that camera has rotated from the default view direction, this angle is 315 degrees.*/

public static int YZ_angle
```

```java
/** This object is a large rectangle that represents the area of the game on the screen.*/

public static final Rectangle screen
```

```java
/** Variable that indicates whether the camera is positioned at the starting position.*/

public static boolean restart
```

```java
/** This variable holds the flight time of the camera.*/

public int flyThroughTimer
```
