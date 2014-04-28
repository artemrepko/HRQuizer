package hrquizer.app;

/**
 * Created by artem on 27.04.14.
 */
public class Answer {

    private String text;

    private int credits;

    public Answer() {}

    public Answer(String text, int credits) {
        this.text = text;
        this.credits = credits;
    }

    public String getText() {
        return text;
    }

    public int getCredits() {
        return credits;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
