package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class Floor {
	private int time = 0;
	private final Map<Position, Integer> smell = new HashMap<>();
	private final List<Position> nests = new ArrayList<>();
	private final List<Ant> ants = new ArrayList<>();

	public void addNest(Position position) {
		nests.add(position);
	}

	public Stream<Position> getNests() {
		return nests.stream();
	}

	public void tick() {
		if (time % 50 == 0)
			nests.stream().map(Ant::new).forEach(ants::add);
		time++;
		ants.forEach(a -> a.step(this));
		ants.stream().filter(a -> a.getAge() > 2).forEach(a -> deposit(a.getPosition()));
		ants.removeIf(Ant::isDead);
		ants.removeIf(a -> nests.stream().filter(n -> !a.isNest(n)).mapToInt(p -> p.distanceTo(a.getPosition())).anyMatch(i -> i < 5));
		smell.keySet().forEach(pos -> smell.put(pos, smell.get(pos) - 1));
		smell.keySet().removeIf(pos -> smell.get(pos) == 0);
	}

	public int getTime() {
		return time;
	}

	public int getAntCount() {
		return ants.size();
	}

	public void deposit(Position position) {
		smell.merge(position, 300, (n1, n2) -> Math.min(1000, n1 + n2));
	}

	public int getSmell(Position position) {
		return Math.min(100, smell.getOrDefault(position, 0));
	}

	public void forEachSmell(BiConsumer<Position, Integer> action) {
		smell.forEach(action);
	}

	@Override
	public String toString() {
		return smell.toString();
	}
}
