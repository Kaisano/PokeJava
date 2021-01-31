package game;

public class pikachu extends pocketMonsters {
	private String[] moveset = {"Quick Attack", "Tackle", "Thunder", "Scratch"};

	/**
	 * initialize stats for pikachu 
	 * @param Health basic health stat
	 * @param Attack basic attack stat
	 * @param Defense basic defense stat
	 */
	public pikachu(int Health, int Attack, int Defense) {
		super(Health, Attack, Defense);
	}
	
	/**
	 * default constructor
	 */
	public pikachu()
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
				return this.getAtk()*4;
			case 3:
				return this.getAtk()*2;
		}
		//return a random move that does damage
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
		return "Pikachu";
	}
}
