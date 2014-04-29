package hrquizer.app;

/**
 * Created by artem on 29.04.14.
 */
public class MyException extends Exception {

    public MyException() {}

    @Override
    public String toString() {
        return "Это ошибка";
    }
}
