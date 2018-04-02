package com.echisan.model.vo;

import com.echisan.model.po.Comment;

public class CommentVO {
    private Comment comment;
    private String replyUsername;


    public CommentVO() {
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getReplyUsername() {
        return replyUsername;
    }

    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }
}
