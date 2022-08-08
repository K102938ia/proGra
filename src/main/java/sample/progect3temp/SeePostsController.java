package sample.progect3temp;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SeePostsController {
    public ArrayList< Post > posts = new ArrayList <> ();
    public ArrayList<String > postText = new ArrayList <> ();
    public ArrayList<String> likers = new ArrayList<> ();
    public ArrayList<String> commenters = new ArrayList<> ();
    public ArrayList<String> comments = new ArrayList<> ();
    public ArrayList<Integer> likesNumer = new ArrayList<> ();
    public ArrayList<Integer> commentsNumer = new ArrayList<> ();
    public HashMap<String, Boolean> postsAndState = new HashMap<> ();
    public HashMap<String,Boolean> myPostsText = new HashMap <> ();
    public ArrayList<String> myPosts = new ArrayList<> ();
    public ArrayList<String> myComments = new ArrayList<> ();
    public ArrayList<String> myLikers = new ArrayList<> ();
    public ArrayList<Integer> myLikesNumer = new ArrayList<> ();
    public ArrayList<Integer> myCommnetsNumber = new ArrayList<> ();
    public ArrayList<String> myCommenters = new ArrayList<> ();
    public ArrayList< Seen > seenOfPost = new ArrayList<> ();
    public ArrayList< Like > likeOfPost = new ArrayList <> ();
    public HashMap< LocalDate,Integer > seenByDay = new HashMap <> ();
    public HashMap< LocalDate,Integer > likeByDay = new HashMap <> ();
    public ArrayList<String > allMyPosts = new ArrayList<> ();
    public ArrayList<String > seeCommentOnComment = new ArrayList<> ();
    public ArrayList<String > seeCommentOnCommentCommentors = new ArrayList<> ();
    public ArrayList<String > commentLikes= new ArrayList<> ();
    public HashMap< Integer,String > seeCommentLikes = new HashMap <> ();
    public ArrayList<String > commentLikers= new ArrayList<> ();
    public ArrayList<String > commentIDs = new ArrayList<> ();
    public ArrayList<String > seeCommentOnCommentLikes = new ArrayList<> ();
    public  ArrayList<String > specifiedCommentIDs = new ArrayList<> ();
    public  ArrayList<String > specifiedCommentors = new ArrayList<> ();
    public  ArrayList<String > specifiedComments = new ArrayList<> ();


    public boolean liked = false;
    public int likesNum = 0;
    public int commentsNum = 0;
    public boolean seePostCheck(String name, String seePostOfThis){
        ArrayList<String> following = new ArrayList<> ();
        File usersFile = new File (name + "following.txt");
        try {
            Scanner readUsers = new Scanner (usersFile);
            String temp;
            while(readUsers.hasNextLine ()){
                temp = readUsers.nextLine ();
                following.add (temp);
            }
            return following.contains (seePostOfThis);
        } catch ( FileNotFoundException e ) {
            e.printStackTrace ();
            return false;
        }

    }

    public ArrayList<String> seePosts(String name, String seePostOfThis){
        likesNumer.clear ();
        commentsNumer.clear ();
        postsAndState.clear ();
        comments.clear ();
        commenters.clear ();
        likers.clear ();
        commentIDs.clear ();
        postText.clear ();
        likesNum = 0;
        commentsNum = 0;
        File usersFile = new File (name + "following.txt");
        try {
            Scanner readUsers = new Scanner (usersFile);
            String temp;
            while (readUsers.hasNextLine ()) {
                temp = readUsers.nextLine ();
                if ( temp.equals (seePostOfThis) ) {
                    File postsFile = new File (seePostOfThis + "Posts.txt");
                    Scanner readPosts = new Scanner (postsFile);
                    int i = 0;
                    int num = 0;
                    while (readPosts.hasNextLine ()) {
                        String str = readPosts.nextLine ();
                        String[] stri = str.split ("&:");
                        String txt = stri[0] + "&";
                        String[] idTemp = str.split ("\\*");
                        postText.add (txt);
                        //System.out.println (txt);
                        String[] temp4 = stri[1].split (":");
                        boolean b =  Boolean.parseBoolean (temp4[1]);
                        postsAndState.put (txt,b);

                        try {
                            File likes = new File ( idTemp[1]+"PostLikers.txt");
                            Scanner readLikers = new Scanner (likes);
                            String liker;
                            //  int num = 0;
                            while (readLikers.hasNextLine ()) {
                                try {
                                    String readLike = readLikers.nextLine ();
                                    String[] read = readLike.split (":");
                                    liker = read[0];
                                    likers.add (liker);
                                }
                                catch ( NoSuchElementException exception ){
                                    break;
                                }
                                num++;
                            }
                            likesNumer.add (i,num);
                            readLikers.close ();

                        } catch ( FileNotFoundException e ) {
                            e.printStackTrace ();
                        }

                        try {
                            File commentsFile = new File ( idTemp[1]+"PostComments.txt");
                            Scanner readComments = new Scanner (commentsFile);
                            String commenter;
                            String commentText;
                            num = 0;
                            while (readComments.hasNextLine ()) {
                                //num = 0;
                                try {
                                    String str2 = readComments.nextLine ();
                                    String[] temp2 = str2.split (":");
                                    String[] temp3 = str2.split ("\\*");
                                    String commentID = temp3[1];
                                    commentText = temp2[0];
                                    commenter = temp2[1];
                                    comments.add (commentText);
                                    commenters.add (commenter);
                                    commentIDs.add (commentID);
                                    //commentsNum++;
                                    num++;
                                }
                                catch ( NoSuchElementException e ){
                                    break;
                                }
                            }
                            commentsNumer.add(i,num);
                            readComments.close ();
                        } catch ( FileNotFoundException e ) {
                            e.printStackTrace ();
                        }
                        i++;
                        num = 0;
                    }
                    break;
                }
            }
        }
        catch ( IOException e ){
            e.printStackTrace ();
        }
        return postText;
    }

    public boolean likeAPost(String liker, String beingLiked,String postNum) throws IOException {
        likers.clear ();
        String id = beingLiked + postNum;

        String text = "";
        if(!beingLiked.equals (liker)) {
            int num = Integer.parseInt (postNum);
            //text = postText.get (num - 1);
            text = postText.get (num);
        }
        else if(beingLiked.equals (liker)){
            int num = Integer.parseInt (postNum);
            text = myPosts.get (num - 1);
        }
//        File postsFile = new File (beingLiked + "Posts.txt");
//        Scanner readPosts = new Scanner (postsFile);
//        while (readPosts.hasNextLine ()){
//            String s = readPosts.nextLine ();
//            String[] str = s.split ("&:");
//            //String[] temp = s.split ("\\*");
//            String[] temp2 = s.split ("&:");
//            String[] temp3 = temp2[1].split (":@");
//
//            //id = temp[1];
//            String ss = str[0] + "&";
//
//            if(ss.equals (text)){
//                String[] temp = s.split ("\\*");
//                id = temp[1];
//                break;
//            }
//
//        }

        File file1 = new File (id + "PostLikers.txt");
        Scanner readLikers = new Scanner (file1);
        while (readLikers.hasNextLine ()){
            String s = readLikers.nextLine ();
            String[] str = s.split (":");
            String like = str[0];
            likers.add (like);
        }

        if(!likers.contains (liker)) {
            File file2 = new File (id + "PostLikers.txt");
            try {
                LocalDateTime myObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myObj.format(myFormatObj);
                FileWriter fileWriter1 = new FileWriter (file2, true);
                fileWriter1.write (liker + ":@" + formattedDate + "\n");
                fileWriter1.close ();
            } catch ( IOException e ) {
                e.printStackTrace ();
            }
        }
        else if(likers.contains (liker)){
            return false;
        }
        return true;
    }

    public boolean myCanComment(String username, String postNum) throws FileNotFoundException {
        int num = Integer.parseInt (postNum);
        String text = postText.get (num - 1);
        String id = "";
        String idOfComment = "";
        int commentID = 0;
        File postsFile = new File (username + "Posts.txt");
        Scanner readPosts = new Scanner (postsFile);
        while (readPosts.hasNextLine ()) {
            String s = readPosts.nextLine ();
            String[] str = s.split ("&");
            String post = str[0] + "&" + str[1] + "&";

            String[] temp2 = s.split ("&:");
            String[] temp3 = temp2[1].split (":@");
            String[] temp4 = str[0].split ("&");
            String s4 = str[0]+ "&";
           // System.out.println ("ttttt      " + text);
           // System.out.println (post);
            if((temp3[0].equals ("true") && post.equals (text)) || (temp3[0].equals ("true") && s4.equals (text))){
                return true;
            }
            else if((temp3[0].equals ("false") && post.equals (text)) || (s4.equals ("false") && str[0].equals (text))){
                return false;
            }
        }
        return false;
    }

    public boolean commentOnAPost(String commenter, String beingCommented,String postNum, String comment) throws IOException {
        int num = Integer.parseInt (postNum);
        //String text = postText.get (num - 1);
        String text = postText.get (num);
        String id = "";
        String idOfComment ="";
        int commentID = 0;
        File postsFile = new File (beingCommented + "Posts.txt");
        try {
            Scanner readPosts = new Scanner (postsFile);
            while (readPosts.hasNextLine ()){
                String s = readPosts.nextLine ();
                String[] str = s.split ("&:");
                String[] temp = s.split ("\\*");
                String[] temp2 = s.split ("&:");
                String[] temp3 = temp2[1].split (":@");

                id = temp[1];
                String ss = str[0] + "&";
                //System.out.println ("temp3   " + temp3[0]);
                //System.out.println ("str[0]   " + str[0]);
                //System.out.println ("text   " + text);
                if(temp3[0].equals ("true") && ss.equals (text)){
                    File file1 = new File (id + "PostComments.txt");
                    try {
                        Scanner readForID = new Scanner (file1);
                        while (readForID.hasNextLine ()){
                            String s1 = readForID.nextLine ();
                            commentID++;
                        }
                        idOfComment = id + "c" + commentID;
                        LocalDateTime myObj = LocalDateTime.now();
                        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String formattedDate = myObj.format(myFormatObj);
                        //System.out.println ("here");
                        FileWriter fileWriter1 = new FileWriter (file1, true);
                        fileWriter1.write(comment + ":" + commenter + ":@" + formattedDate + "*" + idOfComment + "\n");
                        fileWriter1.close();
                    } catch ( IOException e ) {
                        e.printStackTrace ();
                    }
                    File file2 = new File (idOfComment + "CommentLikes.txt");
                    file2.createNewFile ();

                    File file3 = new File (idOfComment + "CommentComments.txt");
                    file3.createNewFile ();
                    return true;
                }
                //else if(str[1].equals ("false")){
                //     return false;
                // }
                /*
                if(text.startsWith ("$(Ad)")) {
                    File file2 = new File (id + "PostSeen.txt");
                    //Scanner readSeen = new Scanner (file2);
                    String seener = commenter;
                    LocalDateTime myObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = myObj.format(myFormatObj);
                    FileWriter fileWriter2 = new FileWriter (file2, true);
                    fileWriter2.write (seener + "@" + formattedDate + "\n");
                    fileWriter2.close ();
                }
                 */
            }
        }
        catch ( IOException e ){
            e.printStackTrace ();
        }


/*
        File file2 = new File (text + comment + "CommentLikes.txt");
        file2.createNewFile ();
 */
        /*
        for ( Post post : posts ) {
            if(post.text.equals (text)){
                Comment comment1 = new Comment ();
                post.totalComments++;
                for ( User user : ManagerBasic.users ) {
                    if(user.getUserName ().equals (commenter)){
                        comment1.commentor = user;
                    }
                    else if(user.getUserName ().equals (beingCommented)){
                        comment1.beingCommented = user;
                    }
                }
                comment1.post = posts.get (num - 1);
                post.comments.add (comment1);
         */

                /*
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream (text + "Post.ser");
                    ObjectOutputStream out = new ObjectOutputStream (fileOutputStream);
                    out.writeObject (post);
                    out.close ();
                    fileOutputStream.close ();
                } catch ( IOException e ) {
                    e.printStackTrace ();
                }
                 */
        //}
        // }
        return false;
    }




    public ArrayList<String> seeLikes(String postNum,String userAccount) throws FileNotFoundException {
        likers.clear ();
        int num = Integer.parseInt (postNum);
       // String text = postText.get (num-1);
        String text = postText.get (num);
        int idNum = 0;
        String id = "";
        File posts = new File ( userAccount+"Posts.txt");
        Scanner readPosts = new Scanner (posts);
        while (readPosts.hasNextLine ()){
            String s = readPosts.nextLine ();
            idNum++;
        }
        id = userAccount + (idNum-1);
        try {
            File likes = new File ( id+"PostLikers.txt");
            Scanner readLikers = new Scanner (likes);
            String liker;
            while (readLikers.hasNextLine ()) {
                try {
                    String str = readLikers.nextLine ();
                    String[] s = str.split (":@");
                    liker = s[0];
                    likers.add (liker);
                    likesNum++;
                }
                catch ( NoSuchElementException exception ){
                    break;
                }

            }
            readLikers.close ();
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace ();
        }

        return likers;
    }

    public ArrayList<String> seeComments(){
        return commentIDs;
    }

    public  ArrayList<String> seeCommentsSpecified(String postNum ,String userName) throws FileNotFoundException {
        specifiedCommentIDs.clear ();
        specifiedCommentors.clear ();
        specifiedComments.clear ();
        String text = "";
        int num = Integer.parseInt (postNum);
//        if(num != 0){
//             text = postText.get (num - 1);
//        }
//        else {
//             text = postText.get (num );
//        }
        //text = postText.get (num - 1);
        text = postText.get (num );
        File posts = new File ( userName+"Posts.txt");
        Scanner readPosts = new Scanner (posts);
        while (readPosts.hasNextLine ()){
            String s = readPosts.nextLine ();
            String[] strings = s.split ("&:");
            String sss = strings[0] + "&";
            if(sss.equals (text)){
                String[] str = s.split ("\\*");
                String id = str[1];
                File comments = new File ( id+"PostComments.txt");
                Scanner scanner = new Scanner (comments);
                while (scanner.hasNextLine ()){
                    String temp = scanner.nextLine ();
                    String[] s1 = temp.split (":");
                    String[] s2 = temp.split ("\\*");
                    String name = s1[1];
                    String textC = s1[0];
                    String idC = s2[1];
                    specifiedComments.add (textC);
                    specifiedCommentors.add (name);
                    specifiedCommentIDs.add (idC);


                }
            }
        }
        return specifiedCommentIDs;
    }

    public boolean canComment(String user, String postNum){
        int num = Integer.parseInt (postNum);
        String text = postText.get (num - 1);
        for ( String s : postsAndState.keySet () ) {
            if(s.equals (text)){
                if(postsAndState.get (s)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }

    public void commentOnComment(String commentID,String commentText, String commenter) throws IOException {

        String idOfNewComment = "";
        int tempID = 0;
        File file = new File (commentID + "CommentComments.txt");
        Scanner redComments = new Scanner (file);
        while (redComments.hasNextLine ()){
            String s = redComments.nextLine ();
            tempID++;
        }
        idOfNewComment = commentID + "c" + tempID;
        LocalDateTime myObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myObj.format(myFormatObj);
        FileWriter fileWriter1 = new FileWriter (file, true);
        fileWriter1.write(commenter + ":" + commentText + ":@" + formattedDate + "*" + idOfNewComment + "\n");
        fileWriter1.close();

        File file2 = new File (idOfNewComment + "CommentComments.txt");
        file2.createNewFile ();

        File file3 = new File (idOfNewComment + "CommentLikes.txt");
        file3.createNewFile ();
    }

    public void seeCommentOnComment(String comment,String postNum) throws FileNotFoundException {
        seeCommentLikes.clear ();
        seeCommentOnComment.clear ();
        seeCommentOnCommentCommentors.clear ();


//        int num = Integer.parseInt (postNum);
//        String text = postText.get (num - 1);
//        File file = new File (text + comment + "CommentComments.txt");
//        Scanner readComments = new Scanner (file);
//        int j = 0;
//        while (readComments.hasNextLine ()){
//            String s = readComments.nextLine ();
//            String[] str = s.split (":");
//            String commenter = str[0];
//            String commentOnComment = str[1];
//            seeCommentOnComment.add (commentOnComment);
//            seeCommentOnCommentCommentors.add (commenter);
//            int i = 0;
//            File file1 = new File (text + comment + "CommentLikes.txt");
//            Scanner readComments1 = new Scanner (file1);
//            while (readComments1.hasNextLine ()){
//                String s33 = readComments1.nextLine ();
//                i++;
//            }
//            commentLikers.add (j,i);
//            j++;
//        }

    }

    public ArrayList<String> seeCommentsOfAComment(String currentComment,String userName, String commentID) throws FileNotFoundException {
        ArrayList<String> IDs = new ArrayList<> ();
        seeCommentOnCommentCommentors.clear ();
        seeCommentOnComment.clear ();
        seeCommentOnCommentLikes.clear ();
        File file = new File (commentID + "CommentComments.txt");
        Scanner readComments = new Scanner (file);
        while (readComments.hasNextLine ()){
            String s = readComments.nextLine ();
            String[] str = s.split (":");
            String[] str2 = s.split ("\\*");
            String commentText = str[1];
            String commentid = str2[1];
            String commentor = str[0];
            seeCommentOnComment.add (commentText);
            seeCommentOnCommentCommentors.add (commentor);
            IDs.add (commentid);
            File file2 = new File (commentid + "CommentLikes.txt");
            Scanner readCommentLikes = new Scanner (file2);
            while (readCommentLikes.hasNextLine ()){
                String s2 = readCommentLikes.nextLine ();
                String[] temp = s2.split (":");
                String liker = temp[0];
                seeCommentOnCommentLikes.add (liker);
            }
        }
        return IDs;
    }

    public boolean likeAComment( String commentID, String liker) throws IOException {
        commentLikes.clear ();
        commentLikers.clear ();
        File file = new File (commentID + "CommentLikes.txt");
        Scanner readLikes = new Scanner (file);
        while (readLikes.hasNextLine ()){
            String s = readLikes.nextLine ();
            String[] str = s.split (":");
            String like = str[0];
            commentLikers.add (like);
        }
        if(commentLikers.contains (liker)){
            return false;
        }
        else {
            FileWriter fileWriter1 = new FileWriter (file, true);
            LocalDateTime myObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myObj.format(myFormatObj);
            fileWriter1.write(liker  + ":@" + formattedDate + "\n");
            fileWriter1.close();
            return true;
        }
//        int num = Integer.parseInt (postNum);
//        String text = postText.get (num - 1);
//        File file = new File ( text + comment + "CommentLikes.txt");
//        Scanner readComments = new Scanner (file);
//        while (readComments.hasNextLine ()){
//            String s = readComments.nextLine ();
//            String[] str = s.split (":");
//            String liker1 = str[0];
//            String[] s2 = s.split (":@");
//            String date = s2[1];
//            commentLikes.add (liker1);
//        }
//
//        if(commentLikes.contains (liker)){
//            return false;
//        }
//        else {
//            FileWriter fileWriter1 = new FileWriter (file, true);
//            LocalDateTime myObj = LocalDateTime.now();
//            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//            String formattedDate = myObj.format(myFormatObj);
//            fileWriter1.write(liker + ":" + text + ":@" + formattedDate);
//            fileWriter1.close();
//            return true;
//        }
    }

    public ArrayList<String> seeCommentLikes(String currentComment, String commentID) throws FileNotFoundException {
        seeCommentOnCommentLikes.clear ();
        File file = new File (commentID + "CommentLikes.txt");
        Scanner readLikes = new Scanner (file);
        while (readLikes.hasNextLine ()){
            String s = readLikes.nextLine ();
            String[] str = s.split (":");
            String liker = str[0];
            seeCommentOnCommentLikes.add (liker);
        }

        return seeCommentOnCommentLikes;

    }

    public void seeMyOwnPosts(String userName){
        postText.clear ();
        postsAndState.clear ();
        commentIDs.clear ();
        String id;
        File postsFile = new File (userName + "Posts.txt");
        try {
            Scanner readPosts = new Scanner (postsFile);
            int i = 0;
            while (readPosts.hasNextLine ()){
                String temp = readPosts.nextLine ();
                String[] str = temp.split ("&:");
                String[] s = temp.split ("\\*");
                id = s[1];
                String txt = str[0] + "&";
                myPosts.add (txt);
                boolean b =  Boolean.parseBoolean (str[1]);
                postsAndState.put (txt,b);
                postText.add (txt);

                try {
                    File likes = new File ( id +"PostLikers.txt");
                    Scanner readLikers = new Scanner (likes);
                    String liker;
                    int num = 0;
                    while (readLikers.hasNextLine ()) {
                        //num =0;
                        try {
                            liker = readLikers.nextLine ();
                            myLikers.add (liker);
                            // likesNum++;
                            num++;
                        }
                        catch ( NoSuchElementException exception ){
                            break;
                        }

                    }
                    myLikesNumer.add (i,num);
                    readLikers.close ();

                } catch ( FileNotFoundException e ) {
                    e.printStackTrace ();
                }

                try {
                    File commentsFile = new File ( id+"PostComments.txt");
                    Scanner readComments = new Scanner (commentsFile);
                    String commenter;
                    String commentText;
                    int num = 0;
                    while (readComments.hasNextLine ()) {
                        //num = 0;
                        try {
                            String str2 = readComments.nextLine ();
                            String[] temp2 = str2.split (":");
                            String[] temp3 = str2.split ("\\*");
                            commentText = temp2[0];
                            commenter = temp2[1];
                            myComments.add (commentText);
                            myCommenters.add (commenter);
                            commentIDs.add (temp3[1]);
                            //commentsNum++;
                            //System.out.println (commentText);
                            num++;
                        }
                        catch ( NoSuchElementException e ){
                            break;
                        }
                    }
                    myCommnetsNumber.add(i,num);
                    readComments.close ();
                } catch ( FileNotFoundException e ) {
                    e.printStackTrace ();
                }
                i++;

            }
        } catch ( FileNotFoundException e ) {
            e.printStackTrace ();
        }
    }

    public void seeMyComments(String userName, String postNum) throws FileNotFoundException {
        myCommenters.clear ();
        myComments.clear ();
        int num = Integer.parseInt (postNum);
        String text = postText.get (num - 1);
        String id = "";
        File myPosts = new File (userName + "Posts.txt");
        Scanner readMyPosts = new Scanner (myPosts);
        while (readMyPosts.hasNextLine ()){
            String s = readMyPosts.nextLine ();
            String[] str = s.split ("\\*");
            String tempID = str[1];
            String[] str1 = s.split (":");
            if(str1[0].equals (text)){
                id = tempID;
                break;
            }
        }

        File postComments = new File (id + "PostComments.txt");
        Scanner readComments = new Scanner (postComments);
        while (readComments.hasNextLine ()){
            String s = readComments.nextLine ();
            String[] str = s.split (":");
            myComments.add (str[0]);
            myCommenters.add (str[1]);
        }
    }

    public boolean canShowStats(String userName){
        for ( User businessUser : ManagerBasic.businessUsers ) {
            if(businessUser.getUserName ().equals (userName)){
                return true;
            }
        }
        return false;
    }

    public void showStats(String userName, int postNum) throws FileNotFoundException {
        seenOfPost.clear ();
        String id = "";
        for ( User businessUser : ManagerBasic.businessUsers ) {
            if(businessUser.getUserName ().equals (userName)){
                File postsFile = new File (userName + "Posts.txt");
                try {
                    Scanner readPosts = new Scanner (postsFile);
                    while (readPosts.hasNextLine ()){
                        String temp = readPosts.nextLine ();
                        String[] s = temp.split (":");
                        String[] str = temp.split ("@");
                        String text = s[0];
                        Post post = new Post (text);
                        allMyPosts.add (text);
                    }

                } catch ( FileNotFoundException e ) {
                    e.printStackTrace ();
                }

                String text = allMyPosts.get (postNum );
                File postsFile2 = new File (userName + "Posts.txt");
                Scanner readPosts2 = new Scanner (postsFile2);
                while (readPosts2.hasNextLine ()){
                    String s = readPosts2.nextLine ();
                    String[] str = s.split ("\\*");
                    String[] s1 = s.split (":");
                    if(s1[0].equals (text)){
                        id = str[1] ;
                        break;
                    }
                }
                File seenFile = new File (id + "PostSeen.txt");
                Scanner readSeen = null;
                try {
                    readSeen = new Scanner (seenFile);
                } catch ( FileNotFoundException e ) {
                    e.printStackTrace ();
                }
                while (readSeen.hasNextLine ()){
                    Seen seen = new Seen ();
                    String s1 = readSeen.nextLine ();
                    String[] str1 = s1.split ("@");
                    String text1 = str1[0];
                    String date = str1[1];
                    String[] s  = date.split (" " );
                    String yearDate = s[0];
                    DateTimeFormatter myFormatObj1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    LocalDateTime localDateTime1 = LocalDateTime.parse (date,myFormatObj1);
                    LocalDate date1 = localDateTime1.toLocalDate ();
                    seen.localDate = date1;
                    seen.seener = text1;
                    seenOfPost.add (seen);
                    //seenByDay.put (localDateTime1,0);

                }

                File likeFile = new File (id + "PostLikers.txt");
                Scanner readLike = null;
                try {
                    readLike = new Scanner (likeFile);
                } catch ( FileNotFoundException e ) {
                    e.printStackTrace ();
                }
                while (readLike.hasNextLine ()){
                    Like like = new Like ();
                    String s2 = readLike.nextLine ();
                    String[] str2 = s2.split (":@");
                    String liker = str2[0];
                    String date = str2[1];
                    String[] s  = date.split (" " );
                    String yearDate = s[0];
                    DateTimeFormatter myFormatObj2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    LocalDateTime localDateTime2 = LocalDateTime.parse (date,myFormatObj2);;
                    LocalDate date1 = localDateTime2.toLocalDate ();
                    like.liker = liker;
                    like.dateMade = localDateTime2;
                    like.localDate = date1;
                    likeOfPost.add (like);
                    //seenByDay.put (localDateTime2,0);

                }

                //Duration duration = Duration.between(seenOfPost.get (0).seenDate, seenOfPost.get (seenOfPost.size ()-1).seenDate);
                //int totalSeenDays = (int) duration.toDays ();
                //Duration duration2 = Duration.between(likeOfPost.get (0).dateMade, likeOfPost.get (likeOfPost.size ()-1).dateMade);
                //int totalLikeDays = (int) duration2.toDays ();
                int i = 0;

                if(!seenOfPost.isEmpty ()){
                    for (LocalDate date = seenOfPost.get (0).localDate; date.isBefore(seenOfPost.get (seenOfPost.size ()-1).localDate);
                         date = date.plusDays(1)) {
                        seenByDay.put (date,0);
                        i++;
                    }
                    seenByDay.put (seenOfPost.get (seenOfPost.size ()-1).localDate, 0);
                }

                /*
                for ( Seen seen : seenOfPost ) {
                    for ( LocalDateTime localDateTime : seenByDay.keySet () ) {
                        if(seen.seenDate.getDayOfYear () == localDateTime.getDayOfYear ()){
                            int temp = seenByDay.get (localDateTime);
                            temp++;
                            //System.out.println (temp);
                            seenByDay.put (localDateTime,temp);
                        }
                    }
                }
                 */
                /*
                for ( Seen seen : seenOfPost ) {
                    System.out.println ("seener    " + seen.seener);
                    System.out.println ("date" + seen.seenDate);
                }
                 */

                for ( Seen seen : seenOfPost ) {
                    int temp = seenByDay.get (seen.localDate);
                    temp++;
                    seenByDay.put (seen.localDate, temp);
                }

                if(!likeOfPost.isEmpty ()){
                    i = 0;
                    for (LocalDate date = likeOfPost.get (0).localDate; date.isBefore(likeOfPost.get (likeOfPost.size ()-1).localDate);
                         date = date.plusDays(1)) {
                        likeByDay.put (date,0);
                        i++;
                    }

                    likeByDay.put (likeOfPost.get (likeOfPost.size ()-1).localDate, 0);
                    for ( Like like : likeOfPost ) {
                        int temp = likeByDay.get (like.localDate);
                        temp++;
                        likeByDay.put (like.localDate,temp);
                    }
                }
                /*
                for ( Like like : likeOfPost ) {
                    for ( LocalDate localDate : likeByDay.keySet () ) {
                        if(like.dateMade.getDayOfYear () == localDate.getDayOfYear ()){
                            int temp = likeByDay.get (localDate);
                            temp++;
                            likeByDay.put (localDate,temp);
                        }
                    }
                }
                 */

                break;
            }
        }
    }

    public void selectedSeen(int postNum , String seen,String account) throws IOException {
        String text = postText.get (postNum);
        String id = "";
        File file = new File (account + "Posts.txt");
        Scanner scanner = new Scanner (file);
        while (scanner.hasNextLine ()){
            String s = scanner.nextLine ();
            String[] str = s.split ("\\*");
            String[] s1 = s.split ("&:");
            String sss = s1[0] + "&";
            if (sss.equals (text) ){
                id = str[1];
            }
        }
        if(text.startsWith ("$(Ad)")) {
            File file2 = new File (id + "PostSeen.txt");
            //Scanner readSeen = new Scanner (file2);
            String seener = seen;
            LocalDateTime myObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = myObj.format(myFormatObj);
            FileWriter fileWriter2 = new FileWriter (file2, true);
            fileWriter2.write (seener + "@" + formattedDate + "\n");
            fileWriter2.close ();
        }
    }

}
