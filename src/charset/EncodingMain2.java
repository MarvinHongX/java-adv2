package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain2 {
    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== 영문 ASCII 호환성 ==");
        encodingAndDecoding("A", US_ASCII, US_ASCII);
        encodingAndDecoding("A", US_ASCII, ISO_8859_1); // LATIN-1
        encodingAndDecoding("A", US_ASCII, EUC_KR);
        encodingAndDecoding("A", US_ASCII, MS949);
        encodingAndDecoding("A", US_ASCII, UTF_8);
        encodingAndDecoding("A", US_ASCII, UTF_16BE); // 호환 안됨


        System.out.printf("\n== 한글 호환성 ==\n");
        encodingAndDecoding("가", US_ASCII, US_ASCII); // 한글 지원 안함
        encodingAndDecoding("가", ISO_8859_1, ISO_8859_1); // 한글 지원 안함

        encodingAndDecoding("가", EUC_KR, EUC_KR);
        encodingAndDecoding("가", EUC_KR, MS949);
        encodingAndDecoding("가", EUC_KR, UTF_8); // 호환 안됨
        encodingAndDecoding("가", EUC_KR, UTF_16BE); // 호환 안됨

        encodingAndDecoding("가", MS949, MS949);
        encodingAndDecoding("가", MS949, EUC_KR);
        encodingAndDecoding("가", MS949, UTF_8); // 호환 안됨
        encodingAndDecoding("가", MS949, UTF_16BE); // 호환 안됨

        encodingAndDecoding("가", UTF_8, UTF_8);
        encodingAndDecoding("가", UTF_8, EUC_KR); // 호환 안됨
        encodingAndDecoding("가", UTF_8, MS949); // 호환 안됨
        encodingAndDecoding("가", UTF_8, UTF_16BE); // 호환 안됨

        encodingAndDecoding("가", UTF_16BE, UTF_16BE);
        encodingAndDecoding("가", UTF_16BE, EUC_KR); // 호환 안됨
        encodingAndDecoding("가", UTF_16BE, MS949); // 호환 안됨
        encodingAndDecoding("가", UTF_16BE, UTF_8); // 호환 안됨

        System.out.printf("\n== 한글 호환성(복잡한 문자) ==\n");
        encodingAndDecoding("뷁", EUC_KR, EUC_KR); // 복잡한 한글 지원 안함
        encodingAndDecoding("뷁", MS949, MS949);

        encodingAndDecoding("뷁", UTF_8, UTF_8);
        encodingAndDecoding("뷁", UTF_16BE, UTF_16BE);
    }

    private static void encodingAndDecoding(String s, Charset encodingCharest, Charset decodingCharset) {
        byte[] encoded = encoding(s, encodingCharest);
        String decoded = decoding(encoded, decodingCharset);


        System.out.printf("[%15s -> %-15s]: %s -> %s -> %s\n", encodingCharest.name(), decodingCharset.name(), s, Arrays.toString(encoded), decoded);
    }


    private static byte[] encoding(String s, Charset charset) {
        byte[] encoded = s.getBytes(charset);
        return encoded;
    }

    private static String decoding(byte[] b, Charset charset) {
        String decoded = new String(b, charset);
        return decoded;
    }
}
