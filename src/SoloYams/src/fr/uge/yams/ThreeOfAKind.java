package fr.uge.yams;

import java.util.HashMap;
import java.util.Objects;

public record ThreeOfAKind(Boolean sacrificed) implements Combination {

	@Override
	public int score(Board board) {
		Objects.requireNonNull(board);
		if(sacrificed) {
			return 0;
		}
		var counter = new HashMap<Integer, Integer>();  // Compteur permettant de compter le nb d'occurence de chaque dé
		var valid = false;
		var score = 0;
		for(int i=0;  i<7; i++) {
			counter.put(i, 0);
		}
		for(Dice dice: board.fiveDice()) {
			counter.put(dice.value(), counter.getOrDefault(dice.value(), 0) + 1);
			score += dice.value();
			if(counter.get(dice.value()) >= 3) {
				valid = true;
			}
		}
		if(valid) {
			return score;
		}
		else {
			System.out.println("\nVous n'avez pas les dés nécessaires pour réaliser un brelan (ThreeOfAKind)");
			return 0;
		}
		
	}
	
	
	@Override
    public boolean equals(Object o) {
        return o instanceof ThreeOfAKind;
    }

    @Override
    public int hashCode() {
        return ThreeOfAKind.class.hashCode();
    }

	@Override
	public String toString() {
		return "Three of A Kind";
	}

}
