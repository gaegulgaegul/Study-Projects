package me.whiteship.jpawebapplication.post;

import lombok.Getter;

@Getter
public class CommentSum {

    private String comment;

    private int up;

    private int down;

    public CommentSum(String comment, int up, int down) {
        this.comment = comment;
        this.up = up;
        this.down = down;
    }

    public String getVotes() {
        return this.up + " " + this.down;
    }

}
