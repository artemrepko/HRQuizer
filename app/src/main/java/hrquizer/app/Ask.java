package hrquizer.app;

import java.util.ArrayList;

/**
 * Created by artem on 27.04.14.
 */
public class Ask {

    private String question;

    private ArrayList<Answer> answers;

    public Ask() {}

    public Ask(String question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public Answer getAnswer(int position) {
        return this.answers.get(position);
    }
}
