package sample.progect3temp;

import java.util.ArrayList;

public  class User {
    public String userName;
    int numberOfFollower=0,numberOfFollowing=0;
    ArrayList <String> followers=new ArrayList<>();
    ArrayList <String> followings=new ArrayList<>();
    String type;
    String passWord;
    String securityAnswer;
    public ArrayList<Post> posts = new ArrayList<> ();



    public User(String userName, int numberOfFollower, int numberOfFollowing) {
        this.userName = userName;
        this.numberOfFollower = numberOfFollower;
        this.numberOfFollowing = numberOfFollowing;
    }

    public void setUserName ( String userName ) {
        this.userName = userName;
    }

    public void setFollowers ( ArrayList < String > followers ) {
        this.followers = followers;
    }

    public void setFollowings ( ArrayList < String > followings ) {
        this.followings = followings;
    }

    public void setPassWord ( String passWord ) {
        this.passWord = passWord;
    }

    public void setSecurityAnswer ( String securityAnswer ) {
        this.securityAnswer = securityAnswer;
    }

//    public void setPosts ( ArrayList < Post > posts ) {
//        this.posts = posts;
//    }

    public void setType ( String type ) {
        this.type = type;
    }

    public String getType () {
        return type;
    }

    public User() {

    }

    public void setFollowers(String newFollower) {
        followers.add(newFollower);
    }

    public void setFollowings(String newFollowing) {
        followings.add(newFollowing) ;
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public ArrayList<String> getFollowings() {
        return followings;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumberOfFollower() {
        return numberOfFollower;
    }

    public int getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public void setNumberOfFollower(int numberOfFollower) {
        this.numberOfFollower = numberOfFollower;
    }

    public void setNumberOfFollowing(int numberOfFollowing) {
        this.numberOfFollowing = numberOfFollowing;
    }

}
