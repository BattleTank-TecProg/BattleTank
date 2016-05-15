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
	
	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getSpriteIndex() {
		return spriteIndex;
	}

	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}

	public int getFrameIndex() {
		return frameIndex;
	}

	public void setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
	}

	public int getAuraIndex() {
		return auraIndex;
	}

	public void setAuraIndex(int auraIndex) {
		this.auraIndex = auraIndex;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getGroundZero() {
		return groundZero;
	}

	public void setGroundZero(int groundZero) {
		this.groundZero = groundZero;
	}

	public boolean isExplicitDrawing() {
		return explicitDrawing;
	}

	public void setExplicitDrawing(boolean explicitDrawing) {
		this.explicitDrawing = explicitDrawing;
	}

	public Polygon3D getExplosionAura() {
		return explosionAura;
	}

	public void setExplosionAura(Polygon3D explosionAura) {
		this.explosionAura = explosionAura;
	}

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

		assert (x > 0 || y < 0 || z > 0 || size > 0);

		final double LENGHT = 0.001;

		final double HEIGHT = 0.001;

		final double WIDTH = 0.001;

		final double XPOSITION = x - 0.1;

		final double YPOSITION = z + 0.1;

		final double LENGHTRECTANGLE = 0.2;

		final double HEIGHTRECTANGLE = 0.2;

		start = new Vector(x, y, z);
		iDirection = new Vector(1, 0, 0);
		jDirection = new Vector(0, 1, 0);
		kDirection = new Vector(0, 0, 1);

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
		if (size > 1) {
			spriteIndex = 18;
		} else {
			// Does nothing
		}
		Vector v[] = new Vector[] { put(-0.3, 0, 0.3), put(0.3, 0, 0.3),
				put(0.3, 0, -0.3), put(-0.3, 0, -0.3) };
		if (size > 3) {
			v = new Vector[] { put(-0.12, 0, 0.12), put(0.12, 0, 0.12),
					put(0.12, 0, -0.12), put(-0.12, 0, -0.12) };
		} else {
			// Does nothing
		}
		explosionAura = new Polygon3D(v, v[0], v[1], v[3], Main.textures[21],
				1, 1, 2);

		this.size = size;

		modelType = 4;
		makeBoundary(LENGHT, HEIGHT, WIDTH);

		boundary2D = new Rectangle2D(XPOSITION, YPOSITION, LENGHTRECTANGLE,
				HEIGHTRECTANGLE);

		lifeSpan = 16;

		findCentre();

		groundZero = (int) (x * 4) + (129 - (int) (z * 4)) * 80;
	}

	/**
	 * This method is responsible for updating the explosion in the scenario,
	 * according to the actions in the game.
	 */
	public void update() {
		assert (explosionAura != null);
		visible = true;

		if (damage != 0) {
			explosionAura.update();
			if (explosionAura.visible) {
				explosionAura.myTexture.Texture = explosionAura.myTexture.lightMapData[auraIndex];
				Rasterizer.rasterize(explosionAura);
			} else {
				// Does nothing
			}
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

		if (lifeSpan == 15 && damage != 0) {
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

	/** This method is responsible for draw the explosion on the scenario. */
	public void draw() {

		tempCentre.updateLocation();
		/* This variable holds the proportion of explosion */
		double ratio = size * 2 / tempCentre.z;

		Rasterizer.temp = this.type;
		Rasterizer.renderExplosionSprite(
				Main.textures[spriteIndex].explosions[frameIndex], ratio,
				tempCentre.screenX, tempCentre.screenY, 64, 64);

		frameIndex++;

	}

	/** This method is responsible for return the limits of the explosion. */
	public Rectangle2D getBoundary2D() {
		assert (boundary2D != null);
		return boundary2D;
	}

	private double size = 0;

	private int spriteIndex = 0;

	private int frameIndex = 0;

	private int auraIndex = 0;

	private int damage = 5;

	private int type = 0;

	private int groundZero = 0;

	private boolean explicitDrawing;

	private Polygon3D explosionAura;
}