## public class Annihilator
```This class gathers all the features of the annihilator enemy.```

### Methods:

**public Annihilator(double x, double y, double z, int angle)**

```This is a builder method. X, y and z are coordinates for construction of the annihilator in the space.```


**private void makeBody()**

```This method is responsible to create the polygons of the body of annihilator.```


**private void makeTurret()**

```This method is responsible to create polygons for the tank turret.```


**public void update()**

```This method is responsible for updating the annihilator in the scenario, according to the actions in the game.```

**public void draw()**

```This method is responsible for drawing the body annihilator.```


**public void damage(int damagePoint)**

```This method is responsible for specifying the loss of life of annihilator.```


**public Rectangle2D getBoundary2D()**

```This method is responsible for return the 2D boundary of the annihilator.```

### Variables:

