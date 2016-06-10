package project;
public class GameEventHandler {

	public static void processEvent() {

		if (Main.Terrain.powerPlants[0] == null
				&& Main.Terrain.powerPlants[1] == null
				&& Main.Terrain.fences[26] != null) {
			for (int i = 26; i < 38; i++) {
				Main.Terrain.fences[i].destroy();
				Main.Terrain.fences[i] = null;
			}
		} else {
			// Does nothing.
		}

		if (Main.Terrain.powerPlants[5] == null
				&& Main.Terrain.powerPlants[6] == null
				&& Main.Terrain.fences[14] != null) {
			for (int i = 14; i < 26; i++) {
				Main.Terrain.fences[i].destroy();
				Main.Terrain.fences[i] = null;
			}
		} else {
			// Does nothing.
		}

		if (Main.Terrain.powerPlants[0] == null
				&& Main.Terrain.powerPlants[1] == null
				&& Main.Terrain.powerPlants[5] == null
				&& Main.Terrain.powerPlants[6] == null
				&& Main.Terrain.fences[0] != null) {
			for (int i = 0; i < 14; i++) {
				Main.Terrain.fences[i].destroy();
				Main.Terrain.fences[i] = null;
			}

			for (int i = 38; i < 43; i++) {
				Enemies.enemy[i].damage(-1);
			}

			for (int i = 96; i < 107; i++) {
				Enemies.enemy[i].damage(-1);
			}

		} else {
			// Does nothing.
		}

		if (Main.Terrain.powerPlants[2] == null
				&& Main.Terrain.powerPlants[3] == null
				&& Main.Terrain.powerPlants[4] == null
				&& Main.Terrain.fences[39] != null) {
			for (int i = 38; i < 62; i++) {
				Main.Terrain.fences[i].destroy();
				Main.Terrain.fences[i] = null;
			}
			Enemies.enemy[107].damage(-1);
			Enemies.enemy[108].damage(-1);

		} else {
			// Does nothing.
		} 

		if (Main.gameNotStart == false) {
			if (Enemies.enemy[107] == null && Enemies.enemy[108] == null) {
				Main.win = true;
				Main.PT.HP = 100;
			} else {
				// Does nothing.
			} 

		} else {
			// Does nothing.
		}

	}

}
