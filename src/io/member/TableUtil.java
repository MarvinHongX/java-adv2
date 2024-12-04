package io.member;

import java.util.List;

public class TableUtil {
    // 문자열의 실제 출력 폭 계산 (한글은 2폭, 나머지는 1폭)
    public static int getDisplayWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            width += (c >= '\uAC00' && c <= '\uD7A3') ? 2 : 1; // 한글은 2폭
        }
        return width;
    }

    // 좌측 정렬
    public static String leftAlign(String text, int width) {
        int displayWidth = getDisplayWidth(text);
        if (displayWidth >= width) return text;
        return text + " ".repeat(width - displayWidth);
    }

    // 우측 정렬
    public static String rightAlign(String text, int width) {
        int displayWidth = getDisplayWidth(text);
        if (displayWidth >= width) return text;
        return " ".repeat(width - displayWidth) + text;
    }

    // 가운데 정렬
    public static String centerAlign(String text, int width) {
        int displayWidth = getDisplayWidth(text);
        if (displayWidth >= width) return text;
        int padding = (width - displayWidth) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - padding - displayWidth);
    }

    // 테이블 출력
    public static void printTable(List<Member> members, int idWidth, int nameWidth, int ageWidth) {
        String columnHeader = String.format("|%s|%s|%s|",
                centerAlign("ID", idWidth),
                centerAlign("Name", nameWidth),
                centerAlign("Age", ageWidth));

        String divider = String.format("+%s+%s+%s+",
                "-".repeat(idWidth),
                "-".repeat(nameWidth),
                "-".repeat(ageWidth));

        String dividerMiddle = String.format("|%s+%s+%s|",
                "=".repeat(idWidth),
                "=".repeat(nameWidth),
                "=".repeat(ageWidth));

        System.out.println(divider);
        System.out.println(columnHeader);
        System.out.println(dividerMiddle);
        for (Member member : members) {
            String row = String.format("|%s|%s|%s|",
                    leftAlign(member.getId(), idWidth),
                    leftAlign(member.getName(), nameWidth),
                    rightAlign(Integer.toString(member.getAge()), ageWidth));
            System.out.println(row);
        }
        System.out.println(divider);
    }
}
