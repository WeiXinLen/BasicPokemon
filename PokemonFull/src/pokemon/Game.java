package pokemon;
/**
 * This class contains various methods such as:
 * Scanner - To get the input from a user
 * ArrayList - To create an ArrayList to store multiple values
 * Random - To use the random generator function to create random values
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
/**
 *The main purpose of this class is to:
 *create a platform for the players to battle,view highscore, clear highscore and exit the game
 *
 *@author Jordan and Nicholas
 */

public class Game {
	/**
	 * Creates a keyboard for input
	 * Intialise 3 players, player 1 as current player, player 2 as opponent player 
	 * swap player is a temporary player
	 */
	static Scanner keyboard = new Scanner(System.in);
	static Player currentPlayer, opponentPlayer, swapPlayer;
	static Scoreboard scoreboard = new Scoreboard();
	public static void main(String[] args) {
		/**
		 * main method
		 * initialise option to "0"
		 * creates a key art "Pokemon"
		 * prompts name for player1 and player2
		 */
		int option = 0;
		System.out.println(
			"________       __                                 \n" + 
			"\\____   \\____ |  | __ ____   _____   ____   ____  \n" + 
			" |   ___/  _ \\|  |/ // __ \\ /     \\ /  _ \\ /    \\ \n" + 
			" |  |  (  <_> )    <\\  ___/|  Y Y  (  <_> )   |  \\\n" + 
			" |__|   \\____/|__|__\\\\_____>__|_|__/\\____/|___|__/\n");
		System.out.println("Welcome to rip-off OOP-style Pokémon!");
		System.out.print("Player #1, please enter your name > ");
		currentPlayer = new Player(keyboard.next());
		System.out.print("Player #2, please enter your name > ");
		opponentPlayer = new Player(keyboard.next());
		/**
		 * shows selection of features of the game
		 * 1 to start game, 2 to display top 10 scores, 3 to clear scores, 4 to exit game
		 */
		menu:
		while(true) {
			System.out.print("\nChoose option:\n  1. Start game\n  2. Display top 10 scores\n  3. Clear scoreboard\n  4. Exit\n\n");
			/**
			 * This loop catches exception where player input is not in range of 1 to 4
			 * The loop will not end if input is not in range of 1 to 4
			 * This will print an error message and prompts user to enter a valid option
			 * If input is in range of 1 to 4, this loop will break
			 */
			do {
				try {
					System.out.print("Option > ");
					option = keyboard.nextInt();
					if (option > 0 && option <= 4) {
						break;
					}
				} catch (Exception e) {}
				System.out.println("Invalid option, please try again.");
				keyboard.nextLine();
			} while (option <= 0 || option > 4);
			/**
			 * This creates an output based on player's input
			 * Case 1: to start the game, where it will print "drawing cards for players"
			 * and then generates a deck of 6 pokemons and store it in the player class for both
			 * player1 and player2 
			 * Case 2: to print scoreboard to show highscores of players
			 * Case 3: clearing the current scoreboard
			 * Case 4: Exiting the game, prints "thank you and goodbye"
			 */
			switch(option) {
				case 1:
					System.out.println("Drawing cards for " + currentPlayer.getName() + "...");
					System.out.println("Drawing cards for " + opponentPlayer.getName() + "...");
					currentPlayer.generateDeck(6);
					opponentPlayer.generateDeck(6);
					printMenu();
					break;
				case 2:
					scoreboard.show();
					break;
				case 3:
					scoreboard.clear();
					break;
				case 4:
					System.out.println("\nThank you and goodbye!");
					break menu;
			}
		}
	}
	/**
	 * This method prints the menu where players can select one of their pokemon to perform an action
	 */
	public static void printMenu() {
		/**
		 * This method checks if player has lost the game where they have lost 3 pokemons
		 * If player has more than 3 pokemons alive, it will run.
		 * Else, exit loop
		 */
		while (currentPlayer.getPokemonDeathCount() < 3) { 
			/**
			 * Initialise option to "0"
			 * Initialise damage to "1" so that pokemon with attack power 0 can deal atleast 1 damage
			 * Create the pokemons, current pokemon and oppenent pokemon
			 */
			int option = 0, damage = 1;
			Pokemon currentPokemon, opponentPokemon;
			/**
			 * prints both player1 and player2 deck so that they can refer to what pokemon
			 * they select
			 */
			currentPlayer.printDeck();
			opponentPlayer.printDeck();
			/**
			 * Print a menu where player can choose to attack, charge or train
			 */
			System.out.println("\n" + currentPlayer.getName() + "'s turn:");
			System.out.print("  1. Attack\n  2. Recharge\n  3. Train\n");
			/**
			 * This loop catches exceptions where player input is not in range of 1 to 3
			 * If input is invalid, prompts players to input again
			 * If input is in range of 1 to 3, it will break from the loop
			 */
			do {
				try {
					System.out.print("Option > ");
					option = keyboard.nextInt();
					if (option > 0 && option <= 3) {
						break;
					}
				} catch (Exception e) {}
				System.out.println("Invalid option, please try again.");
			} while (option <= 0 || option > 3);
			/**
			 * Prompts player to choose a pokemon
			 * If chosen pokemon is in current deck of the player, continues
			 * else prints error message and prompts player to enter an option again
			 */
			do {
				System.out.print("Choose Pokemon > ");
				currentPokemon = choosePokemon(currentPlayer.deck, true);
			} while (currentPokemon == null);
			/**
			 * Case 1: Attack option chosen, player chooses an opponent pokemon to attack
			 * if opponent pokemon chosen is not in opponent's current deck, prompts player to
			 * choose an opponent pokemon to attack again
			 */
			switch(option) {
			case 1:
				do {
					System.out.print("Attack Pokemon > ");
					opponentPokemon = choosePokemon(opponentPlayer.deck, false);
				}  while (opponentPokemon == null);
				 /** Check if player's pokemon is attacking type, flip the coin, if head, double the damage,
				 * if tails, remain same damage
				 */
				if (currentPokemon.getType().equals("Attacking") && coin()) {
					System.out.println("[Flip a coin: head] Attack point is " + ((Attacking) currentPokemon).getAttack());
					damage = ((Attacking) currentPokemon).getAttack();
				}
				/** Check if player's pokemon is the same type as chosen opponent pokemon, damage
				 * dealt is doubled and reduce the energy of current player's pokemon by 2
				 */
				if (currentPokemon.getType().equals(opponentPokemon.getType())) {
					System.out.println("Weakness: on, double attack point");
					currentPokemon.decreaseEnergy(2);
					damage *= 2;
				/**
				 * If there is no attacking type, weakness, reduce energy of current
				 * player's pokemon by 1
				 */
				} else {
					System.out.println("Weakness: off");
					currentPokemon.decreaseEnergy(1);
				}
				/**
				 * Check if opponent pokemon is in idle state, if yes, damage dealt is doubled
				 */
				if (opponentPokemon.getIdle() > 0) {
					damage *= 2;
				}
				/**
				 * Check if chosen opponent pokemon is a defending type, if yes flip a coin,
				 * if head, damage dealt reduced based on resistance value of opponent pokemon,
				 * else no damage reduction
				 * if damage dealt is less than 1 after damage reduction from resistance of 
				 * opponent pokemon, damage dealt equals "0" instead of negative value damage
				 * which would instead "Heal" or increase opponent pokemon's health
				 */
				if (opponentPokemon.getType().equals("Defending") && coin()) {
					System.out.println("[Flip a coin: head] resistance point is " + ((Defending) opponentPokemon).getResistance());
					damage = damage - ((Defending) opponentPokemon).getResistance();
					if (damage < 1) {
						damage = 0;
					}
				/**
				 * if current player's chosen pokemon is fairy, flip the coin
				 * if heads, opponent pokemon is poisoned, idle for 1 round
				 * if tails, opponent pokemon is paralysed, idle for 2 rounds
				 */
				}
				if (currentPokemon.getType().equals("Fairy")) {
					if (coin()) {
						System.out.println("[Flip a coin: head] Pokemon " + opponentPokemon.getName() + " is poisoned");
						opponentPokemon.setState("Poisoned");
						opponentPokemon.setIdle(1);				
					} else {
						System.out.println("[Flip a coin: tail] Pokemon " + opponentPokemon.getName() + " is paralysed");
						opponentPokemon.setState("Paralysed");
						opponentPokemon.setIdle(2);
					}
				}
				/**
				 * After going through all the if else statements, shows health and damage taken of opponent's pokemon
				 * decrease health of opponent's pokemon and increase exp of current 
				 * player's pokemon
				 */
				System.out.println("HitPoint for Pokemon " + opponentPokemon.getName() + " damaged by " + damage);
				opponentPokemon.decreaseHP(damage);
				currentPokemon.increaseExp(1);
				break;
				/**
				 *Player chooses option 2, recharging energy
				 *If current player's pokemon chosen has same colour as drawn energy card,
				 *increases energy by 5, else, energy remains unchanged
				 */
			case 2:
				if (currentPokemon.getenergyColour().equals("Colourless") || currentPokemon.drawEnergyCard().equals(currentPokemon.getenergyColour())) {
					System.out.println("Matched. Energy for Pokemon " + currentPokemon.getName() + "increased by 5");
					currentPokemon.increaseEnergy(5);
				} else {
					System.out.println("Not matched. Energy for Pokemon " + currentPokemon.getName() + "remained");
				}
				break;
				/**
				 * Player chooses option3, train
				 * checks if pokemon's energy is more than 5
				 * if true, reduce pokemon's energy by 5 and increase exp by 1
				 * if false, prints player's turn forfeited
				 */
			case 3:
				if (currentPokemon.getEnergy() >= 5 && currentPokemon.getExp() < 20) {
					currentPokemon.decreaseEnergy(5);
					currentPokemon.increaseExp(1);
					break;
				} else {
					System.out.println("The pokemon you have selected does not have enough energy, your turn is forfeit");
				}
			}
			/**
			 * After every round(1 turn each of player1 and player2, get the number of pokemon
			 * in player's deck
			 * For each pokemon in the player's deck, get their idle count and store it into a
			 * temporary idle variable
			 * If idle value more than 0, reduce idle status count by 1
			 * else does not do anything
			 * If pokemon status equals "defeated", increase death count by 1 and update the death
			 * count
			 */
			for (int i=0; i < currentPlayer.deck.size(); i++) {
				int pokemonDeathCount = 0;
				int idle = currentPlayer.deck.get(i).getIdle();
				if (idle > 0) {
					currentPlayer.deck.get(i).setIdle(idle - 1);
				}
				if (currentPlayer.deck.get(i).getState().equals("Defeated")) {
					pokemonDeathCount++;
				}
				currentPlayer.setPokemonDeathCount(pokemonDeathCount);
			}
			/**
			 * Increase turn count of players by 1
			 * After every turn, Changes players so that after current player's turn, opponent player 
			 * becomes current player where he can choose a pokemon to perform 1 of 3 options: 
			 * attack, recharge and train
			 * Point of view of players is swapped
			 */
			currentPlayer.increaseTurns();
			swapPlayer = currentPlayer;
			currentPlayer = opponentPlayer;
			opponentPlayer = swapPlayer;
		}
		/**
		 * If loop exited, where pokemon's death count of a player is 3, prints winning player
		 * defeated his opponent, adds the winner's name to the scoreboard and turns taken to 
		 * defeat opponent 
		 */
		System.out.println("Player " + opponentPlayer.getName() + " has successfully defeated three Pokemon of Player " + currentPlayer.getName() + ". Player " + opponentPlayer.getName() + " is the winner!");
		scoreboard.add(opponentPlayer.getName(), opponentPlayer.getTurns());
	}
	/**
	 * Checks if pokemon chosen by player to perform an action is in active state
	 * If pokemon is in idle or dead state, prompts player to choose another pokemon, returns null value
	 * Else if choice is valid, return chosen pokemon as choice
	 * If there is an invalid value, prints invalid choice and prompts for input again, returns null value
	 * @param deck the arraylist of current player's deck
	 * @param active the state of the pokemon chosen
	 * @return either null value or chosen pokemon as a choice
	 */
	public static Pokemon choosePokemon(ArrayList<Pokemon> deck, boolean active) {
		try {
			int choice = keyboard.nextInt() - 1;
			if (active) {
				if (deck.get(choice).getIdle() > 0) {
					System.out.println("Pokemon " + deck.get(choice).getName() + "is " + deck.get(choice).getType().toLowerCase() + ", please choose another Pokemon.");
					return null;
				}
			}
			if ("Defeated".equals(deck.get(choice).getState())) {
				System.out.println("Pokemon " + deck.get(choice).getName() + "is " + "defeated, please choose another Pokemon.");
				return null;
			} else if (choice >= 0 && choice < deck.size()) {
				return deck.get(choice);
			}
		} catch (Exception e) {}
		System.out.println("Invalid choice.");
		keyboard.nextLine();
		return null;
	}
	/**
	 * Tossing the coin method
	 * @return true as in "heads", false as in "tails"
	 */
	public static boolean coin() {
		/**
		 * Randomise boolean value which functions as flip a coin, heads or tails
		 * returns boolean value
		 */
		Random rand = new Random();
		return rand.nextBoolean();
	}
}
