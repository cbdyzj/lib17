package org.jianzhao.boot.util;

/**
 * Wrapper checked exception
 *
 * @see lombok.SneakyThrows
 */
public final class ExceptionWrapper {

    private ExceptionWrapper() {
    }

    public static <T> T apply(Action<T> action) {
        try {
            return action.apply();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void apply(VoidAction action) {
        try {
            action.apply();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @FunctionalInterface
    public interface Action<T> {

        T apply() throws Exception;
    }

    @FunctionalInterface
    public interface VoidAction {

        void apply() throws Exception;
    }
}
