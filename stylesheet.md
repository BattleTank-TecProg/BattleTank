![alt tag](http://cifmc.fis.unb.br/ivefras/logounb.png)

# University of Brasilia - Campus Gama

 **Course**: Programming Techniques

 **Teacher**: Maurício Serrano

 **Project Language**: Java



# Style Sheet


## Members:

**Nicácio Arruda Bezerra Neto** - 130015857

**João Paulo Busche da Cruz** - 140023348

**Matheus Henrique Sousa Costa** - 140028455

**Ruan N. Herculano Pereira** - 130145173



## Index:



[1. Objective](#1-objective)

[2. Code Comments](#2-code-comments)

[3. Variables](#3-variables)

[4. Classes](#4-classes)

[5. Methods](#5-methods)

[6. If/Else](#6-if/else)

[7. Switch Case](#7-switch-case)

[8. While](#8-while)

[9. Do/While](#9-do/while)

[10. For](#10-for)

[11. Try/Catch](#11-try/catch)

[12. Asserts](#12-asserts)

[13. Arrays, Vectors](#13-Arrays, Vectors)

[14. Identation](#14-identation)

[15. Syntax](#15-syntax)


# 1. Objective:


That style sheet have the objective to standardize all and any form of code on the project, whether in form of code or comments


# 2. Code Comments:

* The comments should be write always above the expression, which refers to;

### Incorrect example:

``` java
findCentre();
// Find centre of the model in world coordinate.
```
### Correct example:

``` java
// Find centre of the model in world coordinate.
findCentre();
```

* Alls comments should start with uppercase and finish with full stop;

### Incorrect example:

``` java
// this class is part of BattleTank 2
```

### Correct example:

``` java
// This class is part of BattleTank 2.
```


* The comments in block (more than one line) should be write on the begin with "```/*```" and end with "```*/```";

### Incorrect example:

``` java
// Class to create objects Anniquilator type, which are contained values ​​and
// methods for this purpose
```

### Correct example:

``` java
/**
* Class to create objects Anniquilator type, which are contained values ​​and
* methods for this purpose
*/
```

* Only the comments with one line should start with ```//```;

### Incorrect Exemples:
```java
/* Find centre of the model in world coordinate. */
```

### Correct comments Examples:
```java
// Find centre of the model in world coordinate.
```

* The Comments always should be aligned with the instruction, which refers to.

* The JavaDoc comments respect the same rules as normal comments.

### Comments Exemples:
```java
/*
  * If medium tank is engaged with player, it will send alert to
  * nearby tanks
 */
```

### javaDoc comments Examples:
```java
/**
 * This is a constructor method. X, y and z are coordinates for construction
 * of the annihilator in the space.
 *
 * @param double - x
 * @param double - y
 * @param double - y
 * @param int - angle
*/
```

# 3. Variables:

* The variables should be declared on that order: 1ª - ```public```, 2ª - ```protected``` and 3ª - ```private```;
* The variables must be declared at the time of its use. The variables must be declared as late as possible.
```java
for (int index = 0; index < body.length; index++) {
....
....
}  
```
* In this case , the variable has been declared within the 'for' , and was immediately used.+
* Should be evited the change or conversion of the type of variable;

### Incorrect example:

``` java
short color = (short) ((int) r << 10 | (int) g << 5 | (int) b);
```
* The variables should start after the declaration;

### Correct example:

``` java
private int stuckCount = 0;
```

### Incorrect example:

``` java
private int stuckCount;
```

* The declaration of variables should be fulfilled one by line;

### Correct example:

``` java
private boolean forward;
private boolean aimRight;
```

### Incorrect example:

``` java
private boolean forward, aimRight;
```

* The names of variables should be significant;

### Correct example:

```java
// Exemple does not belong to the code
private int randomNumberForGenerateTheObstaclesOfCoordenatesInMap = 0;
private int randomNumberForGenerateTheTanksOfCoordenatesInMap = 0;
```

### Incorrect example:

```java
private int randomNumber1 = 0;
private int randomNumber2 = 0;
```

* Compost names should be write if the firts name in lowercase and the firts letter of seconde name in uppercase;

### Correct example:

```java
private int bodyAngle = 0;
```

### Incorrect example:

```java
public static double old_X;
```

* Const Varibles: They must be declared with capital letters and separated by Underline;

### Correct example:

``` java
final double LENGHT = 0.1;
// Exemple does not belong to the code
private static final int MAX_COUNT = 10;
```

### Incorrect example:

``` java
final double LENGHTRECTANGLE = 0.23;
public static final int Z_length = 475;
private static final long serialVersionUID = 1L;
```

* Const Varibles: the const variables should be used to replace values which do not exchange;


# 4. Classes:

* All classes should contain documentation ```javaDoc```;

* should write following the model ```UpperCamelCase``` where the word starts with lowercase letters, and each new word begins with capital letter and followed by lowercase letters with no space between them;

### Correct example:
```java

public class RocketTail extends SolidObject {

}

```

### Incorrect example:
```java
// Exemple does not belong to the code
public class Rockettail extends SolidObject {

}

```

* All classes should contain a constructor method;

### Example:
```java

public class Tinker implements Runnable {

	public Tinker(int i) {
		delay = i;
		t = new Thread(this);
		t.start();
		isTicking = false;
	}

}
```

* The classes should be declared with the first letter in uppercase;

### Correct example:
```java
public class RocketTail extends SolidObject {

}
```
### Incorrect example:
```java
// Exemple does not belong to the code
public class rocketTail extends SolidObject {

}
```
# 5. Methods:
* Should not have spaces between parentheses and attributes;

### Incorrect example:

```java
public static int translate( String s) {
  .
  .
  .
}
```

### Correct example:

```java
public static int translate(String s) {
  .
  .
  .
}
```

* The parentheses of open should be setted up after the name of method;

```java
public static int translate (String s) {
  .
  .
  .
}
```

### Correct example:

```java
public static int translate(String s) {
  .
  .
  .
}
```

* If any more than one parameter being passed, should be put a comma close the variable and putting a long space after. Writing the last parameter, should not exist any space between the parameter and the close parantheses;

### Incorrect example:

```java
private double dividedByNumber( double numberForDivided ,double valueThatWillDivideTheNumber ) {
	.
  .
  .
}
```

### Correct example:

```java
private double dividedByNumber(double numberForDivided, double valueThatWillDivideTheNumber) {
  .
  .
  .
}
```


* The passing of parameter should be write close the brackets, there is no space between the brackets.

### Declaration Example:
```java
public void draw() {

		tempCentre.updateLocation();
		double ratio = size * 2 / tempCentre.z;
		Rasterizer.temp = this.type;
		Rasterizer.renderExplosionSprite(
				Main.textures[spriteIndex].explosions[frameIndex], ratio,
				tempCentre.screenX, tempCentre.screenY, 64, 64);

		frameIndex++;
	}
```
### Parameter Passing Example:
* Corrects:
```java
method(parameter);
method(parameter_1, parameter_2);
```
* Incorrects:
```java
method( parameter );
method( parameter);
method(parameter );
method(parameter_1 , parameter_2 );
method( parameter_1, parameter_2);
method(parameter_1 , parameter_2);
```



# 6. If/Else:
* Should not have space between two or more parantheses;
* Should not have space between the operations and the parantheses;
* All "```if```" should be accompanied with a "```else```";
* When do not have to do on ‘else’, should be post a comment inside the "```else```" signaling that there is nothing to do;
* The "```else```" shoulb be start at same line of end of "```if```";
* Should have a space between the end of "```if```" and the begin of "```else```";
* When there is a "```else if```" should be a space between the end of "```else```" and the beginning of "```if```";
* Even if the instructions have just one line, the keys are required.
* The ternary conditional operator should never be used to replace the "``` if / else```" ;

### Example:
```java
if ((main.timer + randomNumber1 * 3) % 1000 == 0) {
 if (randomNumber2 > 50) {
   randomNumber = 50;
 } else {
   randomNumber = 51;
 }
}
```

###	Exemple:
```java
if (ObstacleMap.collideWithObstacle1(this, newPosition)) {
  canMoveAngle1 = false;
} else if (ObstacleMap.collideWithObstacle2(this, newPosition)) {
  canMoveAngle1 = false;
} else {
 // Does nothing.
}
```
# 7. Switch Case:
* Should have a line between the declaration and the first case;
* Each case of "```switch```" should be separeted by one line;
* All switch should have a "```default```";
* When not have to declare on the default should have a comment signaling that there is nothing to do.

### Generic Example:

```java

switch (WeekDay) {

	case 1:
		System.out.println("Sunday");
		break;

	case 2:
		System.out.println("Monday");
		break;

	case 3:
		System.out.println("Tuesday");
		break;

	case 4:
		System.out.println("Wednesday");
		break;

	default:
		System.out.println("Thats isn't a valid day!");

}
```

# 8. While:
* Should not have space between two or more parantheses;
* Should not have space between the operations and the parantheses;
* Even if the instructions have just one line, the keys are required.

### Generic Example:

```java
while (count < 50) {
	System.out.println("Number Repetition: " + count);
	count++;
}
```

# 9. Do/While:
* Should have a space between the "```do```" and the open key;
* Should not have space between the operations and the parantheses;
* The "```while```" should be start at same line of the close key of the "```do```";
* Should have a space between the close key of the "```do```" and the "```while```".

### Example:

```java
do {
	fireActionPerformed();
	try {
		Thread.sleep(delay);
	} catch (InterruptedException interruptedexception) {
		System.out.println("WARNING: Ticker thread interrupted");   
	}
} while (true);
```

# 10. For:
* Should not have space between the operations and the parantheses;
* After each semicolon inside the parantheses should have a space;
* Even if the instructions have just one line, the keys are required;
* Between operator and number should have a space.

### Example:
* Correct:

```java
//draw body
for (int i = 0; i < body.lenght; i++) {
	body[i].draw();
}
```

* Incorrect:

```java
//update boundary
for(int i=0   ;i<5  ;  i++)
	boundary[i].update();

```

# 11. Try/Catch:
* Should have a space between the "```try```" and the open key;
* Should not have space between the operations and the parantheses;
* The "```catch```" should be start at the same line of the close key of the "```try```"
 Should have a space between the close key of the "```try```" and the "```catch```".

### Example:

```java
try {
	Thread.sleep(delay);
} catch (InterruptedException interruptedexception) {
	System.out.println("WARNING: Ticker thread interrupted");   
}
```

# 12. Asserts:
* Assertive should be for the situations that should never happen.
* They should follow the example below ;

### Example:
```java
assert(displacement != null);
assert(turret != null);
assert(turret != null);
assert(centre != null);
assert(boundary2D != null);
```

# 13. Arrays, Vectors:

* Arrays , Vectors :
* The opening and closing brackets, ```[]```,should be made at the end of the statement;  

### Correct Examples:

```java
private polygon3D body[];
private polygon3D turret[];
```

### Incorrect Examples:
```java
public static model[] enemy;
private polygon3D[] polygons;
```

# 14. Identation:

* Use the template of eclipse to indenting the tab;
* Finish which file with a blank line;
* Use spaces on around operators, after commas and  semicolon and around keys;
* Should not use spaces before ```(```, ```[``` or after ```]```, ```)``` ;
* When not have content between ```{}```,```()```, full with a space ```( )```,```{ }```;
* When not have Content between ```[]```, Dont write nothing;
* Leave a blank line above the declaration of method;
* Each new function/method should be initialized together the left extreme of the screen;
* The identation of operators should be separeted of its operands.

### Correct Examples:
```java

(parameter_1 == parameter_2)
(parameter_1 != parameter_2)
```
### Incorrect Examples:
```java

(parameter_1== parameter_2)
(parameter_1 !=parameter_2)
(parameter_1==parameter_2)
```


## 14.1 Keys:
* The keys should be open right after the declaration of operators and should be closed on the line below the end of operations;
* Before use the keys always should have a space.


# 14.2 Line Break:


* Should be evited very long lines;
* The new line should start on the same level of the origin expression.


# 15. Syntax:


* Class: firts letter of each word uppercase;
* Class member and local variables: first letter uppercase;
* Class constant: all letter on uppercase;
* Compost wors should not separeted, should be writes together and should not have any symbol between then.
