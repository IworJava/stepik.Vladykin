package part5_2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Checksum {
    public static void main(String[] args) throws IOException {
        System.out.println( checkSumOfStream(
                new ByteArrayInputStream(new byte[]{0x33, 0x45, 0x01}))
        );
    }

    /**
     * Method calculates checksum of input data.
     * Checksum of three bytes 0x33 0x45 0x01 must equal 71.
     *
     * @param inputStream   input data
     * @return              checksum
     */
    public static int checkSumOfStream(InputStream inputStream) throws IOException {
        int sum = 0;
        int read;
        while ((read = inputStream.read()) >= 0) {
            sum = Integer.rotateLeft(sum, 1) ^ read;
        }
        return sum;
    }
}
