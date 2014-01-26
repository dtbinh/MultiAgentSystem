package particles;

import lombok.AllArgsConstructor;
import lombok.Data;
import core.Coordonnees;

@Data
@AllArgsConstructor
public class Direction {

	private int x;
	private int y;

	public static Direction getRandomDirection() {
		int x = (int) Math.random() * 3;
		x--;
		int y = (int) Math.random() * 3;
		y--;

		return new Direction(x, y);
	}

	public void getRebound(final Coordonnees next, final int sup) {
		if (next.getX() == 0 || next.getX() == sup) {
			x = -x;
		}
		if (next.getY() == 0 || next.getY() == sup) {
			y = -y;
		}
	}
}
