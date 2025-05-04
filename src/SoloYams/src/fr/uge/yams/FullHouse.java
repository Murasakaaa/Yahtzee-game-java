package fr.uge.yams;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.IntStream;

public record FullHouse(Boolean sacrificed) implements Combination {

	@Override
	public int score(Board board) {
		Objects.requireNonNull(board);
		if(sacrificed) {
			return 0;
		}
		var counter = new HashMap<Integer, Integer>();  // Compteur permettant de compter le nb d'occurence de chaque dé
		
		for(int i=0;  i<7; i++) {   // Initialisation du compteur {n°:0}
			counter.put(i, 0);
		}
		for(Dice dice: board.fiveDice()) {
			counter.put(dice.value(), counter.getOrDefault(dice.value(), 0) + 1);
		}
		for(Integer nb : counter.values()) {
			if(IntStream.of(0, 2, 3).noneMatch(n -> n == nb)) {  // Si le joueur n'a pas les dé nécessaires pour réaliser un full
				System.out.println("\nVous n'avez pas les dés nécessaires pour réaliser un Full (FullHouse)");
				return 0;
			}
		}
		return 25;

	}
	@Override
    public boolean equals(Object o) {
        return o instanceof FullHouse;
    }

    @Override
    public int hashCode() {
        return FullHouse.class.hashCode();
    }

	@Override
	public String toString() {

		return "Full House";
	}

}
