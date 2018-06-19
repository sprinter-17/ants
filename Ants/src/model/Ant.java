package model;

import java.util.EnumMap;
import java.util.Random;

public class Ant {
	private static final Random random = new Random();
	private final Position nest;
	private Position position = Position.ORIGIN;
	private Direction facing = Direction.random();
	private int age = 0;

	public Ant(Position nest) {
		this.nest = nest;
		this.position = nest;
	}

	@Override
	public String toString() {
		return position + "->" + facing;
	}

	public Position getPosition() {
		return position;
	}

	public void step(Floor floor) {
		age++;
		facing = facing.add(nextAngle(floor));
		position = position.move(facing);
		if (position.distanceTo(Position.ORIGIN) > 150)
			facing = facing.add(Angle.BACKWARD);
		// position = position.negate().move(facing);
	}

	public Angle nextAngle(Floor floor) {
		EnumMap<Angle, Integer> probabilities = new EnumMap<>(Angle.class);
		putAngle(floor, Angle.FORWARD, 80, probabilities);
		putAngle(floor, Angle.LEFT_60, 10, probabilities);
		putAngle(floor, Angle.RIGHT_60, 10, probabilities);
		int total = random.nextInt(probabilities.values().stream().mapToInt(i -> i).sum());
		for (Angle angle : probabilities.keySet()) {
			total -= probabilities.get(angle);
			if (total < 0)
				return angle;
		}
		throw new IllegalStateException();
	}

	private void putAngle(Floor floor, Angle angle, int base, EnumMap<Angle, Integer> probabilities) {
		probabilities.put(angle, base + getSmell(floor, angle));
	}

	private int getSmell(Floor floor, Angle angle) {
		return floor.getSmell(position.move(facing.add(angle)));
	}

	public boolean isNest(Position other) {
		return nest.equals(other);
	}

	public boolean isDead() {
		return age > 1000;
	}

	public Integer getAge() {
		return age;
	}
}
