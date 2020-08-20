package draft.closure;

@FunctionalInterface
public interface F {

    default F apply(int v) {
        return () -> this.get() + v * v;
    }

    int get();
}
