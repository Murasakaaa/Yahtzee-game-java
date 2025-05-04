package fr.uge.yams;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Yams {

	public static String init(Scanner scanner) {
		Objects.requireNonNull(scanner);

		System.out.println("Welcome, player, please enter your name.");
		return scanner.nextLine();

	}

	private static int askReroll(Scanner scanner) {
		Objects.requireNonNull(scanner);
		System.out.println("Type 0 to skip reroll\n"
				+ "To reroll one dice type it as 1 for the first one...etc\n"
				+ "To Reroll multiple dices type them as '123' to reroll dice 1, dice 2 and dice 3\n");
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
	
	private static Combination sacrificeSlot(String combinationName, Scanner scanner) {
		Objects.requireNonNull(scanner);
		Objects.requireNonNull(combinationName);
		
		Combination res = switch (combinationName) {
			case "1" : {
				var threeOfAkind = new ThreeOfAKind(true);
				if(ScoreSheet.alreadyUsed(threeOfAkind)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner);
				}
				else {
					yield new ThreeOfAKind(true);
				}
			}
			case "2" : {
				var fourOfAKind = new FourOfAKind(true);
				if(ScoreSheet.alreadyUsed(fourOfAKind)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner);
				}
				else {
					yield new FourOfAKind(true);
				}
			}
			case "3" : { 
				var fullHouse = new FullHouse(true);
				if(ScoreSheet.alreadyUsed(fullHouse)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner);
				}
				else {
					yield new FullHouse(true);
				}
			}
			case "4" : { 
				var smallStraight = new SmallStraight(true);
				if(ScoreSheet.alreadyUsed(smallStraight)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner);
				}
				else {
					yield new SmallStraight(true);
				}
				
			}
			case "5" : { 
				var largeStraight = new LargeStraight(true);
				if(ScoreSheet.alreadyUsed(largeStraight)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner);
				}
				else {
					yield new LargeStraight(true);
				}
				
			}
			case "6" :  {
				var yamsComb = new YamsComb(true);
				if(ScoreSheet.alreadyUsed(yamsComb)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner);
				}
				else {
					yield new YamsComb(true);
				}
			}
			case "7" :  {
				var chance = new Chance(true);
				if(ScoreSheet.alreadyUsed(chance)) {
					System.out.println("This box is already checked you can't sacrifice it\n");
					yield sacrificeSlot(askSacrificedSlot(scanner), scanner);
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
	            return sacrificeSlot(askSacrificedSlot(scanner), scanner);
	        }
		return res;
	}
	
	private static Combination parseCombination(String combinationName, Scanner scanner) {
		Objects.requireNonNull(scanner);
		Objects.requireNonNull(combinationName);
		Combination res1 = switch (combinationName) {
			case "0" : {
				// var combScanner = new Scanner(System.in);
				yield sacrificeSlot(askSacrificedSlot(scanner), scanner);
			}
			case "1" : {
				var threeOfKind = new ThreeOfAKind(false);
				if(ScoreSheet.alreadyUsed(threeOfKind)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner);
				}
				
				else{
					yield new ThreeOfAKind(false);
				}
			}
			case "2" : { 
				var fourOfAKind = new FourOfAKind(false);
				if(ScoreSheet.alreadyUsed(fourOfAKind)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner);
				}
				
				else{
					yield new FourOfAKind(false);
				}
				
			}
			case "3" : { 
				var fullHouse = new FullHouse(false);
				if(ScoreSheet.alreadyUsed(fullHouse)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner);
				}
				
				else{
					yield new FullHouse(false);
				}
				
			}
			case "4" : { 
				var smallStraight = new SmallStraight(false);
				if(ScoreSheet.alreadyUsed(smallStraight)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner);
				}
				
				else{
					yield new SmallStraight(false);
				}
				
			}
			case "5" : { 
				var largeStraight = new LargeStraight(false);
				if(ScoreSheet.alreadyUsed(largeStraight)) {
					System.out.println("This combination is already used\n");
					parseCombination(askCombination(scanner), scanner);
				}
				
				else{
					yield new LargeStraight(false);
				}
				
			}
			case "6" : {
				var yamsComb = new YamsComb(false);
				if(ScoreSheet.alreadyUsed(yamsComb)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner);
				}
				
				else{
					yield new YamsComb(false);
				}
			}
			case "7" : {
				var chance = new Chance(false);
				if(ScoreSheet.alreadyUsed(chance)) {
					System.out.println("This combination is already used\n");
					yield parseCombination(askCombination(scanner), scanner);
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
            return parseCombination(askCombination(scanner), scanner);
        }
		return res1;
	}
	
	public static void playerTurn(Board board, ScoreSheet scoreSheet, Scanner scanner) {
		var res = new ArrayList<>();
		System.out.println(board);
		// Relances dans le tour
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
					}
					else {
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
		
		// demande quelle case de combinaison on veut choisir
		var combinationChoice = parseCombination(askCombination(scanner), scanner);
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

	
	public static void main(String[] args) {

		var scanner = new Scanner(System.in);
		var name = init(scanner);
		System.out.println("Hello " + name + ", good luck !\n");

		var scoreSheet = new ScoreSheet();
		// Début du tour du joueur
		
		for (var roundCounter = 0; roundCounter < 7; roundCounter++) {
			System.out.println("\n——————————————————————————— Welcome in round " + (roundCounter + 1) + " ———————————————————————————");
			var board = new Board();
			playerTurn(board, scoreSheet, scanner);
		}
		System.out.println("C'est fini !");
		}

}
