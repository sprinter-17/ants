package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PositionTest {
	@Test
	void testIllegalPositions() {
		assertThatThrownBy(() -> new Position(5, 4, 2));
		assertThatThrownBy(() -> new Position(0, -1, 0));
	}

	@Test
	void testNegate() {
		Position position = new Position(34, -19, -15);
		assertThat(position.add(position.negate())).isEqualTo(Position.ORIGIN);
		assertThat(Position.ORIGIN.negate()).isEqualTo(Position.ORIGIN);
	}

	@Test
	void testNorth() {
		assertThat(Position.ORIGIN.move(Direction.NW, 3).getNorth()).isCloseTo(+4.5, within(.01));
		assertThat(Position.ORIGIN.move(Direction.NE, 3).getNorth()).isCloseTo(+4.5, within(.01));
		assertThat(Position.ORIGIN.move(Direction.SW, 3).getNorth()).isCloseTo(-4.5, within(.01));
		assertThat(Position.ORIGIN.move(Direction.SE, 3).getNorth()).isCloseTo(-4.5, within(.01));
	}

	@Test
	void testEast() {
		assertThat(Position.ORIGIN.move(Direction.E, 7).getEast()).isCloseTo(Math.sqrt(3) * 7, within(.01));
		assertThat(Position.ORIGIN.move(Direction.NE, 7).getEast()).isCloseTo(Math.sqrt(3) * 3.5, within(.01));
		assertThat(Position.ORIGIN.move(Direction.SE, 7).getEast()).isCloseTo(Math.sqrt(3) * 3.5, within(.01));
		assertThat(Position.ORIGIN.move(Direction.SW, 7).getEast()).isCloseTo(Math.sqrt(3) * -3.5, within(.01));
	}

	@Test
	void testDistance() {
		assertThat(Position.ORIGIN.move(Direction.E).distanceTo(Position.ORIGIN)).isEqualTo(1);
		assertThat(Position.ORIGIN.move(Direction.NE, 7).distanceTo(Position.ORIGIN)).isEqualTo(7);
		assertThat(Position.ORIGIN.move(Direction.NE, 7).move(Direction.W, 5).distanceTo(Position.ORIGIN)).isEqualTo(7);
		assertThat(Position.ORIGIN.move(Direction.NE, 7).move(Direction.W, 15).distanceTo(Position.ORIGIN)).isEqualTo(15);
	}
}
