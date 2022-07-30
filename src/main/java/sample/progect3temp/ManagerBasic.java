package sample.progect3temp;



import java.util.ArrayList;

public class ManagerBasic {

   private static ArrayList<User> users=new ArrayList<>();

   private static ArrayList<String> groupNames=new ArrayList<>();

    private static ArrayList<String> groupIDs=new ArrayList<>();



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

}
