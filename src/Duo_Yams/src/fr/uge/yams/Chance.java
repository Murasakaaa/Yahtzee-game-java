package fr.uge.yams;

import java.util.Objects;

public record Chance(Boolean sacrificed) implements Combination {

	@Override
	public int score(Board board) {
		Objects.requireNonNull(board);
		if(sacrificed) {
			return 0;
		}
		var score = 0;
		for(Dice dice: board.fiveDice()) {
			score += dice.value();
		}
		return score;
	}

	@Override
    public boolean equals(Object o) {
        return o instanceof Chance;
    }

    @Override
    public int hashCode() {
        return Chance.class.hashCode();
    }
    
	@Override
	public String toString() {

		return "Chance";
	}

}
