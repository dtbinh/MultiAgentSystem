package particles;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Direction {

	private int x;
	private int y;

	public void getRebound() {
		x = -x;
		y = -y;
	}
}
