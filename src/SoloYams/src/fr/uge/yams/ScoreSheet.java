package fr.uge.yams;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ScoreSheet {

	private final static HashMap<Combination, Integer> scoreMap = new HashMap<>();

	public void updateScore(Combination pattern, Board board) {
		Objects.requireNonNull(pattern);
		Objects.requireNonNull(board);
		if (scoreMap.containsKey(pattern)) {
			// throw new IllegalArgumentException("already a score for this combination");
			System.out.println("already a score for this combination");
		}

		scoreMap.put(pattern, pattern.score(board));
		
	}

	public int scoreTotal() {

		return scoreMap.values().stream().mapToInt(Integer::intValue).sum();
	}

	
	
	public static boolean alreadyUsed(Combination combinaison) {
		Objects.requireNonNull(combinaison);
		return scoreMap.containsKey(combinaison);
	}
	
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔═════════════════════════╗\n");
        sb.append("║   Combination   ║ score ║\n");
        sb.append("║═════════════════════════║\n");
        
        for (Map.Entry<Combination, Integer> entry : scoreMap.entrySet()) {
            sb.append(String.format("║ %-15s ║ %5d ║\n", entry.getKey().toString(), entry.getValue()));
        }
        
        sb.append("╚═════════════════════════╝");
        return sb.toString();
    }

}
