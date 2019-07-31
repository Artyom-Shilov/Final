package by.shilov.newsSite.domain;

import java.util.Date;
import java.util.Objects;

public class Comment extends Entity {
    private String text;
    private Date creationDate;
    private Integer commentatorId;
    private Integer articleId;
    private Integer quotedCommentId;
    private String commentatorLogin;
    private Integer plusNumber;
    private Integer minusNumber;

    public Integer getPlusNumber() {
        return plusNumber;
    }

    public void setPlusNumber(Integer plusNumber) {
        this.plusNumber = plusNumber;
    }

    public Integer getMinusNumber() {
        return minusNumber;
    }

    public void setMinusNumber(Integer minusNumber) {
        this.minusNumber = minusNumber;
    }

    public String getCommentatorLogin() {
        return commentatorLogin;
    }

    public void setCommentatorLogin(String commentatorLogin) {
        this.commentatorLogin = commentatorLogin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(Integer commentatorId) {
        this.commentatorId = commentatorId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getQuotedCommentId() {
        return quotedCommentId;
    }

    public void setQuotedCommentId(Integer quotedCommentId) {
        this.quotedCommentId = quotedCommentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(text, comment.text) &&
                Objects.equals(creationDate, comment.creationDate) &&
                Objects.equals(commentatorId, comment.commentatorId) &&
                Objects.equals(articleId, comment.articleId) &&
                Objects.equals(quotedCommentId, comment.quotedCommentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, creationDate, commentatorId, articleId, quotedCommentId);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "text='" + text + '\'' +
                ", creationDate=" + creationDate +
                ", commentatorId=" + commentatorId +
                ", articleId=" + articleId +
                ", quotedCommentId=" + quotedCommentId + '\'' +
                '}';
    }
}
