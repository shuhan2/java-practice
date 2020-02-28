package exception;

public class MyClosableType implements AutoCloseable {
    private ClosableStateReference reference;

    public MyClosableType(ClosableStateReference reference) {

        this.reference = reference;
    }

    public boolean isClosed() {
        return reference.isClosed();
    }

    @Override
    public void close() {
        reference.close();
    }
}
