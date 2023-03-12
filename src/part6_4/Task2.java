package part6_4;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 {
    public static void main(String[] args) {
        findMinMax1(
                Stream.of(new A(4), new A(9), new A(3), new A(6)),
                // Stream.empty(),
                (a, b) -> a == null ? 0 : ((A) b).b.i - ((A) a).b.i,
                (min, max) -> System.out.printf("min: %s; max: %s", min, max)
        );
    }

    public static <T> void findMinMax1(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        List<T> list = stream.sorted(order)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            minMaxConsumer.accept(null, null);
        } else {
            minMaxConsumer.accept(list.get(0), list.get(list.size() - 1));
        }
    }

    public static <T> void findMinMax2(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        MinMax<T> minMax = new MinMax<>();
        stream.sorted(order)
                .forEach(minMax);
        minMaxConsumer.accept(minMax.min, minMax.max);
    }
    static class MinMax<T> implements Consumer<T> {
        T min;
        T max;

        @Override
        public void accept(T t) {
            if (min == null) {
                min = t;
            }
            max = t;
        }
    }
}

class A {
    B b;

    public A(int i) {
        b = new B(i);
    }

    @Override
    public String toString() {
        return "A(" + b + ")";
    }
}
class B {
    int i;

    public B(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return String.valueOf(i);
    }
}
