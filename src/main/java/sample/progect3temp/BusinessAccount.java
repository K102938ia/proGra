package sample.progect3temp;

import java.util.ArrayList;

public class BusinessAccount extends User{
    public BusinessAccount(String userName) {
        super(userName);
    }
    public ArrayList <BusinessPost> posts = new ArrayList<>();

}
