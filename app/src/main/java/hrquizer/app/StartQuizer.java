package hrquizer.app;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StartQuizer extends Activity implements AdapterView.OnItemClickListener {

    private String testQuiz = "";
    private ArrayList<Quiz> quizArrayList = new ArrayList<Quiz>();
    QuizAdapter quizAdapter;
    public String categoryName;
    public int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quizer);

        categoryName = getIntent().getStringExtra("categoryName");
        categoryId = getIntent().getIntExtra("categoryId",1);

        quizArrayList.add(MainMenu.workquiz);

        quizAdapter = new QuizAdapter(this, quizArrayList);

        ListView lvMain = (ListView) findViewById(R.id.list_view);
        lvMain.setAdapter(quizAdapter);
        lvMain.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(StartQuizer.this, StartAsk.class);
        startActivity(intent);
        finish();
    }
}


