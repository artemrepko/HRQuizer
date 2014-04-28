package hrquizer.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by artem on 28.04.14.
 */
public class QuizAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Quiz> quizers;

    public QuizAdapter(Context _context, ArrayList<Quiz> _quizers) {
        context = _context;
        quizers = _quizers;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return quizers.size();
    }

    @Override
    public Object getItem(int position) {
        return quizers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.activity_name_listitem, parent, false);
        }

        Quiz p = (Quiz) getItem(position);

        ((TextView) view.findViewById(R.id.textListItem)).setText(p.name);
        return view;
    }
}
