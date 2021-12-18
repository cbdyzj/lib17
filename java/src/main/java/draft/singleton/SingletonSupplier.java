package draft.singleton;

import java.util.Objects;
import java.util.function.Supplier;

public class SingletonSupplier<T> implements Supplier<T> {

    private volatile T instance;

    private Supplier<T> supplier;

    public SingletonSupplier(Supplier<T> supplier) {
        this.supplier = Objects.requireNonNull(supplier);
    }

    @Override
    public T get() {
        if (this.instance == null) {
            synchronized (this) {
                if (this.instance == null) {
                    this.instance = this.supplier.get();
                }
            }
        }
        return this.instance;
    }
}
