package mqtest.thread2.threadlocal;

public class Context {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Context{" +
                "value='" + value + '\'' +
                '}';
    }
}
