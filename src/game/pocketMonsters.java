package game;

public abstract class pocketMonsters {
	private int HP,Atk,Def;
	
	/**
	 * defined per pokemon
	 * @param Health default health stat
	 * @param Attack default attack stat
	 * @param Defense default defense stat
	 */
	public pocketMonsters(int Health, int Attack, int Defense)
	{
		HP = Health;
		Atk = Attack;
		Def = Defense;
	}
	/**
	 * default constructor
	 */
	public pocketMonsters()
	{
	}
	
	/**
	 * @return the current moveset of the pokemon
	 */
	public abstract String[] getMoveSetNames();
	
	/**
	 * @return attack stat
	 */
	public int getAtk()
	{
		return Atk;
	}
	
	/**
	 * @return defense stat
	 */
	public int getDef()
	{
		return Def;
	}
	
	/**
	 * @return Health Point stat
	 */
	public int getHP()
	{
		return HP;
	}
	
	/**
	 * @param Health sets Helth stat
	 */
	public void setHP(int Health)
	{
		HP = Health;
	}
	
	/**
	 * @param attack sets Attack stat
	 */
	public void setAtk(int attack)
	{
		Atk = attack;
	}
	
	/**
	 * @param Defense sets Defense stat
	 */
	public void setDef(int Defense)
	{
		Def = Defense;
	}
	
	/**
	 * @param move what move is selected
	 * @return the damage dealt to the opposing pokemon
	 */
	abstract int moveSet(int move);
	
	@Override
	public String toString()
	{
		return null;
	}
}
