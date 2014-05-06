package hrquizer.app;

import java.util.ArrayList;

/**
 * Created by artem on 27.04.14.
 */
public class Quiz {

    protected int id;

    protected String name;

    protected ArrayList<Ask> asks;

    protected String typereport;

    public Quiz() {
        this.typereport = "noreport";
    }

    public Quiz(String name) {
        this.name = name;
        this.typereport = "noreport";
    }

    public Quiz(String name, ArrayList<Ask> asks) {
        this.name = name;
        this.asks = asks;
        this.typereport = "noreport";
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

    public String getTypereport() {
        return typereport;
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

    public void setTypereport(String typereport) {
        this.typereport = typereport;
    }

    public Ask getAsk(int position) {
        return this.asks.get(position);
    }
}
