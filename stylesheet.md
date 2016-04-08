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

[5. Identation](#5-identation)

[6. Line Break](#6-line-break)

[7. Syntax](#7-syntax)

[8. Statements](#8-statements)



# 1. Objective:


That style sheet have the objective to standardize all and any form of code on the project, whether in form of code or comments


# 2. Code Comments:


* The comments should be write always above the expression, which refers to;
* Alls comments should start with uppercase and finish with full stop;
* Should not be space after or before the operator used to declare the comment;
* The comments in block (more than one line) should be write on the begin with "```/*```" and end with "```*/```";
* Only the comments with one line should start with ```//```;
* The Comments always should be aligned with the instruction, which refers to.

### Exemple:

```java
//This class is responsible for enemy annihilator.
```


# 3. Variables:


* The variables shoudl be declared at firts of class or at firts of method, no way any variable should be declared on the middle or end of code, except in cases where it is strictly need to work of algorithm;
* Should be evited the change or conversion of the type of variable;
* The variables should start after the declaration;
* The declaration of variables should be fulfilled one by line;
* The names of variables should be significant;
* Compost names should be write if the firts name in lowercase and the firts letter of seconde name in uppercase;
* Should not  divide the name of variables with ‘-,/,etc’;
* Const Varibles: the const variables should be used to replace values which do not exchange.


### Example:

```	java

private int targetAngle = 0;    
private int targetAngleody = 0;
private int previousTargetAngleBody = 0;
protected int bodyAngle = 0;
protected int turrentAngle = 0;
protected int coolDownShell = 33;
public int coolDownShell = 33;
public double distance = 0;
public boolean forward = 0;
```

# 4. Classes:


* All classes should contain documentation ```javaDoc``` and should be write following the template ```CamelCase``` , more precisely ```UpperCamelCase```, where the word is started with uppercase and united without space;
* The variables should be declared on that order: 1ª - ```public```, 2ª - ```protected``` and 3ª - ```private```;
* All classes shoud contain a constructor method.


# 5. Identation:

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


## 5.1 Keys:
* The keys should be open right after the declaration of operators and should be closed on the line below the end of operations;
* Before use the keys always should have a space.


# 6. Line Break:


* Should be evited very long lines;
* The new line should start on the same level of the origin expression.


# 7. Syntax:


* Class: firts letter of each word uppercase;
* Class member and local variables: first letter uppercase;
* Class constant: all letter on uppercase;
* Compost wors should not separeted, should be writes together and should not have any symbol between then.


# 8. Statements:


## 8.1 - Methods:
* Methods shoulb have the first name in uppercase;
* Should not have spaces between parentheses and attributes;
* The parentheses of open should be setted up after the name of method;
* If any more than one parameter being passed, should be put a comma close the variable and putting a long space after. Writing the last parameter, should not exist any space between the parameter and the close parantheses;
* The passing of parameter should be write closo the parantheses.

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

## 8.2 - Classes:
* The classes should be declared with the first letter in uppercase;
* All classes should have the documentation ```javaDoc```;
* Should have the constructor method.

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

## 8.3 - If/Else:
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




## 8.4 - Switch Case:
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

## 8.5 While:
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

## 8.6 Do/While:
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

## 8.7 For:
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
for(int i = 0; i < 5; i++) {
	boundary[i].update();
}
```

## 8.8 Try/Catch:
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
## 8.9 Arrays, Vectors:

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
