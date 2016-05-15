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
 *  along with BattleTank 2. If not, see <http://www.gnu.org/licenses/>
 */

public class Geometry {
	public static boolean compareModels(model a, model b) {

		if (a.getZDepth() > b.getZDepth()) {
			return true;
		} else {
			return false;
		}
	}

	public final static void sortPolygons(Polygon3D[] polygons, int start) {
		int length = polygons.length;
		for (int i = 1; i < length; i++) {
			for (int j = start; j < length - i; j++) {
				if (Geometry.comparePolygons(polygons[j], polygons[j + 1])) {
					Polygon3D temp = polygons[j + 1];
					polygons[j + 1] = polygons[j];
					polygons[j] = temp;
				} else {
					// Does nothing.
				}
			}
		}
	}

	public final static boolean comparePolygons(Polygon3D a, Polygon3D b) {
		if (!a.visible) {
			return false;
		} else {
			// Does nothing.
		}
		
		if (!b.visible) {
			return true;
		} else {
			// Does nothing.
		}

		if (a.tempVertex[0].z < b.tempVertex[0].z
				&& a.tempVertex[1].z < b.tempVertex[1].z
				&& a.tempVertex[2].z < b.tempVertex[2].z
				&& a.tempVertex[3].z < b.tempVertex[3].z) {
			return true;
		} else {
			// Does nothing.
		}
		
		Vector tempVector = new Vector(0, 0, 0);

		boolean inside = true;
		for (int i = 0; i < b.tempVertex.length; i++) {
			tempVector.set(b.tempVertex[i]);
			tempVector.subtract(a.centre);
			tempVector.unit();

			if (tempVector.dot(a.normal) > 0.0001) {
				inside = false;
				break;
			} else {
				// Does nothing.
			}

		}
		
		if (inside) {
			return true;
		} else {
			// Does nothing.
		}
		
		inside = true;
		for (int i = 0; i < a.tempVertex.length; i++) {
			tempVector.set(a.tempVertex[i]);
			tempVector.subtract(b.centre);
			tempVector.unit();

			if (tempVector.dot(b.normal) < -0.0001) {
				inside = false;
				break;
			} else {
				// Does nothing.
			}
		}

		if (inside) {
			return true;
		} else {
			// Does nothing.
		}
		
		return false;
	}
}
