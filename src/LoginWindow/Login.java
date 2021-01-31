package LoginWindow;

public class Login 
{
    private String username, password;
    /**
     * constructor to setup username and password functions
     * @param username
     * @param password
     */
    public Login(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    /**
     * @return username of account
     */
    public String getUsername()
    {
        return username;
    }
    
    /**
     * @return password of account
     */
    public String getPassword()
    {
        return password;
    }
    
    @Override
    public String toString()
    {
        return "\nUsername: " + username + "\npassword: " + password + "\n";
    }
}