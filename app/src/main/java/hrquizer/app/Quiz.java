package hrquizer.app;

import java.util.ArrayList;

/**
 * Created by artem on 27.04.14.
 */
public class Quiz {

    protected int id;

    protected String name;

    protected ArrayList<Ask> asks;

    public Quiz() {}

    public Quiz(int id, String name, ArrayList<Ask> asks) {
        this.id = id;
        this.name = name;
        this.asks = asks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ask> getAsks() {
        return asks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAsks(ArrayList<Ask> asks) {
        this.asks = asks;
    }

    public Ask getAsk(int position) {
        return this.asks.get(position);
    }
}
