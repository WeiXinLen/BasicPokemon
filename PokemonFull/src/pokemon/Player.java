package pokemon;
/**
 * This class various methods such as:
 * Scanner - To get the input from a user
 * ArrayList - To create an ArrayList to store multiple values
 * ThreadLocalRandom - to use the random generator to create random values
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
/**
 *The main purpose of this class is to:
 *i)generate a deck for players
 *ii)counts the number of dead pokemon 
 *iii)count the number of turn
 *
 *@author Jordan and Nicholas
 */
public class Player {
	private String name;
	/**
	 * Initialise 0 as the number of dead pokemon a player has
	 * Initialise 0 for the number of turns in a game
	 */
	private int pokemonDeathCount = 0, turns = 0;
	Scanner keyboard = new Scanner(System.in);
	ArrayList<Pokemon> deck = new ArrayList<Pokemon>();
	/**
	 * Record player's name
	 * @param name gets player's name
	 */
	public Player(String name) {
		this.name = name;
	}
	/**
	 * This returns the number of dead pokemon a player has
	 * @return number of dead pokemon a player has
	 */
	public int getPokemonDeathCount() {
		return pokemonDeathCount;
	}
	/**
	 * Sets the number of dead pokemon for a player
	 * @param pokemonDeathCount the number of dead pokemon for a player
	 */
	public void setPokemonDeathCount(int pokemonDeathCount) {
		this.pokemonDeathCount = pokemonDeathCount;
	}
	/**
	 * This increases a turn of the game
	 */
	public void increaseTurns() {
		this.turns += 1;
	}
	/**
	 * This returns the list of pokemon a player has in his deck
	 * @return the list of player's pokemon
	 */
	public ArrayList<Pokemon> getDeck() {
		return deck;
	}
	/**
	 * This gets the player's name
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	/**
	 * This sets the name of the player
	 * @param name set player name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This gets the number of turns in the game
	 * @return the number of turns in the game
	 */
	public int getTurns() {
		return turns;
	}
	/**
	 * This sets the number of turns in the game
	 * @param turns sets the value of the turn
	 */
	public void setTurns(int turns) {
		this.turns = turns;
	}
	/**
	 * This adds pokemon into a player's deck depending on the number of cards they input
	 * 2 attacking and 1 defending type pokemon added initially into the deck
	 * the remaining number of pokemons and their types are randomly generated
	 * @param cards number of pokemon cards 
	 */
	public void generateDeck(int cards) {
		this.deck.add(new Attacking(1));
		this.deck.add(new Attacking(2));
		this.deck.add(new Defending(3));
		for(int i = 4; i <= cards; i++) {
			switch(ThreadLocalRandom.current().nextInt(1, 4)) {
			case 1:
				this.deck.add(new Attacking(i));
				break;
			case 2:
				this.deck.add(new Defending(i));
				break;
			case 3:
				this.deck.add(new Fairy(i));
				break;
			}
		}		
	}
	/**
	 * This creates a table view to show the current number of pokemon a player has and current stats 
	 * of the player's pokemon
	 */
	public void printDeck() {
		System.out.println("\n" + name + "'s cards:");
		System.out.println(" " + String.format("%76s", "").replace(" ", "_") + " ");
		System.out.println("|No.| Type      |Stage| EXP | HP  | Energy | Attack | Resistance | Status    |");
		System.out.println("|" + String.format("%76s", "").replace(" ", "=") + "|");
		/**
		 * This loop generates random stats for a pokemon
		 * Initialise attack and resistance of all pokemon to 0
		 * In the case where a pokemon is an attacking type, its attack stats will be extracted 
		 * based on the Attacking class
		 * In the case where a pokemon is an defending type, its resistance stats will be extracted 
		 * based on the Defending class
		 * In the case where pokemon type is not attacking or defending, error is catched and nothing is done
		 * print side dividers and space
		 * get the other stats of the pokemon
		 * print bottom divider
		 * it will loop until deck reaches max number of pokemons assingned
		 */
		for(int i = 0; i < deck.size(); i++) {
			int attack = 0 , resistance = 0;
			try {
				attack = ((Attacking) deck.get(i)).getAttack();
			} catch (Exception e) {}
			try {
				resistance = ((Defending) deck.get(i)).getResistance();
			} catch (Exception e) {}
			System.out.format("| %-1s | %-9s | %-3d | %-3d | %-3d | %-6d | %-6d | %-10d | %-9s |\n",
					deck.get(i).getName(),
					deck.get(i).getType(),
					deck.get(i).getStage(),
					deck.get(i).getExp(),
					deck.get(i).getHP(),
					deck.get(i).getEnergy(),
					attack, resistance,
					deck.get(i).getState());
		}
		System.out.println("|" + String.format("%76s", "").replace(" ", "_") + "|");
	}
}
