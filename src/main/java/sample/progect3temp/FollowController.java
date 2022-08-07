package sample.progect3temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FollowController {
    ManagerBasic managerBasic=new ManagerBasic();


    public void followProcess(String followerPerson ,String followedPerson) throws IOException {

        File following = new File(followerPerson+"following.txt");
        File follower = new File (followedPerson+"follower.txt");


        User user1=null;

        for (int i = 0; i < managerBasic.getUserSize(); i++) {
            if (managerBasic.getUsers().get(i).getUserName().equals(followerPerson)) {
                user1 = managerBasic.getUsers().get(i);
                break;
            }
        }
        if (user1!=null) {

            user1.setNumberOfFollowing(user1.getNumberOfFollowing() + 1);
            user1.setFollowings(followedPerson);

        }
        //FileWriter fileWriter = new FileWriter(following, true);
        //fileWriter.write( followedPerson+"\n");
        //fileWriter.close();



        try {
            User user=null;

            for (int i = 0; i < managerBasic.getUserSize(); i++) {
                if (managerBasic.getUsers().get(i).getUserName().equals(followerPerson)) {
                    user = managerBasic.getUsers().get(i);
                    break;
                }
            }
            if (user!=null) {

                user.setNumberOfFollowing(user.getNumberOfFollowing() + 1);
                user.setFollowings(followedPerson);

            }
            FileWriter fileWriter1 = new FileWriter (following, true);
            fileWriter1.write( followedPerson+"\n");
            fileWriter1.close();

        }

        catch ( IOException e) {
            e.printStackTrace();
        }



        User user2 = null;

        for (int i = 0; i < managerBasic.getUserSize(); i++) {
            if (managerBasic.getUsers().get(i).getUserName().equals(followedPerson)) {
                user2 = managerBasic.getUsers().get(i);
                break;
            }
        }
        if (user2 !=null)
        {
            user2.setNumberOfFollower(user2.getNumberOfFollower() + 1);
            user2.setFollowers(followerPerson);
        }




        try {

            User user3 = null;

            for (int i = 0; i < managerBasic.getUserSize(); i++) {
                if (managerBasic.getUsers().get(i).getUserName().equals(followedPerson)) {
                    user3 = managerBasic.getUsers().get(i);
                    break;
                }
            }
            if (user3 !=null)
            {
                user2.setNumberOfFollower(user3.getNumberOfFollower() + 1);
                user2.setFollowers(followerPerson);
            }



            FileWriter fileWriter1 = new FileWriter(follower, true);
            fileWriter1.write(followerPerson + "\n");
            fileWriter1.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }

    public boolean checkUser(String userName)
    {
        ArrayList<String> tempNameSaving=new ArrayList<>();
        try {
            File usersFile = new File("usersAndPasswords.txt");
            Scanner readUsers = new Scanner(usersFile);
            String name;

            while (readUsers.hasNextLine()) {
                name = readUsers.nextLine();
                tempNameSaving.add(name.split("\\:")[0]);
            }
            readUsers.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }


        if (tempNameSaving.contains(userName))
            return true;

        return false;
    }


    public boolean followedBefore(String followerPerson ,String followedPerson)
    {
        User user=null;

        for (int i = 0; i < managerBasic.getUserSize(); i++) {
            if (managerBasic.getUsers().get(i).getUserName().equals(followerPerson))
            {
                user=managerBasic.getUsers().get(i);
                break;
            }

        }

        if (user!=null)
            if (user.getNumberOfFollowing()>0)
            {
                ArrayList<String> tempNameSaving=new ArrayList<>();
                try {
                    File following = new File(followerPerson+"following.txt");
                    Scanner readUsers = new Scanner(following);
                    String name;
                    while (readUsers.hasNextLine()) {
                        name = readUsers.nextLine();
                        tempNameSaving.add(name);
                    }
                    readUsers.close();
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                if (tempNameSaving.contains(followedPerson))
                    return true;



            }

        for (int i = 0; i < user.getFollowings().size(); i++) {

            if (user.getFollowings().get(i).equals(followedPerson))
                return true;

        }

        if (user.getFollowings().contains(followedPerson))

            return true;



        return false;

    }




    public void unfollowProcess(String unFollowerPerson ,String unfollowedPerson)
    {



        User user1=null;

        for (int i = 0; i < managerBasic.getUserSize(); i++) {
            if (managerBasic.getUsers().get(i).getUserName().equals(unFollowerPerson)) {
                user1 = managerBasic.getUsers().get(i);
                break;
            }
        }

        if (user1!=null) {

            user1.setNumberOfFollowing(user1.getNumberOfFollowing() - 1);

            user1.getFollowings().remove(unfollowedPerson);

        }




        User user2 = null;

        for (int i = 0; i < managerBasic.getUserSize(); i++) {
            if (managerBasic.getUsers().get(i).getUserName().equals(unfollowedPerson)) {
                user2 = managerBasic.getUsers().get(i);
                break;
            }
        }
        if (user2 !=null)
        {
            user2.setNumberOfFollower(user2.getNumberOfFollower() - 1);
            user2.getFollowers().remove(unFollowerPerson);
        }




    }
}
