package part6_1;

public class Main {
    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst();
        String s = pair.getSecond();

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2);
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode();

        System.out.println(i);              // 1
        System.out.println(s);              // "hello"
        System.out.println(mustBeTrue);     // true
        System.out.println(mustAlsoBeTrue); // true
    }
}
