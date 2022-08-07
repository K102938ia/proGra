package sample.progect3temp;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PostController {
    public boolean addedPost = false;

    public void addPost(String userName,String post, boolean commentable) throws FileNotFoundException {
        addedPost = false;
        boolean found = false;
        for ( User businessUser : ManagerBasic.businessUsers ) {
            if ( businessUser.getUserName ().equals (userName)  && !businessUser.getType ().equals (null)) {
                found = true;
                String confPost = "$(Ad) " + post;
                BusinessPost businessPost = new BusinessPost (confPost);
                businessUser.posts.add (businessPost);
                Post post1 = new Post (confPost);
                ManagerBasic.allPosts.add (post1);
                File file = new File (userName + "Posts.txt");
                Scanner readID = new Scanner (file);
                int postsNum = 0;
                while (readID.hasNextLine ()){
                    String s = readID.nextLine ();
                    postsNum++;
                }
                FileWriter fileWriter = null;
                String ID = userName + postsNum;
               // System.out.println (ID);
                try {
                    LocalDateTime myObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = myObj.format(myFormatObj);
                    //System.out.println(myObj);
                    fileWriter = new FileWriter (file, true);
                    fileWriter.write (confPost + ":" + commentable + ":@" + formattedDate + "*" + ID + "\n");
                    fileWriter.close ();
                } catch ( IOException e ) {
                    e.printStackTrace ();
                }

                File file1 = new File (ID + "PostLikers.txt");
                FileWriter fileWriter1 = null;
                try {
                    fileWriter1 = new FileWriter (file1, true);
                    //fileWriter1.write(confPost + "\n");
                    fileWriter1.close ();
                } catch ( IOException e ) {
                    e.printStackTrace ();
                }

                File file2 = new File (ID + "PostComments.txt");
                FileWriter fileWriter2 = null;
                try {
                    fileWriter2 = new FileWriter (file2, true);
                    //fileWriter2.write(confPost + "\n");
                    fileWriter2.close ();
                } catch ( IOException e ) {
                    e.printStackTrace ();
                }

                File file3 = new File (ID + "PostSeen.txt");
                FileWriter fileWriter3 = null;
                try {
                    fileWriter3 = new FileWriter (file3, true);
                    //fileWriter2.write("0");
                    fileWriter3.close ();
                } catch ( IOException e ) {
                    e.printStackTrace ();
                }

                addedPost = true;
                break;
            }
        }
        if ( ! found ) {
            for ( User user : ManagerBasic.users ) {
                if ( user.getUserName ().equals (user.getUserName ()) ) {
                    if ( user.getType ().equals ("Business") ) {
                        String confPost = "$(Ad) " + post;
                        for ( User businessUser : ManagerBasic.businessUsers ) {
                            if ( businessUser.getUserName ().equals (userName) ) {
                                BusinessPost businessPost = new BusinessPost (confPost);
                                user.posts.add (businessPost);
                                Post post1 = new Post (confPost);
                                ManagerBasic.allPosts.add (post1);
                                File file = new File (userName + "Posts.txt");
                                Scanner readID = new Scanner (file);
                                int postsNum = 0;
                                while (readID.hasNextLine ()){
                                    String s = readID.nextLine ();
                                    postsNum++;
                                }
                                FileWriter fileWriter = null;
                                String ID = userName + postsNum;
                                try {
                                    LocalDateTime myObj = LocalDateTime.now();
                                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                                    String formattedDate = myObj.format(myFormatObj);
                                    fileWriter = new FileWriter (file, true);
                                    fileWriter.write (confPost + ":" + commentable + ":@" + formattedDate + "*" + ID + "\n");
                                    fileWriter.close ();
                                } catch ( IOException e ) {
                                    e.printStackTrace ();
                                }

                                File file1 = new File (ID + "PostLikers.txt");
                                FileWriter fileWriter1 = null;
                                try {
                                    fileWriter1 = new FileWriter (file1, true);
                                    //fileWriter1.write(confPost + "\n");
                                    fileWriter1.close ();
                                } catch ( IOException e ) {
                                    e.printStackTrace ();
                                }

                                File file2 = new File (ID + "PostComments.txt");
                                FileWriter fileWriter2 = null;
                                try {
                                    fileWriter2 = new FileWriter (file2, true);
                                    //fileWriter2.write(confPost + "\n");
                                    fileWriter2.close ();
                                } catch ( IOException e ) {
                                    e.printStackTrace ();
                                }
                                addedPost = true;
                                break;
                            }
                        }
                    } else if ( user.getType ().equals ("Personal") ) {
                        for ( User personalUser : ManagerBasic.personalUsers ) {
                            if ( personalUser.getUserName ().equals (userName) ) {
                                PersonalPost personalPost = new PersonalPost (post);
                                personalUser.posts.add (personalPost);
                                Post post2 = new Post (post);
                                ManagerBasic.allPosts.add (post2);
                                FileWriter fileWriter = null;
                                File file = new File (userName + "Posts.txt");
                                Scanner readID = new Scanner (file);
                                int postsNum = 0;
                                while (readID.hasNextLine ()){
                                    String s = readID.nextLine ();
                                    postsNum++;
                                }
                                String ID = userName + postsNum;
                                try {
                                    LocalDateTime myObj = LocalDateTime.now();
                                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                                    String formattedDate = myObj.format(myFormatObj);
                                    fileWriter = new FileWriter (file, true);
                                    fileWriter.write (post + ":" + commentable +":@" + formattedDate + "*" + ID +  "\n");
                                    fileWriter.close ();
                                } catch ( IOException e ) {
                                    e.printStackTrace ();
                                }

                                File file1 = new File (ID + "PostLikers.txt");
                                FileWriter fileWriter1 = null;
                                try {
                                    fileWriter1 = new FileWriter (file1, true);
                                    //fileWriter1.write(confPost + "\n");
                                    fileWriter1.close ();
                                } catch ( IOException e ) {
                                    e.printStackTrace ();
                                }

                                File file2 = new File (ID + "PostComments.txt");
                                FileWriter fileWriter2 = null;
                                try {
                                    fileWriter2 = new FileWriter (file2, true);
                                    fileWriter2.close ();
                                } catch ( IOException e ) {
                                    e.printStackTrace ();
                                }

                                addedPost = true;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
}
