package part5_2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        // try (InputStream is = new ByteArrayInputStream(new byte[]{65, 13, 10, 10, 13, 77})) {
        try (InputStream is = System.in) {
            int prev = is.read();
            int next;
            while (prev >= 0) {
                next = is.read();
                if (prev != 13 || next != 10) {
                    System.out.write(prev);
                }
                prev = next;
            }
            System.out.flush();
        }
    }
}
