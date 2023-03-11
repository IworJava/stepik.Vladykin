package part6_2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Task1 {
    public static void main(String[] args) {
        Set<Integer> set = symmetricDifference(
                new HashSet<>(Arrays.asList(1, 2, 3)),
                new HashSet<>(Arrays.asList(0, 1, 2))
        );
        System.out.println(set);
    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> temp = new HashSet<>(set1);
        temp.retainAll(set2);

        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        result.removeAll(temp);

        return result;
    }
}
