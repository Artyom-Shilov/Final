package by.shilov.newsSite.domain;

public enum ArticleCategory {

    WORLD("World"),
    SCIENCE("Science"),
    POLITICS("Politics"),
    ACCIDENTS("Accidents"),
    TECHNOLOGY("Technology"),
    CARS("Cars");

    private String name;

    ArticleCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ArticleCategory getCategoryByString(String articleCategory){
        for (ArticleCategory category : ArticleCategory.values()){
            if (articleCategory.equals(category.getName())){
                return category;
            }
        }
        return null;
    }

}
