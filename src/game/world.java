package game;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
//pre: the user has logged into the program and it is a valid user too.
public class world extends JFrame implements KeyListener  {
	private static String location = System.getProperty("user.dir"); //gets current directory of the folder
	public static String userFile = location + "\\Players\\Default.txt"; //location of the userfile
	
	private int upCount = 0, downCount = 0, leftCount = 0, rightCount = 0; //for the walk thingy. not too good honestly.
	
	private static boolean debug = false; //debug boolean
	
	private static boolean menuOnOff = false; //tracker if the menu is on or off
	private static boolean HPSET;
	private JPanel imageFrames, pauseMenu; //Frame that holds all components
	
	//tiles to be displayed as map
	//TL = top left, TM = top middle, TR = top right
	//ML = middle left, Character = middle middle, MR = middle right
	//BL = bottom left, BM = bottom middle, BR = bottom right
	private static JLabel TL, TM, TR, ML, Character, MR, BL, BM, BR;
	
	
	//integer is used instead of enum due to wording shortening. Instead of enum.ENUM.getValue(), it is simply the word
	//F = floor, G = ground, R = rock
	private static final int FL = 0, GR = 2, RK = 1;
	
	
	//the current map that is displayed. tileSize: 7X31
	private static int map[][] = {
		//   1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
			{RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK},// 1
			{RK,GR,GR,GR,GR,GR,GR,GR,RK,RK,GR,GR,GR,GR,GR,RK,RK,RK,RK,RK,RK,RK,GR,GR,GR,GR,GR,GR,RK,RK,RK},// 2
			{RK,GR,GR,GR,GR,GR,GR,GR,RK,GR,GR,GR,GR,GR,GR,GR,GR,RK,RK,FL,RK,RK,GR,GR,GR,GR,GR,GR,GR,GR,RK},// 3
			{RK,GR,GR,GR,GR,GR,GR,GR,GR,GR,GR,GR,RK,GR,GR,GR,GR,FL,FL,FL,RK,GR,GR,GR,GR,GR,GR,GR,GR,GR,RK},// 4
			{RK,GR,GR,RK,RK,GR,GR,GR,GR,GR,GR,RK,RK,GR,GR,GR,GR,FL,FL,FL,GR,GR,GR,GR,GR,GR,RK,RK,GR,GR,RK},// 5
			{RK,GR,GR,RK,RK,RK,GR,GR,GR,GR,RK,RK,GR,GR,GR,FL,FL,FL,FL,FL,GR,GR,GR,GR,GR,RK,RK,GR,GR,GR,RK},// 5
			{RK,GR,RK,RK,RK,RK,RK,FL,RK,RK,RK,RK,GR,GR,GR,FL,FL,FL,FL,FL,GR,GR,GR,GR,GR,RK,RK,GR,GR,GR,RK},// 5
			{RK,GR,GR,RK,RK,RK,GR,FL,FL,RK,RK,GR,GR,GR,GR,FL,FL,FL,FL,FL,GR,GR,RK,FL,FL,RK,GR,GR,RK,GR,RK},// 5
			{RK,GR,GR,RK,RK,GR,GR,GR,GR,GR,GR,GR,GR,GR,RK,FL,GR,RK,GR,FL,GR,GR,GR,GR,GR,GR,GR,GR,GR,GR,RK},// 5
			{RK,GR,GR,GR,GR,GR,GR,GR,GR,GR,GR,GR,GR,RK,RK,RK,GR,FL,GR,FL,GR,GR,GR,GR,GR,GR,GR,GR,GR,GR,RK},// 5
			{RK,GR,GR,GR,GR,GR,GR,GR,GR,GR,GR,GR,RK,RK,RK,RK,FL,FL,FL,RK,GR,GR,GR,GR,GR,GR,GR,GR,GR,GR,RK},// 5
			{RK,GR,GR,GR,GR,GR,RK,GR,GR,GR,GR,GR,RK,RK,RK,RK,FL,FL,FL,RK,GR,GR,GR,GR,GR,RK,RK,GR,GR,GR,RK},// 5
			{RK,GR,GR,GR,GR,RK,RK,GR,FL,RK,RK,GR,GR,GR,RK,RK,FL,FL,FL,RK,RK,GR,GR,GR,GR,RK,RK,GR,GR,GR,RK},// 5
			{RK,GR,GR,GR,GR,GR,GR,GR,FL,RK,RK,GR,GR,GR,RK,RK,FL,FL,FL,RK,RK,RK,GR,GR,GR,GR,GR,GR,GR,GR,RK},// 6
			{RK,RK,GR,GR,GR,GR,GR,GR,FL,RK,RK,GR,GR,GR,RK,GR,FL,FL,FL,FL,RK,RK,RK,GR,GR,GR,GR,GR,GR,GR,RK},// 7
			{RK,RK,RK,RK,FL,FL,FL,FL,FL,RK,RK,RK,RK,GR,RK,GR,FL,FL,FL,FL,FL,RK,RK,GR,GR,GR,GR,GR,GR,GR,RK},// 8
			{RK,RK,RK,FL,FL,FL,FL,FL,FL,FL,RK,RK,FL,GR,GR,GR,FL,FL,FL,FL,FL,RK,RK,RK,FL,FL,GR,FL,RK,GR,RK},// 9
			{RK,RK,FL,FL,FL,FL,FL,FL,FL,FL,FL,RK,FL,GR,GR,GR,FL,FL,FL,FL,FL,FL,RK,RK,FL,FL,GR,RK,GR,GR,RK},// 1
			{RK,RK,FL,FL,FL,RK,RK,FL,FL,FL,FL,FL,FL,GR,GR,GR,GR,GR,GR,GR,GR,FL,FL,FL,FL,FL,GR,GR,GR,GR,RK},// 1
			{RK,RK,FL,GR,GR,RK,RK,RK,FL,FL,FL,FL,FL,GR,GR,GR,GR,GR,GR,GR,GR,FL,FL,FL,FL,FL,GR,GR,GR,RK,RK},// 1
			{RK,RK,FL,GR,GR,RK,RK,RK,RK,FL,FL,FL,FL,GR,GR,GR,GR,GR,GR,GR,GR,FL,FL,FL,FL,GR,GR,GR,RK,RK,RK},// 1
			{RK,RK,FL,FL,RK,RK,RK,FL,FL,FL,FL,FL,RK,GR,GR,GR,GR,GR,GR,GR,GR,FL,FL,FL,FL,GR,RK,RK,RK,RK,RK},// 1
			{RK,RK,GR,FL,RK,RK,RK,FL,FL,FL,FL,FL,RK,GR,GR,GR,RK,RK,GR,GR,GR,FL,FL,FL,GR,GR,GR,GR,RK,RK,RK},// 1
			{RK,RK,GR,RK,RK,RK,RK,FL,FL,FL,FL,FL,RK,GR,GR,GR,RK,RK,RK,GR,GR,FL,FL,FL,GR,RK,GR,GR,GR,GR,RK},// 1
			{RK,GR,GR,FL,RK,RK,FL,FL,FL,FL,FL,FL,RK,GR,GR,GR,GR,RK,GR,GR,GR,FL,FL,FL,GR,RK,RK,GR,GR,GR,RK},// 1
			{RK,GR,GR,FL,FL,FL,FL,GR,GR,FL,FL,RK,RK,GR,GR,GR,GR,GR,GR,GR,GR,FL,FL,FL,GR,GR,RK,RK,GR,GR,RK},// 2
			{RK,GR,FL,FL,FL,FL,FL,GR,GR,FL,RK,RK,RK,GR,GR,GR,GR,GR,GR,GR,GR,FL,FL,RK,GR,GR,RK,GR,GR,GR,RK},// 3
			{RK,RK,FL,FL,FL,FL,RK,FL,FL,FL,RK,RK,RK,GR,GR,GR,GR,GR,GR,GR,GR,FL,FL,RK,GR,RK,GR,GR,GR,RK,RK},// 4
			{RK,RK,RK,FL,FL,FL,RK,FL,FL,RK,RK,RK,RK,RK,GR,GR,GR,GR,GR,GR,GR,RK,FL,RK,GR,GR,GR,GR,RK,RK,RK},// 5
			{RK,RK,RK,RK,GR,RK,RK,FL,RK,RK,RK,RK,RK,RK,RK,GR,GR,GR,GR,GR,GR,RK,RK,RK,GR,GR,GR,RK,RK,RK,RK},// 6
			{RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK,RK} // 7
		};
	private static int posX = (map[0].length)/2; //eventually replace with login user settings
	private static int posY = (map.length)/2;
	private static int perimPlayer[][] = new int[3][3]; //perimeter around the player to display images
	private static pocketMonsters userU, otherO;
	//images: 0 = grey rocks, 1 = fence, 2 tiles.Grass.getValue()
	
	//this is the pokemon array for what pokemon the player has //Pokemon pokeSet[] = new Pokemon[3]; 
	
	//up facing images
	private static ImageIcon upImgs[] = {	new ImageIcon(location + "\\Images\\Character\\up_static.png"),
									new ImageIcon(location + "\\Images\\Character\\up_walk1.png"),
									new ImageIcon(location + "\\Images\\Character\\up_walk2.png"),
							 	 };
	//down facing images
	private static ImageIcon downImgs[] =  { 	new ImageIcon(location + "\\Images\\Character\\down_static.png"),
										new ImageIcon(location + "\\Images\\Character\\down_walk1.png"),
										new ImageIcon(location + "\\Images\\Character\\down_walk2.png"),
									};
	//left facing images
	private static ImageIcon leftImgs[] =  {	new ImageIcon(location + "\\Images\\Character\\left_static.png"),
										new ImageIcon(location + "\\Images\\Character\\left_walk1.png"),
										new ImageIcon(location + "\\Images\\Character\\left_walk2.png"),
									};
	//right facing images
	private static ImageIcon rightImgs[] = {	new ImageIcon(location + "\\Images\\Character\\right_static.png"),
										new ImageIcon(location + "\\Images\\Character\\right_walk1.png"),
										new ImageIcon(location + "\\Images\\Character\\right_walk2.png"),
									};
	//terrain images -- expandable
	private static ImageIcon terrain[] = 	{	new ImageIcon(location + "\\Images\\Terrain\\floor.png"),
										new ImageIcon(location + "\\Images\\Terrain\\rocks.png"),
										new ImageIcon(location + "\\Images\\Terrain\\grass.png"),
									};
	/*
	private ImageIcon megumin1 = new ImageIcon(location+"\\images\\Megumin.png");
	private Image megumin2 = megumin1.getImage().getScaledInstance(250,500, Image.SCALE_DEFAULT);
	private ImageIcon meguminChar = new ImageIcon(megumin2);
	*/
	private static ImageIcon pI = new ImageIcon(location + "\\Images\\Pokemon\\Pikachu\\1.png");
	private static Image pI2 = pI.getImage().getScaledInstance(165, 165, Image.SCALE_DEFAULT);
	private static ImageIcon pikachuIcon = new ImageIcon(pI2);
	//pikachu images
	private static ImageIcon pikachuImgs[] = 	{ 	new ImageIcon(location + "\\Images\\Pokemon\\Pikachu\\2.png"),
											new ImageIcon(location + "\\Images\\Pokemon\\Pikachu\\3.png")
										};
	//squritle images
	private static ImageIcon squritleImgs[] = 	{ 	new ImageIcon(location + "\\Images\\Pokemon\\Squirtle\\2.png"),
											new ImageIcon(location + "\\Images\\Pokemon\\Squirtle\\3.png")
										};
	//bulbasaur images
	private static ImageIcon bulbasaurImgs[] = { 	new ImageIcon(location + "\\Images\\Pokemon\\Bulbasaur\\2.png"),
											new ImageIcon(location + "\\Images\\Pokemon\\Bulbasaur\\3.png")
										};
	//charmander images
	private static ImageIcon charmandarImgs[] = { 	new ImageIcon(location + "\\Images\\Pokemon\\Charmander\\2.png"),
											new ImageIcon(location + "\\Images\\Pokemon\\Charmander\\3.png")
										};
	
	
	/**
	 * resizes icons to the correct size
	 * @param icons what icon set should be resized
	 */
	public static void resizeP(ImageIcon[] icons)
	{
		int count = 0;
		for(ImageIcon i : icons)
		{
			Image t = i.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
			icons[count] = new ImageIcon(t);
			
			count++;
		}
	}
	
	//private ArrayList<JLabel> mapDisplay = new ArrayList<JLabel>(); //idea of loading all of the map components, but scrapped for now
	private static JLabel UnderChar;
	private static JPanel battle;
	private static ArrayList<JLabel> messages = new ArrayList<JLabel>(); //sets the labels for the text display in the top left corner of the battle panel
	private static boolean inBattle = false; //if the player is in a battle or not.
	private static ImageIcon userIcon; //, opponentIcon; later implementation
	
    //private final Set<Character> pressed = new HashSet<Character>(); used for loading multiple keystrokes
    //private final List<Character> pressedAL = new ArrayList<Character>(); 
	
	
    //all JLabels for the popup menu
    private JLabel PKM_1_NAME;
    private JLabel PKM_1_IMG;
    private JLabel PKM_1_DSC;
    
    private JLabel PKM_2_NAME;
    private JLabel PKM_2_IMG;
    private JLabel PKM_2_DSC;
    
    private JLabel PKM_3_NAME;
    private JLabel PKM_3_IMG;
    private JLabel PKM_3_DSC;
    
    private JLabel PKM_4_NAME;
    private JLabel PKM_4_IMG;
    private JLabel PKM_4_DSC;
    private static JPanel GUI;
    private static JLabel otherPokemonIMG ;
	private static JLabel userPokemonIMG;
    private JPanel chooseMove;
    private static JButton move1, move2, move3, move4;
    private static int userHP, otherHP;
    private static JLabel otherHPLbl, userHPLbl;
    
    //private static pokemon userPkmn, otherPkmn;
	/**
	 * Launch the application <br>
	 * for testing
	 */
	public static void main(String[] args) {
		if(debug) {
			System.out.println(downImgs[1].toString());
			int grass = 0, rocks = 0, floor = 0;
			for(int[] arr : map)
			{
				for(int num : arr)
				{
					switch(num) {
						case 0:
							floor++;
							break;
						case 1:
							rocks++;
							break;
						case 2:
							grass++;
						default:
							break;
					}
				}
			}
			System.out.println(returnSize() + "\n" + "Floor: " + floor + "\n" + "Rocks: " + rocks + "\n" + "Grass: " + grass + "\n");
		}
			EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					world frame = new world();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame and the components inside it including the main game and tiles
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public world() throws InterruptedException, NumberFormatException, IOException {
		resizeP(pikachuImgs);
		resizeP(squritleImgs);
		resizeP(bulbasaurImgs);
		resizeP(charmandarImgs);
		
		//sets the posX and posY of the player that was saved in the file.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 940);
		imageFrames = new JPanel();
		imageFrames.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(imageFrames);
		imageFrames.setFocusable(true);
		setLocationRelativeTo(null);
		
		//pause menu panel
		pauseMenu = new JPanel(); //pause menu when the player presses escape
		pauseMenu.setBounds(544, 0, 360, 900);
		pauseMenu.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pauseMenu.setVisible(false); 
		imageFrames.setLayout(null);
		
		//battle frame panel
		battle = new JPanel();
		battle.setFocusable(false);
		battle.setBounds(0, 0, 904, 900);
		imageFrames.add(battle);
		battle.setVisible(false);
		battle.setLayout(null);
		
		
		
		userPokemonIMG = new JLabel(userIcon);
		userPokemonIMG.setFocusable(false);
		userPokemonIMG.setBounds(0, 500, 400, 400);
		battle.add(userPokemonIMG);
		
		otherPokemonIMG = new JLabel();
		otherPokemonIMG.setFocusable(false);
		otherPokemonIMG.setBounds(504, 0, 400, 400);
		battle.add(otherPokemonIMG);
		
		chooseMove = new JPanel();
		chooseMove.setFocusable(false);
		chooseMove.setBounds(504, 660, 400, 240);
		battle.add(chooseMove);
		chooseMove.setLayout(null);
		
		move1 = new JButton("moveTL");
		/*move1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMessage(messages, "move 1");
			}
		});*/
		
		move1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		move1.setFocusable(false);
		move1.setBounds(0, 0, 200, 120);
		chooseMove.add(move1);
		
		move2 = new JButton("moveTL");
		/*move2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addMessage(messages, "move 2");
			}
		});*/
		move2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		move2.setFocusable(false);
		move2.setBounds(200, 0, 200, 120);
		chooseMove.add(move2);
		
		move3 = new JButton("moveTL");
		/*move3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addMessage(messages, "move 3");
			}
		});*/
		move3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		move3.setFocusable(false);
		move3.setBounds(0, 120, 200, 120);
		chooseMove.add(move3);
		
		move4 = new JButton("moveTL");
		/*move4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addMessage(messages, "move 4");
			}
		});*/
		move4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		move4.setFocusable(false);
		move4.setBounds(200, 120, 200, 120);
		chooseMove.add(move4);
		
		JPanel eventsText = new JPanel();
		eventsText.setBorder(new LineBorder(new Color(0, 0, 0)));
		eventsText.setBounds(10, 11, 400, 115);
		battle.add(eventsText);
		eventsText.setLayout(null);
		
		userHPLbl = new JLabel("HPUSER");
		userHPLbl.setBounds(10, 461, 384, 28);
		battle.add(userHPLbl);
		
		otherHPLbl = new JLabel("HPUSER");
		otherHPLbl.setBounds(514, 411, 384, 28);
		battle.add(otherHPLbl);
		
		imageFrames.add(pauseMenu);
		
		int count = 0;
		for(int i = 0 ; i < 5 ; i++)
		{
			messages.add(new JLabel(""));
			messages.get(i).setBounds(0,count, battle.getWidth(),23);
			eventsText.add(messages.get(i));
			count+=23;
		}
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 44));
		btnSave.setBounds(10, 11, 340, 64);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						save();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}

			private void save() throws IOException {

		    	replaceLine(userFile,"posX", "posX:" + posX);
				replaceLine(userFile,"posY", "posY:" + posY);
				JOptionPane.showMessageDialog(null,"Game Saved.");
			}
			
		});
		
		pauseMenu.setLayout(null);
		btnSave.setFocusable(false);
		pauseMenu.add(btnSave);
		//Pause Menu Labels------------------------------------------------------------------------
		PKM_1_NAME = new JLabel("Pokemon1");
		PKM_1_NAME.setBounds(10, 86, 165, 14);
		PKM_1_NAME.setFocusable(false);
		pauseMenu.add(PKM_1_NAME);
		
		PKM_1_IMG = new JLabel(pikachuIcon);
		PKM_1_IMG.setBounds(10, 111, 165, 165);
		PKM_1_IMG.setFocusable(false);
		pauseMenu.add(PKM_1_IMG);
		
		PKM_1_DSC = new JLabel("TO BE IMPLEMENTED");
		PKM_1_DSC.setBounds(10, 287, 165, 128);
		PKM_1_DSC.setFocusable(false);
		pauseMenu.add(PKM_1_DSC);
		
		PKM_2_NAME = new JLabel("TO BE IMPLEMENTED");
		PKM_2_NAME.setBounds(185, 86, 165, 14);
		PKM_2_NAME.setFocusable(false);
		pauseMenu.add(PKM_2_NAME);
		
		PKM_2_IMG = new JLabel("TO BE IMPLEMENTED");
		PKM_2_IMG.setBounds(185, 111, 165, 165);
		PKM_2_IMG.setFocusable(false);
		pauseMenu.add(PKM_2_IMG);
		
		PKM_2_DSC = new JLabel("TO BE IMPLEMENTED");
		PKM_2_DSC.setBounds(185, 287, 165, 128);
		PKM_2_DSC.setFocusable(false);
		pauseMenu.add(PKM_2_DSC);
		
		PKM_3_NAME = new JLabel("TO BE IMPLEMENTED");
		PKM_3_NAME.setBounds(10, 426, 165, 14);
		PKM_3_NAME.setFocusable(false);
		pauseMenu.add(PKM_3_NAME);
		
		PKM_3_IMG = new JLabel("TO BE IMPLEMENTED");
		PKM_3_IMG.setBounds(10, 451, 165, 165);
		PKM_3_IMG.setFocusable(false);
		pauseMenu.add(PKM_3_IMG);
		
		PKM_3_DSC = new JLabel("TO BE IMPLEMENTED");
		PKM_3_DSC.setBounds(10, 627, 165, 128);
		PKM_3_DSC.setFocusable(false);
		pauseMenu.add(PKM_3_DSC);
		
		PKM_4_NAME = new JLabel("TO BE IMPLEMENTED");
		PKM_4_NAME.setBounds(185, 426, 165, 14);
		PKM_4_NAME.setFocusable(false);
		pauseMenu.add(PKM_4_NAME);
		
		PKM_4_IMG = new JLabel("TO BE IMPLEMENTED");
		PKM_4_IMG.setBounds(185, 451, 165, 165);
		PKM_4_IMG.setFocusable(false);
		pauseMenu.add(PKM_4_IMG);
		
		PKM_4_DSC = new JLabel("TO BE IMPLEMENTED");
		PKM_4_DSC.setBounds(185, 627, 165, 128);
		PKM_4_DSC.setFocusable(false);
		pauseMenu.add(PKM_4_DSC);
		
		JButton Exit = new JButton("Exit Game");
		Exit.setFont(new Font("Tahoma", Font.PLAIN, 44));
		Exit.setFocusable(false);
		Exit.setBounds(10, 825, 340, 64);
		Exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "EXIT GAME", JOptionPane.YES_NO_OPTION);
		        if (reply == JOptionPane.YES_OPTION) {
		        	System.exit(0);
		        }
		        else {
		        	
		        }
				
			}
			
		});
		pauseMenu.add(Exit);
		
		GUI = new JPanel();
		GUI.setBounds(0, 0, 904, 900);
		imageFrames.add(GUI);
		GUI.setLayout(null);
		
		
		
		
		
		//images for main GUI----------------------------------------------
		Character = new JLabel(downImgs[0]);
		Character.setBounds(300, 300, 300, 300);
		GUI.add(Character);
		Character.setFocusable(false);
		
		TL = new JLabel();
		TL.setBounds(0, 0, 300, 300);
		GUI.add(TL);
		TL.setFocusable(false);
		
		TM = new JLabel();
		TM.setBounds(300, 0, 300, 300);
		GUI.add(TM);
		TM.setFocusable(false);
		
		TR = new JLabel();
		TR.setBounds(600, 0, 300, 300);
		GUI.add(TR);
		TR.setFocusable(false);
		
		ML = new JLabel();
		ML.setBounds(0, 300, 300, 300);
		GUI.add(ML);
		ML.setFocusable(false);
		
		UnderChar = new JLabel();
		UnderChar.setBounds(300, 300, 300, 300);
		GUI.add(UnderChar);
		UnderChar.setFocusable(false);
		
		MR = new JLabel();
		MR.setBounds(600, 300, 300, 300);
		GUI.add(MR);
		MR.setFocusable(false);
		
		BL = new JLabel();
		BL.setBounds(0, 600, 300, 300);
		GUI.add(BL);
		BL.setFocusable(false);
		
		BM = new JLabel();
		BM.setBounds(300, 600, 300, 300);
		GUI.add(BM);
		BM.setFocusable(false);
		
		BR = new JLabel();
		BR.setBounds(600, 600, 300, 300);
		GUI.add(BR);
		BR.setFocusable(false);
		
		pickTexture();
		imageFrames.addKeyListener(this);
		move1.addActionListener(new ActionListener()  { //button listeners to find what the user wants to do
			public void actionPerformed(ActionEvent arg0) {
				btnStuff(userU, otherO, 0, move1);
			}
			
		});
		move2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				btnStuff(userU, otherO, 1, move2);
			}
			
		});
		move3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStuff(userU, otherO, 2, move3);
			}
		});
		move4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStuff(userU, otherO, 3, move4);
			}
		});
		//Image scaledImage = originalImage.getScaledInstance(jPanel.getWidth(),jPanel.getHeight(),Image.SCALE_SMOOTH); for each arrayList
	}

	
	
	
	
	
	
	//METHODS FOR PROGRAM ------------------------------------------------------------------------
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		/*for(java.lang.Character p : pressed)
		{
			pressedAL.add(p);
		}
        pressed.add(e.getKeyChar());
        if (pressed.size() > 1) {
            if(pressedAL.get(0).getKeyCode() == KeyEvent.VK_ESCAPE && pressed.get(1).getKeyCode() == KeyEvent.VK_SHIFT)
            	{
            	
            	}
        }*/
		if(!inBattle) { //sees if they are in battle or not.
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) { //if right button is clicked
				if(debug)
					System.out.println("right");
				
				if(map[posY][posX + 1] != 1) //checks if there is a block there to stop the player
				{
					posX++;
					try {
						walk(rightImgs,rightCount);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					//setImage(MM, rightImgs[2]);
				}
					
				setPerimeter(); //sets the tracking numbers of what the player is on and where the player is.
				
				if(debug) {
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
				
					for(int i = 0 ; i < perimPlayer.length ; i++) {
						for(int j = 0 ; j < perimPlayer[0].length ; j++)
							System.out.print(perimPlayer[i][j]);
						System.out.println();
					}
				}
				rightCount++;
				pickTexture();
				try {
					checkPos();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) { //if left button is clicked
				if(debug) 
					System.out.println("left"); 
				if(map[posY][posX - 1] != 1) //checks if there is a block there to stop the player
				{
					posX--;
					try {
						walk(leftImgs,leftCount);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					//setImage(MM, leftImgs[1]);
				}
				
				setPerimeter();
				if(debug) {
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					
					for(int i = 0 ; i < perimPlayer.length ; i++) {
						for(int j = 0 ; j < perimPlayer[0].length ; j++)
							System.out.print(perimPlayer[i][j]);
						System.out.println();
					}
				}
				leftCount++;
				pickTexture();
				try {
					checkPos();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_UP) { // if up button is clicked
				if(debug)
					System.out.println("up");
				if(map[posY - 1][posX] != 1) //checks if there is a block there to stop the player
				{
					posY--;
					try {
						walk(upImgs,upCount);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					//setImage(MM, upImgs[2]);
				}
				
				setPerimeter();
				if(debug) {
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					
					for(int i = 0 ; i < perimPlayer.length ; i++) {
						for(int j = 0 ; j < perimPlayer[0].length ; j++)
							System.out.print(perimPlayer[i][j]);
						System.out.println();
					}
				}
				upCount++;
				pickTexture();
				try {
					checkPos();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) { // if down button clicked
				if(debug)
					System.out.println("down");
				if(map[posY + 1][posX] != 1) //checks if there is a block there to stop the player
				{
					posY++;
					try {
						walk(downImgs, downCount);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					//setImage(MM, downImgs[2]);
				}
				
				setPerimeter(); //sets the viewing field around the player
				if(debug) {
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println();
					
					for(int i = 0 ; i < perimPlayer.length ; i++) {
						for(int j = 0 ; j < perimPlayer[0].length ; j++)
							System.out.print(perimPlayer[i][j]);
						System.out.println();
					}
				}
				downCount++;
				pickTexture();
				try {
					checkPos();
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			if(menuOnOff)
			{
				inBattle = false;
				pauseMenu.setVisible(false);
				menuOnOff = false;
			}
			else if(!menuOnOff)
			{
				inBattle = true;
				pauseMenu.setVisible(true);
				menuOnOff = true;
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) { //what happens when a key is released?
		//pressed.remove(e.getKeyChar()); used for loading multiple keystrokes. unused atm
		if(!inBattle) {
			if(debug)
				System.out.println("Key Released");
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				setImage(Character, downImgs[0]);
				downCount = 0;
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP) {
				setImage(Character, upImgs[0]);
				upCount = 0;
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				setImage(Character, rightImgs[0]);
				rightCount = 0;
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				setImage(Character, leftImgs[0]);
				leftCount = 0;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	
	
	//image methods ------------------------------------------------------------------------------
	public static void setImage(JLabel imageFrame, ImageIcon image) //sets image of JLabel
	{
		imageFrame.setIcon(image);
		imageFrame.repaint();
	}
	
	public void walk(ImageIcon image[], int count) throws InterruptedException //just sets the image for "walking"
	{
		count %= 2; 
		setImage(Character, image[count + 1]);
	}
	
	public static void pickTexture() //picks the textures
	{
		int choice = 0;
		for(int i = 0; i < perimPlayer.length ; i++)//map.length ; i++)
		{
			for(int j = 0 ; j < perimPlayer[0].length ; j++)//map[0].length ; j++)
			{
				choice = perimPlayer[i][j];
				switch(choice) 
				{
				case 0: //checks floor texture
					if(i == 0 && j == 0)
						setImage(TL, terrain[0]);
					else if(i == 0 && j == 1)
						setImage(TM, terrain[0]);
					else if(i == 0 && j == 2)
						setImage(TR, terrain[0]);
					else if(i == 1 && j == 0)
						setImage(ML, terrain[0]);
					else if(i == 1 && j == 1)
						setImage(UnderChar, terrain[0]);
					else if(i == 1 && j == 2)
						setImage(MR, terrain[0]);
					else if(i == 2 && j == 0)
						setImage(BL, terrain[0]);
					else if(i == 2 && j == 1)
						setImage(BM, terrain[0]);
					else if(i == 2 && j == 2)
						setImage(BR, terrain[0]);
					//put the rest into here
					break; 
					
				case 1: //checks rock texture
					if(i == 0 && j == 0)
						setImage(TL, terrain[1]);
					else if(i == 0 && j == 1)
						setImage(TM, terrain[1]);
					else if(i == 0 && j == 2)
						setImage(TR, terrain[1]);
					else if(i == 1 && j == 0)
						setImage(ML, terrain[1]);
					else if(i == 1 && j == 1)
						setImage(UnderChar, terrain[1]);
					else if(i == 1 && j == 2)
						setImage(MR, terrain[1]);
					else if(i == 2 && j == 0)
						setImage(BL, terrain[1]);
					else if(i == 2 && j == 1)
						setImage(BM, terrain[1]);
					else if(i == 2 && j == 2)
						setImage(BR, terrain[1]);
					break;
					
				case 2: //checks grass
					if(i == 0 && j == 0)
						setImage(TL, terrain[2]);
					else if(i == 0 && j == 1)
						setImage(TM, terrain[2]);
					else if(i == 0 && j == 2)
						setImage(TR, terrain[2]);
					else if(i == 1 && j == 0)
						setImage(ML, terrain[2]);
					else if(i == 1 && j == 1)
						setImage(UnderChar, terrain[2]);
					else if(i == 1 && j == 2)
						setImage(MR, terrain[2]);
					else if(i == 2 && j == 0)
						setImage(BL, terrain[2]);
					else if(i == 2 && j == 1)
						setImage(BM, terrain[2]);
					else if(i == 2 && j == 2)
						setImage(BR, terrain[2]);
					break;
				}
			}
		}
	}
	
	public static void setPerimeter() //sets the perimeter values for the 3x3 map around the player
	{
		perimPlayer[0][2] = map[posY - 1][posX + 1]; //top left
		perimPlayer[0][1] = map[posY - 1][posX]; //top middle
		perimPlayer[0][0] = map[posY - 1][posX - 1]; //top right
		
		perimPlayer[1][2] = map[posY][posX + 1]; //middle left
		perimPlayer[1][1] = map[posY][posX]; //middle middle
		perimPlayer[1][0] = map[posY][posX - 1]; //middle right
		
		perimPlayer[2][2] = map[posY + 1][posX + 1]; //bottom left
		perimPlayer[2][1] = map[posY + 1][posX]; //bottom middle
		perimPlayer[2][0] = map[posY + 1][posX - 1]; //bottom right
	}
	
	public static void checkPos() throws IOException //checks the position of the player and returns value based on it. Also sets up the battle portion of the game. -------------------as8d0-f8a0-r80-128340-c18n2304uj1-278fr09f1cj9-we7jf99qchy9dfh90qhca08fdh68q79w0dh0
, InterruptedException
	{
		int choice = 0;
		if(perimPlayer[1][1] == 2)
		{
			choice = (int)(Math.random()*50); //4/30 chance of getting encounter with pokemon
			if(debug)
				System.out.println(choice);
			
			if(choice < 4) //if the choice is able to be chosen to fight a pokemon ie 0,1,2,3, then start a battle with the pokemon.
			{
				String line = findWord(userFile,"P1"); //finds the word to find the current first slot pokemon
				String userPokemon = line.substring(line.indexOf(":") + 1);
				battle.setVisible(true);
				inBattle = true;
				pocketMonsters user = pickPokemon(userPokemon); //picks the pokemon and sets it as a pokemon Object
				
				user.setAtk(Integer.parseInt(findWord(userFile,"1ATK").substring(findWord(userFile,"1ATK").indexOf(":") + 1))); //sets the attack of the pokemon
				user.setHP(Integer.parseInt(findWord(userFile,"1HP").substring(findWord(userFile,"1HP").indexOf(":") + 1)));
				user.setDef(Integer.parseInt(findWord(userFile,"1DEF").substring(findWord(userFile,"1DEF").indexOf(":") + 1)));
				
				pocketMonsters other = pickPokemon(choice);
				if(debug) {
					System.out.println(userPokemon + " ATK:" + user.getAtk() + " : " + other);
				}
				
				//userPkmn = user; //sets the global variable to set the user pokemon when a button is pushed above
				setImage(userPokemonIMG, pikachuImgs[1]);
				//otherPkmn = other; //sets the global variable to set the opponent pokemon when a button is pushed above
				ImageIcon otherimg = null;
				switch(choice) { //selects image for the other pokemon that is fighting
					case 0:
						otherimg = pikachuImgs[0];
						break;
					case 1:
						otherimg = charmandarImgs[0]; //spelling errors 
						break;
					case 2:
						otherimg = bulbasaurImgs[0];
						break;
					case 3:
						otherimg = squritleImgs[0]; //spelling errrors, fix later
						break;
				}
				setImage(otherPokemonIMG, otherimg);
				String[] userMoveset = user.getMoveSetNames();
				move1.setText(userMoveset[0]); //sets the texts for the moves.
				move2.setText(userMoveset[1]);
				move3.setText(userMoveset[2]);
				move4.setText(userMoveset[3]);
				
				userU = user;
				otherO = other;
				
				fight(user, other); //initiates the battle sequence
				userHPLbl.setText(user + " HP: " + userHP);
				otherHPLbl.setText(other + " HP: " + otherHP);
			}
		}
	}
	
	public static void fight(pocketMonsters user, pocketMonsters other) throws InterruptedException //button listener for 
	{
		if(!HPSET) { //sets the HP if it isn't set.
			HPSET = true;
			if(debug)
				System.out.println(HPSET);
			userHP = user.getHP(); //starts the user HP and the other HP
			otherHP = other.getHP();
		}
			//if(debug)
				//System.out.println(userHP + " : " + otherHP);
	}

	public static void btnStuff(pocketMonsters pkmn, pocketMonsters other, int btnInput, JButton button)
	{
		String userPokemonName = pkmn.toString(), opponentPokemonName = other.toString();
		addMessage(messages, userPokemonName + " used " + button.getText() + " dealing " + other.moveSet(btnInput) + " to " + opponentPokemonName); //sets a message that includes the move and how much damage it did.
		otherHP = otherHP - pkmn.moveSet(btnInput);
		
		int otherMove = (int)(Math.random()*3); //random move the other pokemon will use
		addMessage(messages, opponentPokemonName + " used " + other.getMoveSetNames()[otherMove] + " dealing " + other.moveSet(otherMove) + " damage to your " + userPokemonName);
		userHP = userHP - other.moveSet(otherMove);
		if(debug)
			System.out.println(pkmn + ":" + other);
		if(debug)
			System.out.println(pkmn.moveSet(btnInput) + " 1ATK : " + other.moveSet(otherMove) + " otherAtk\n" + userHP  + " : " + otherHP);
		userHPLbl.setText(pkmn + " HP: " + userHP);
		otherHPLbl.setText(other + " HP: " + otherHP);
		
		if(userHP <= 0)
			userHPLbl.setText(pkmn + " HP: 0");
		if(otherHP <= 0)
			otherHPLbl.setText(other + "HP: 0");
		
		if(otherHP <= 0 || userHP <= 0)
		{
			if(debug)
				System.out.println("battle finished");
			if(userHP <= 0)
			{
				JOptionPane.showMessageDialog(null, "Your pokemon fainted and you ran. End of demo!");
			}
			else if(otherHP <= 0)
			{
				JOptionPane.showMessageDialog(null, other + " fainted!\nYou gained no experience or money!\nBut you won! End of demo!");
			}
			pkmn = null;
			other = null;
			inBattle = false;
			battle.setVisible(false);
			HPSET = false;
			if(debug)
				System.out.println("In Here :3");
		}
		if(otherHP <= 0 || userHP <= 0)
		{
			clearMessage(messages);
		}
	}
    public static pocketMonsters pickPokemon(int name) //picks a pokemon depending on the name put in
    {
    	switch(name)
    	{
    		case 0:
    			return new pikachu(400,8,6);
    		case 1:
    			return new charmander(400,8,6);
    		case 2:
    			return new bulbasaur(400,8,6);
    		case 3:
    			return new squirtle(400,8,7);
    		
    	}
    	return null;
    }
    public static pocketMonsters pickPokemon(String name) //picks a pokemon depending on the name put in
    {
    	switch(name)
    	{
    		case "Pikachu":
    			return new pikachu();
    		case "Charmander":
    			return new charmander();
    		case "Bulbasaur":
    			return new bulbasaur();
    		case "Squirtle":
    			return new squirtle();
    		
    	}
    	return null;
    }
	//for reading and saving to files -------------------------------------------------------------------------------------------------------------
    public static void readFile(ArrayList<String> words, String fName) throws IOException //reads file and puts it into an arrayList
    {
        Scanner input = new Scanner(new FileReader(fName));
        int i=0;                         	//index for placement in the array
        String line;   
        
        while (input.hasNextLine())	//while there is another line in the file
        {
            line = input.nextLine();        	//read in the next line and store it
            words.add(line);               	//add the line into the array
            i++;                          		//advance the index of the array         
        }
        input.close();                
    }
    
    public static int getFileSize(String fileName) throws IOException  //helper for initalizing the array at the beginning
    {
        Scanner input = new Scanner(new FileReader(fileName));
        int size=0;
        
        while (input.hasNextLine())  //while there is another line in the file
        {
            size++;                     		//add to the size
            input.nextLine();          	//go to the next line in the file
        }
        input.close();             		//always close the files when you are done
        return size;
    }
    
    public static void saveToFile(String file, String[] messages) throws IOException //this is the method that saves to a file and is universal to Login objects
    {
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        for(String m : messages)
        	writer.println(m);
        writer.close();
    }
    public static void saveToFile(String file, ArrayList<String> messages) throws IOException //saves to a file for arrayLists
    {
    	PrintWriter writer = new PrintWriter(file, "UTF-8");
        for(String m : messages)
        	writer.println(m);
        writer.close();
    }
    //pre: file is NOT empty, file location is valid
    //post: replaces the line in the txt file
    public static void replaceLine(String file, String word, String message) throws IOException //replaces a certain line in a file
    {
    	int index = -1;
    	ArrayList<String> fileMessages = new ArrayList<String>(); //arraylist to hold the file contents
    	readFile(fileMessages, file); //reads the file and loads into the arraylist
    	if(debug) {
    		//for(String k : fileMessages)
    			//System.out.println(k);
    	}
    	
    	for(int i = 0 ; i < fileMessages.size(); i++) //goes through the array
    	{
    		if(fileMessages.get(i).indexOf(word) > -1)
    		{
    			index = i;
    			break;
    		}
    	}
    	if(index != -1 ) {
    		if(debug)
    			System.out.println(fileMessages.get(index));
    		fileMessages.set(index, message);
    		saveToFile(file, fileMessages);
    	}
    	else {
    		if(debug)
    			System.out.println("Word not found.");
    	}
    }
    
    public static String findWord(String file, String word) throws IOException //finds the line of where the word is. else, it returns null.
    {
    	ArrayList<String> fileMessages = new ArrayList<String>();
    	readFile(fileMessages, file);
    	if(fileMessages.size() !=0) {
	    	for(String m:fileMessages)
	    	{
	    		if(m.indexOf(word) > -1)
	    		{
	    			return m;
	    		}
	    	}
    	}
    	return null;
    }
    
    public int getMapWidth()
    {
    	int returnStatement = map.length/2;
    	    	while(map[map.length/2][map[0].length/2] == 1)
    	    		returnStatement++;
    	return returnStatement;
    }
    public int getMapHeight()
    {
    	int returnStatement = map[0].length/2;
    	while(map[map.length/2][map[0].length/2] == 1)
    		returnStatement++;
    	return returnStatement;
    }
    public static void init(String user) throws IOException //if the default is not used, set the userFile thing.
    {
    	userFile = user;
    	
    	if(debug)
    		System.out.println(userFile);
		
    	posX = Integer.parseInt(findWord(userFile,"posX").substring(findWord(userFile,"posX").indexOf(":") + 1));
		posY = Integer.parseInt(findWord(userFile,"posY").substring(findWord(userFile,"posY").indexOf(":") + 1));
		
		if(debug)
			System.out.println(posX + ":" + posY);
		
		setPerimeter();
		pickTexture();
		
		main(null);
    }
    public static String returnSize()
    {
    	return map.length + " X " + map[0].length;
    }

	public static void addMessage(ArrayList<JLabel> labels, String newMessage) //takes an array list and pushes up all messages and also includes a new message at the bottom
	{
		ArrayList<String> list = new ArrayList<String>(); //this list includes string
		//stores old JLabel at the most current position
		for(JLabel label : labels) //stores the strings of the labels
			list.add(label.getText()); //adds to new string list
		
		list.remove(0); //deletes the top most message
		list.add(newMessage); //adds the message to the end
		
		int count = 0;
		for(String msg : list) //replaces the old list with new messages
		{
			labels.get(count).setText(msg);
			count++;
		}
	}
	public static void clearMessage(ArrayList<JLabel> labels)
	{
		for(JLabel m : labels)
			m.setText("");
	}
}