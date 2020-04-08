package pokemon;

/**
 * This class contains various methods for manipulating arrays
 * (such as sorting and searching). This class also contains a
 * static factory that allows arrays to be viewed as lists.
 */
import java.util.Arrays;

/**
 * An ordered collection (also known as a sequence). The user
 * of this interface has precise control over where in the list
 * each element is inserted. The user can access elements by their
 * integer index (position in the list), and search for elements
 * in the list.
 */
import java.util.List;

/**
 * An instance of this class is used to generate a stream of
 * pseudorandom numbers. The class uses a 48-bit seed, which
 * is modified using a linear congruential formula.
 */
import java.util.Random;

/**
 * A random number generator isolated to the current thread.
 * A ThreadLocalRandom is initialized with an internally generated 
 * seed that may not otherwise be modified.
 */
import java.util.concurrent.ThreadLocalRandom;

/**
 * The abstract class ensures that the pokemon created in the Player
 * class has a 'type'(Attacking, Defending, Fairy).
 * 
 * @author Jordan and Nicholas
 */
abstract class Pokemon {
	private int name, exp = 0, stage = 0, maxHP, HP, maxEnergy, energy, idle = 0;
	private String energyColour, type, state = "Active";
	
	Random rand = new Random();
	
	/**
	 * The ThreadLocalRandom.current().nextInt(50, 81) and (20, 51)
	 * is used to generate random numbers for the maxHP and maxEnergy
	 * from 50 to 81 and 20 to 51 respectively.
	 * 
	 * Arrays.asList is used to convert the array to a list so that the
	 * value in the array can be randomised to the pokemon.
	 * 
	 * @param name to set the pokemon's name
	 */
	public Pokemon(int name) {
		this.name = name;
		this.maxHP = ThreadLocalRandom.current().nextInt(50, 81);
		this.HP = this.maxHP;
		this.maxEnergy = ThreadLocalRandom.current().nextInt(20, 51);
		this.energy = this.maxEnergy;
		this.energyColour = Arrays.asList("Red", "Blue", "Yellow", "Colourless").get(rand.nextInt(4));
	}
	
	/**
	 * @return the exp from the main program
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * @param exp to set the exp for the pokemon
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
	
	/**
	 * This method is to increase the exp of the pokemon depending on
	 * the situation(when a player wants to attack, train, this method
	 * will be called to increase the exp).
	 * 
	 * Once the exp reaches 20, the pokemon will increase a stage and
	 * all the pokemon stats will be doubled but the HP and energy will
	 * be fully replenished(this is done to reflect more like a proper
	 * pokemon game
	 * 
	 * @param exp to set the new exp for pokemon
	 */
	public void increaseExp(int exp) {
		this.exp += exp;
		if (this.exp >= 20) {
			this.stage += 1;
			this.exp = 0;
			maxHP = multiply(maxHP);
			HP = maxHP;
			maxEnergy = multiply(maxEnergy);
			energy = maxEnergy;
		}
	}
	/**
	 * @return the maxHp of the pokemon
	 */
	public int getMaxHP() {
		return maxHP;
	}
	
	/**
	 * @param maxHP to set the pokemon's maxHP
 	 */
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	
	/**
	 * @return the maxEnergy of the pokemon
	 */
	public int getMaxEnergy() {
		return maxEnergy;
	}
	
	/**
	 * @param maxEnergy to set the pokemon's maxEnergy
 	 */
	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	
	/**
	 * @return the stage of the pokemon
	 */
	public int getStage() {
		return stage;
	}
	
	/**
	 * @param stage to set the pokemon's stage
 	 */
	public void setStage(int stage) {
		this.stage = stage;
	}
	
	/**
	 * @return the HP of the pokemon
	 */
	public int getHP() {
		return HP;
	}
	
	/**
	 * @param HP to set the pokemon's HP
 	 */
	public void setHP(int HP) {
		this.HP = HP;		
	}
	
	/**
	 * When pokemon's HP reaches 0, it will change the state of the
	 * pokemon from 'Active' to 'Defeated'.
	 * 
	 * @param HP so that when a pokemon is attacked or poisoned, it's
	 * HP will decrease
	 */
	public void decreaseHP(int HP) {
		this.HP -= HP;
		System.out.println("HitPoint for Pokemon " + name + " damaged by " + HP);
		if (this.HP < 0) {
			this.HP = 0;
			state = "Defeated";
		}
	}
	
	/**
	 * @return the energy of the pokemon
	 */
	public int getEnergy() {
		return energy;
	}

	/**
	 * @param energy to set the pokemon's energy
 	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	/**
	 * To increase energy when a stage is increased
	 * @param e so that the current energy value of pokemon will be passed to
	 * parameter e and increase the energy (double it)
	 */
	public void increaseEnergy(int e) {
		this.energy += e;
	}
	
	/**
	 * When the player performs attack or train, this method will be called
	 * to the main program to reduce the current energy of the pokemon.
	 * 
	 * @param e is the current pokemon's energy
	 */
	public void decreaseEnergy(int e) {
		this.energy -= e;
		System.out.println("Energy for Pokemon " + name + " reduced by " + e);
	}
	
	/**
	 * Arrays.asList is used to convert the array to a list so that the
	 * value in the array can be randomised to the pokemon.(The energy
	 * drawn will be randomised to the player).
	 * 
	 * @return drawn to the player
	 */
	public String drawEnergyCard() {
		String drawn = Arrays.asList("Red", "Blue", "Yellow").get(rand.nextInt(3));
		System.out.println("Draw card... Color drawn: " + drawn);
		return drawn;
	}
	
	/**
	 * @return the name of the pokemon
	 */
	public int getName() {
		return name;
	}

	/**
	 * @param name to set the pokemon's name
 	 */
	public void setName(int name) {
		this.name = name;
	}

	/**
	 * @return the idle of the pokemon
	 */
	public int getIdle() {
		return idle;
	}

	/**
	 * @param idle to set the pokemon's idle
 	 */
	public void setIdle(int idle) {
		this.idle = idle;
	}

	/**
	 * @return the eneryColour of the pokemon
	 */
	public String getenergyColour() {
		return energyColour;
	}

	/**
	 * @param energyColour to set the pokemon's energyColour
 	 */
	public void setenergyColour(String energyColour) {
		this.energyColour = energyColour;
	}

	/**
	 * @return the type of the pokemon
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type to set the pokemon's type
 	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the state of the pokemon
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state to set the pokemon's state
 	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * For each increased stage, the stats of the pokemon will be
	 * calculated in such way: 2^Stage. This means that the stats will
	 * be doubled corresponding to the current stage of the pokemon
	 * (Eg: When stage is 0, all the pokemon will be at their base stats
	 * since 2^0=1.
	 * When stage is 1, all the pokemon's stats will be doubled based on
	 * the stage since 2^1=2).
	 * 
	 * @param variable so that the stats can be applied in this parameter
	 * 
	 * @return the value after multiplying the stats
	 */
	public int multiply(int variable) {
		return (int) (variable * Math.pow(2, stage));
	}
	
}