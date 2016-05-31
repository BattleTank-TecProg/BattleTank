public class PowerUps {
	public static PowerUp[] PU;

	public static void init() {
		PU = new PowerUp[100];

		register(new PowerUp(18.15, -0.875, 16.575, 4));
		register(new PowerUp(18.6, -0.875, 16.575, 4));
		register(new PowerUp(18.6, -0.875, 16.15, 4));
		register(new PowerUp(18.15, -0.875, 16.15, 4));
		register(new PowerUp(18.37, -0.875, 16.37, 4));

		register(new PowerUp(4.15, -0.875, 14.175, 4));
		register(new PowerUp(4.6, -0.875, 14.175, 4));
		register(new PowerUp(4.6, -0.875, 13.65, 4));
		register(new PowerUp(4.15, -0.875, 13.65, 4));
		register(new PowerUp(4.37, -0.875, 13.87, 4));
	}

	public static void update() {

		for (int i = 0; i < PU.length; i++) {
			if (PU[i] != null) {
				PU[i].update();
				if (Rectangle2D.testIntersection(PU[i].boundary2D,
						Main.PT.boundary2D)) {
					if (PU[i].type == 1) {
						if (PlayerTank.shells == 0) {
							PlayerTank.shells += 10;
							Main.PT.changeWeapon(1);
						} else {
							PlayerTank.shells += 10;
						}
					}
					if (PU[i].type == 2) {
						if (PlayerTank.rockets == 0) {
							PlayerTank.rockets += 10;
							Main.PT.changeWeapon(2);

						} else {
							PlayerTank.rockets += 10;
						}
					}
					if (PU[i].type == 3) {
						if (PlayerTank.slugs == 0) {
							PlayerTank.slugs += 10;
							Main.PT.changeWeapon(3);
						} else {
							PlayerTank.slugs += 10;
						}
					}

					if (PU[i].type == 4) {
						if (PlayerTank.plasma == 0) {
							PlayerTank.plasma += 10;
							Main.PT.changeWeapon(4);
						} else {
							PlayerTank.plasma += 10;
						}
					}

					PU[i] = null;

				}
			}
		}
	}

	public static void register(PowerUp P) {
		for (int i = 0; i < PU.length; i++) {
			if (PU[i] == null) {
				PU[i] = P;
				i = PU.length;
			}
		}
	}
}
