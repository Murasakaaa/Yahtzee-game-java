package fr.uge.yams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ScoreSheet {

	private final static HashMap<Combination, Integer> scoreMap = new HashMap<>();
	private final List<Combination> unusedCombinations = new ArrayList<>(List.of(new Chance(false), new ThreeOfAKind(false), new FourOfAKind(false),new FullHouse(false),new SmallStraight(false),new LargeStraight(false), new YamsComb(false)));

	public void updateScore(Combination pattern, Board board) {
		Objects.requireNonNull(pattern);
		Objects.requireNonNull(board);
		if (scoreMap.containsKey(pattern)) {
			// throw new IllegalArgumentException("already a score for this combination");
			System.out.println("Already a score for this combination");
		}
		else {
			scoreMap.put(pattern, pattern.score(board));
			unusedCombinations.remove(pattern);
		}
	}
	
	
	
	public List<Combination> unusedCombinations() {
		return unusedCombinations;
	}
	
	public int scoreTotal() {

		return scoreMap.values().stream().mapToInt(Integer::intValue).sum();
	}
	

	
	
	public boolean alreadyUsed(Combination combinaison) {
		Objects.requireNonNull(combinaison);
		return scoreMap.containsKey(combinaison);
	}
	
	
	public List<Object> bestCombinationPossible(Board board) {
		var bestScore = unusedCombinations.get(0).score(board);
		var bestCombination = unusedCombinations.get(0);
		for(Combination comb: unusedCombinations) {
			if (comb.score(board) >= bestScore) {
				bestScore = comb.score(board);
				bestCombination = comb;
			}
		}
		return new ArrayList<>(List.of(bestCombination, bestScore));
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
