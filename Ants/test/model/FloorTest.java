package model;

import org.junit.jupiter.api.Test;

public class FloorTest {
	@Test
	void testSteps() {
		Floor floor = new Floor();
		floor.addNest(Position.ORIGIN.move(Direction.E, 50));
		for (int i = 0; i < 1000; i++) {
			floor.tick();
		}
		System.out.println(floor);
	}
}
