package sample.progect3temp;

import java.util.ArrayList;

public class PersonalAccount extends User {
    public ArrayList <PersonalPost> posts = new ArrayList<>();

    public PersonalAccount(String userName) {
        super(userName);
    }
}
