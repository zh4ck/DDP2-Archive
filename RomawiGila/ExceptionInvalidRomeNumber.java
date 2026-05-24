public class ExceptionInvalidRomeNumber extends RuntimeException {
    public ExceptionInvalidRomeNumber(String s) {
        super(s);
    }

    public ExceptionInvalidRomeNumber(String s, Throwable t) {
        super(s, t);
    }
}
