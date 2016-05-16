# Class:

```java
/** This class is responsible for the camera for the game.*/

 public class Camera

```

## Methods:

```java
/**
 * Method that returns a Vector regarding the position of the object.
 *
 * @return position
 */
public static Vector getPosition()

/**
 * Method that set a Vector regarding the position of the object.
 *
 * @param position
 */
public static void setPosition(Vector position)

/**
 * Method that return a Vector regarding the absolute position of the
 * object.
 *
 * @return position
 */
public static Vector getAbsolutePosition()

/**
 * Method that set a Vector regarding the absolute position of the object.
 *
 * @param position
 */
public static void setAbsolutePosition(Vector absolutePosition)

/**
 * This method returns a vector referring to the third person displacement.
 *
 * @return thirdPersonDisplacement
 */
public Vector getThirdPersonDisplacement()

/**
 * This method set a vector referring to the third person displacement.
 *
 * @param thirdPersonDisplacement
 */
public void setThirdPersonDisplacement(Vector thirdPersonDisplacement)

/**
 * This method returns an array refers to the vision of direction.
 *
 * @return viewDirection
 */
public static Vector getViewDirection()

/**
 * This method set an array refers to the vision of direction.
 *
 * @param viewDirection
 */
public static void setViewDirection(Vector viewDirection)

/**
 * This method returns the angle related to X and Z coordinates.
 * @return XZ_angle
 */
public static int getXZ_angle()

/**
 * This method set the angle related to X and Z coordinates.
 * @param XZ_angle
 */
public static void setXZ_angle(int xZ_angle)

/**
 * This method returns the angle related to Y and Z coordinates.
 * @return YZ_angle
 */
public static int getYZ_angle()

/**
 * This method set the angle related to Y and Z coordinates.
 * @param YZ_angle
 */
public static void setYZ_angle(int yZ_angle)


/**
 * This method boolean indicating whether the user has restarted the game or not.
 * @return restart
 */
public static boolean isRestart()

/**
 * This method boolean set whether the user has restarted the game or not.
 * @param restart
 */
public static void setRestart(boolean restart)

/**
 * This method returns the flight through time.
 * @return flyThroughTimer
 */
public int getFlyThroughTimer()

/**
 * This method set the flight through time.
 * @param flyThroughTimer
 */
public void setFlyThroughTimer(int flyThroughTimer)

/**
 * This method returns the size larger than a variable type double may have.
 * @return LARGER_SIZE_FOR_A_DOUBLE_VARIABLE
 */
public static double getLargerSizeForADoubleVariable()

/**
 * This method returns the size smaller than a variable type double may have.
 * @return MINIMUM_MOST_FOR_A_DOUBLE_VARIABLE
 */
public static double getMinimumMostForADoubleVariable()

/**
 * This method returns the X coordinate of.
 * @return XCOORDINADE
 */
public static int getXcoordinade()

/**
 * This method returns the Y coordinate of.
 * @return YCOORDINADE
 */
public static int getYcoordinade()

/**
 * This method returns the width.
 * @return WIDTH
 */
public static int getWidth()

/**
 * This method returns the height.
 * @return HEIGHT
 */
public static int getHeight()

/**
 * This method returns a rectangle related to screen position.
 * @return screen
 */
public static Rectangle getScreen()

/**
 * This is a constructor method, which init camera with default values.
 */
public Camera()

`/**
 * This method is responsible for updating the camera in the scenario,
 * according to the actions in the game.
 */
public void update()
```

## Declarations:

```java
/** X coordinate of the rectangle. */
private static final int XCOORDINADE = 0;

/** Y coordinate of the rectangle. */
private static final int YCOORDINADE = 0;

/** Width of rectangle. */
private final static int WIDTH = 640;

/** Weight of rectangle. */
private final static int HEIGHT = 480;

/** This vector represents the position of the camera (third person view). */
private static Vector position = new Vector(10, 0.25, 1.5);

/** This vector represents the absolute position of the camera */
private static Vector absolutePosition = new Vector(10, 0.25, 1.5);

/** The vector represents the displacement for creating third person effect. */
private Vector thirdPersonDisplacement = new Vector(0, 0, 0);

/** The vector represents the direction of the view. */
private static Vector viewDirection = new Vector(0, 0, 1);

/**
 * The angle that camera has rotated from the default view direction, this
 * angle is 315 degrees.
 */
private static int XZ_angle = 0;

/**
 * The angle that camera has rotated from the default view direction, this
 * angle is 315 degrees.
 */
private static int YZ_angle = 319;

/**
 * This object is a large rectangle that represents the area of the game on
 * the screen.
 */
public static final Rectangle screen = new Rectangle(XCOORDINADE,
    YCOORDINADE, WIDTH, HEIGHT);

/**
 * Variable that indicates whether the camera is positioned at the starting
 * position.
 */
private static boolean restart;

/** This variable holds the flight time of the camera.*/

public int flyThroughTimer
```
