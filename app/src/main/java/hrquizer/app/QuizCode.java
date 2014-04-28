package hrquizer.app;

/**
 * Created by artem on 27.04.14.
 */
public class QuizCode extends Quiz {

    private int code;

    public QuizCode() {}

    public QuizCode(Quiz quiz, int code) {
        this.code = code;
        this.name = quiz.getName();
        this.id = quiz.getId();
        this.asks = quiz.getAsks();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
