package by.shilov.newsSite.domain;

import java.util.Objects;

public class ArticleUserTag extends Entity {
    private Article article;
    private Mark mark;

    public ArticleUserTag(){}

    public ArticleUserTag(Article article, Mark mark) {
        this.article = article;
        this.mark = mark;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleUserTag that = (ArticleUserTag) o;
        return Objects.equals(article, that.article) &&
                Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, mark);
    }

    @Override
    public String toString() {
        return "ArticleUserTag{" +
                "article=" + article +
                ", mark=" + mark +
                '}';
    }
}
