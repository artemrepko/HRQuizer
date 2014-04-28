package hrquizer.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by artem on 28.04.14.
 */
public class MainMenu extends Activity {

    private final int IDD_EXIT = 0;

    static public Quiz workquiz = new Quiz();
    public Answer answer1 = new Answer("Ansver 1",0);
    public Answer answer2 = new Answer("Ansver 2",1);
    public Answer answer3 = new Answer("Ansver 3",2);
    public ArrayList<Answer> answerArrayList = new ArrayList<Answer>();
    public ArrayList<Ask> askArrayList = new ArrayList<Ask>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        workquiz.setName("Quiz 1");
        workquiz.setId(1);

        for (int i=0; i<5; i++) {
            Ask ask = new Ask();
            ask.setQuestion("Question " + Integer.toString(i+1));
            answerArrayList.clear();
            answerArrayList.add(answer1);
            answerArrayList.add(answer2);
            answerArrayList.add(answer3);
            ask.setAnswers(answerArrayList);
            askArrayList.add(ask);
        }

        workquiz.setAsks(askArrayList);

        Log.e("MY_LOG", workquiz.getName());
        Log.e("MY_LOG", Integer.toString(workquiz.getId()));


        for (int i=0; i<workquiz.getAsks().size(); i++) {
            Log.e("MY_LOG", workquiz.getAsk(i).getQuestion());
            Log.e("MY_LOG", workquiz.getAsk(i).getAnswer(0).getText());
            Log.e("MY_LOG", workquiz.getAsk(i).getAnswer(1).getText());
            Log.e("MY_LOG", workquiz.getAsk(i).getAnswer(2).getText());
        }
    }

    public void toCodeQuiz(View v) {
        Intent intent = new Intent(MainMenu.this, StartCodeQuiz.class);
        startActivity(intent);
    }

    public void toCategoryQuiz(View v) {
        Intent intent = new Intent(MainMenu.this, StartCategoryQuiz.class);
        startActivity(intent);
    }

    public void toExit(View v) {
        showDialog(IDD_EXIT);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case IDD_EXIT:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Вы действительно хотите выйти?");

                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainMenu.this.finish();
                    }
                });

                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setCancelable(false);
                return builder.create();
            default:
                return null;
        }
    }
}
