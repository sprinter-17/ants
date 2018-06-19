package model;

import org.junit.jupiter.api.Test;

public class AntTest {

	@Test
	void randomWalk() {
		Floor floor = new Floor();
		Ant ant = new Ant(Position.ORIGIN);
		for (int i = 0; i < 300; i++) {
			ant.step(floor);
			System.out.println(ant);
		}
		System.out.println(floor);
	}
}
