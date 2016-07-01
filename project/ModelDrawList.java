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
 * Class responsable to do a list of 3D models which will be sent 
 * to the drawing pipeline during a game loop
 */
public class ModelDrawList {
	
	//List of the visible models
	public static Model[] visibleModels;
	
	//the number of models
	public static int modelCount;

	//create a list
	public static void makeList() {
		visibleModels = new Model[300];
		modelCount = 0;
	}

	//insert a model into the list
	public static void register(Model m) {
		for (int i = 0; i < visibleModels.length; i++) {
			if (visibleModels[i] == null) {
				visibleModels[i] = m;
				modelCount++;
				break;
			} else {
				//Does nothing.
			}
		}
	}

	//draw models
	public static void draw() {
		for (int i = 0; i < modelCount; i++) {
			visibleModels[i].draw();
		}
	}

	/**
	 * Sort models so the closer object will get drawn later than further objects
	 * If the models all have cubic shapes, the culling algorithm compares the Z value of their centre vectors,
	 * otherwise a more complicated culling algrithm will be used(based their boundary volumes).
	 */
	public static void sort() {

		for (int i = 1; i < modelCount; i++) {
			for (int j = 0; j < modelCount - i; j++) {
				if (Geometry.compareModels(visibleModels[j + 1],
						visibleModels[j])) {
					Model temp = visibleModels[j + 1];
					visibleModels[j + 1] = visibleModels[j];
					visibleModels[j] = temp;
				} else {
					//Does nothing.
				}
			}
		}
	}
}
