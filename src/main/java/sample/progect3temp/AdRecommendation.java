package sample.progect3temp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class AdRecommendation {
    public ArrayList<String > followersList =new ArrayList<> ();
    public ArrayList<String > followingsList  =new ArrayList<> ();
    public ArrayList<String > liked =new ArrayList<> ();
    //public HashMap <String,String > toRecomm =new HashMap <> ();// person - post
    public HashMap <String,String > toCheck =new HashMap <> ();// post - id
    //public HashMap <String,String > randomRecomm =new HashMap <> ();
    public ArrayList<String > peopleToRecom = new ArrayList<> ();
    public ArrayList<String > postToRecom = new ArrayList<> ();
    public ArrayList<String > postToRecomRand = new ArrayList<> ();
    public ArrayList<String > peopleToRecomRand = new ArrayList<> ();
    public ArrayList<String > images = new ArrayList<> ();
    public ArrayList<String > imageToRecomRand = new ArrayList<> ();
    public ArrayList<String > idToRecomRand = new ArrayList<> ();
    public ArrayList<String > idToRecom = new ArrayList<> ();



    public boolean findAdBasedOnPeople(String userName) throws FileNotFoundException {
        followersList.clear ();
        followingsList.clear ();
        peopleToRecom.clear ();
        postToRecom.clear ();
        images.clear ();
        File followers = new File (userName + "follower.txt");
        Scanner followerScanner = new Scanner (followers);
        while (followerScanner.hasNextLine ()){
            String s = followerScanner.nextLine ();
            if(!followersList.contains (s)){
                followersList.add (s);
            }
        }

        File followings = new File (userName + "following.txt");
        Scanner followingScanner = new Scanner (followings);
        while (followingScanner.hasNextLine ()){
            String s = followingScanner.nextLine ();
            if(!followingsList.contains (s)){
                followingsList.add (s);
            }
        }

        for ( User businessUser : ManagerBasic.businessUsers ) {
            String name = businessUser.getUserName ();
            File posts = new File (name + "Posts.txt");
            Scanner postsScanner = new Scanner (posts);
            while (postsScanner.hasNextLine ()){
                String s = postsScanner.nextLine ();
                String[] str = s.split ("&:");
                String[] str1 = s.split ("\\*");
                String[] temp = str[0].split ("&");
                String text = "";
                String image = "";
                if(temp.length > 1){
                     text = temp[0];
                     image = temp[1];
                     text = temp[0];
                    //postToRecom.add (text);
                    //peopleToRecom.add (name);
                    //images.add (image);
                }
                else {
                    text = temp[0];
                    image = "";
                    //postToRecom.add (text);
                    //peopleToRecom.add (name);
                    //images.add ("");
                }
                //String text = str[0];
                String id = str1[1];
                //toCheck.put (text,id);
                File likers = new File (id + "PostLikers.txt");
                Scanner readLikes = new Scanner (likers);
                while (readLikes.hasNextLine ()){
                    String s1 = readLikes.nextLine ();
                    String[] str2 = s1.split (":");
                    String like = str2[0];
                    if(like.equals (userName)){
                        break;
                    }
                    else {
                        if(followingsList.contains (like) || followersList.contains (like)){
                            // toRecomm.put (name,text);
                            peopleToRecom.add (name);
                            postToRecom.add (text);
                            images.add (image);
                        }
                    }
                }
            }
        }
        if(postToRecom.isEmpty ()){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean findAdRandomly(String userName) throws FileNotFoundException {
        boolean liked = false;
        boolean likedBefore = false;
        //toRecomm.clear ();
        //randomRecomm.clear ();
        postToRecom.clear ();
        peopleToRecom.clear ();
        idToRecom.clear ();
        images.clear ();
        postToRecomRand.clear ();
        peopleToRecomRand.clear ();
        imageToRecomRand.clear ();
        idToRecomRand.clear ();

        String id = "";

        for ( User businessUser : ManagerBasic.businessUsers ) {
            String name = businessUser.getUserName ();
            if(!name.equals (null)){
                File posts = new File (name + "Posts.txt");
                Scanner postsScanner = new Scanner (posts);
                while (postsScanner.hasNextLine ()){
                    String s = postsScanner.nextLine ();
                    String[] str = s.split ("&:");
                    String[] str1 = s.split ("\\*");
                    String[] temp = str[0].split ("&");
                    if(temp.length > 1){
//                    String text = temp[0];
//                    String image = temp[1];
//                    postToRecom.add (text);
//                    peopleToRecom.add (name);
//                    images.add (image);
                    }
                    else {
//                   String text = temp[0];
//                    postToRecom.add (text);
//                    peopleToRecom.add (name);
//                    images.add ("");
                    }
                    //String text = str[0];
                    id = str1[1];
                    File l = new File (id + "PostLikers.txt");
                    Scanner lSc = new Scanner (l);
                    while (lSc.hasNextLine ()){
                        String n = lSc.nextLine ();
                        String liker = n.split (":@")[0];
                        if(liker.equals (userName)){
                            likedBefore = true;
                            break;
                        }
                    }
                    if(!likedBefore){
                        idToRecom.add (id);
                        if(temp.length > 1){
                            String text = temp[0];
                            String image = temp[1];
                            postToRecom.add (text);
                            peopleToRecom.add (name);
                            images.add (image);
                        }
                        else {
                            String text = temp[0];
                            postToRecom.add (text);
                            peopleToRecom.add (name);
                            images.add (" ");
                        }
                    }
                    likedBefore = false;

                    //toRecomm.put (name,text);
                    // peopleToRecom.add (name);
                    //postToRecom.add (text);
                }
            }
//            File posts = new File (name + "Posts.txt");
//            Scanner postsScanner = new Scanner (posts);
//            while (postsScanner.hasNextLine ()){
//                String s = postsScanner.nextLine ();
//                String[] str = s.split ("&:");
//                String[] str1 = s.split ("\\*");
//                String[] temp = str[0].split ("&");
//                if(temp.length > 1){
////                    String text = temp[0];
////                    String image = temp[1];
////                    postToRecom.add (text);
////                    peopleToRecom.add (name);
////                    images.add (image);
//                }
//                else {
////                   String text = temp[0];
////                    postToRecom.add (text);
////                    peopleToRecom.add (name);
////                    images.add ("");
//                }
//                //String text = str[0];
//                id = str1[1];
//                File l = new File (id + "PostLikers.txt");
//                Scanner lSc = new Scanner (l);
//                while (lSc.hasNextLine ()){
//                    String n = lSc.nextLine ();
//                    if(n.equals (userName)){
//                        likedBefore = true;
//                        break;
//                    }
//                }
//                if(!likedBefore){
//                    idToRecom.add (id);
//                    if(temp.length > 1){
//                        String text = temp[0];
//                        String image = temp[1];
//                        postToRecom.add (text);
//                        peopleToRecom.add (name);
//                        images.add (image);
//                    }
//                    else {
//                        String text = temp[0];
//                        postToRecom.add (text);
//                        peopleToRecom.add (name);
//                        images.add ("");
//                    }
//                }
//                likedBefore = false;
//
//                //toRecomm.put (name,text);
//               // peopleToRecom.add (name);
//                //postToRecom.add (text);
//            }
        }

        //Set <String> keySet = toRecomm.keySet();
        //List <String> keyList = new ArrayList<>(keySet);
        int index = (int)(Math.random() * postToRecom.size());
        if(postToRecom.size () > 0){
            String postText = postToRecom.get (index);
            String user = peopleToRecom.get (index);
            postToRecomRand.add (postText);
            peopleToRecomRand.add (peopleToRecom.get (index));
            imageToRecomRand.add (images.get (index));
            imageToRecomRand.add (idToRecom.get (index));
        }

        //int size = keyList.size();
        //int randIdx = new Random().nextInt(size);

        //String randomKey = keyList.get(randIdx);
        //String randomValue = toRecomm.get(randomKey);
//        File namePosts = new File (user + "Posts.txt");
//        Scanner readPosts = new Scanner (namePosts);
//        while (readPosts.hasNextLine ()){
//            String s = readPosts.nextLine ();
//            String[] str = s.split ("\\*");
//            String ide = str[1];
//            System.out.println (ide);
//            if(ide.equals (id)){
//                File likes = new File (ide + "PostLikers.txt");
//                Scanner readLike = new Scanner (likes);
//                while (readLike.hasNextLine ()){
//                    String likerTemp = readLike.nextLine ();
//                    String[] liker2Temp = likerTemp.split (":");
//                    String liker = liker2Temp[0];
//                    System.out.println (liker);
//                    if ( liker.equals (userName) ){
//                        liked = true;
//                        break;
//                    }
//                }
//                if(!liked) {
//                    postToRecomRand.add (postText);
//                    peopleToRecomRand.add (peopleToRecom.get (index));
//                    imageToRecomRand.add (images.get (index));
//                    //randomRecomm.put (randomKey, randomValue);
//                }
//                liked = false;
//            }
      //  }
//            File likes = new File (ide + "PostLikers.txt");
//            Scanner readLike = new Scanner (likes);
//            while (readLike.hasNextLine ()){
//                String likerTemp = readLike.nextLine ();
//                String[] liker2Temp = likerTemp.split (":");
//                String liker = liker2Temp[0];
//                if ( liker.equals (userName) ){
//                    liked = true;
//                    break;
//                }
//            }
//        }
//        if(!liked) {
//            postToRecomRand.add (postText);
//            peopleToRecomRand.add (peopleToRecom.get (index));
//            //randomRecomm.put (randomKey, randomValue);
//        }
        if(postToRecomRand.isEmpty ()){
            return false;
        }
        else {
            return true;
        }
    }
}
