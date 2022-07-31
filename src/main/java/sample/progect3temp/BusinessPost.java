package sample.progect3temp;

import java.util.ArrayList;

public class BusinessPost extends Post{
    public BusinessPost(String text ) {
        super.text = text;
    }

    ArrayList <Comment> comments = new ArrayList<>();
}
