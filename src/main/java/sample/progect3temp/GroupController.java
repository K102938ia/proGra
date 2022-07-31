package sample.progect3temp;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GroupController {

    ManagerBasic managerBasic=new ManagerBasic();

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


    public boolean checkGroupIDExistence(String groupID, String groupName) throws FileNotFoundException {


        // we can not make two groups with same names:

        for (int i = 0; i < managerBasic.getGroupNames().size(); i++) {


            if (managerBasic.getGroupNames().get(i).equals(groupName))
               return true;
        }

        for (int i = 0; i < managerBasic.getGroupIDs().size(); i++) {


            if (managerBasic.getGroupIDs().get(i).equals(groupID))
                return true;
        }

        return  false;

    }


    public void setGroupName(String groupName)
    {
        managerBasic.setGroupNames(groupName);
    }

    public void setGroupID(String groupID)
    {
        managerBasic.setGroupIDs(groupID);
    }




    public void makeGroupFile(String fileName, String adminUserName,String groupID)
    {
        //file name is group name

        File file = new File(fileName + ".txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.write("GroupID: "+groupID+ " \n");
            fileWriter.write("Admin: " + adminUserName+ " \n");
            fileWriter.write("Members: " + adminUserName +" \n");

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean checkGroupExistence (String groupName,String groupID) throws FileNotFoundException {
        boolean groupNameExistence=false;
        for (int i = 0; i < managerBasic.getGroupNames().size(); i++) {

            if (managerBasic.getGroupNames().get(i).equals(groupName))
                groupNameExistence=true;


        }

        if (groupNameExistence)
        {
            File file = new File(groupName+ ".txt");
            String line=null;

            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();
                break;
            }

            if (line!=null)
                if (line.split("\\s")[1].equals(groupID))
                    return true;

        }





        return false;
    }


    public boolean checkGroupMember (String groupName, String memberID)
    {
        File file = new File(groupName + ".txt");
        try {
            String line=null;

            Scanner readUsers = new Scanner(file);
            int counter=0;

            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();
                counter++;
                if (counter==3)
                break;
            }
            readUsers.close();

            for (int i = 1; i < line.split("\\s").length; i++) {
                if (line.split("\\s")[i].equals(memberID))
                    return true;
            }



        }

        catch (IOException e) {
            e.printStackTrace();
        }
return false;
    }



    public boolean checkAdmin (String groupName, String userID)
    {
        File file = new File(groupName + ".txt");
        try {
            String line=null;

            Scanner readUsers = new Scanner(file);
            int counter=0;

            while (readUsers.hasNextLine()) {

                line = readUsers.nextLine();
                counter++;
                if (counter==2)
                    break;
            }
            readUsers.close();


                if (line.split("\\s")[1].equals(userID))
                    return true;




        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }


    public int addNewMember (String groupName, String userID)
    {
        // 1 for when member was added before

        //2 for when added successfully



        try {
            File usersFile = new File(groupName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            BufferedReader file = new BufferedReader(new FileReader(groupName + ".txt"));
            StringBuffer inputBuffer = new StringBuffer();

            int check=0;



//            boolean checkIfWasBlocked=checkBlock(fileName);
//
//            if (checkIfWasBlocked)
//
//                return false;


            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();



                ID = line.split("\\#");


                String tempText = ID[0].split("\\:")[1];

                if (line.startsWith("Members"))
                    {
                        for (int i = 0; i < line.split("\\s").length; i++) {

                            if (line.split("\\s")[i].equals(userID))
                                return 1;

                        }


                        line+=userID+" ";







                    }



                inputBuffer.append(line);
                inputBuffer.append('\n');


                check=1;

            }


            readUsers.close();

            String inputStr = inputBuffer.toString();


            FileOutputStream fileOut = new FileOutputStream(groupName + ".txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            if (check==1)
                return 2;

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return 1;

    }

    public int removeNewMember (String groupName, String userID)
    {
        // 1 for when user is not a member of group

        //2 for when removed successfully



        try {
            File usersFile = new File(groupName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            BufferedReader file = new BufferedReader(new FileReader(groupName + ".txt"));
            StringBuffer inputBuffer = new StringBuffer();

            int check=0;

            StringBuilder tempLine=new StringBuilder();



//            boolean checkIfWasBlocked=checkBlock(fileName);
//
//            if (checkIfWasBlocked)
//
//                return false;


            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();



                ID = line.split("\\#");


                String tempText = ID[0].split("\\:")[1];

                if (line.startsWith("Members"))
                {
                    boolean exist=false;

                    for (int i = 0; i < line.split("\\s").length; i++) {

                        if (line.split("\\s")[i].equals(userID))
                        {

                            exist=true;


                        }


                    }

                    if (!exist)
                        return 1;

                    tempLine.append("Members: ");
                    for (int i = 1; i < line.split("\\s").length; i++) {

                        if (!line.split("\\s")[i].equals(userID))
                            tempLine.append(line.split("\\s")[i]+" ");

                    }
                    line=tempLine.toString();




                }



                inputBuffer.append(line);
                inputBuffer.append('\n');


                check=1;

            }


            readUsers.close();

            String inputStr = inputBuffer.toString();


            FileOutputStream fileOut = new FileOutputStream(groupName + ".txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            if (check==1)
                return 2;

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return 1;

    }

    // these two method are used for changing name and ID of group

    public boolean checkGroupNameExistence (String groupName)
    {
        for (int i = 0; i < managerBasic.getGroupNames().size(); i++) {


            if (managerBasic.getGroupNames().get(i).equals(groupName))
                return true;
        }
        return false;
    }

    public boolean checkGroupIDExistence (String groupID)
    {

        for (int i = 0; i < managerBasic.getGroupIDs().size(); i++) {


            if (managerBasic.getGroupIDs().get(i).equals(groupID))
                return true;
        }

        return  false;

    }


    public void changeGroupName(String groupNamePre, String groupNameNew)
    {
        managerBasic.getGroupNames().remove(groupNamePre);

        managerBasic.setGroupNames(groupNameNew);

        File file = new File(groupNamePre+".txt");


        File rename = new File(groupNameNew+".txt");

        boolean flag = file.renameTo(rename);





    }

    public void changeGroupID(String groupIDPre, String groupIDNew,String groupName)
    {
        managerBasic.getGroupIDs().remove(groupIDPre);

        managerBasic.setGroupIDs(groupIDNew);


        try {
            File usersFile = new File(groupName + ".txt");
            String line;

            String[] ID = new String[2];
            Scanner readUsers = new Scanner(usersFile);

            BufferedReader file = new BufferedReader(new FileReader(groupName + ".txt"));
            StringBuffer inputBuffer = new StringBuffer();

            int counter=0;




            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();

                if (counter==0)
                {
                    line="GroupID: "+groupIDNew;
                }



                inputBuffer.append(line);
                inputBuffer.append('\n');


              counter++;

            }


            readUsers.close();

            String inputStr = inputBuffer.toString();


            FileOutputStream fileOut = new FileOutputStream(groupName + ".txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }






}
