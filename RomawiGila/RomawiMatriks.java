public class RomawiMatriks extends GenericMatrix<Romawi> {
    @Override
    protected Romawi add(Romawi o1, Romawi o2) {
        return new Romawi(o1.intValue() + o2.intValue());
    }

    @Override
    protected Romawi multiply(Romawi o1, Romawi o2) {
        return new Romawi(o1.intValue() * o2.intValue());
    }

    @Override
    protected Romawi zero() {
        return new Romawi(0);
    }
}