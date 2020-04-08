package pokemon;

/**
 * The contents of Fairy class are basically inherited from
 * the Pokemon class so that it can use all the code from that
 * class itself.
 * 
 * @author Jordan and Nicholas
 */
public class Fairy extends Pokemon{
	
	/**
	 * In this Fairy class, it has a Fairy constructor with the
	 * variable 'name' of integer type in the parameter
	 * 
	 * @param name to set the pokemon's name
	 * 
	 * In the Fairy constructor, the name of the pokemon is taken
	 * from the superclass, Pokemon class, using the reserve word
	 * "super" and set the pokemon type to 'Fairy'.
	 * 
	 */
	public Fairy(int name) {
		super(name);
		this.setType("Fairy");
	}
}