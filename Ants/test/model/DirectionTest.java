package model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DirectionTest {
	@Test
	void testDirections() {
		assertThat(Direction.E.add(Angle.LEFT_60)).isEqualTo(Direction.NE);
		assertThat(Direction.W.add(Angle.FORWARD)).isEqualTo(Direction.W);
		assertThat(Direction.NE.add(Angle.RIGHT_120)).isEqualTo(Direction.SE);
	}

}
