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
 *  along with BattleTank 2. If not, see <http://www.gnu.org/licenses/>
 */

public class Geometry {
	public static boolean compareModels(Model a, Model b) {
		//Compare centre Z depth values
		boolean resultCompareModels;
		if (a.getZDepth() > b.getZDepth()) {
			resultCompareModels = true;
		} else {
			resultCompareModels = false;
		}
		return resultCompareModels;
	}
	//Sort polygons
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
		boolean resultComparePolygons = false; 
		if (!a.visible) {
			resultComparePolygons = false;
		} else {
			// Does nothing.
		}
		
		if (!b.visible) {
			resultComparePolygons = true;
		} else {
			// Does nothing.
		}

		if (a.getTempVertex()[0].z < b.getTempVertex()[0].z
				&& a.getTempVertex()[1].z < b.getTempVertex()[1].z
				&& a.getTempVertex()[2].z < b.getTempVertex()[2].z
				&& a.getTempVertex()[3].z < b.getTempVertex()[3].z) {
			resultComparePolygons = true;
		} else {
			// Does nothing.
		}
		
		Vector tempVector = new Vector(0, 0, 0);

		boolean inside = true;
		for (int i = 0; i < b.getTempVertex().length; i++) {
			tempVector.set(b.getTempVertex()[i]);
			tempVector.subtract(a.getCentre());
			tempVector.unit();

			if (tempVector.dot(a.getNormal()) > 0.0001) {
				inside = false;

			} else {
				// Does nothing.
			}

		}
		
		if (inside) {
			resultComparePolygons =  true;
		} else {
			// Does nothing.
		}
		
		inside = true;
		for (int i = 0; i < a.getTempVertex().length; i++) {
			tempVector.set(a.getTempVertex()[i]);
			tempVector.subtract(b.getCentre());
			tempVector.unit();

			if (tempVector.dot(b.getNormal()) < -0.0001) {
				inside = false;
			} else {
				// Does nothing.
			}
		}
		
		return resultComparePolygons;
	}
}
