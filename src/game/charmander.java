package game;
public class charmander extends pocketMonsters {
	private String[] moveset = {"Flamethrower", "Tackle", "Spark", "Scratch"};
	
	/**
	 * initialize stats for charmander 
	 * @param Health basic health stat
	 * @param Attack basic attack stat
	 * @param Defense basic defense stat
	 */
	public charmander(int Health, int Attack, int Defense) {
		super(Health, Attack, Defense);
	}
	
	/*
	 * default constructor
	 */
	public charmander()
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
				return this.getAtk()*4;
			case 1:
				return this.getAtk()*2;
			case 2:
				return this.getAtk()*3;
			case 3:
				return this.getAtk()*2;
		}
		//return a move that does damage
		return 0;
	}
	
	/**
	 * returns moveset
	 */
	public String[] getMoveSetNames() {
		return moveset;
	}
	public String toString() 
	{
		return "Charmander";
	}
}
