package hrquizer.app;

/**
 * Created by artem on 29.04.14.
 */
public class MinusInCodeException extends Exception {

    public MinusInCodeException() {}

    @Override
    public String toString() {
        return "Это ошибка";
    }
}
