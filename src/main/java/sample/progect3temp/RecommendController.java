package sample.progect3temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecommendController {
    ManagerBasic managerBasic=new ManagerBasic();

    ChatController chatController =new ChatController();



    public String findFofUrFollowers(String mainUser)
    {
        StringBuilder candidates=new StringBuilder();

        User user=null;


        for (int i = 0; i < managerBasic.getUserSize(); i++) {


            if (managerBasic.getUsers().get(i).getUserName().equals(mainUser))
                user=managerBasic.getUsers().get(i);
        }

        //for followers





//        for (int i = 0; i < user.getFollowers().size(); i++) {
//
//           String tempUser= user.getFollowers().get(i);
//
//            for (int j = 0; j < managerBasic.getUsers().size(); j++) {
//               if (managerBasic.getUsers().get(j).getUserName().equals(tempUser))
//               {
//                   candidates.append(gatherProbableCandidate(managerBasic.getUsers().get(j), mainUser));
//
//               }
//
//            }
//
//        }






        File file = new File(user.userName+"follower.txt");
        try {


            String line;

            if (chatController.fileExistence(user.userName+"follower"))
            {


                Scanner readUsers = new Scanner(file);

                while (readUsers.hasNextLine()) {


                    line = readUsers.nextLine();

                    String tempUser = line;


                    for (int j = 0; j < managerBasic.getUsers().size(); j++) {
                        if (managerBasic.getUsers().get(j).getUserName().equals(tempUser) && !managerBasic.getUsers().get(j).equals (null)
                                &&  !managerBasic.getUsers().get(j).getUserName().equals (null)) {
                            candidates.append(gatherProbableCandidate(managerBasic.getUsers().get(j), mainUser));

                        }

                    }


                }
                readUsers.close();


            }
        }

        catch ( FileNotFoundException e) {
            e.printStackTrace();
        }




        //for followings

//        for (int i = 0; i < user.getFollowings().size(); i++) {
//
//            String tempUser= user.getFollowings().get(i);
//
//            for (int j = 0; j < managerBasic.getUsers().size(); j++) {
//                if (managerBasic.getUsers().get(j).getUserName().equals(tempUser))
//                {
//                    candidates.append(gatherProbableCandidate(managerBasic.getUsers().get(j), mainUser));
//
//                }
//
//            }
//
//        }



        File file1 = new File(user.userName+"following.txt");
        try {


            String line;

            if (chatController.fileExistence(user.userName+"following")) {


                Scanner readUsers = new Scanner(file1);

                while (readUsers.hasNextLine()) {


                    line = readUsers.nextLine();

                    String tempUser = line;


                    for (int j = 0; j < managerBasic.getUsers().size(); j++) {
                        if (managerBasic.getUsers().get(j).getUserName().equals(tempUser) && !managerBasic.getUsers().get(j).equals (null)&&
                                !managerBasic.getUsers().get(j).getUserName().equals (null)) {
                            candidates.append(gatherProbableCandidate(managerBasic.getUsers().get(j), mainUser));

                        }


                    }


                }
                readUsers.close();


            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }





        String [] candidatesF=candidates.toString().split("\\s");

        return  makeFinaleRecommendList(candidatesF,user);


    }

    // user here is the one who requested for recommendation

    public String gatherProbableCandidate(User tempUser,String user)
    {

        StringBuilder ProbableCandidate=new StringBuilder();

//followers

//
//        for (int i = 0; i < tempUser.getNumberOfFollower(); i++) {
//
//            if (!tempUser.getFollowers().get(i).equals(user))
//
//                ProbableCandidate.append(tempUser.getFollowers().get(i)+" ");
//
//        }



        File file = new File(tempUser.userName+"follower.txt");
        try {


            String line;

            if(chatController.fileExistence(tempUser.userName+"follower")) {


                Scanner readUsers = new Scanner(file);

                while (readUsers.hasNextLine()) {


                    line = readUsers.nextLine();


                    if (!line.equals(user))

                        ProbableCandidate.append(line + " ");


                }
                readUsers.close();

            }

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }






        //followings
//        for (int i = 0; i < tempUser.getNumberOfFollowing(); i++) {
//
//            if (!tempUser.getFollowings().get(i).equals(user))
//
//                ProbableCandidate.append(tempUser.getFollowings().get(i)+" ");
//
//        }



        File file1 = new File(tempUser.userName+"following.txt");
        try {


            String line;

            if(chatController.fileExistence(tempUser.userName+"following")) {


                Scanner readUsers = new Scanner(file1);

                while (readUsers.hasNextLine()) {


                    line = readUsers.nextLine();

                    if (!line.equals(user))

                        ProbableCandidate.append(line + " ");


                }
                readUsers.close();


            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }




        return  ProbableCandidate.toString();


    }




    public String makeFinaleRecommendList(String [] candidate, User mainUser)
    {
        ArrayList <String> finalList=new ArrayList<>();

        boolean followedBefore=false;
        boolean existsInList=false;

        for (int i = 0; i < candidate.length; i++) {

            followedBefore=false;
            existsInList=false;



            if (finalList.contains(candidate[i]))
                existsInList=true;


//            for (int j = 0; j < mainUser.getFollowings().size(); j++) {
//
//                if (finalList.contains(candidate[i]))
//                    existsInList=true;
//
//                    if (mainUser.getFollowings().get(j).equals(candidate[i]))
//
//                       followedBefore=true;
//
//
//
//
//            }



            File file = new File(mainUser.userName+"following.txt");
            try {


                String line;

                if (chatController.fileExistence(mainUser.userName+"following")) {


                    Scanner readUsers = new Scanner(file);

                    while (readUsers.hasNextLine()) {


                        line = readUsers.nextLine();


                        if (line.equals(candidate[i]))

                            followedBefore = true;


                    }
                    readUsers.close();


                }

            }

            catch (FileNotFoundException e) {
                e.printStackTrace();
            }




            if (!followedBefore&&!existsInList)
            {
                finalList.add(candidate[i]);

            }

        }

        StringBuilder temp=new StringBuilder();

        for (int i = 0; i < finalList.size(); i++) {

            temp.append(finalList.get(i)+"    ");

        }

        if (finalList.size()==0)
            temp.append("    ");

        return temp.toString();

    }


}
