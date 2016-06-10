package project;
public class ModelDrawList {
	public static Model[] visibleModels;
	public static int modelCount;

	public static void makeList() {
		visibleModels = new Model[300];
		modelCount = 0;
	}

	public static void register(Model m) {
		for (int i = 0; i < visibleModels.length; i++) {
			if (visibleModels[i] == null) {
				visibleModels[i] = m;
				modelCount++;
				break;
			}
		}
	}

	public static void draw() {
		for (int i = 0; i < modelCount; i++) {
			visibleModels[i].draw();
		}
	}

	public static void sort() {

		for (int i = 1; i < modelCount; i++) {
			for (int j = 0; j < modelCount - i; j++) {
				if (Geometry.compareModels(visibleModels[j + 1],
						visibleModels[j])) {
					Model temp = visibleModels[j + 1];
					visibleModels[j + 1] = visibleModels[j];
					visibleModels[j] = temp;
				}
			}
		}
	}
}
