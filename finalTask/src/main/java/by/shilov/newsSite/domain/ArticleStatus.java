package by.shilov.newsSite.domain;

public enum ArticleStatus {

    APPROVED("approved"),
    NOT_APPROVED("not_approved");

    private String name;

    ArticleStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ArticleStatus getStatusByString(String articleStatus){
        for (ArticleStatus status : ArticleStatus.values()){
            if (articleStatus.equals(status.getName())){
                return status;
            }
        }
        return null;
    }
}
