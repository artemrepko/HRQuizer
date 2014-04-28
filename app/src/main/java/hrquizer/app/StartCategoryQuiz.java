package hrquizer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by artem on 28.04.14.
 */
public class StartCategoryQuiz extends Activity implements ListView.OnItemClickListener{

    public Category myCategory = new Category("Category 1",1);
    public Quiz myQuiz = MainMenu.workquiz;
    public QuizCategory quizCategory = new QuizCategory(myQuiz,myCategory);
    public ArrayList<Category> categoryArrayList = new ArrayList<Category>();
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_menu);

        categoryArrayList.add(myCategory);
        categoryAdapter = new CategoryAdapter(this, categoryArrayList);

        ListView lvMain = (ListView) findViewById(R.id.list_view_category);
        lvMain.setAdapter(categoryAdapter);
        lvMain.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(StartCategoryQuiz.this, StartQuizer.class);
        intent.putExtra("categoryName",categoryArrayList.get(position).getName());
        intent.putExtra("categoryId",categoryArrayList.get(position).getId());
        startActivity(intent);
        finish();
    }
}
