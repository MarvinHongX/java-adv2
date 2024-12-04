package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsetsMain {
    public static void main(String[] args) {

        System.out.println("== 사용 가능한 charset (자바 + os) ==");
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (String charset : charsets.keySet()) {
            System.out.println("charset = " + charset);
        }

        System.out.printf("\n== 이름으로 charset 조회(ms949) ==\n");
        Charset ms949 = Charset.forName("MS949");
        System.out.println("Charset.forName(\"MS949\") = " + ms949);
        Set<String> aliases = ms949.aliases();
        for (String alias : aliases) {
            System.out.println("ms949's alias = " + alias);
        }

        System.out.printf("\n== 이름으로 charset 조회(utf-8) ==\n");
        Charset utf8 = Charset.forName("UTF-8");
        System.out.println("Charset.forName(\"UTF-8\") = " + utf8);

        System.out.printf("\n== 상수로 charset 조회(utf-8) ==\n");
        Charset utf_8 = StandardCharsets.UTF_8;
        System.out.println("StandardCharsets.UTF_8 = " + utf_8);

        System.out.printf("\n== 시스템의 default charset 조회 ==\n");
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("Charset.defaultCharset = " + defaultCharset);
    }
}
