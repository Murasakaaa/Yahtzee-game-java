package fr.uge.yams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Yams {

	public static String init(Scanner scanner) {
		Objects.requireNonNull(scanner);

		System.out.println("Welcome, player, please enter your name.");
		var input = scanner.nextLine();
		if(input.length() == 0) {
			System.out.println("\nYour name has to be at least 1 character long");
			return init(scanner);
		}
		return input;

	}
	
	public static String init2(Scanner scanner) {
		Objects.requireNonNull(scanner);

		System.out.println("Welcome player2, please enter your name.");
		return scanner.nextLine();

	}

	private static int askReroll(Scanner scanner) {
		Objects.requireNonNull(scanner);
		System.out.println("Type 0 to skip reroll\n"
				+ "To reroll a single die, type its number (1 for dice 1...etc)\n"
				+ "To Reroll multiple dices, type all their numbers one after another (123 for dice 1, dice 2 and dice 3)\n");
		var choice = scanner.nextLine();
		return Integer.parseInt(choice);
	}

	private static String askCombination(Scanner scanner) {
		Objects.requireNonNull(scanner);
		System.out.println("Please choose a combination to score (or sacrifice one) among the choices bellow :");
		System.out.println("\nSacrifier une case - 0\n"
				+ "ThreeOfAKind (Brelan) - 1\n"
				+ "FourOfAKind (Carré) - 2\n"
				+ "FullHouse (Full) - 3\n"
				+ "SmallStraight (Petite Suite) - 4\n"
				+ "LargeStraight (Grande Suite) - 5\n"
				+ "Yahtzee (Yam's) - 6\n"
				+ "Chance - 7\n");
		var choice = scanner.nextLine();
		return choice;
	}
	
	private static String askSacrificedSlot(Scanner scanner) {
		Objects.requireNonNull(scanner);
		// var choice_scanner = new Scanner(System.in);
		// var choice = init(choice_scanner);
		System.out.println("\nQuel case désirez-vous sacrifier ?\n");
		System.out.println("ThreeOfAKind (Brelan) - 1\n"
				+ "FourOfAKind (Carré) - 2\n"
				+ "FullHouse (Full) - 3\n"
				+ "SmallStraight (Petite Suite) - 4\n"
				+ "LargeStraight (Grande Suite) - 5\n"
				+ "Yahtzee (Yam's) - 6\n"
				+ "Chance - 7\n");
		return scanner.nextLine();
	}
	
	private static Combination sacrificeSlot(String combinationName, Scanner scanner, ScoreSheet scoreSheet) {
		Objects.requireNonNull(scanner);
		Objects.requireNonNull(combinationName);
		Objects.requireNonNull(scoreSheet);
		
		Combination res = switch (combinationName) {
			case "1" : {
				var threeOfAkind = new ThreeOfAKind(true);
				if(scoreSheet.alreadyUsed(threeOfAkind)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner, scoreSheet);
				}
				else {
					yield new ThreeOfAKind(true);
				}
			}
			case "2" : {
				var fourOfAKind = new FourOfAKind(true);
				if(scoreSheet.alreadyUsed(fourOfAKind)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner, scoreSheet);
				}
				else {
					yield new FourOfAKind(true);
				}
			}
			case "3" : { 
				var fullHouse = new FullHouse(true);
				if(scoreSheet.alreadyUsed(fullHouse)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner, scoreSheet);
				}
				else {
					yield new FullHouse(true);
				}
			}
			case "4" : { 
				var smallStraight = new SmallStraight(true);
				if(scoreSheet.alreadyUsed(smallStraight)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner, scoreSheet);
				}
				else {
					yield new SmallStraight(true);
				}
				
			}
			case "5" : { 
				var largeStraight = new LargeStraight(true);
				if(scoreSheet.alreadyUsed(largeStraight)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner, scoreSheet);
				}
				else {
					yield new LargeStraight(true);
				}
				
			}
			case "6" :  {
				var yamsComb = new YamsComb(true);
				if(scoreSheet.alreadyUsed(yamsComb)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner, scoreSheet);
				}
				else {
					yield new YamsComb(true);
				}
			}
			case "7" :  {
				var chance = new Chance(true);
				if(scoreSheet.alreadyUsed(chance)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner, scoreSheet);
				}
				else {
					yield new Chance(true);
				}
			}
			default : {
				yield null;
			}
		};
		 if (res == null) {
	            return sacrificeSlot(askSacrificedSlot(scanner), scanner, scoreSheet);
	        }
		return res;
	}
	
	
	private static Combination parseCombination(String combinationName, Scanner scanner, ScoreSheet scoreSheet) {
		Objects.requireNonNull(scanner);
		Objects.requireNonNull(combinationName);
		Objects.requireNonNull(scoreSheet);
		Combination res1 = switch (combinationName) {
			case "0" : {
				// var combScanner = new Scanner(System.in);
				yield sacrificeSlot(askSacrificedSlot(scanner), scanner, scoreSheet);
			}
			case "1" : {
				var threeOfKind = new ThreeOfAKind(false);
				if(scoreSheet.alreadyUsed(threeOfKind)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner, scoreSheet);
				}
				
				else{
					yield new ThreeOfAKind(false);
				}
			}
			case "2" : { 
				var fourOfAKind = new FourOfAKind(false);
				if(scoreSheet.alreadyUsed(fourOfAKind)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner, scoreSheet);
				}
				
				else{
					yield new FourOfAKind(false);
				}
				
			}
			case "3" : { 
				var fullHouse = new FullHouse(false);
				if(scoreSheet.alreadyUsed(fullHouse)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner, scoreSheet);
				}
				
				else{
					yield new FullHouse(false);
				}
				
			}
			case "4" : { 
				var smallStraight = new SmallStraight(false);
				if(scoreSheet.alreadyUsed(smallStraight)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner, scoreSheet);
				}
				
				else{
					yield new SmallStraight(false);
				}
				
			}
			case "5" : { 
				var largeStraight = new LargeStraight(false);
				if(scoreSheet.alreadyUsed(largeStraight)) {
					System.out.println("This combination is already used\n");
					parseCombination(askCombination(scanner), scanner, scoreSheet);
				}
				
				else{
					yield new LargeStraight(false);
				}
				
			}
			case "6" : {
				var yamsComb = new YamsComb(false);
				if(scoreSheet.alreadyUsed(yamsComb)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner, scoreSheet);
				}
				
				else{
					yield new YamsComb(false);
				}
			}
			case "7" : {
				var chance = new Chance(false);
				if(scoreSheet.alreadyUsed(chance)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner, scoreSheet);
				}
				
				else{
					yield new Chance(false);
				}
			}
			default : {
				yield null;
			}
		};
		if (res1 == null) {
            return parseCombination(askCombination(scanner), scanner, scoreSheet);
        }
		return res1;
	}

	public static void printPlayerBox(String playerName) {
		System.out.println("\n\n");
	    int boxWidth = playerName.length() + 4; // padding

	    System.out.print("╔");
	    for (int i = 0; i < boxWidth - 2; i++) System.out.print("═");
	    System.out.println("╗");
	    
	    System.out.println("║ " + playerName + " ║");

	    System.out.print("╚");
	    for (int i = 0; i < boxWidth - 2; i++) System.out.print("═");
	    System.out.println("╝");
	}

	
	
	public static void playerTurn(Board board, Scanner scanner, ScoreSheet scoreSheet, String player) {
		printPlayerBox(player + ", it's your turn");
		var res = new ArrayList<>();
		
		System.out.println(board);
		Boolean correctInput = false;
		for (var updateCounter = 0; updateCounter < 2; updateCounter++) {
			var rerollLeft = 2 - updateCounter;
			correctInput = false;
			var choice0 = false;
			
			while (!correctInput) { // tant que ce qu'a rentrer l'utilisateur n'est pas conforme, on lui redemande de rentrer une valeur
				System.out.println("Reroll left : " + rerollLeft);
				
				// fait en sorte que quand on entre un espace, rien, ou une lettre, le prog redemande au joueur de rentrer un chiffre
				var choice = 0;
				var correctChoice = false;
				while (!correctChoice) {
					try {
						choice = askReroll(scanner);
						correctChoice = true;
					} catch (Exception e) {
						System.out.println("Incorrect input: You have to enter a number.");
					}
				}
				
				if(Integer.toString(choice).length() > 1 && Integer.toString(choice).length() < 6) { // on vérifie que le joueur a entré plus d'une position mais pas plus de 5 position de dés à relancer.
					String[] playerInput = Integer.toString(choice).split("");
					if (board.rerollMultiple(playerInput)) {						
						board.rerollMultiple(playerInput);
						System.out.println(board);
						correctInput = true;
					} else { // si rerollMultiple renvoie false c'est qu'il y a un pb dans l'input
						System.out.println("Incorrect choice, you either chosed the same die 2 times or one die does not exist (for example : 6 does not exist)");
					}
				} else if (choice > 0 && choice < 6) {	// si il entre un seul chiffre entre 1 et 5 inclus, on reroll simplement.
					board.reroll(choice);
					System.out.println(board);
					correctInput = true;
				} else if(choice == 0) {	// si il entre 0, on ne fait rien de plus et on passe à la suite
					correctInput = true;
					choice0 = true;
				} else {
					// si on est rentré dans aucun if c'est qu'on a pas un input correct.
					System.out.println("Incorrect choice, you either chosed the same die 2 times or one die does not exist (for example : 6 does not exist)");
				}
			}
			if(choice0) {
				break; // on sort de la boucle for.
			}
		}
		var combinationChoice = parseCombination(askCombination(scanner), scanner, scoreSheet);
		scoreSheet.updateScore(combinationChoice, board);
		
		System.out.println(scoreSheet);
		System.out.println(String.format(
	            "╔═══════╦═══════╗%n" +
	            "║ Total ║ %5d ║%n" +  // 5 chiffres max
	            "╚═══════╩═══════╝",
	            scoreSheet.scoreTotal()
	        ));
		res.add(scoreSheet);
		res.add(board);
	}
	
	public static ArrayList<Object> IaTurn(Board board, Scanner scanner, ScoreSheet scoreSheet, int nbRollLeft) {
		printPlayerBox("AI, it's your turn");
		System.out.println(board);
		var res = new ArrayList<>();
		// Determiner avec le set de dé actuel quelle combinaison rapporte le meilleure score (parmi les combinaisons restantes)
		var bestScore = (int) scoreSheet.bestCombinationPossible(board).get(1);
		if (bestScore < 25 && nbRollLeft > 0) { // Si score faible
			var maxOccurrenceValues = board.maxOccurrence();
			// Vérifier s'il y a plus de 3 occurences d'un nombre (possibilité de tenter un Carré voir un yams qui peuvent rapporter beaucoup de points)
			if((Integer) maxOccurrenceValues.get(0) >= 3) {
				
				Set<Combination> actual = new HashSet<>(scoreSheet.unusedCombinations());
				Set<Combination> expected = Set.of(new LargeStraight(false), new FullHouse(false));
				boolean match = actual.equals(expected);

				// Si il restent d'autres combinaisons que les suites (avec au moins triplet de dés)
				if(!match) {
					expected = Set.of(new SmallStraight(false), new LargeStraight(false), new FullHouse(false));
					match = actual.equals(expected);
					// S'il y a la possibilité de faire un FullHouse alors lorsqu'on a 4 dés identiques (sans Yams atteignable)
					if((Integer) maxOccurrenceValues.get(0) == 4 && match) {
						var indexesToReroll = board.NoYamsToFullHouseWithOneReroll();
						var printMessage = new StringBuilder();
						printMessage.append("AI has decided to reroll the dice(s) ");
						var str = "";
						for(int index: indexesToReroll) {
							str += Integer.toString(index + 1);
							printMessage.append(Integer.toString(index + 1) + " ");
						}
						String[] indexes = str.split("");
						
						System.out.println(printMessage);
						board.rerollMultiple(indexes);
						nbRollLeft -= 1;
						return IaTurn(board, scanner, scoreSheet, nbRollLeft);
					}
					// Si le yams n'est plus disponible, qu'on possède 5 dés identiques et que les autres combinaisons n'apportent pas un score suffisant (pour éviter que le programme essaie tout de même de reroll "le dé différent"=
					else if((int) ((List<Object>) maxOccurrenceValues.get(2)).size() > 0){
						var printMessage = new StringBuilder();
						printMessage.append("AI has decided to reroll the dice(s) ");
						var str = "";
						for(int index: (List<Integer>) maxOccurrenceValues.get(2)) {
							str += Integer.toString(index + 1);
							printMessage.append(Integer.toString(index + 1) + " ");
						}
						String[] indexes = str.split("");
						
						System.out.println(printMessage);
						board.rerollMultiple(indexes);
						nbRollLeft -= 1;
						return IaTurn(board, scanner, scoreSheet, nbRollLeft);
					}
					else {
						scoreSheet.updateScore((Combination) scoreSheet.bestCombinationPossible(board).get(0), board);
						res.add(scoreSheet);
						res.add(board);
						return res;
					}
				}
				// Si il ne restent que les suites de disponibles
				else {
					var printMessage = new StringBuilder();
					printMessage.append("AI has decided to reroll the dice(s) ");
					var str = "";
					for(int index: board.IndexesToRerollForTripletCaseForStraights()) {
						str += Integer.toString(index + 1);
						printMessage.append(Integer.toString(index + 1) + " ");
					}
					String[] indexes = str.split("");
					System.out.println(printMessage);
					board.rerollMultiple(indexes);
					nbRollLeft -= 1;
					return IaTurn(board, scanner, scoreSheet, nbRollLeft);
				}
			}
			// Si le set de dés contient un ou plusieurs doublons et qu'on peut réaliser un grande suite avec un seul reroll (par exemple pour 22345 il suffit de reroll un des 2 pour obtenir une grande suite)
			else if ((Integer) maxOccurrenceValues.get(0) == 2){
				if(board.CanReachLargeStraightWithOneReroll())  {
					var printMessage = "";
					printMessage += "AI has decided to reroll the dice " + Integer.toString(board.IndexRerollForLargeStraight());
					System.out.println(printMessage);
					board.reroll(board.IndexRerollForLargeStraight());
					nbRollLeft -= 1;
					return IaTurn(board, scanner, scoreSheet, nbRollLeft);
				}
				else  {
					// S'il y a qu'un seul doublons alors il y a 3 dés à reroll
					if(board.IndexesToRerollForDuplicationCase().size() > 1) {
						var printMessage = new StringBuilder();
						printMessage.append("AI has decided to reroll the dice(s) ");
						var str = "";
						for(int index: board.IndexesToRerollForDuplicationCase()) {
							str += Integer.toString(index + 1);
							printMessage.append(Integer.toString(index + 1) + " ");
						}
						String[] indexes = str.split("");
						System.out.println(printMessage);
						board.rerollMultiple(indexes);
						nbRollLeft -= 1;
						return IaTurn(board, scanner, scoreSheet, nbRollLeft);
					}
					// S'il y a deux doublons (donc il seul dé à reroll)
					else {
						board.reroll(board.IndexesToRerollForDuplicationCase().get(0));
						System.out.println("AI has decided to reroll the dice " + Integer.toString(board.IndexesToRerollForDuplicationCase().get(0)));
						System.out.println(board);
						nbRollLeft -= 1;
						return IaTurn(board, scanner, scoreSheet, nbRollLeft);
					}
				}
			}
			return IaTurn(board, scanner, scoreSheet, nbRollLeft);
		}
		// Si une des combinaisons marque un score suffisant ou qu'il n'y a plus de reroll alors on retourne d'office la combinaisons la plus haute (celle qui marque le plus de points avec la configuration actuel)
		else {
			scoreSheet.updateScore((Combination) scoreSheet.bestCombinationPossible(board).get(0), board);
			res.add(scoreSheet);
			res.add(board);
			return res;
		}
	}
	
	public static void main(String[] args) {

		
		var scanner = new Scanner(System.in);
		var player1 = init(scanner);
		var scoreSheet = new ScoreSheet();
		var scoreSheet2 = new ScoreSheet();
		
		
		System.out.println("\n" + player1 + " you'll be the first one starting\n");
		
		// Début du tour du joueur
		for (var roundCounter = 0; roundCounter < 7; roundCounter++) {
			var nbRollLeft = 2;
			System.out.println("\n——————————————————————————— Welcome in round " + (roundCounter + 1) + " ———————————————————————————");
			var board2 = new Board(new ArrayList<>(List.of(new Dice(1), new Dice(6),new Dice(1),new Dice(1),new Dice(1))));
			var board = new Board();
			playerTurn(board, scanner, scoreSheet, player1);
			IaTurn(board2, scanner, scoreSheet2, nbRollLeft);
			
			System.out.println("Total Scores :\n"
					+ player1 + "\n" + scoreSheet + "\n"
					+ "AI" + "\n" + scoreSheet2);
			
			
		}
		if(scoreSheet.scoreTotal() > scoreSheet2.scoreTotal()) {
			printPlayerBox("           " + player1 + " won! Congratulation           ");
		}
		else if (scoreSheet.scoreTotal() == scoreSheet2.scoreTotal()) {
			printPlayerBox("           It's a draw!           ");
		}
		else {
			printPlayerBox("           AI won! Congratulation           ");
		}
	}

}
