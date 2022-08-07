package sample.progect3temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class MainMenuController {
    public ArrayList < Post > recentPosts = new ArrayList <> ();
    public ArrayList < String > recentComments = new ArrayList <> ();
    public ArrayList < Post > recentFollowingPosts = new ArrayList <> ();
    public ArrayList< Seen > seenOfPost = new ArrayList<> ();
    public ArrayList< Like > likeOfPost = new ArrayList <> ();
    public HashMap < LocalDateTime,Integer > seenByDay = new HashMap <> ();
    public HashMap< LocalDateTime,Integer > likeByDay = new HashMap <> ();

    public ArrayList<Post> postsForStats = new ArrayList<> ();

    public ArrayList<Post> showRecent ( String userName ) {
        int myPostsCount = 0;
        ArrayList < Integer > followingPostsCount = new ArrayList <> ();
        ArrayList < String > followingRecentPosts = new ArrayList <> ();
        ArrayList < Post > recentMyPosts = new ArrayList <> ();
        ArrayList < Post > recentAllPosts = new ArrayList <> ();
        ArrayList < Comment > allComments = new ArrayList <> ();
        ArrayList < Comment > eachRecentComments = new ArrayList <> ();
        //LocalDateTime localDateTime = LocalDateTime.parse ("25-07-2022 09:53:13");

        File myPosts = new File (userName + "Posts.txt");
        try {
            Scanner readMyPosts = new Scanner (myPosts);
            while (readMyPosts.hasNextLine ()) {
                String s = readMyPosts.nextLine ();
                String[] temp = s.split ("@");
                String[] str = s.split ("&:");
                String text = str[0];
                String[] dateTemp = temp[1].split ("\\*");
                String date = dateTemp[0];
                Post post = new Post (text);
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                LocalDateTime localDateTime = LocalDateTime.parse (date,myFormatObj);
                post.dateMade = localDateTime;
                post.posterUser = userName;
                String[] idTemp = s.split ("\\*");
                String id = idTemp[1];
                File myPostsComments = new File (id + "postComments.txt");
                Scanner readMyComments = new Scanner (myPostsComments);
                while (readMyComments.hasNextLine ()) {
                    String s1 = readMyComments.nextLine ();
                    String[] str1 = s1.split (":");
                    String commentText = str1[0];
                    String commenter = str1[1];
                    String[] temp1 = s1.split ("@");
                    String[] dateTemp2 = temp[1].split ("\\*");
                    String commentDate = dateTemp2[0];
                    Comment comment = new Comment ();
                    DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    LocalDateTime localDateTime1 = LocalDateTime.parse (commentDate,myFormatObj2);
                    post.dateMade = localDateTime;
                    comment.post = post;
                    comment.text = commentText;
                    comment.dateMade = localDateTime1;
                    comment.commentorName = commenter;
                    //allComments.add (comment);
                    eachRecentComments.add(comment);
                    //post.comments.add (comment);
                }
                if(eachRecentComments.size () >= 2){
                    allComments.add (eachRecentComments.get (eachRecentComments.size () - 2));
                    allComments.add (eachRecentComments.get (eachRecentComments.size () - 1));

                    post.comments.add (eachRecentComments.get (eachRecentComments.size () - 2));
                    post.comments.add (eachRecentComments.get (eachRecentComments.size () - 1));
                }
                else if(eachRecentComments.size () == 1){
                    allComments.add (eachRecentComments.get (eachRecentComments.size () - 1));
                    post.comments.add (eachRecentComments.get (eachRecentComments.size () - 1));
                }
                eachRecentComments.clear ();
                //Collections.sort (allComments,new CommentComparator ());
                recentMyPosts.add (post);

            }
        } catch ( FileNotFoundException e ) {
            e.printStackTrace ();
        }
        if(recentMyPosts.size () >= 2){
            recentFollowingPosts.add (recentMyPosts.get (recentMyPosts.size ()-2));
            recentFollowingPosts.add (recentMyPosts.get (recentMyPosts.size ()-1));

        }
        else if(recentMyPosts.size () == 1){
            recentFollowingPosts.add (recentMyPosts.get (recentMyPosts.size ()-1));
        }
        /*
        for ( Post recentFollowingPost : recentFollowingPosts ) {
            System.out.println (recentFollowingPost.text);
        }

         */
        File followings = new File (userName + "following.txt");
        try {
            Scanner readFollowings = new Scanner (followings);
            while (readFollowings.hasNextLine ()) {
                String following = readFollowings.nextLine ();
                File followingsPosts = new File (following + "Posts.txt");
                Scanner readFollowingsPosts = new Scanner (followingsPosts);
                while (readFollowingsPosts.hasNextLine ()) {
                    String s = readFollowingsPosts.nextLine ();
                    String[] temp = s.split ("@");
                    String[] str = s.split ("&:");
                    String[] dateTemp = temp[1].split ("\\*");
                    String text = str[0];
                    String date = dateTemp[0];
                    Post post = new Post (text);
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    LocalDateTime localDateTime = LocalDateTime.parse (date,myFormatObj);
                    post.dateMade = localDateTime;
                    post.posterUser = following;
                    String[] idTemp = s.split ("\\*");
                    String id = idTemp[1];
                    File myPostsComments = new File (id + "PostComments.txt");
                    Scanner readMyComments = new Scanner (myPostsComments);
                    while (readMyComments.hasNextLine ()) {
                        String s1 = readMyComments.nextLine ();
                        String[] str1 = s1.split (":");
                        String commentText = str1[0];
                        String commenter = str1[1];
                        String[] temp1 = s1.split ("@");
                        String[] dateTemp1 = temp[1].split ("\\*");
                        String commentDate = dateTemp1[0];
                        Comment comment = new Comment ();
                        DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        LocalDateTime localDateTime1 = LocalDateTime.parse (commentDate,myFormatObj1);
                        post.dateMade = localDateTime;
                        comment.post = post;
                        comment.text = commentText;
                        comment.dateMade = localDateTime1;
                        comment.commentorName = commenter;
                        //post.comments.add (comment);
                        eachRecentComments.add(comment);
                    }
                    if(eachRecentComments.size () >= 2){
                        allComments.add (eachRecentComments.get (eachRecentComments.size () - 1));
                        allComments.add (eachRecentComments.get (eachRecentComments.size () - 2));

                        post.comments.add (eachRecentComments.get (eachRecentComments.size () - 1));
                        post.comments.add (eachRecentComments.get (eachRecentComments.size () - 2));
                    }
                    else if(eachRecentComments.size () == 1){
                        allComments.add (eachRecentComments.get (eachRecentComments.size () - 1));
                        post.comments.add (eachRecentComments.get (eachRecentComments.size () - 1));
                    }
                    eachRecentComments.clear ();
                    recentAllPosts.add (post);
                }
                if(recentAllPosts.size () >= 2) {
                    recentFollowingPosts.add (recentAllPosts.get (recentAllPosts.size () - 2));
                    recentFollowingPosts.add (recentAllPosts.get (recentAllPosts.size () - 1));
                }
                else if(recentAllPosts.size () == 1){
                    recentFollowingPosts.add (recentAllPosts.get (recentAllPosts.size () - 1));
                }
                recentAllPosts.clear ();
            }
        } catch ( FileNotFoundException e ) {
            e.printStackTrace ();
        }

        if(recentMyPosts.size () >= 2) {
            recentAllPosts.add (recentMyPosts.get (recentMyPosts.size () - 2));
            recentAllPosts.add (recentMyPosts.get (recentMyPosts.size () - 1));
        }
        else if(recentMyPosts.size () == 1){
            recentAllPosts.add (recentMyPosts.get (recentMyPosts.size () - 1));
        }

        Collections.sort (recentFollowingPosts,new PostComparator ());
        for ( Post recentFollowingPost : recentFollowingPosts ) {
            if(recentFollowingPost.text.startsWith ("$(Ad)")){
                File file2 = new File (recentFollowingPost.text + "PostSeen.txt");
                //Scanner readSeen = new Scanner (file2);
                try {
                    String seener = userName;
                    LocalDateTime myObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = myObj.format(myFormatObj);
                    FileWriter fileWriter2 = new FileWriter (file2, true);
                    fileWriter2.write (seener + "@" + formattedDate + "\n");
                    fileWriter2.close ();
                }
                catch ( IOException e ){
                    break;
                }

            }
        }
        return recentFollowingPosts;
    }

}
