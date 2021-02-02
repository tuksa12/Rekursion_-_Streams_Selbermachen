package eidi.recursion;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface MyStream<T> {
    Pair<T> eval();

    class Pair<T> {
        T value;
        MyStream<T> rest;

        public Pair(T value, MyStream<T> rest) {
            this.value = value;
            this.rest = rest;
        }
    }

    static <T> MyStream<T> empty() {
        return () -> null;
    }

    static <T> MyStream<T> of(T x) {
        return () -> new Pair<T>(x, empty());
    }

    static <T> MyStream<T> iterate(T seed, Function<T, T> f) {
        // TODO
        return null;
    }

    default MyStream<T> filter(Predicate<? super T> p) {
        return () -> {
            Pair<T> pair = eval();
            if (pair == null)
                return null;
            if (p.test(pair.value))
                return new Pair<T>(pair.value, pair.rest.filter(p));
            else
                return pair.rest.filter(p).eval();
        };
    }

    default <S> MyStream<S> map(Function<? super T, ? extends S> f) {
        return () -> {
            Pair<T> pair = eval();
            if (pair == null)
                return null;
            return new Pair<S>(f.apply(pair.value), pair.rest.map(f));
        };
    }

    default void forEach(Consumer<? super T> action) {
        for (Pair<T> pair = eval(); pair != null; pair = pair.rest.eval()) {
            action.accept(pair.value);
        }
    }

    default MyStream<T> dropWhile(Predicate<? super T> p) {
        // TODO
        return null;
    }

    default MyStream<T> takeWhile(Predicate<? super T> p) {
        // TODO
        return null;
    }

}
