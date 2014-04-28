package hrquizer.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by artem on 28.04.14.
 */
public class Authorization extends Activity {

    public User myUser = new User();
    public ArrayList<User> userArrayList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        userArrayList.add(new User("artem","123"));
    }

    public void Login(View v) {

        myUser.setLogin(((EditText) findViewById(R.id.editLogin)).getText().toString());
        myUser.setPassword(((EditText) findViewById(R.id.editPass)).getText().toString());

        for (int i=0; i<userArrayList.size(); i++) {
            if (userArrayList.get(i).getLogin().equals(myUser.getLogin()) && userArrayList.get(i).getPassword().equals(myUser.getPassword())) {
                Intent intent = new Intent(Authorization.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        }
    }
}
