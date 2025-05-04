package fr.uge.yams;

import java.util.ArrayList;
import java.util.Objects;

public class Board {

	private final ArrayList<Dice> fiveDice = new ArrayList<Dice>();
	
	public ArrayList<Dice> fiveDice() {
		return fiveDice;
	}
	
	public Board() {
		for (var i = 1; i <= 5; i++) {
			fiveDice.add(new Dice());
		}
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
	
	public static void main(String[] args) {

		var board = new Board();
		System.out.println(board);
		board.reroll(2);
		System.out.println(board);
	}

}
