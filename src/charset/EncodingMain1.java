package charset;

import java.nio.charset.Charset;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain1 {
    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== 영문 인코딩 ==");
        encoding("A", US_ASCII);
        encoding("A", ISO_8859_1);
        encoding("A", EUC_KR);
        encoding("A", MS949);
        encoding("A", UTF_8);
        encoding("A", UTF_16BE);

        System.out.printf("\n== 한글 인코딩 ==\n");
        encoding("가", EUC_KR);
        encoding("가", MS949);
        encoding("가", UTF_8);
        encoding("가", UTF_16BE);
    }

    private static void encoding(String s, Charset charset) {
        byte[] encoded = s.getBytes(charset);
        System.out.printf("[%15s] %s -> %-17s (%sbyte)\n", charset.name(), s, Arrays.toString(encoded), encoded.length);
    }
}
