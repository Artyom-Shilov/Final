package by.shilov.newsSite.domain;

import java.util.Date;
import java.util.Objects;

public class Article extends Entity{

    private String title;
    private String text;
    private ArticleStatus status;
    private Integer writerId;
    private Date creationDate;
    private ArticleCategory category;
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public Integer getWriterId() {
        return writerId;
    }

    public void setWriterId(Integer writerId) {
        this.writerId = writerId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title) &&
                Objects.equals(text, article.text) &&
                status == article.status &&
                Objects.equals(writerId, article.writerId) &&
                Objects.equals(creationDate, article.creationDate) &&
                category == article.category &&
                Objects.equals(imageUrl, article.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, status, writerId, creationDate, category, imageUrl);
    }
}
