package model;

public enum Angle {
	FORWARD((x, y, z) -> new Position(x, y, z)),
	RIGHT_60((x, y, z) -> new Position(-z, -x, -y)),
	RIGHT_120((x, y, z) -> new Position(y, z, x)),
	BACKWARD((x, y, z) -> new Position(-x, -y, -z)),
	LEFT_120((x, y, z) -> new Position(z, x, y)),
	LEFT_60((x, y, z) -> new Position(-y, -z, -x));

	@FunctionalInterface
	private interface Rotation {
		Position rotate(int x, int y, int z);
	}

	private final Rotation rotation;

	private Angle(Rotation rotation) {
		this.rotation = rotation;
	}

	public Position rotate(int x, int y, int z) {
		return rotation.rotate(x, y, z);
	}
}