package fr.uge.yams;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;



public record SmallStraight(Boolean sacrificed) implements Combination {

	@Override
	public int score(Board board) {
		Objects.requireNonNull(board);
		if(sacrificed) {
			return 0;
		}
		// Trier la liste de dés (enlever les doublons)
		Set<Integer> unique = new TreeSet<>();
        for (Dice d : board.fiveDice()) {
            unique.add(d.value());
        }

        // Convertir le TreeSet en liste pour itérer plus facilement
        List<Integer> sorted = new ArrayList<>(unique);

        
        // Vérifier s'il y a 4 dés consécutifs dans la liste triée
        int consecutive = 1;
        // Parcours de la liste triée à partir du 2ème élément (indice 1)
        for (int i = 1; i < sorted.size(); i++) {
            // Si le dé actuel est consécutif au dé précédent
            if (sorted.get(i) == sorted.get(i - 1) + 1) {
                consecutive++; // Augmenter le compteur de consécutifs
                if (consecutive >= 4) {
                    return 30; // Si on trouve 4 consécutifs, on retourne le score (30 points)
                }
            } else {
                consecutive = 1; // Si la suite est brisée, réinitialiser à 1
            }
        }
        System.out.println("\nVous n'avez pas les dés nécessaires pour réaliser une petite suite (SmallStraight)");
        return 0;
		

	}

	@Override
    public boolean equals(Object o) {
        return o instanceof SmallStraight;
    }

    @Override
    public int hashCode() {
        return SmallStraight.class.hashCode();
    }
    
	@Override
	public String toString() {

		return "Small Straight";
	}

}
