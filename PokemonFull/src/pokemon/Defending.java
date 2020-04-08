package pokemon;

/**
 * A random number generator isolated to the current thread.
 */
import java.util.concurrent.ThreadLocalRandom;

/**
 * The contents of Defending class are basically inherited from
 * the Pokemon class so that it can use all the code from that
 * class itself.
 * 
 * @author Jordan and Nicholas
 */
public class Defending extends Pokemon{
	private int resistance;
	
	/**
	 * In this Defending class, it has a Defending constructor 
	 * with the variable 'name' of integer type in the parameter.
	 * 
	 * @param name to set the pokemon's name
	 * 
	 * In the Defending constructor, the name of the pokemon is taken
	 * from the superclass, Pokemon class, using the reserve word
	 * "super" and set the pokemon type to 'Defending'.
	 * 
	 * The ThreadLocalRandom.current().nextInt(1, 4) is used to
	 * generate random numbers for the resistance point from 1 to 4.
	 */
	public Defending(int name) {
		super(name);
		this.resistance = ThreadLocalRandom.current().nextInt(1, 4);
		this.setType("Defending");
	}
	
	/**
	 * This method is to get the value for resistance from the main
	 * program.
	 * 
	 * @return the value for resistance
	 */
	public int getResistance() {
		return resistance;
	}
	
	/**
	 * This method is to set the value for the resistance from the
	 * main program.
	 * 
	 * @param resistance to set the resistance of the pokemon
	 */
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}
}
