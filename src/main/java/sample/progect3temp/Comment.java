package sample.progect3temp;

import java.time.LocalDateTime;

public class Comment {
    public Post post;
    public User commentor;
    public User beingCommented;
    public String text;
    public String commentorName;
    public LocalDateTime dateMade;
}
