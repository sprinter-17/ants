package model;

public class Position {
	private static final double SQRT3 = Math.sqrt(3);
	public static final Position ORIGIN = new Position(0, 0, 0);
	private final int x;
	private final int y;
	private final int z;

	protected Position(int x, int y, int z) {
		assert x + y + z == 0 : "coords do not add to 0";
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}

	public double getNorth() {
		return y * 1.5;
	}

	public double getEast() {
		return SQRT3 * (x + y / 2.0);
	}

	public Position add(Position other) {
		return new Position(this.x + other.x, this.y + other.y, this.z + other.z);
	}

	public Position times(int factor) {
		return new Position(x * factor, y * factor, z * factor);
	}

	public Position rotate(Angle angle) {
		return angle.rotate(x, y, z);
	}

	public Position move(Direction direction) {
		return add(direction.delta);
	}

	public Position move(Direction direction, int distance) {
		return add(direction.delta.times(distance));
	}

	public int distanceTo(Position other) {
		return Math.max(Math.abs(x - other.x), Math.max(Math.abs(y - other.y), Math.abs(z - other.z)));
	}

	public Position negate() {
		return new Position(-x, -y, -z);
	}

	@Override
	public int hashCode() {
		return x * 31 + y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return x == other.x && y == other.y && z == other.z;
	}
}
