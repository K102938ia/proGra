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


    public boolean fileExistence(String fileName) {
        File file = new File(fileName + ".txt");
        if (file.exists() && !file.isDirectory())
            return true;


        return false;
    }

    public void chatFileMaker(String fileName) {





        File file = new File(fileName + ".txt");
        try {
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int addNewMember (String groupName, String userID)
    {
        // 1 for when member was added before

        //2 for when added successfully

        if (!fileExistence(userID+"PGNames"))
        {
            chatFileMaker(userID+"PGNames");
        }



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

    public int removeMember(String groupName, String userID)
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
        try {
            File file = new File("groupNames.txt");

            String line;


            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();



                if (line.startsWith(groupName))

                    return true;


            }
            readUsers.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return false;

    }

    public boolean checkGroupIDExistence (String groupID)
    {

        try {
            File file = new File("groupIDs.txt");

            String line;


            Scanner readUsers = new Scanner(file);

            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();

                if (line.startsWith(groupID))

                    return true;

            }
            readUsers.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return false;

    }









    public void changeGroupName(String groupNamePre, String groupNameNew) throws IOException {
//        File file = new File(groupNamePre+".txt");


//        managerBasic.getGroupNames().remove(groupNamePre);
//
//        managerBasic.setGroupNames(groupNameNew);

//        File rename = new File(groupNameNew+".txt");


//        boolean flag = file.renameTo(rename);



        for (int i = 0; i < getMembers(groupNamePre).split("\\s").length; i++) {

            File file = new File(getMembers(groupNamePre).split("\\s")[i]+"PGNames.txt");

            try {


                String line;


                Scanner readUsers = new Scanner(file);

                StringBuffer inputBuffer = new StringBuffer();




                while (readUsers.hasNextLine()) {


                    line = readUsers.nextLine();



                    if (line.startsWith(groupNamePre))
                    {
                        line=groupNameNew;
                    }




                    inputBuffer.append(line);

                    if (!line.equals(""))
                        inputBuffer.append('\n');





                }


                readUsers.close();

                String inputStr = inputBuffer.toString();


                FileOutputStream fileOut = new FileOutputStream( getMembers(groupNamePre).split("\\s")[i]+"PGNames.txt");
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








        try {
            File file = new File(groupNamePre+".txt");

            String line;


            Scanner readUsers = new Scanner(file);

            StringBuffer inputBuffer = new StringBuffer();




            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();



                if (line.startsWith(groupNamePre))
                {
                    line=groupNameNew;
                }




                inputBuffer.append(line);

                if (!line.equals(""))
                    inputBuffer.append('\n');




            }


            readUsers.close();

            String inputStr = inputBuffer.toString();


            FileOutputStream fileOut = new FileOutputStream(groupNameNew + ".txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }



        FileOutputStream fileOut = new FileOutputStream(groupNamePre + ".txt");
        String temp="";
        fileOut.write(temp.getBytes());
        fileOut.close();





        try {
            File usersFile = new File( "groupNames.txt");
            String line;


            Scanner readUsers = new Scanner(usersFile);


            StringBuffer inputBuffer = new StringBuffer();
            int check=0;


            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();





                if (line.startsWith(groupNamePre))

                {

                    line=groupNameNew;


                }



                inputBuffer.append(line);

                if (!line.equals(""))
                    inputBuffer.append('\n');




            }


            readUsers.close();

            String inputStr = inputBuffer.toString();


            FileOutputStream fileOut2 = new FileOutputStream("groupNames.txt");
            fileOut2.write(inputStr.getBytes());
            fileOut2.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }






    }



    public void changeGroupID(String groupIDPre, String groupIDNew,String groupName)
    {


        try {
            File usersFile = new File( "groupIDs.txt");
            String line;


            Scanner readUsers = new Scanner(usersFile);

            BufferedReader file = new BufferedReader(new FileReader(  "groupIDs.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            int check=0;


            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();





                if (line.startsWith(groupIDPre))

                {

                    line=groupIDNew;


                }



                inputBuffer.append(line);

                if (!line.equals(""))
                    inputBuffer.append('\n');




            }


            readUsers.close();

            String inputStr = inputBuffer.toString();


            FileOutputStream fileOut = new FileOutputStream("groupIDs.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();


        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




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


    public  String getGroupID(String fileName)
    {
        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            Scanner readUsers = new Scanner(usersFile);




            int counter=0;




            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();

                if (counter==0)
                {
                    return line.split("\\:")[1];
                }



                counter++;

            }


            readUsers.close();






        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public  String getAdmin(String fileName)
    {
        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            Scanner readUsers = new Scanner(usersFile);




            int counter=0;




            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();

                if (counter==1)
                {
                    return line.split("\\:")[1];
                }



                counter++;

            }


            readUsers.close();






        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public  String getMembers(String fileName)
    {
        try {
            File usersFile = new File(fileName + ".txt");
            String line;

            Scanner readUsers = new Scanner(usersFile);




            int counter=0;




            while (readUsers.hasNextLine()) {


                line = readUsers.nextLine();

                if (counter==2)
                {
                    return line.split("\\:")[1];
                }



                counter++;

            }


            readUsers.close();






        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }






}
