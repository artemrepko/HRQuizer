package hrquizer.app;

/**
 * Created by artem on 27.04.14.
 */
public class Category {

    private String name;

    private int id;

    public Category() {}

    public Category(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
