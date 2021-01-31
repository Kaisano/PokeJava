package game;
public class bulbasaur extends pocketMonsters {
	private String[] moveset = {"Tackle", "Solar Beam", "Headbutt", "Scratch"};

	/**
	 * initialize stats for bulbasaur 
	 * @param Health basic health stat
	 * @param Attack basic attack stat
	 * @param Defense basic defense stat
	 */
	public bulbasaur(int Health, int Attack, int Defense) {
		super(Health, Attack, Defense);
	}
	
	/**
	 * default constructor
	 */
	public bulbasaur()
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
				return this.getAtk()*2;
			case 1:
				return this.getAtk()*4;
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
	
	@Override
	public String toString()
	{
		return "Bulbasaur";
	}

}
