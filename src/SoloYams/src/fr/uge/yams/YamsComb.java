package fr.uge.yams;

import java.util.Objects;

public record YamsComb(Boolean sacrificed) implements Combination {

	@Override
	public int score(Board board) {
		Objects.requireNonNull(board);
		if(sacrificed) {
			return 0;
		}
		var firstElem = board.fiveDice().getFirst().value();
		for(int i=1; i<5; i++) {
			System.out.println("Dé : " + board.fiveDice().get(i));
			if(board.fiveDice().get(i).value() != firstElem)  {
				System.out.println("\nVous n'avez pas les dés nécessaires pour réaliser un Yam's (Yams)");
				return 0;
			}
		}
		return 50;
		
		
	}

	
	@Override
    public boolean equals(Object o) {
        return o instanceof YamsComb;
    }

    @Override
    public int hashCode() {
        return YamsComb.class.hashCode();
    }
    
	@Override
	public String toString() {

		return "Yams";
	}

}
