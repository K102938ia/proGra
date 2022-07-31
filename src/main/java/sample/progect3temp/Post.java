package sample.progect3temp;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post {
    Post(){

    }
    public Post(String text){
        this.text = text;
    }

    public ArrayList <Comment> comments = new ArrayList<> ();
    public ArrayList<Comment> recentComments = new ArrayList<> ();
    public ArrayList<Like> likes = new ArrayList<> ();
    public ArrayList<String> likedUsers = new ArrayList<> ();
    public int totalLikes = 0;
    public int totalComments = 0;
    public String text;
    public LocalDateTime dateMade;
    public String posterUser;
}
