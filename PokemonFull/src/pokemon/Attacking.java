package pokemon;

/**
 * A random number generator isolated to the current thread.
 * A ThreadLocalRandom is initialized with an internally generated 
 * seed that may not otherwise be modified.
 */
import java.util.concurrent.ThreadLocalRandom;

/**
 * The contents of Attacking class are basically inherited from
 * the Pokemon class so that it can use all the code from that
 * class itself.
 * 
 * @author Jordan and Nicholas
 */
public class Attacking extends Pokemon{
	private int attack;
	
	/**
	 * In this Attacking class, it has a Attacking constructor 
	 * with the variable 'name' of integer type in the parameter.
	 * 
	 * @param name to set the pokemon's name
	 * 
	 * In the Attacking constructor, the name of the pokemon is taken
	 * from the superclass, Pokemon class, using the reserve word
	 * "super" and set the pokemon type to 'Attacking'.
	 * 
	 * The ThreadLocalRandom.current().nextInt(2, 6) is used to
	 * generate random numbers for the attacking point from 2 to 6.
	 */
	public Attacking(int name) {
		super(name);
		this.attack = ThreadLocalRandom.current().nextInt(2, 6);
		this.setType("Attacking");
	}
	
	/**
	 * This method is to get the value for attacking from the main
	 * program.
	 * 
	 * @return the value for attacking
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * This method is to set the value for the attack from the
	 * main program.
	 * 
	 * @param attack to set the attack of the pokemon
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}
}
