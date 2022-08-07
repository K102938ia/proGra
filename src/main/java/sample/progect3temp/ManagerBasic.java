package sample.progect3temp;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ManagerBasic {

    public static ArrayList<User> users=new ArrayList<>();

    private static ArrayList<String> groupNames=new ArrayList<>();

    private static ArrayList<String> groupIDs=new ArrayList<>();

    public static ArrayList < User > businessUsers = new ArrayList <> ();
    public static ArrayList < User > personalUsers = new ArrayList <> ();
    static ArrayList < Post > allPosts = new ArrayList <> ();

    static String currentUser;
    public void setCurrentUser(String userName)
    {
        currentUser=userName;

    }

    public  String getCurrentUser()
    {
        return currentUser;
    }


    public void setGroupIDs(String groupID) {
        groupIDs.add(groupID);
    }

    public ArrayList<String> getGroupIDs() {
        return groupIDs;
    }

    public  void setGroupNames(String groupName) {
        groupNames.add(groupName);
    }

    public  ArrayList<String> getGroupNames() {
        return groupNames;
    }

    public void setUsers(User user) {
        users .add(user);

    }
    public  int getUserSize()
    {
        return users.size();
    }

     public ArrayList<User> getUsers() {
        return users;
    }

    public static void addUsersObjSignIn ( String userName, String password, String securityQuestion, String uType ) {
        User user = new User ();
        user.setUserName (userName);
        user.setPassWord (password);
        user.setSecurityAnswer (securityQuestion);
        user.setType (uType);

        //users.add (user);
        if ( uType.equals ("Personal") ) {
            personalUsers.add (user);
        } else if ( uType.equals ("Business") ) {
            businessUsers.add (user);
        }
    }

    public void addUsersObjLoggedIn () {
        HashMap< String, String > usersAndPasswords = new HashMap < String, String > ();

        try {
            File usersFile = new File ("usersAndPasswords.txt");
            Scanner readUsers = new Scanner (usersFile);
            String nameAndPass;
            String[] splitNameAndAnswer;
            while (readUsers.hasNextLine ()) {
                nameAndPass = readUsers.nextLine ();
                splitNameAndAnswer = nameAndPass.split (":");
                usersAndPasswords.put (splitNameAndAnswer[0], splitNameAndAnswer[2]);
                User user = new User ();
                user.setUserName (splitNameAndAnswer[0]);
                user.setPassWord (splitNameAndAnswer[1]);
                user.setSecurityAnswer (splitNameAndAnswer[2]);
                user.setType (splitNameAndAnswer[3]);
                if(user.getType ().equals ("Personal")){
                    personalUsers.add (user);
                }
                else if(user.getType ().equals ("Business")){
                    businessUsers.add(user);
                }
                users.add (user);

            }

        } catch ( FileNotFoundException e ) {
            e.printStackTrace ();
        }
    }


}
