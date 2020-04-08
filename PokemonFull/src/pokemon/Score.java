/**
 * java.io.serializable is imported to implement serializable for the score class
 * 
 * The purpose of using serializable is to be able to sort
 * the type Object Score
 */
package pokemon;
import java.io.Serializable;
/**
 * Class Score is created so that we can make an object
 * of the type Score that contains the name and the score of a
 * player to be stored in an arraylist.
 * @author Jordan and Nicholas
 *
 */

public class Score implements Serializable {
	private String name;
	private int turns;
	
	/**
	 * 
	 * @return the current players' names 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name to set the players' names as the player enter their name 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return the latest number of turns that the players used
	 */
	public int getTurns() {
		return turns;
	}
	
	/**
	 * 
	 * @param turns to set and update the latest number of turns the players used
	 */
	public void setTurns(int turns) {
		this.turns = turns;
	}
	
	/**
	 * 
	 * @param name to set the latest name of players for the scoreboard array
	 * @param turns to set the total number of turns used by the player for the scoreboard array
	 */
	public Score(String name, int turns) {
		this.name = name;
		this.turns = turns;
	}
}
