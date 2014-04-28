package hrquizer.app;

/**
 * Created by artem on 27.04.14.
 */
public class QuizCategory extends Quiz {

    private Category category;

    public QuizCategory() {}

    public QuizCategory(Quiz quiz, Category category) {
        this.category = category;
        this.name = quiz.getName();
        this.id = quiz.getId();
        this.asks = quiz.getAsks();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
