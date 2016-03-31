public class powerUps {
	public static powerUp[] PU;

	public static void init() {
		PU = new powerUp[100];

		register(new powerUp(18.15, -0.875, 16.575, 4));
		register(new powerUp(18.6, -0.875, 16.575, 4));
		register(new powerUp(18.6, -0.875, 16.15, 4));
		register(new powerUp(18.15, -0.875, 16.15, 4));
		register(new powerUp(18.37, -0.875, 16.37, 4));

		register(new powerUp(4.15, -0.875, 14.175, 4));
		register(new powerUp(4.6, -0.875, 14.175, 4));
		register(new powerUp(4.6, -0.875, 13.65, 4));
		register(new powerUp(4.15, -0.875, 13.65, 4));
		register(new powerUp(4.37, -0.875, 13.87, 4));
	}

	public static void update() {

		for (int i = 0; i < PU.length; i++) {
			if (PU[i] != null) {
				PU[i].update();
				if (rectangle2D.testIntersection(PU[i].boundary2D,
						main.PT.boundary2D)) {
					if (PU[i].type == 1) {
						if (playerTank.shells == 0) {
							playerTank.shells += 10;
							main.PT.changeWeapon(1);
						} else {
							playerTank.shells += 10;
						}
					}
					if (PU[i].type == 2) {
						if (playerTank.rockets == 0) {
							playerTank.rockets += 10;
							main.PT.changeWeapon(2);

						} else {
							playerTank.rockets += 10;
						}
					}
					if (PU[i].type == 3) {
						if (playerTank.slugs == 0) {
							playerTank.slugs += 10;
							main.PT.changeWeapon(3);
						} else {
							playerTank.slugs += 10;
						}
					}

					if (PU[i].type == 4) {
						if (playerTank.plasma == 0) {
							playerTank.plasma += 10;
							main.PT.changeWeapon(4);
						} else {
							playerTank.plasma += 10;
						}
					}

					PU[i] = null;

				}
			}
		}
	}

	public static void register(powerUp P) {
		for (int i = 0; i < PU.length; i++) {
			if (PU[i] == null) {
				PU[i] = P;
				return;
			}
		}
	}
}
