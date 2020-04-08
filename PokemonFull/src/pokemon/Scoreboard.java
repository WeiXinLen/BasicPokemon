/**
 * java.util.Collections and comparator is imported so that Java can sort the arraylist scores
 * correctly with the help of "comparator" 
 * 
 * java.io is imported to display the right and specific error message under specific
 * conditions
 */ 
package pokemon;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 * The main purpose of this class is to :
 * i) store the players' names and turns into the arraylist
 * ii) create a file to store the scoreboard
 * iii) save the arraylist so the scoeboard file is created and saved in it
 * iv) load the saved scoreboard into the arraylist if there is a scoreboard
 * v) print the scoareboard for the players to see
 * vi) compare the turns of the winners to determine the placing in the scoreboard 
 * 
 * @author Jordan and Nicholas
 *
 */
public class Scoreboard{
	private String name;
	private int turns;
	/**
	 * creation of the file for saving/loading the latest high scores 
	 */
	private static final String fname = "scoreboard.txt";
	
	/**
	 * Initialising inputStream and outputStream for writing or 
	 * reading the high score (scoreboard.txt) file
	 */
	ObjectOutputStream outputStream = null;
	ObjectInputStream inputStream = null;
	
	/**
	 * Arraylist "scoreList" is used to store the object type "score"
	 */
	ArrayList<Score> scoreList = new ArrayList<Score>();
	
	
	/**
	 *This method loads the arraylist that is in the "scoreboard.txt" file and will put it into the "scoreList"-arraylist,
	 * in another words, this function reads the file for the high score to be displayed correctly on the scoreboard
	 * The try-catch structure updates the user with the problems regarding the program (like the file is corrupted or doesn't exist)
	 * however, the first time this class is called in the main class, error message "FNF Error" will appear, 
	 * meaning that the program is unable to find the "scoreboard.txt" file, this is because the file hasn't been created yet
	 * hence, running it the second time will not have this error, 
	 * since Java will create the file itself after the first time this class is called
	 */
	public Scoreboard() {
		try {
			inputStream = new ObjectInputStream(new FileInputStream(fname));
			scoreList = (ArrayList<Score>) inputStream.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("[Load] FNF Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("[Load] IO Error: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("[Load] CND Error : " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();;
					outputStream.close();
				}
			} catch (IOException e) {
				System.out.println("[Load] IO Error: " + e.getMessage());
			}
		}
		save();
	}
	
	/**
	 * This method adds scores to the "scoreboard.txt" with the parameters "name" and "score", 
	// which are the name and the steps taken for the player to win the game respectively
	 * @param name to set the name of the winning players name
	 * @param turns to set the turns taken by the winning player to determine
	 * placing in the scoreboard
	 */
	public void add(String name, int turns) {
		scoreList.add(new Score(name, turns));
		
		/**
		 * The collection.sort() function allows Java to sort the arraylist "scores" correctly with the help of "comparator"
		 * with the new winner value(turns) passed into the arraylist "scorelist", 
		 * and the comparator sorts it out for the placing in the scoreboard
		 */
		Collections.sort(scoreList, new Comparator<Score>() {
			
			/**
			 * This method is used to allow Java make a comparison 
			 * between 2 score objects where the lesser number of steps
			 * taken to win the game will be on the higher place in the
			 * scoreboard
			 * 
			 * When a player takes lesser number of steps to win the game
			 * it will return -1, while return +1 means that the player
			 * takes more step to win the game, and 0 means that they 
			 * both have the same number of steps 
			 */
			public int compare(Score score1, Score score2) {
				int turn1 = score1.getTurns();
				int turn2 = score2.getTurns();
				
				if (turn1 > turn2) {
					return +1;
				} else if (turn1 < turn2) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		
		if (scoreList.size() > 10) {
			scoreList.subList(10, scoreList.size()).clear();
		}
		save();
	}
	
	/**
	 * This method is used to print the scoreboard in table format when it is called,
	 * if the scoreboard is empty, then "The scoreboard is empty!" will be shown instead
	 */
	public void show() {
		if (scoreList.size() == 0) {
			System.out.println("The scoreboard is empty!");
		} else {
			System.out.println(" " + String.format("%35s", "").replace(" ", "_") + " ");
			System.out.println("| No | Name                 | Turns |");
			System.out.println("|" + String.format("%35s", "").replace(" ", "=") + "|");
			for(int i = 0; i < scoreList.size(); i++) {
				System.out.format("| %-2d | %-20s | %-5d |\n", (i + 1), scoreList.get(i).getName(), scoreList.get(i).getTurns());
			}
			System.out.println("|" + String.format("%35s", "").replace(" ", "_") + "|");
		}
	}
	
	/**
	 * This method performs the clearing scoreboard feature
	 * and prints out a short sentence to notify the user about the action,
	 * and calls the save() method to ensure the "scoreboard.txt" file is updated,
	 * meaning the file is updated to be empty 
	 */
	public void clear() {
		scoreList.clear();
		System.out.println("Scoreboard has been cleared!");
		save();
	}
	
	/**
	 * this method is the same asScoreboard(),
	 * but it perform write instead of reading,
	 * ensuring the the "scoreboard.txt" file is updated with the latest values
	 * from the scoreList-arraylist
	 */
	private void save() {
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(fname));
			outputStream.writeObject(scoreList);
		} catch (FileNotFoundException e) {
			System.out.println("[Update] FNF Error : " + e.getMessage() + ", the program will try and make a new file");
		} catch (IOException e) {
			System.out.println("[Update] IO Error: " + e.getMessage());
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch ( IOException e) {
				System.out.println("[Update] Error: " + e.getMessage());
			}
		}
	}
}
 