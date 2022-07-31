package sample.progect3temp;

import java.util.ArrayList;

public class PersonalPost extends Post{
    public PersonalPost( String text ) {
        super.text = text;
    }

    ArrayList <Comment> comments = new ArrayList<>();

}
