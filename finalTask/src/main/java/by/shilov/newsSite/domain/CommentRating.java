package by.shilov.newsSite.domain;

import java.util.Objects;

public class CommentRating extends Entity {
    private User evaluator;
    private Comment evaluatedComment;
    private boolean plus;
    private boolean minus;

    public CommentRating() {}

    public CommentRating(User evaluator, Comment evaluatedComment, boolean plus, boolean minus) {
        this.evaluator = evaluator;
        this.evaluatedComment = evaluatedComment;
        this.plus = plus;
        this.minus = minus;
    }

    public User getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(User evaluator) {
        this.evaluator = evaluator;
    }

    public Comment getEvaluatedComment() {
        return evaluatedComment;
    }

    public void setEvaluatedComment(Comment evaluatedComment) {
        this.evaluatedComment = evaluatedComment;
    }

    public boolean isPlus() {
        return plus;
    }

    public void setPlus(boolean plus) {
        this.plus = plus;
    }

    public boolean isMinus() {
        return minus;
    }

    public void setMinus(boolean minus) {
        this.minus = minus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentRating that = (CommentRating) o;
        return plus == that.plus &&
                minus == that.minus &&
                Objects.equals(evaluator, that.evaluator) &&
                Objects.equals(evaluatedComment, that.evaluatedComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evaluator, evaluatedComment, plus, minus);
    }

    @Override
    public String toString() {
        return "CommentRating{" +
                "evaluator=" + evaluator +
                ", evaluatedComment=" + evaluatedComment +
                ", plus=" + plus +
                ", minus=" + minus +
                '}';
    }
}
