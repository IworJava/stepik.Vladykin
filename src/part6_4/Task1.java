package part6_4;

import java.util.stream.IntStream;

public class Task1 {
    public static void main(String[] args) {
        pseudoRandomStream(13)
                .limit(10)
                .forEach(System.out::println);
    }

    public static IntStream pseudoRandomStream(int seed) {
        return IntStream.iterate(seed, n -> (n * n / 10) % 1000);
    }
}
