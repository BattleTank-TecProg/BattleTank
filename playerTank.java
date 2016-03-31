public class playerTank extends SolidObject {

	private polygon3D[] body;

	private int bodyAngle;

	public static vector bodyCenter;

	private polygon3D[] turret;

	private polygon3D shadowBody;

	private polygon3D shadowTurret;

	public static vector turretCenter;

	public static int turretAngle;

	private int nukeCannonAngle;

	private int angularSpeed;

	private polygon3D[] nukeCannonRear, nukeCannonMiddle, nukeCannonFront;

	public static boolean forward, backward, turnRight, turnLeft, moveRight,
			moveLeft, firing;

	public int currentWeapon = 1;

	public int[] coolDowns = new int[] { 0, 16, 30, 40, 5 };

	public int currentCoolDown = 0;

	public static int shells;
	public static int rockets;
	public static int slugs;
	public static int plasma;
	public static int currentAmmo;

	public boolean outOfAmmo;

	public static vector displacement = new vector(0, 0, 0);

	private int bodyAngleDelta;

	public static double speed = 0;

	private int turretAngleDelta;

	int position;

	private vector tempVector1 = new vector(0, 0, 0);
	private vector tempVector2 = new vector(0, 0, 0);

	private int underAttackCount;

	public playerTank(double x, double y, double z) {

		shells = 50;
		rockets = 0;
		slugs = 0;
		plasma = 0;

		start = new vector(x, y, z);
		iDirection = new vector(1, 0, 0);
		jDirection = new vector(0, 0.95, 0);
		kDirection = new vector(0, 0, 1.05);

		turretAngle = 0;
		bodyAngle = 0;

		modelType = 1;
		makeBoundary(0.1, 0.25, 0.1);

		boundary2D = new rectangle2D(x - 0.07, z + 0.07, 0.14, 0.14);
		position = (int) (x * 4) + (129 - (int) (z * 4)) * 80;
		obstacleMap.registerObstacle2(this, position);

		findCentre();

		bodyCenter = centre;

		makeBody();
		makeTurretCannon();

		HP = 150;
	}

	public rectangle2D getBoundary2D() {
		return boundary2D;
	}

	public void makeBody() {
		vector[] v;

		body = new polygon3D[11];

		v = new vector[] { put(-0.07, 0.025, 0.11), put(-0.07, 0.025, -0.11),
				put(-0.07, 0.005, -0.11), put(-0.07, -0.025, -0.08),
				put(-0.07, -0.025, 0.07), put(-0.07, 0.005, 0.11) };
		body[0] = new polygon3D(v, put(-0.07, 0.027, 0.11), put(-0.07, 0.027,
				-0.11), put(-0.07, -0.025, 0.11), main.textures[12], 1, 1, 6);

		v = new vector[] { put(0.07, 0.005, 0.11), put(0.07, -0.025, 0.07),
				put(0.07, -0.025, -0.08), put(0.07, 0.005, -0.11),
				put(0.07, 0.025, -0.11), put(0.07, 0.025, 0.11) };
		body[1] = new polygon3D(v, put(0.07, 0.027, -0.11), put(0.07, 0.027,
				0.11), put(0.07, -0.025, -0.11), main.textures[12], 1, 1, 6);

		v = new vector[] { put(-0.06, 0.055, 0.05), put(0.06, 0.055, 0.05),
				put(0.06, 0.055, -0.1), put(-0.06, 0.055, -0.1) };
		body[2] = new polygon3D(v, v[0], v[1], v[3], main.textures[11], 1, 0.9,
				6);

		v = new vector[] { put(-0.07, 0.04, 0.11), put(0.07, 0.04, 0.11),
				put(0.06, 0.055, 0.05), put(-0.06, 0.055, 0.05) };
		body[3] = new polygon3D(v, v[2], v[3], v[1], main.textures[11], 1, 0.3,
				6);

		v = new vector[] { put(-0.06, 0.055, 0.05), put(-0.06, 0.055, -0.1),
				put(-0.07, 0.04, -0.11), put(-0.07, 0.04, 0.11) };
		body[4] = new polygon3D(v, v[2], v[3], v[1], main.textures[11], 1, 0.3,
				6);

		v = new vector[] { put(0.07, 0.04, 0.11), put(0.07, 0.04, -0.11),
				put(0.06, 0.055, -0.1), put(0.06, 0.055, 0.05) };
		body[5] = new polygon3D(v, v[2], v[3], v[1], main.textures[11], 1, 0.3,
				6);

		v = new vector[] { put(-0.06, 0.055, -0.1), put(0.06, 0.055, -0.1),
				put(0.07, 0.04, -0.11), put(-0.07, 0.04, -0.11) };
		body[6] = new polygon3D(v, v[2], v[3], v[1], main.textures[11], 1, 0.3,
				6);

		v = new vector[] { put(0.07, 0.04, 0.11), put(-0.07, 0.04, 0.11),
				put(-0.07, 0.01, 0.11), put(0.07, 0.01, 0.11) };
		body[7] = new polygon3D(v, v[2], v[3], v[1], main.textures[11], 1, 0.3,
				6);

		v = new vector[] { put(-0.07, 0.04, 0.11), put(-0.07, 0.04, -0.11),
				put(-0.07, 0.015, -0.11), put(-0.07, 0.005, -0.09),
				put(-0.07, 0.005, 0.09), put(-0.07, 0.015, 0.11) };
		body[8] = new polygon3D(v, put(-0.07, 0.04, 0.11), put(-0.07, 0.04,
				-0.11), put(-0.07, 0.025, 0.11), main.textures[11], 1, 0.3, 6);

		v = new vector[] { put(0.07, 0.015, 0.11), put(0.07, 0.005, 0.09),
				put(0.07, 0.005, -0.09), put(0.07, 0.015, -0.11),
				put(0.07, 0.04, -0.11), put(0.07, 0.04, 0.11) };
		body[9] = new polygon3D(v, put(0.07, 0.04, 0.11),
				put(0.07, 0.04, -0.11), put(0.07, 0.025, 0.11),
				main.textures[11], 1, 0.3, 6);

		v = new vector[] { put(-0.07, 0.04, -0.11), put(0.07, 0.04, -0.11),
				put(0.07, 0.015, -0.11), put(-0.07, 0.015, -0.11) };
		body[10] = new polygon3D(v, v[0], v[1], v[3], main.textures[11], 1,
				0.3, 6);

		turretCenter = put(0, 0.055, -0.02);

		start.add(-0.025, 0, -0.02);
		start.y = -1;
		v = new vector[] { put(-0.2, 0, 0.2), put(0.2, 0, 0.2),
				put(0.2, 0, -0.2), put(-0.2, 0, -0.2) };
		shadowBody = new polygon3D(v, v[0], v[1], v[3], main.textures[14], 1,
				1, 2);
	}

	public void makeTurretCannon() {
		start = turretCenter.myClone();
		vector[] v;
		turret = new polygon3D[10];

		iDirection.set(1, 0, 0);
		jDirection.set(0, 0.95, 0);
		kDirection.set(0, 0, 1.05);

		iDirection.rotate_XZ(turretAngle);
		kDirection.rotate_XZ(turretAngle);

		v = new vector[] { put(0, 0.025, 0.18), put(0.004, 0.022, 0.18),
				put(0.007, 0.022, 0.06), put(0, 0.025, 0.06) };
		turret[0] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.1,
				1, 6);

		v = new vector[] { put(0, 0.025, 0.06), put(-0.007, 0.022, 0.06),
				put(-0.004, 0.022, 0.18), put(0, 0.025, 0.18) };
		turret[1] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.1,
				1, 6);

		v = new vector[] { put(-0.04, 0.035, 0.06), put(0.04, 0.035, 0.06),
				put(0.05, 0.035, 0.04), put(0.05, 0.035, -0.03),
				put(0.03, 0.035, -0.07), put(-0.03, 0.035, -0.07),
				put(-0.05, 0.035, -0.03), put(-0.05, 0.035, 0.04) };
		turret[2] = new polygon3D(v, put(-0.04, 0.035, 0.19), put(0.04, 0.035,
				0.19), put(-0.04, 0.035, 0.09), main.textures[13], 0.8, 0.8, 6);

		v = new vector[] { put(0.03, 0, -0.07), put(-0.03, 0, -0.07),
				put(-0.03, 0.035, -0.07), put(0.03, 0.035, -0.07) };
		turret[3] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.6,
				0.3, 6);

		v = new vector[] { put(0.03, 0.035, -0.07), put(0.05, 0.035, -0.03),
				put(0.05, 0, -0.03), put(0.03, 0, -0.07) };
		turret[4] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.6,
				0.3, 6);

		v = new vector[] { put(-0.03, 0, -0.07), put(-0.05, 0, -0.03),
				put(-0.05, 0.035, -0.03), put(-0.03, 0.035, -0.07) };
		turret[5] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.6,
				0.3, 6);

		v = new vector[] { put(-0.05, 0.035, 0.04), put(-0.05, 0.035, -0.03),
				put(-0.05, 0, -0.03), put(-0.05, 0, 0.04) };
		turret[6] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.5,
				0.2, 6);

		v = new vector[] { put(0.05, 0, 0.04), put(0.05, 0, -0.03),
				put(0.05, 0.035, -0.03), put(0.05, 0.035, 0.04) };
		turret[7] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.5,
				0.2, 6);

		v = new vector[] { put(-0.04, 0.035, 0.06), put(-0.05, 0.035, 0.04),
				put(-0.05, 0, 0.04), put(-0.04, 0, 0.06) };
		turret[8] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.2,
				0.2, 6);

		v = new vector[] { put(0.04, 0, 0.06), put(0.05, 0, 0.04),
				put(0.05, 0.035, 0.04), put(0.04, 0.035, 0.06) };
		turret[9] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.2,
				0.2, 6);

		start.add(-0.04, 0, -0.04);
		start.y = -1;
		v = new vector[] { put(-0.2, 0, 0.2), put(0.2, 0, 0.2),
				put(0.2, 0, -0.2), put(-0.2, 0, -0.2) };
		shadowTurret = new polygon3D(v, v[0], v[1], v[3], main.textures[15], 1,
				1, 2);
	}

	public void makeTurretRocket() {
		start = turretCenter.myClone();
		vector[] v;
		turret = new polygon3D[35];

		iDirection.set(1, 0, 0);
		jDirection.set(0, 0.95, 0);
		kDirection.set(0, 0, 1.05);

		iDirection.rotate_XZ(turretAngle);
		kDirection.rotate_XZ(turretAngle);

		v = new vector[] { put(-0.05, 0.035, 0.04), put(-0.05, 0.035, -0.03),
				put(-0.05, 0, -0.03), put(-0.05, 0, 0.04) };
		turret[0] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.5,
				0.2, 6);

		v = new vector[] { put(0.05, 0, 0.04), put(0.05, 0, -0.03),
				put(0.05, 0.035, -0.03), put(0.05, 0.035, 0.04) };
		turret[1] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.5,
				0.2, 6);

		v = new vector[] { put(-0.04, 0.035, 0.06), put(-0.05, 0.035, 0.04),
				put(-0.05, 0, 0.04), put(-0.04, 0, 0.06) };
		turret[2] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.2,
				0.2, 6);

		v = new vector[] { put(0.04, 0, 0.06), put(0.05, 0, 0.04),
				put(0.05, 0.035, 0.04), put(0.04, 0.035, 0.06) };
		turret[3] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.2,
				0.2, 6);

		double r = 0.015;
		double theta = Math.PI / 8;
		tempVector1.set(start);
		start = put(0.068, 0, 0);

		for (int i = 0; i < 16; i++) {
			v = new vector[] {
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), -0.03),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), -0.03),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), 0.03),
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), 0.03) };
			turret[i + 4] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[25], 0.1, 0.1, 6);
		}

		start.set(tempVector1);
		start = put(0.068, 0, 0.01);

		for (int i = 0; i < 8; i++) {
			v = new vector[] {
					put(r * Math.cos(i * theta), r * Math.sin(i * theta),
							-0.004),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), -0.004),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), 0.004),
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), 0.004) };
			turret[i + 20] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[26], 0.1, 0.1, 6);
		}

		start.set(tempVector1);
		start = put(0.068, 0, 0);

		v = new vector[16];
		for (int i = 1; i < 17; i++)
			v[16 - i] = put(r * Math.cos(i * theta), r * Math.sin(i * theta),
					-0.03);
		turret[28] = new polygon3D(v, v[0], v[1], v[3], main.textures[25], 1,
				1, 6);

		start.set(tempVector1);
		v = new vector[] { put(0, 0.025, 0.18), put(0.004, 0.022, 0.18),
				put(0.007, 0.022, 0.06), put(0, 0.025, 0.06) };
		turret[29] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.1,
				1, 6);

		v = new vector[] { put(0, 0.025, 0.06), put(-0.007, 0.022, 0.06),
				put(-0.004, 0.022, 0.18), put(0, 0.025, 0.18) };
		turret[30] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.1,
				1, 6);

		v = new vector[] { put(-0.04, 0.035, 0.06), put(0.04, 0.035, 0.06),
				put(0.05, 0.035, 0.04), put(0.05, 0.035, -0.03),
				put(0.03, 0.035, -0.07), put(-0.03, 0.035, -0.07),
				put(-0.05, 0.035, -0.03), put(-0.05, 0.035, 0.04) };
		turret[31] = new polygon3D(v, put(-0.04, 0.035, 0.19), put(0.04, 0.035,
				0.19), put(-0.04, 0.035, 0.09), main.textures[13], 0.8, 0.8, 6);

		v = new vector[] { put(0.03, 0, -0.07), put(-0.03, 0, -0.07),
				put(-0.03, 0.035, -0.07), put(0.03, 0.035, -0.07) };
		turret[32] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.6,
				0.3, 6);

		v = new vector[] { put(0.03, 0.035, -0.07), put(0.05, 0.035, -0.03),
				put(0.05, 0, -0.03), put(0.03, 0, -0.07) };
		turret[33] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.6,
				0.3, 6);

		v = new vector[] { put(-0.03, 0, -0.07), put(-0.05, 0, -0.03),
				put(-0.05, 0.035, -0.03), put(-0.03, 0.035, -0.07) };
		turret[34] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.6,
				0.3, 6);

		start.add(-0.04, 0, -0.04);
		start.y = -1;
		v = new vector[] { put(-0.2, 0, 0.2), put(0.2, 0, 0.2),
				put(0.2, 0, -0.2), put(-0.2, 0, -0.2) };
		shadowTurret = new polygon3D(v, v[0], v[1], v[3], main.textures[15], 1,
				1, 2);
	}

	public void makeTurretRailgun() {
		start = turretCenter.myClone();
		vector[] v;
		turret = new polygon3D[72];

		iDirection.set(1, 0, 0);
		jDirection.set(0, 0.95, 0);
		kDirection.set(0, 0, 1.05);

		iDirection.rotate_XZ(turretAngle);
		kDirection.rotate_XZ(turretAngle);

		double r = 0.025;
		double theta = Math.PI / 16;
		tempVector1.set(start);
		start = put(0, 0, 0.12);

		for (int i = 0; i < 32; i++) {
			v = new vector[] {
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), -0.06),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), -0.06),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), 0.06),
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), 0.06) };
			turret[i] = new polygon3D(v, v[0], v[1], v[3], main.textures[13],
					0.001, 0.01, 6);
		}

		for (int i = 32; i < 64; i++) {
			v = new vector[] {
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), 0.05),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), 0.05),
					put(r * Math.cos((i + 1) * theta),
							r * Math.sin((i + 1) * theta), 0.06),
					put(r * Math.cos(i * theta), r * Math.sin(i * theta), 0.06) };
			turret[i] = new polygon3D(v, v[0], v[1], v[3], main.textures[23],
					0.01, 0.01, 6);
		}

		start.set(tempVector1);
		v = new vector[] { put(-0.04, 0.035, 0.06), put(0.04, 0.035, 0.06),
				put(0.05, 0.035, 0.04), put(0.05, 0.035, -0.03),
				put(0.03, 0.035, -0.07), put(-0.03, 0.035, -0.07),
				put(-0.05, 0.035, -0.03), put(-0.05, 0.035, 0.04) };
		turret[64] = new polygon3D(v, put(-0.04, 0.035, 0.19), put(0.04, 0.035,
				0.19), put(-0.04, 0.035, 0.09), main.textures[13], 0.8, 0.8, 6);

		v = new vector[] { put(0.03, 0, -0.07), put(-0.03, 0, -0.07),
				put(-0.03, 0.035, -0.07), put(0.03, 0.035, -0.07) };
		turret[65] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.6,
				0.3, 6);

		v = new vector[] { put(0.03, 0.035, -0.07), put(0.05, 0.035, -0.03),
				put(0.05, 0, -0.03), put(0.03, 0, -0.07) };
		turret[66] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.6,
				0.3, 6);

		v = new vector[] { put(-0.03, 0, -0.07), put(-0.05, 0, -0.03),
				put(-0.05, 0.035, -0.03), put(-0.03, 0.035, -0.07) };
		turret[67] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.6,
				0.3, 6);

		v = new vector[] { put(-0.05, 0.035, 0.04), put(-0.05, 0.035, -0.03),
				put(-0.05, 0, -0.03), put(-0.05, 0, 0.04) };
		turret[68] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.5,
				0.2, 6);

		v = new vector[] { put(0.05, 0, 0.04), put(0.05, 0, -0.03),
				put(0.05, 0.035, -0.03), put(0.05, 0.035, 0.04) };
		turret[69] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.5,
				0.2, 6);

		v = new vector[] { put(-0.04, 0.035, 0.06), put(-0.05, 0.035, 0.04),
				put(-0.05, 0, 0.04), put(-0.04, 0, 0.06) };
		turret[70] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.2,
				0.2, 6);

		v = new vector[] { put(0.04, 0, 0.06), put(0.05, 0, 0.04),
				put(0.05, 0.035, 0.04), put(0.04, 0.035, 0.06) };
		turret[71] = new polygon3D(v, v[0], v[1], v[3], main.textures[13], 0.2,
				0.2, 6);

		start.add(-0.04, 0, -0.04);
		start.y = -1;
		v = new vector[] { put(-0.5, 0, 0.2), put(0.5, 0, 0.2),
				put(0.5, 0, -0.2), put(-0.5, 0, -0.2) };
		shadowTurret = new polygon3D(v, v[0], v[1], v[3], main.textures[15], 1,
				1, 2);
	}

	public void makeTurretNuke() {
		start = turretCenter.myClone();
		vector[] v;
		turret = new polygon3D[171];
		int polyIndex = 0;

		iDirection.set(1, 0, 0);
		jDirection.set(0, 1, 0);
		kDirection.set(0, 0, 1);

		if (!firing) {
			angularSpeed--;
			if (angularSpeed < 0)
				angularSpeed = 0;
		}

		nukeCannonAngle += angularSpeed;
		nukeCannonAngle %= 360;
		iDirection.rotate_XY(nukeCannonAngle);
		jDirection.rotate_XY(nukeCannonAngle);

		int angle = (turretAngle - turretAngleDelta + 360) % 360;
		iDirection.rotate_XZ(angle);
		kDirection.rotate_XZ(angle);
		jDirection.rotate_XZ(angle);

		double r = 0.012;
		int theta = 20;

		tempVector1.set(start);

		start = put(0.017, 0.017, 0.1);
		nukeCannonRear = new polygon3D[72];
		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], -0.04),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], -0.04),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.06),
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.06) };
			nukeCannonRear[i] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[13], 0.001, 0.01, 6);
		}

		start.set(tempVector1);
		start = put(-0.017, 0.017, 0.1);
		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], -0.04),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], -0.04),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.06),
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.06) };
			nukeCannonRear[i + 18] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[13], 0.001, 0.01, 6);
		}

		start.set(tempVector1);
		start = put(-0.017, -0.017, 0.1);
		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], -0.04),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], -0.04),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.06),
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.06) };
			nukeCannonRear[i + 36] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[13], 0.001, 0.01, 6);
		}

		start.set(tempVector1);
		start = put(0.017, -0.017, 0.1);
		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], -0.04),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], -0.04),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.06),
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.06) };
			nukeCannonRear[i + 54] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[13], 0.001, 0.01, 6);
		}

		start.set(tempVector1);
		r = 0.045;
		nukeCannonMiddle = new polygon3D[19];

		v = new vector[18];
		for (int i = 1; i < 19; i++)
			v[18 - i] = put(r * GameData.cos[i * theta], r
					* GameData.sin[i * theta], 0.16);
		nukeCannonMiddle[0] = new polygon3D(v, put(-0.2, 0.2, 0), put(0.2, 0.2,
				0), put(-0.2, -0.2, 0), main.textures[13], 0.001, 0.01, 6);
		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.16),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.16),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.167),
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.167) };
			nukeCannonMiddle[i + 1] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[13], 0.001, 0.01, 6);
		}

		r = 0.012;
		tempVector1.set(start);
		start = put(0.017, 0.017, 0.1);
		nukeCannonFront = new polygon3D[72];
		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.07),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.07),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.1),
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.1) };
			nukeCannonFront[i] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[13], 0.001, 0.01, 6);
		}

		start.set(tempVector1);
		start = put(-0.017, 0.017, 0.1);
		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.07),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.07),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.1),
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.1) };
			nukeCannonFront[i + 18] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[13], 0.001, 0.01, 6);
		}

		start.set(tempVector1);
		start = put(-0.017, -0.017, 0.1);
		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.07),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.07),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.1),
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.1) };
			nukeCannonFront[i + 36] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[13], 0.001, 0.01, 6);
		}

		start.set(tempVector1);
		start = put(0.017, -0.017, 0.1);
		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.07),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.07),
					put(r * GameData.cos[(i + 1) * theta], r
							* GameData.sin[(i + 1) * theta], 0.1),
					put(r * GameData.cos[i * theta], r
							* GameData.sin[i * theta], 0.1) };
			nukeCannonFront[i + 54] = new polygon3D(v, v[0], v[1], v[3],
					main.textures[13], 0.001, 0.01, 6);
		}

		for (int i = 0; i < 72; i++) {
			turret[i] = nukeCannonRear[i];
		}

		for (int i = 0; i < 19; i++) {
			turret[72 + i] = nukeCannonMiddle[i];
		}

		for (int i = 0; i < 72; i++) {
			turret[i + 91] = nukeCannonFront[i];
		}

		polyIndex = 163;

		start = turretCenter.myClone();

		iDirection.set(1, 0, 0);
		jDirection.set(0, 0.95, 0);
		kDirection.set(0, 0, 1.05);

		iDirection.rotate_XZ(angle);
		kDirection.rotate_XZ(angle);

		v = new vector[] { put(-0.04, 0.035, 0.06), put(0.04, 0.035, 0.06),
				put(0.05, 0.035, 0.04), put(0.05, 0.035, -0.03),
				put(0.03, 0.035, -0.07), put(-0.03, 0.035, -0.07),
				put(-0.05, 0.035, -0.03), put(-0.05, 0.035, 0.04) };
		turret[0 + polyIndex] = new polygon3D(v, put(-0.04, 0.035, 0.19), put(
				0.04, 0.035, 0.19), put(-0.04, 0.035, 0.09), main.textures[13],
				0.8, 0.8, 6);

		v = new vector[] { put(0.03, 0, -0.07), put(-0.03, 0, -0.07),
				put(-0.03, 0.035, -0.07), put(0.03, 0.035, -0.07) };
		turret[1 + polyIndex] = new polygon3D(v, v[0], v[1], v[3],
				main.textures[13], 0.6, 0.3, 6);

		v = new vector[] { put(0.03, 0.035, -0.07), put(0.05, 0.035, -0.03),
				put(0.05, 0, -0.03), put(0.03, 0, -0.07) };
		turret[2 + polyIndex] = new polygon3D(v, v[0], v[1], v[3],
				main.textures[13], 0.6, 0.3, 6);

		v = new vector[] { put(-0.03, 0, -0.07), put(-0.05, 0, -0.03),
				put(-0.05, 0.035, -0.03), put(-0.03, 0.035, -0.07) };
		turret[3 + polyIndex] = new polygon3D(v, v[0], v[1], v[3],
				main.textures[13], 0.6, 0.3, 6);

		v = new vector[] { put(-0.05, 0.035, 0.04), put(-0.05, 0.035, -0.03),
				put(-0.05, 0, -0.03), put(-0.05, 0, 0.04) };
		turret[4 + polyIndex] = new polygon3D(v, v[0], v[1], v[3],
				main.textures[13], 0.5, 0.2, 6);

		v = new vector[] { put(0.05, 0, 0.04), put(0.05, 0, -0.03),
				put(0.05, 0.035, -0.03), put(0.05, 0.035, 0.04) };
		turret[5 + polyIndex] = new polygon3D(v, v[0], v[1], v[3],
				main.textures[13], 0.5, 0.2, 6);

		v = new vector[] { put(-0.04, 0.035, 0.06), put(-0.05, 0.035, 0.04),
				put(-0.05, 0, 0.04), put(-0.04, 0, 0.06) };
		turret[6 + polyIndex] = new polygon3D(v, v[0], v[1], v[3],
				main.textures[13], 0.2, 0.2, 6);

		v = new vector[] { put(0.04, 0, 0.06), put(0.05, 0, 0.04),
				put(0.05, 0.035, 0.04), put(0.04, 0.035, 0.06) };
		turret[7 + polyIndex] = new polygon3D(v, v[0], v[1], v[3],
				main.textures[13], 0.2, 0.2, 6);

		start.add(-0.05, 0, -0.02);
		start.y = -1;
		v = new vector[] { put(-0.7, 0, 0.2), put(0.7, 0, 0.2),
				put(0.7, 0, -0.2), put(-0.7, 0, -0.2) };
		shadowTurret = new polygon3D(v, v[0], v[1], v[3], main.textures[15], 1,
				1, 2);
	}

	public void update() {
		if (main.gameOver)
			return;

		if (underAttackCount > 0)
			underAttackCount--;

		if (HP < 150 && HP > 0 && underAttackCount == 0 && !main.gamePaused) {
			if (main.timer % 2 == 0)
				HP += 1;
		}

		visible = true;
		modelDrawList.register(this);

		if (turnLeft && turnRight) {
			turretAngleDelta = 0;
		} else if (turnLeft) {
			turretAngleDelta = 4;
			turretAngle += 4;
			if (turretAngle >= 360)
				turretAngle -= 360;
		} else if (turnRight) {
			turretAngleDelta = 356;
			turretAngle -= 4;
			if (turretAngle < 0)
				turretAngle += 360;
		} else {
			turretAngleDelta = 0;
		}

		if (forward && backward) {
			speed = 0;
			displacement.set(0, 0, 0);
		} else if (forward) {

			if (speed < 0.015)
				speed += 0.001;

		} else if (backward) {

			if (speed > -0.01)
				speed -= 0.001;

		} else {
			if (speed > 0 || speed < 0) {
				if (speed > 0)
					speed -= 0.002;
				if (speed < 0)
					speed += 0.002;
				if (speed > -0.002 && speed < 0.002)
					speed = 0;
			}
		}
		tempVector1.set(0, 0, 1);
		tempVector1.rotate_XZ(bodyAngle);
		displacement.set(tempVector1.x * speed, 0, tempVector1.z * speed);

		if (moveLeft && moveRight) {
			speed = 0;
			displacement.set(0, 0, 0);
		} else if (moveLeft) {

			bodyAngleDelta = 5;
			bodyAngle = (bodyAngle + bodyAngleDelta) % 360;

		} else if (moveRight) {
			bodyAngleDelta = 355;
			bodyAngle = (bodyAngle + bodyAngleDelta) % 360;

		}

		centre.add(displacement);

		boundary2D.update(displacement);

		int newPosition = (int) (boundary2D.xPos * 4)
				+ (129 - (int) (boundary2D.yPos * 4)) * 80;

		if (obstacleMap.collideWithObstacle1(this, newPosition)) {
			displacement.scale(-1);
			boundary2D.update(displacement);
			centre.add(displacement);
			displacement.set(0, 0, 0);
		} else if (obstacleMap.collideWithObstacle2(this, newPosition)) {
			displacement.scale(-1);
			boundary2D.update(displacement);
			centre.add(displacement);
			displacement.set(0, 0, 0);
		} else if (outSideBorder()) {
			displacement.scale(-1);
			boundary2D.update(displacement);
			centre.add(displacement);
			displacement.set(0, 0, 0);
		} else {
			if (!obstacleMap.isOccupied(newPosition)) {
				obstacleMap.removeObstacle2(position);
				obstacleMap.registerObstacle2(this, newPosition);
				position = newPosition;

			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++)
				boundary[i].vertex3D[j].add(displacement);
			boundary[i].update();
		}

		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(Camera.position);
		tempCentre.rotate_XZ(Camera.XZ_angle);
		tempCentre.rotate_YZ(Camera.YZ_angle);

		for (int i = 0; i < body.length; i++) {
			body[i].origin.add(displacement);
			body[i].origin.subtract(centre);
			body[i].origin.rotate_XZ(bodyAngleDelta);
			body[i].origin.add(centre);

			body[i].bottomEnd.add(displacement);
			body[i].bottomEnd.subtract(centre);
			body[i].bottomEnd.rotate_XZ(bodyAngleDelta);
			body[i].bottomEnd.add(centre);

			body[i].rightEnd.add(displacement);
			body[i].rightEnd.subtract(centre);
			body[i].rightEnd.rotate_XZ(bodyAngleDelta);
			body[i].rightEnd.add(centre);

			for (int j = 0; j < body[i].vertex3D.length; j++) {
				body[i].vertex3D[j].add(displacement);
				body[i].vertex3D[j].subtract(centre);
				body[i].vertex3D[j].rotate_XZ(bodyAngleDelta);
				body[i].vertex3D[j].add(centre);
			}

			body[i].findRealNormal();
			body[i].findDiffuse();

			body[i].update();
		}

		tempVector1.set(centre);
		tempVector1.add(-0.03, 0, -0.02);
		shadowBody.origin.add(displacement);
		shadowBody.origin.subtract(tempVector1);
		shadowBody.origin.rotate_XZ(bodyAngleDelta);
		shadowBody.origin.add(tempVector1);

		shadowBody.bottomEnd.add(displacement);
		shadowBody.bottomEnd.subtract(tempVector1);
		shadowBody.bottomEnd.rotate_XZ(bodyAngleDelta);
		shadowBody.bottomEnd.add(tempVector1);

		shadowBody.rightEnd.add(displacement);
		shadowBody.rightEnd.subtract(tempVector1);
		shadowBody.rightEnd.rotate_XZ(bodyAngleDelta);
		shadowBody.rightEnd.add(tempVector1);

		for (int j = 0; j < shadowBody.vertex3D.length; j++) {
			shadowBody.vertex3D[j].add(displacement);
			shadowBody.vertex3D[j].subtract(tempVector1);
			shadowBody.vertex3D[j].rotate_XZ(bodyAngleDelta);
			shadowBody.vertex3D[j].add(tempVector1);
		}

		shadowBody.update();
		rasterizer.rasterize(shadowBody);

		if (currentWeapon == 4) {
			makeTurretNuke();
		}

		turretCenter.add(displacement);
		tempVector1.set(turretCenter);
		turretCenter.subtract(centre);
		turretCenter.rotate_XZ(bodyAngleDelta);
		turretCenter.add(centre);
		tempVector2.set(turretCenter);
		tempVector2.subtract(tempVector1);

		for (int i = 0; i < turret.length; i++) {
			turret[i].origin.add(displacement);
			turret[i].origin.add(tempVector2);
			turret[i].origin.subtract(turretCenter);
			turret[i].origin.rotate_XZ(turretAngleDelta);
			turret[i].origin.add(turretCenter);

			turret[i].bottomEnd.add(displacement);
			turret[i].bottomEnd.add(tempVector2);
			turret[i].bottomEnd.subtract(turretCenter);
			turret[i].bottomEnd.rotate_XZ(turretAngleDelta);
			turret[i].bottomEnd.add(turretCenter);

			turret[i].rightEnd.add(displacement);
			turret[i].rightEnd.add(tempVector2);
			turret[i].rightEnd.subtract(turretCenter);
			turret[i].rightEnd.rotate_XZ(turretAngleDelta);
			turret[i].rightEnd.add(turretCenter);

			for (int j = 0; j < turret[i].vertex3D.length; j++) {
				turret[i].vertex3D[j].add(displacement);
				turret[i].vertex3D[j].add(tempVector2);
				turret[i].vertex3D[j].subtract(turretCenter);
				turret[i].vertex3D[j].rotate_XZ(turretAngleDelta);
				turret[i].vertex3D[j].add(turretCenter);
			}

			turret[i].findRealNormal();
			turret[i].findDiffuse();

			turret[i].update();
		}

		if (currentWeapon == 4) {
			Geometry.sortPolygons(nukeCannonRear, 0);
			Geometry.sortPolygons(nukeCannonFront, 0);

			int index = 0;

			for (int i = 0; i < 72; i++) {
				turret[i] = nukeCannonFront[i];
			}

			index += nukeCannonFront.length;

			for (int i = 0; i < nukeCannonMiddle.length; i++) {
				turret[i + index] = nukeCannonMiddle[i];
			}

			index += nukeCannonMiddle.length;

			for (int i = 0; i < 72; i++) {
				turret[i + index] = nukeCannonRear[i];
			}

		}

		tempVector1.set(turretCenter);
		tempVector1.add(-0.04, 0, -0.04);

		shadowTurret.origin.add(displacement);
		shadowTurret.origin.add(tempVector2);
		shadowTurret.origin.subtract(tempVector1);
		shadowTurret.origin.rotate_XZ(turretAngleDelta);
		shadowTurret.origin.add(tempVector1);

		shadowTurret.bottomEnd.add(displacement);
		shadowTurret.bottomEnd.add(tempVector2);
		shadowTurret.bottomEnd.subtract(tempVector1);
		shadowTurret.bottomEnd.rotate_XZ(turretAngleDelta);
		shadowTurret.bottomEnd.add(tempVector1);

		shadowTurret.rightEnd.add(displacement);
		shadowTurret.rightEnd.add(tempVector2);
		shadowTurret.rightEnd.subtract(tempVector1);
		shadowTurret.rightEnd.rotate_XZ(turretAngleDelta);
		shadowTurret.rightEnd.add(tempVector1);

		for (int j = 0; j < shadowTurret.vertex3D.length; j++) {
			shadowTurret.vertex3D[j].add(displacement);
			shadowTurret.vertex3D[j].add(tempVector2);
			shadowTurret.vertex3D[j].subtract(tempVector1);
			shadowTurret.vertex3D[j].rotate_XZ(turretAngleDelta);
			shadowTurret.vertex3D[j].add(tempVector1);
		}

		shadowTurret.update();
		rasterizer.rasterize(shadowTurret);

		if (currentCoolDown > 0 && !main.gamePaused)
			currentCoolDown--;
		if (firing) {
			if (currentCoolDown == 0) {
				if (currentWeapon == 1 && shells > 0) {
					shells--;
					currentCoolDown = coolDowns[currentWeapon];
					vector direction = new vector(0, 0, 1);
					direction.rotate_XZ(turretAngle);
					direction.scale(0.02);
					direction.add(turretCenter);
					projectiles.register(new shell(direction.x, direction.y,
							direction.z, turretAngle, false, 0));
					if (shells == 0)
						outOfAmmo = true;

				} else if (currentWeapon == 2 && rockets > 0) {
					rockets--;
					currentCoolDown = coolDowns[currentWeapon];
					vector tempVector1 = new vector(0, 0, 1);
					tempVector1.rotate_XZ((turretAngle + 270) % 360);
					tempVector1.scale(0.08);
					vector direction = new vector(0, 0, 1);
					direction.rotate_XZ(turretAngle);
					direction.scale(0.05);
					direction.add(turretCenter);
					direction.add(tempVector1);
					projectiles.register(new rocket(direction.x, direction.y,
							direction.z, turretAngle, false));
					if (rockets == 0)
						outOfAmmo = true;
				} else if (currentWeapon == 3 && slugs > 0) {
					slugs--;
					currentCoolDown = coolDowns[currentWeapon];
					vector direction = new vector(0, 0, 1);
					direction.rotate_XZ(turretAngle);
					direction.scale(0.1);
					direction.add(turretCenter);
					new railgunTail(direction, turretAngle, false);
					if (slugs == 0)
						outOfAmmo = true;

				} else if (currentWeapon == 4 && plasma > 0) {
					if (angularSpeed >= 30) {
						plasma--;
						currentCoolDown = coolDowns[currentWeapon];
						vector direction = new vector(0, 0, 1);
						direction.rotate_XZ(turretAngle);
						direction.scale(0.02);
						direction.add(turretCenter);
						projectiles
								.register(new shell(direction.x, direction.y,
										direction.z, (turretAngle + main.timer
												% 8 + 356) % 360, false, 1));

					} else {

						angularSpeed += 1;
						if (angularSpeed > 30)
							angularSpeed = 30;
					}

					if (plasma == 0)
						outOfAmmo = true;
				}

				if (outOfAmmo) {
					if (shells > 0 && currentWeapon != 1)
						changeWeapon(1);
					else if (rockets > 0 && currentWeapon != 2)
						changeWeapon(2);
					else if (slugs > 0 && currentWeapon != 3)
						changeWeapon(3);
					else if (plasma > 0 && currentWeapon != 4)
						changeWeapon(4);
				}

			}
		}

		if (currentWeapon == 1)
			currentAmmo = shells;
		if (currentWeapon == 2)
			currentAmmo = rockets;
		if (currentWeapon == 3)
			currentAmmo = slugs;
		if (currentWeapon == 4)
			currentAmmo = plasma;

		forward = false;
		backward = false;
		turnRight = false;
		turnLeft = false;
		moveRight = false;
		moveLeft = false;
		bodyAngleDelta = 0;
		turretAngleDelta = 0;
		displacement.reset();
		firing = false;
		outOfAmmo = false;

	}

	public void draw() {
		if (main.gameOver)
			return;

		for (int i = 0; i < body.length; i++) {
			body[i].draw();
		}

		for (int i = 0; i < turret.length; i++) {
			turret[i].draw();
		}

	}

	public boolean outSideBorder() {
		double angle = 0;
		int length = terrain.border.vertex3D.length;
		for (int i = 0; i < length; i++) {
			tempVector1.set(terrain.border.vertex3D[i]);
			tempVector1.subtract(centre);
			tempVector2.set(terrain.border.vertex3D[(i + 1 + length) % length]);
			tempVector2.subtract(centre);
			double dot = tempVector1.dot(tempVector2);
			dot = dot / (tempVector1.getLength()) / (tempVector2.getLength());
			angle += Math.acos(dot);
		}
		return Math.PI * 2 - angle > 0.01;

	}

	public void changeWeapon(int weapon) {
		if (weapon == -1) {
			weapon = (currentWeapon + 1) % 5;
			if (weapon == 0)
				weapon = 1;
		}

		if (weapon == 1 && shells > 0) {
			currentWeapon = 1;
			makeTurretCannon();
		}
		if (weapon == 1 && shells == 0) {
			weapon = 2;
		}

		if (weapon == 2 && rockets > 0) {
			currentWeapon = 2;
			makeTurretRocket();
		}
		if (weapon == 2 && rockets == 0) {
			weapon = 3;
		}

		if (weapon == 3 && slugs > 0) {
			currentWeapon = 3;
			makeTurretRailgun();
		}
		if (weapon == 3 && slugs == 0) {
			weapon = 4;

		}

		if (weapon == 4 && plasma > 0) {
			if (currentWeapon != 4) {
				currentWeapon = 4;
				makeTurretNuke();
				angularSpeed = 0;
			}

		}
		if (weapon == 4 && plasma == 0) {
			currentWeapon = 1;
			makeTurretCannon();
		}

	}

	public void damage(int damagePoint) {
		HP -= (damagePoint * 0.8);
		if (HP <= 0) {
			HP = 0;
			projectiles.register(new Explosion(centre.x, centre.y, centre.z,
					1.7));
			centre.x = 1000;

			main.gameOver = true;
		}
		if (damagePoint > 0)
			underAttackCount = 100;

	}

}
