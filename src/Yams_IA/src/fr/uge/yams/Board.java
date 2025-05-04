package fr.uge.yams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;



public class Board {

	private ArrayList<Dice> fiveDice = new ArrayList<Dice>();
	
	public ArrayList<Dice> fiveDice() {
		return fiveDice;
	}
	
	public Board(ArrayList<Dice> fiveDice) {
		this.fiveDice = fiveDice;
	}
	
	public Board() {
		for (var i = 1; i <= 5; i++) {
			fiveDice.add(new Dice());
		}
	}

	public void reroll(int pos) {
		if (pos < 1 || pos > 5) {
			throw new IllegalArgumentException();
		}
		fiveDice.set(pos - 1, new Dice());
	}

	public boolean rerollMultiple(String[] positions) {
		Objects.requireNonNull(positions);
		var alreadyReRolled = new ArrayList<Integer>(); // liste des positions déjà rencontrées.
		for (String value : positions) {
			var newPos = Integer.parseInt(value); // converti la position en entier.
			if (newPos > 0 && newPos < 6 && !alreadyReRolled.contains(newPos)) { // vérifie que la position est entre 1 et 5 inclus et qu'elle n'a pas déjà été rencontrée avant.
				alreadyReRolled.add(newPos);
				this.reroll(newPos); // si toutes les condition sont bonnes, on peut reroll le dé.
			} else {
				return false; // on renvoie false si une des condition n'est pas bonne.
			}
		}
		return true; // on renvoie true si toute la méthode s'est executée sans problemes.
	}
	
	public List<Object> maxOccurrence() {
		Map<Integer, Integer> freq = new HashMap<>();
		List<Integer> indexesToReroll = new ArrayList<>();
		for (Dice d : fiveDice) {
	        freq.put(d.value(), freq.getOrDefault(d.value(), 0) + 1);
		}
		var maximumOccurrence = Collections.max(freq.values());
		int i = 0;
		for (Dice d : fiveDice) {
			if (freq.get(d.value()) != maximumOccurrence) {
				indexesToReroll.add(i);
			}
			i++;
	    }
        return new ArrayList<>(List.of(maximumOccurrence, freq, indexesToReroll));
	}
	
	public List<Integer> NoYamsToFullHouseWithOneReroll() {
		var add = 0;
		List<Integer> indexesToReroll = (List<Integer>) maxOccurrence().get(2);
		for(int i=0; i<5;i++) {
			if(!indexesToReroll.contains(i)) {
				add = i;
				break;
			}
		}
		indexesToReroll.add(add);
		return indexesToReroll;
		
	}
	
	public boolean CanReachLargeStraightWithOneReroll() {
		Set<Integer> diceValues = new HashSet<>();
	    for (Dice d : fiveDice) diceValues.add(d.value());
	    if((Collections.max(diceValues)) > 2) {
	    	return false;
	    }
	    return true;
	}
	
	
	public int IndexRerollForLargeStraight() {
		/* Méthode permettant de determiner l'indice du dé à reroll si avec le set de dé actuel il est possible d'obtenir une grande suite juste avec un seul reroll*/
	    Set<Integer> diceValues = new HashSet<>();
	    for (Dice d : fiveDice) diceValues.add(d.value());

	    // Définir les patterns de suite possibles
	    List<Set<Integer>> targets = new ArrayList<>();
        targets.add(Set.of(1, 2, 3, 4, 5));
        targets.add(Set.of(2, 3, 4, 5, 6));

        Set<Integer> unique = new HashSet<>();
        int index = 0;
	    // Parcourir les patterns et compter combien de dés sont déjà présents
	    for (Set<Integer> pattern : targets) {
	        for (int num : pattern) {
	            if(!unique.contains(num)) {
	            	unique.add(num);
	            }
	            else {
	            	return index;
	            }
	            index++;
	        }
	    }
	    return index;

	}

	
	public List<Integer> IndexesToRerollForDuplicationCase()  {
		/*Méthode qui renvoie les indices des dés à reroll en cas présence d'un ou de deux doublons*/
		Map<Integer, Integer> freq = new HashMap<>();
	    for (Dice d : fiveDice) {
	        freq.put(d.value(), freq.getOrDefault(d.value(), 0) + 1);
		}
	    Set<Integer> duplicateValues = new HashSet<>();
        for (var entry : freq.entrySet()) {
            if (entry.getValue() == 2) {
                duplicateValues.add(entry.getKey());
            }
        }
	    List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (!duplicateValues.contains(fiveDice.get(i).value())) {
            	indexes.add(i);
            }
        }

	    return indexes;
	}
	
	
	public List<Integer> IndexesToRerollForTripletCaseForStraights()  {
		/*Méthode qui renvoie les indices des dés à reroll en cas présence d'un triplet de dés et que seules les suites sont disponibles*/
		var sizeReroll = 2;
		Map<Integer, Integer> freq = new HashMap<>();
	    for (Dice d : fiveDice) {
	        freq.put(d.value(), freq.getOrDefault(d.value(), 0) + 1);
		}
	    var maximumOccurrence = Collections.max(freq.values());
	    Set<Integer> duplicateValues = new HashSet<>();
        for (var entry : freq.entrySet()) {
            if (entry.getValue() == 3) {
                duplicateValues.add(entry.getKey());
            }
        }
        System.out.println(duplicateValues);
	    List<Integer> indexes = new ArrayList<>();
	    if(maximumOccurrence == 4) { 
	    	sizeReroll = 3;
	    }
	    if(maximumOccurrence > 4) { 
	    	sizeReroll = 4;
	    }
        for (int i = 0; i < 5; i++) {
            if (duplicateValues.contains(fiveDice.get(i).value()) && indexes.size() < sizeReroll) {
            	indexes.add(i);
            }
        }

	    return indexes;
	}
	
	@Override
	public String toString() {
		var builder = new StringBuilder();
		for (var i = 1; i <= 5; i++) {
			builder.append(fiveDice.get(i - 1).toString());
		}
		builder.append("\n").append("-----------------\n");

		return builder.toString();
	}
	
	
	public static void main(String[] args) {

		var board = new Board();
		System.out.println(board);
		board.reroll(2);
		System.out.println(board);
	}

}
