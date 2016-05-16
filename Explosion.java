/**
 *	This class is part of BattleTank 2.
 *
 *  BattleTank 2 is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  BattleTank 2 is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with BattleTank 2.  If not, see <http://www.gnu.org/licenses/>
 */

/**
 * This class is responsible for represent the explosions, which happen while
 * the game is played.
 */
public class Explosion extends SolidObject {

	/**
	 *
	 * This is a constructor method. X, y and z are coordinates for the
	 * explosion and the size is the diameter of explosion.
	 *
	 * @param x
	 * @param y
	 * @param z
	 * @param size
	 */
	public Explosion(double x, double y, double z, double size) {

		assert (x > 0 );
		assert (y < 0);
		assert (z > 0);
		assert (size > 0);
		assert (xDirection == null);
		assert (yDirection == null);
		assert (zDirection == null);
		
		/**Cubic shape lenght of the Explosion*/
		final double LENGHT = 0.001;
		
		/**Cubic shape height of the Explosion*/
		final double HEIGHT = 0.001;
		
		/**Cubic shape width of the Explosion*/
		final double WIDTH = 0.001;
		
		/**Position in the axis X at scenario of the explosion*/
		final double XPOSITION = x - 0.1;
		
		/**Position in the axis Y at scenario of the explosion*/
		final double YPOSITION = z + 0.1;
		
		/** Rectangle lenght, using to build explosion*/
		final double LENGHTRECTANGLE = 0.2;
		
		/** Rectangle height, using to build explosion*/
		final double HEIGHTRECTANGLE = 0.2;

		start = new Vector(x, y, z);
		xDirection = new Vector(1, 0, 0);
		yDirection = new Vector(0, 1, 0);
		zDirection = new Vector(0, 0, 1);

		int random = GameData.getRandom();
		if (random >= 75) {
			spriteIndex = 17;
		} else if (random >= 50) {
			spriteIndex = 18;
		} else if (random >= 25) {
			spriteIndex = 19;
		} else {
			spriteIndex = 20;
		}
		Vector v[] = new Vector[] { put(-0.3, 0, 0.3), put(0.3, 0, 0.3),
				put(0.3, 0, -0.3), put(-0.3, 0, -0.3) };

		if (size > 1) {
			spriteIndex = 18;
		} else if (size > 3) {
			v = new Vector[] { put(-0.12, 0, 0.12), put(0.12, 0, 0.12),
					put(0.12, 0, -0.12), put(-0.12, 0, -0.12) };
		} else {
			// Does nothing.
		}

		explosionAura = new Polygon3D(v, v[0], v[1], v[3], Main.textures[21],
				1, 1, 2);

		this.size = size;
		/*
		 * Boundary of this model has a cubic shape (ie length ~= width ~=
		 * height)
		 */
		modelType = 4;
		makeBoundary(LENGHT, HEIGHT, WIDTH);
		//Create 2D boundary
		boundary2D = new Rectangle2D(XPOSITION, YPOSITION, LENGHTRECTANGLE,
				HEIGHTRECTANGLE);

		lifeSpan = 16;
		//Find center of the model in world coordinate
		findCenter();
		//Find center of explosion
		groundZero = (int) (x * 4) + (129 - (int) (z * 4)) * 80;
	}

	/**
	 * This method is responsible for updating the explosion in the scenario,
	 * according to the actions in the game.
	 */
	public void update() {
		assert (explosionAura != null);

		visible = true;
		explosionAura.update();
		if (explosionAura.visible) {
			explosionAura.myTexture.Texture = explosionAura.myTexture.lightMapData[auraIndex];
			Rasterizer.rasterize(explosionAura);
		} else {
				// Does nothing
		}

		auraIndex++;

		if (!explicitDrawing) {
			ModelDrawList.register(this);
		} else {
			// Does nothing
		}
		tempCentre.set(centre);
		tempCentre.subtract(Camera.getPosition());
		tempCentre.rotate_XZ(Camera.getXZ_angle());
		tempCentre.rotate_YZ(Camera.getYZ_angle());

		if (lifeSpan == 15) {
			ObstacleMap.damageType2Obstacles(damage, boundary2D, groundZero);
		} else {
			// Does nothing
		}

		lifeSpan--;

		if (lifeSpan == 0) {
			lifeSpan = -1;

		} else {
			// Does nothing
		}
	}

	/**
	* This method is responsible for draw the explosion on the scenario.
	*/
	public synchronized void draw() {

		tempCentre.updateLocation();
		/* This variable holds the proportion of explosion */
		double ratio = size * 2 / tempCentre.z;

		Rasterizer.temp = this.type;
		Rasterizer.renderExplosionSprite(
				Main.textures[spriteIndex].explosions[frameIndex], ratio,
				tempCentre.screenX, tempCentre.screenY, 64, 64);

		frameIndex++;

	}

	/**
	 * This method is responsible for return the limits of the explosion.
	 *
	 * @return boundary2D
	 */
	public Rectangle2D getBoundary2D() {
		assert (boundary2D != null);
		return boundary2D;
	}

	/** Size of the explosion.*/
	private double size = 0;

	private int spriteIndex = 0;

	/** The square indicate the index.*/
	private int frameIndex = 0;

	/** Defines the size of the aura.*/
	private int auraIndex = 0;

	/** Amount of damage he can tear the opponent.*/
	private int damage = 5;

	/** Type of explosion 0 = normal and 1 = plasma.*/
	private int type = 0;

	/** Indicates the starting point of explosion.*/
	private int groundZero = 0;

	/** Enable this boolean if this explosion has be to drawn explicitly*/
	private boolean explicitDrawing;

	/** Define the aura explosion*/
	private Polygon3D explosionAura;

	/**
	 * This method return the size.
	 * @return size
	 */
	public double getSize() {
		return size;
	}

	/**
	 * This method set the size.
	 * @param size
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * This method return the index of sprite.
	 * @return spriteIndex
	 */
	public int getSpriteIndex() {
		return spriteIndex;
	}

	/**
	 * This method set the index of sprite.
	 * @param spriteIndex
	 */
	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}

	/**
	 * This method return the index of frame.
	 * @return frameIndex
	 */
	public int getFrameIndex() {
		return frameIndex;
	}

	/**
	 * This method set the index of frame.
	 * @param frameIndex
	 */
	public void setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
	}

	/**
	 * This method return the index of frame.
	 * @return auraIndex
	 */
	public int getAuraIndex() {
		return auraIndex;
	}

	/**
	 * This method set the index of frame.
	 * @param auraIndex
	 */
	public void setAuraIndex(int auraIndex) {
		this.auraIndex = auraIndex;
	}

	/**
	 * This method return the index of damege.
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * This method return the index of damege.
	 * @param damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * This method return the type.
	 * @return type
	 */
	public int getType() {
		return type;
	}

	/**
	 * This method set the type.
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * This method return the index of ground zero.
	 * @return groundZero
	 */
	public int getGroundZero() {
		return groundZero;
	}

	/**
	 * This method set the index of ground zero.
	 * @param groundZero
	 */
	public void setGroundZero(int groundZero) {
		this.groundZero = groundZero;
	}

	/**
	 * This method returns boolean if the design will be explicit or not.
	 * @return explicitDrawing
	 */
	public boolean isExplicitDrawing() {
		return explicitDrawing;
	}


	/**
	 * This method set boolean if the design will be explicit or not.
	 * @param explicitDrawing
	 */
	public void setExplicitDrawing(boolean explicitDrawing) {
		this.explicitDrawing = explicitDrawing;
	}


	/**
	 * This method returns a polygon referring to the explosion of the aura.
	 * @return explosionAura
	 */
	public Polygon3D getExplosionAura() {
		return explosionAura;
	}

	/**
	 * This method set a polygon referring to the explosion of the aura.
	 * @param explosionAura
	 */
	public void setExplosionAura(Polygon3D explosionAura) {
		this.explosionAura = explosionAura;
	}
}
