package model;

import java.util.Random;

public enum Direction {
	E(new Position(+1, 0, -1)),
	SE(new Position(+1, -1, 0)),
	SW(new Position(0, -1, +1)),
	W(new Position(-1, 0, +1)),
	NW(new Position(-1, +1, 0)),
	NE(new Position(0, +1, -1));

	private static Random random = new Random();
	final Position delta;

	public static Direction random() {
		return values()[random.nextInt(6)];
	}

	private Direction(Position delta) {
		this.delta = delta;
	}

	public Direction add(Angle angle) {
		return values()[(ordinal() + angle.ordinal()) % 6];
	}
}