# Class:
```java
 public class Camera
```
* This class is responsible for the camera for the game.

## Methods:
```java
public Camera();
```

* This is a builder method, which init camera with default values.

```java
public void update();
```
* This method is responsible for updating the camera in the scenario, according to the actions in the game.

## Variables:
```java
vector position
```
* This vector represents the position of the camera (third person view).

```java
vector absolutePosition
```
* This vector represents the  absolute position of the camera.

```java
vector thirdPersonDisplacement
```

* The vector represents the displacement for creating third person effect.

```java
vector viewDirection
```

* The vector represents the direction of the view.

```java
XZ_angle
```

* The angle that camera has rotated from the default view direction, this angle is 315 degrees.

```java
YZ_angle
```

* The angle that camera has rotated from the default view direction, this angle is 315 degrees.

```java
final Rectangle screen
```

* This object is a large rectangle that represents the area of the game on the screen.

```java
restart
```

* Variable that indicates whether the camera is positioned at the starting position.

```java
flyThroughTimer
```

* This variable holds the flight time of the camera.
