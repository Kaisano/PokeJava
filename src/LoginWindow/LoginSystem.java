package LoginWindow;
import java.util.*;
import java.io.*;

import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;

import game.world;

import java.awt.Color;
import java.awt.Font;

public class LoginSystem implements ActionListener {
	private static JFrame loginMsg;
	private JFrame frame;
	private JTextField username, userNL;
	private JPasswordField password;
	private JPasswordField passNL, checkPassNL;
	private JPanel loginScreen, newLogin ;
	private String loginsFile;
	private ArrayList<Login> login;
	
	private static boolean debug = false; //debug display y/n

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public LoginSystem() throws IOException, InterruptedException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame and load all components
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private void initialize() throws IOException, InterruptedException {
		//https://www.java-forums.org/awt-swing/19693-how-run-joptionpane-showmessagedialog-background.html
		loginsFile = "UserAndPass.txt";
		String[] files = new String[getFileSize(loginsFile)];
        login = new ArrayList<Login>();
        readFile(files, loginsFile);
        //World = new world();
        
        for(int i = 0; i < files.length; i+=2) {
        	login.add(new Login(files[i],files[i+1]));
        }
        //System.out.println(login);
		//finished putting into array
        if(debug)
        	System.out.println(login);
        
		frame = new JFrame(); //sets the frame up
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//the 2 panels to switch
		loginScreen = new JPanel();
		loginScreen.setBounds(0, 0, 485, 260);
		frame.getContentPane().add(loginScreen);
		loginScreen.setLayout(null);
		//newLogin screen
		newLogin = new JPanel();
		newLogin.setLayout(null);
		newLogin.setBounds(0, 0, 485, 260);
		frame.getContentPane().add(newLogin);
		newLogin.setVisible(false);
		//login label
		JLabel lblLogin = new JLabel("Welcome!");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(10, 0, 465, 42);
		loginScreen.add(lblLogin);
		
		//label for username
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 56, 87, 14);
		loginScreen.add(lblUsername);
		//label for password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 85, 88, 14);
		loginScreen.add(lblPassword);
		
		
		
		//usrename field
		username = new JTextField();
		username.setBounds(108, 53, 367, 20);
		loginScreen.add(username);
		username.setColumns(10);
		username.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				String user = new String(username.getText()); //gets the username
				String passw = new String(password.getPassword()); //gets the password
				String p = passw; //converts into a regular string
				
				boolean loggedIn = false; //initially the login will be fals
				loggedIn = validLoginPass(login,user,p); //validates the login through method
				if(debug)
					System.out.println(user + " : " + p);
				if(debug)
					System.out.println(loggedIn); //debug
				
				if(password.getPassword().length == 0 && username.getText().length() == 0) //if the password and username fields are blank, return this message
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave username and password blank!");
				else if(loggedIn == true) { 												//if the login was good, then log the person in. What do you do after? 
					JOptionPane.showMessageDialog(loginMsg, "You are logged in!" ); 		//wowie you're logged in!
					
					try {
						String location = System.getProperty("user.dir");
						createUser(user);
						String userFileLocation = location + "\\Players\\" + user + ".txt";
						if(debug)
							System.out.println(userFileLocation);
						world.userFile = userFileLocation;
			
						world.init(userFileLocation);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					//creates a file for the user with an available pokemon.
					frame.dispose();
				}
				
				else if(username.getText().equals(null) || username.getText().equals("")) 			//username only blank
					JOptionPane.showMessageDialog(null, "Cannot leave username blank!");
				
				else if(password.getPassword().length == 0 || password.getPassword().equals("")) 	//password only blank
					JOptionPane.showMessageDialog(null, "Cannot leave password blank!");
				
				else
					JOptionPane.showMessageDialog(null, "Login Failed."); 							//both are filled, but the combination is wrong
			}
		});
		
		//password field to type in
		password = new JPasswordField();
		password.setBounds(108, 82, 367, 20);
		loginScreen.add(password);
		password.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = new String(username.getText()); //gets the username
				String passw = new String(password.getPassword()); //gets the password
				String p = passw; //converts into a regular string
				
				boolean loggedIn = false; //initially the login will be fals
				loggedIn = validLoginPass(login,user,p); //validates the login through method
				if(debug)
					System.out.println(user + " : " + p);
				if(debug)
					System.out.println(loggedIn); //debug
				
				if(password.getPassword().length == 0 && username.getText().length() == 0) //if the password and username fields are blank, return this message
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave username and password blank!");
				 
				else if(loggedIn == true) { //if the login was good, then log the person in. What do you do after? 
					JOptionPane.showMessageDialog(loginMsg, "You are logged in!" ); //wowie you're logged in!
					
					try {
						String location = System.getProperty("user.dir");
						createUser(user);
						String userFileLocation = location + "\\Players\\" + user + ".txt";
						if(debug)
							System.out.println(userFileLocation);
						world.userFile = userFileLocation;
			
						world.init(userFileLocation);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					//creates a file for the user with an available pokemon.
					frame.dispose();
				}
				
				else if(username.getText().equals(null) || username.getText().equals("")) //username only blank
					JOptionPane.showMessageDialog(null, "Cannot leave username blank!");
				
				else if(password.getPassword().length == 0 || password.getPassword().equals("")) //password only blank
					JOptionPane.showMessageDialog(null, "Cannot leave password blank!");
				
				else
					JOptionPane.showMessageDialog(null, "Login Failed."); //both are filled, but the combination is wrong
			}
		});
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(10, 117, 465, 30);
		loginScreen.add(btnLogin);
		//btnLogin.addActionListener(new loginListener());
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String user = new String(username.getText()); //gets the username
				String passw = new String(password.getPassword()); //gets the password
				String p = passw; //converts into a regular string
				
				boolean loggedIn = false; //initially the login will be fals
				loggedIn = validLoginPass(login,user,p); //validates the login through method
				
				if(debug)
					System.out.println(user + " : " + p);
				
				if(debug)
					System.out.println(loggedIn); //debug
				
				if(password.getPassword().length == 0 && username.getText().length() == 0) //if the password and username fields are blank, return this message
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave username and password blank!");
				 
				else if(loggedIn == true) { //if the login was good, then log the person in. What do you do after? 
					JOptionPane.showMessageDialog(loginMsg, "You are logged in!" ); //wowie you're logged in!
					
					try {
						String location = System.getProperty("user.dir");
						createUser(user);
						String userFileLocation = location + "\\Players\\" + user + ".txt";
						if(debug)
							System.out.println(userFileLocation);
						world.userFile = userFileLocation;
			
						world.init(userFileLocation);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					//creates a file for the user with an available pokemon.
					frame.dispose();
				}
				
				else if(username.getText().equals(null) || username.getText().equals("")) //username only blank
					JOptionPane.showMessageDialog(null, "Cannot leave username blank!");
				
				else if(password.getPassword().length == 0 || password.getPassword().equals("")) //password only blank
					JOptionPane.showMessageDialog(null, "Cannot leave password blank!");
				
				else
					JOptionPane.showMessageDialog(null, "Login Failed."); //both are filled, but the combination is wrong
			}
		});
		
		//closes the panel 
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setBounds(10, 219, 465, 30);
		loginScreen.add(btnCancel);		
		
		
		
		
		
		
		
//----------------------------------------------------------------------------------------------
		//creation of the second jpanel for creating new logins
		
		JButton btnNewLogin = new JButton("New Login");
		btnNewLogin.setBounds(10, 151, 465, 30);
		loginScreen.add(btnNewLogin);
		//btnCancel.addActionListener(new exitButtonListener());
		btnNewLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginScreen.setVisible(false);
				newLogin.setVisible(true);
				username.setText(null);
				password.setText(null);
			}
		});
		//resets the fields
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(10, 185, 465, 30);
		loginScreen.add(btnReset);
		btnReset.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		        username.setText(null);
		        password.setText(null);
		    }
		});
		
		//label texts for the 5 text inputs
		JLabel lblCreateNewAccount = new JLabel("Create New Account");
		lblCreateNewAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateNewAccount.setForeground(Color.BLACK);
		lblCreateNewAccount.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblCreateNewAccount.setBounds(10, 0, 465, 42);
		newLogin.add(lblCreateNewAccount);
		
		//label for new password field
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(10, 85, 88, 14);
		newLogin.add(lblNewPassword);
		
		//label for new username field
		JLabel lblNewUsername = new JLabel("New Username");
		lblNewUsername.setBounds(10, 56, 87, 14);
		newLogin.add(lblNewUsername);
		//make sure that the password they have is what they want
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(10, 113, 88, 14);
		newLogin.add(lblConfirmPassword);
		
		userNL = new JTextField(); //text field for the first login page
		userNL.setColumns(10);
		userNL.setBounds(108, 53, 367, 20);
		newLogin.add(userNL);
		userNL.addActionListener(new ActionListener() { //checks everything for the word fields
			public void actionPerformed(ActionEvent e) {
				int found = binSearch(login,userNL.getText());
				String passwrd = new String(passNL.getPassword());
				String chkPasswrd = new String(checkPassNL.getPassword());
				if(username.getText().equals("") && passNL.getPassword().length == 0 && checkPassNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Enter in your new login account!"); //if nothing is there at all
				else if(username.getText().equals(null))
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave username blank!"); //if the username is just blank
				else if(passNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave password blank!"); //if the password is just blank
				else if(checkPassNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Enter in your new password!"); //if the new password is blank
				else if(found == -1) {
					if(passwrd.equals(chkPasswrd)) { //if the two passwords = each other, then allow the storing to happen
						//gets username and password --> String
						String user = userNL.getText();
						String password = new String(passNL.getPassword());
						//password = passNL.getPassword();
						String pass = new String(password);
						//adds to Arralist
						login.add(new Login(user, pass));
						
						try {
							saveToFile(login,loginsFile); //finally saves it to the file for future references
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						username.setText(user);
						JOptionPane.showMessageDialog(loginMsg, "Sucessfully made new login");
						newLogin.setVisible(false);
						loginScreen.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(loginMsg, "Passwords do not match try again");
				}
				else
					JOptionPane.showMessageDialog(loginMsg, "Username already used");
				//add the contents to the arraylist & write to the file. after, clear the text
			}
		});
		
		passNL = new JPasswordField(); //the password field on the first page
		passNL.setBounds(108, 82, 367, 20);
		newLogin.add(passNL);
		passNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int found = binSearch(login,userNL.getText());
				String passwrd = new String(passNL.getPassword());
				String chkPasswrd = new String(checkPassNL.getPassword());
				if(username.getText().equals("") && passNL.getPassword().length == 0 && checkPassNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Enter in your new login account!");
				else if(username.getText().equals(null))
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave username blank!");
				else if(passNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave password blank!");
				else if(checkPassNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Enter in your new password!");
				else if(found == -1) {
					if(passwrd.equals(chkPasswrd)) { //if the two passwords = each other, then allow the storing to happen
						//gets username and password --> String
						String user = userNL.getText();
						String password = new String(passNL.getPassword());
						//password = passNL.getPassword();
						String pass = new String(password);
						//adds to Arralist
						login.add(new Login(user, pass));
						
						try {
							saveToFile(login,loginsFile); //finally saves it to the file for future references
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						username.setText(user);
						JOptionPane.showMessageDialog(loginMsg, "Sucessfully made new login");
						newLogin.setVisible(false);
						loginScreen.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(loginMsg, "Passwords do not match try again");
				}
				else
					JOptionPane.showMessageDialog(loginMsg, "Username already used");
				//add the contents to the arraylist & write to the file. after, clear the text
			}
		});
		
		checkPassNL = new JPasswordField();
		checkPassNL.setBounds(108, 110, 367, 20);
		newLogin.add(checkPassNL);
		checkPassNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int found = binSearch(login,userNL.getText());
				String passwrd = new String(passNL.getPassword());
				String chkPasswrd = new String(checkPassNL.getPassword());
				if(username.getText().equals("") && passNL.getPassword().length == 0 && checkPassNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Enter in your new login account!");
				else if(username.getText().equals(null))
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave username blank!");
				else if(passNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave password blank!");
				else if(checkPassNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Enter in your new password!");
				else if(found == -1) {
					if(passwrd.equals(chkPasswrd)) { //if the two passwords = each other, then allow the storing to happen
						//gets username and password --> String
						String user = userNL.getText();
						String password = new String(passNL.getPassword());
						//password = passNL.getPassword();
						String pass = new String(password);
						//adds to Arralist
						login.add(new Login(user, pass));
						
						try {
							saveToFile(login,loginsFile); //finally saves it to the file for future references
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						username.setText(user);
						JOptionPane.showMessageDialog(loginMsg, "Sucessfully made new login");
						newLogin.setVisible(false);
						loginScreen.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(loginMsg, "Passwords do not match try again");
				}
				else
					JOptionPane.showMessageDialog(loginMsg, "Username already used");
				//add the contents to the arraylist & write to the file. after, clear the text
			}
		});
		
		JButton btnOkNL = new JButton("OK"); //confirm button for the new login page
		btnOkNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int found = binSearch(login,userNL.getText());
				String passwrd = new String(passNL.getPassword());
				String chkPasswrd = new String(checkPassNL.getPassword());
				if(username.getText().equals("") && passNL.getPassword().length == 0 && checkPassNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Enter in your new login account!");
				else if(username.getText().equals(null))
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave username blank!");
				else if(passNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Cannot leave password blank!");
				else if(checkPassNL.getPassword().length == 0)
					JOptionPane.showMessageDialog(loginMsg, "Enter in your new password!");
				else if(found == -1) {
					if(passwrd.equals(chkPasswrd)) { //if the two passwords = each other, then allow the storing to happen
						//gets username and password --> String
						String user = userNL.getText();
						String password = new String(passNL.getPassword());
						//password = passNL.getPassword();
						String pass = new String(password);
						//adds to Arraylist
						login.add(new Login(user, pass));
						
						try {
							saveToFile(login,loginsFile); //finally saves it to the file for future references
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						username.setText(user);
						JOptionPane.showMessageDialog(loginMsg, "Sucessfully made new login");
						newLogin.setVisible(false);
						loginScreen.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(loginMsg, "Passwords do not match try again");
				}
				else
					JOptionPane.showMessageDialog(loginMsg, "Username already used");
				//add the contents to the arraylist & write to the file. after, clear the text
			}
		});
		btnOkNL.setBounds(10, 137, 465, 30);
		newLogin.add(btnOkNL);
		
		JButton btnCancelNL = new JButton("Cancel"); //cancels the new login page
		btnCancelNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userNL.setText(null);
				passNL.setText(null);
				checkPassNL.setText(null);
				newLogin.setVisible(false);
				loginScreen.setVisible(true);
			}
		});
		
		JButton btnResetNL = new JButton("Reset");
		btnResetNL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userNL.setText(null);
				passNL.setText(null);
				checkPassNL.setText(null);
			}
		});
		btnResetNL.setBounds(10, 178, 465, 30);
		newLogin.add(btnResetNL);
		btnCancelNL.setBounds(10, 219, 465, 30);
		newLogin.add(btnCancelNL);
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * implemented function from ActionEvent
	 * Depending on the action pressed, this will handle the events happening on screen
	 * @param e the event handler
	 */
	public void actionPerformed(ActionEvent e) //when the user on the username/password field presses enter or user clicks the "Login" button 
	{
		String user = new String(username.getText()); //gets the username
		String passw = new String(password.getPassword()); //gets the password
		String p = passw; //converts into a regular string
		
		boolean loggedIn = false; //initially the login will be fals
		loggedIn = validLoginPass(login,user,p); //validates the login through method
		if(debug)
			System.out.println(loggedIn); //debug
		
		if(password.getPassword().length == 0 && username.getText().length() == 0) //if the password and username fields are blank, return this message
			JOptionPane.showMessageDialog(loginMsg, "Cannot leave username and password blank!");
		 
		else if(loggedIn == true) { //if the login was good, then log the person in. What do you do after? 
			JOptionPane.showMessageDialog(loginMsg, "You are logged in!" ); //wowie you're logged in!
			
			try {
				String location = System.getProperty("user.dir");
				createUser(user);
				String userFileLocation = location + "\\Players\\" + user + ".txt";
				if(debug)
					System.out.println(userFileLocation);
				world.userFile = userFileLocation;
	
				world.init(userFileLocation);
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			//creates a file for the user with an available pokemon.
			frame.dispose();
		}
		
		else if(username.getText().equals(null) || username.getText().equals("")) //username only blank
			JOptionPane.showMessageDialog(null, "Cannot leave username blank!");
		
		else if(password.getPassword().length == 0 || password.getPassword().equals("")) //password only blank
			JOptionPane.showMessageDialog(null, "Cannot leave password blank!");
		
		else
			JOptionPane.showMessageDialog(null, "Login Failed."); //both are filled, but the combination is wrong
		}

	/**
	 * uses binary search to find the login
	 * @param arr array of logins
	 * @param username what is the username to check
	 * @param password the password associated with the username
	 * @return if a username is found, and the password associated is correct, return true. Otherwise, if nothing is found or the password is wrong, return false
	 */
	public static boolean login(ArrayList<Login> arr, String username, String password) //the login command to see if the 
	{
		int account = binSearch(arr, username);
		if(account != -1)
		{
			if(validLoginPass(arr,username,password) == true) 
				return true;
		}
		return false;
	}
	/**
	 * @param fileName what is the file to get
	 * @return how many lines are in the file
	 * @throws IOException
	 */
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
    
	/**
	 * @param words what array should the program put words into
	 * @param fName what is the file name to read from
	 * @throws IOException
	 */
    public static void readFile(String[] words, String fName) throws IOException //reads file and puts it into an array
    {
        Scanner input = new Scanner(new FileReader(fName));
        int i = 0;                         	//index for placement in the array
        String line;   
        
        while (input.hasNextLine())	//while there is another line in the file
        {
            line=input.nextLine();        	//read in the next line and store it
            words[i]= line;               	//add the line into the array
            i++;                          		//advance the index of the array         
        }
        input.close();                
    }
    /**
     * @param AL arraylist to be put into a file
     * @param file what file is used to store the arraylist data
     * @throws IOException
     */
    public static void saveToFile(ArrayList<Login> AL, String file) throws IOException //this is the method that saves to a file and is universal to Login objects
    {
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        if(AL.size() > 0) //after arraylist is edited, add each to the text file to edit it
        {
            for(int i = 0 ; i < AL.size() ; i++)
            {
                writer.println(AL.get(i).getUsername());
                writer.println(AL.get(i).getPassword());
            }
        }
        writer.close();
    }
    /**
     * @param file what file location should the user save to
     * @param messages what are the messages located in this file
     * @throws IOException
     */
    public static void saveToFile(String file, String[] messages) throws IOException //this is the method that saves to a file and only to save an array of messages
    {
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        for(String m : messages)
        	writer.println(m);
        writer.close();
    }
    
    //pre: username is correct
    //post: if the username and password are equal to the username's true password then return true
    /**
     * uses binary search to look for player file
     * @param list what is the list of users
     * @param username what is the username given
     * @param password what is the password given
     * @return
     */
    public static boolean validLoginPass(ArrayList<Login> list, String username, String password)
    {
        int accountPos = binSearch(list, username); //find the username for the password
        String  pass = password;
        if(accountPos != -1) {
        	if(pass.equals(list.get(accountPos).getPassword()))
        		return true;
        }
        
        return false;
    }
    public static int binSearch(ArrayList<Login> array, String key)
    {
        int left = 0;			//left index of subarray to search in
        int right = array.size()-1;	//right index of subarray to search in
         
        
        while(left <= right)
        {
            int mid = (left+right)/2;	//find index in the middle of subarray
            
            if(array.get(mid).getUsername().equals(key)) {
            	if(debug)
            		System.out.println("inhere");
            	return mid;		//we found it â return its index 
            }
            else if(array.get(left).getUsername().equals(key))
                return left;
             else if(array.get(right).getUsername().equals(key))
                return right;
            if(key.compareTo(array.get(mid).getUsername()) > 0) {
                right = mid - 1;		//search in left side
                if(debug) {
                	System.out.println("Key string is more than username");
                }
            }
            else if(key.compareTo(array.get(mid).getUsername()) == 1) {
            	right = mid - 1;
            	if(debug)
            		System.out.println("Same string");
            }
            else
                left = mid + 1;		//seach in right side
        }
        return -1;			//key not found in array
    }
    
    
    /**
     * to make a new file for the users & stores data there.
     * @param user the name of the user
     * @throws IOException
     * @throws InterruptedException
     */
    public static void createUser(String user) throws IOException, InterruptedException
    {
    	world World = new world();
    	String fileLocation = System.getProperty("user.dir");
    	if(debug)
    		System.out.println(fileLocation + "\\Players\\" + user + ".txt");
    	File file = new File(fileLocation + "\\Players\\" + user + ".txt");
    	String[] fileInfo = {"Username:" + user,"posX:" + World.getMapWidth(), "posY:" + World.getMapHeight(),"P1:Pikachu", "1HP:400","1ATK:10","1DEF:10", "P2:", "2HP:","2ATK:","2DEF:", "P3:","3HP:","3ATK:","3DEF:","P4","4HP:","4ATK:","4DEF:"};
    	if (file.createNewFile()) {
    		if(debug) {
    			System.out.println("File created");
    			System.out.println(fileLocation + "\\Players\\" + user + ".txt");
    		}
    		saveToFile(fileLocation + "\\Players\\" + user + ".txt",fileInfo);
    	}
    	else {
    		if(debug)
    			System.out.println("File already exists.");
    	}
    }
    
    /**
     * reads file and puts it into an arrayList
     * @param words arraylist to store words from the file
     * @param fName file location
     * @throws IOException
     */
    public static void readFile(ArrayList<String> words, String fName) throws IOException
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
    public static void saveToFile(String file, ArrayList<String> messages) throws IOException
    {
    	PrintWriter writer = new PrintWriter(file, "UTF-8");
        for(String m : messages)
        	writer.println(m);
        writer.close();
    }
    
    public static void replaceLine(String file, String word, String message) throws IOException
    {
    	int index = 0;
    	ArrayList<String> fileMessages = new ArrayList<String>();
    	readFile(fileMessages, file);
    	for(int i = 0 ; i < fileMessages.size(); i++)
    	{
    		if(fileMessages.get(i).indexOf(word) > -1)
    		{
    			index = i;
    			break;
    		}
    	}
    	if(index > -1 ) {
    		fileMessages.set(index, message);
    		saveToFile(file, fileMessages);
    	}
    	else {
    		if(debug);
    			System.out.println("Word not Found");
    	}
    }
    
    public static String findWord(String file, String word) throws IOException
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
    
    /**
     * @return main frame of the window
     */
    public JFrame getFrame() {
    	return frame;
    }
}
