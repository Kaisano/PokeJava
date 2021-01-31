package game;
public class squirtle extends pocketMonsters {
	private String[] moveset = {"Water Gun", "tackle", "Headbutt", "Hydro Pump"};

	/**
	 * initialize stats for squirtle 
	 * @param Health basic health stat
	 * @param Attack basic attack stat
	 * @param Defense basic defense stat
	 */
	public squirtle(int Health, int Attack, int Defense) {
		super(Health, Attack, Defense);
	}
	
	/**
	 * default constructor
	 */
	public squirtle()
	{
	}
	
	/**
	 * @param move what move is selected
	 * @return the damage dealt to the opposing pokemon
	 */
	public int moveSet(int move)
	{
		switch(move){
			case 0:
				return this.getAtk()*3;
			case 1:
				return this.getAtk()*2;
			case 2:
				return this.getAtk()*3;
			case 3:
				return this.getAtk()*4;
		}
		//return a  move that does damage
		return 0;
	}
	
	/**
	 * gets the moveset
	 */
	public String[] getMoveSetNames() {
		return moveset;
	}
	
	@Override
	public String toString()
	{
		return "Squirtle";
	}
}
