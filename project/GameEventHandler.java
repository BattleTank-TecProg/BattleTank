package project;
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
 * This class processes in game events one example of events can be
 * activate/disactivate an enemy, open a gate etc...
 */
public class GameEventHandler {

	public static void processEvent() {
		/*
		 * Open the West energy gates when the power plants in the north West
		 * are destroyed
		 */

		if (Main.Terrain.powerPlants[0] == null && Main.Terrain.powerPlants[1] == null
				&& Main.Terrain.fences[26] != null) {
			for (int i = 26; i < 38; i++) {
				Main.Terrain.fences[i].destroy();
				Main.Terrain.fences[i] = null;
			}
		} else {
			// Does nothing.
		}
		/*
		 * Open the east energy gates when the power plants in the north east
		 * are destroyed
		 */
		if (Main.Terrain.powerPlants[5] == null && Main.Terrain.powerPlants[6] == null
				&& Main.Terrain.fences[14] != null) {
			for (int i = 14; i < 26; i++) {
				Main.Terrain.fences[i].destroy();
				Main.Terrain.fences[i] = null;
			}
		} else {
			// Does nothing.
		}

		/*
		 * open the energy gates of the main base when all the outer power
		 * plants are destroyed also activate the tanks inside the base
		 */
		if (Main.Terrain.powerPlants[0] == null && Main.Terrain.powerPlants[1] == null
				&& Main.Terrain.powerPlants[5] == null && Main.Terrain.powerPlants[6] == null
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

		/*
		 * open the inner energy gates when all the inner power plants are
		 * destoryed. also activate boss tanks
		 */
		if (Main.Terrain.powerPlants[2] == null && Main.Terrain.powerPlants[3] == null
				&& Main.Terrain.powerPlants[4] == null && Main.Terrain.fences[39] != null) {
			for (int i = 38; i < 62; i++) {
				Main.Terrain.fences[i].destroy();
				Main.Terrain.fences[i] = null;
			}
			Enemies.enemy[107].damage(-1);
			Enemies.enemy[108].damage(-1);

		} else {
			// Does nothing.
		}

		// The game is beaten when both annihilators are destroyed

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
