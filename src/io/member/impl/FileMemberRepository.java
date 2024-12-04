package io.member.impl;

import io.member.Member;
import io.member.MemberRepository;

import java.io.*;

import static java.nio.charset.StandardCharsets.*;

import java.util.ArrayList;
import java.util.List;

public class FileMemberRepository implements MemberRepository {
    private static final String FILE_PATH = "temp/members-txt.dat";
    private static final String DELIMITER_ENCODING = "|";
    private static final String DELIMITER_DECODING = "[|]";

    @Override
    public void add(Member member) {
        // try-with-resources 이므로 내부에서 bw.close() 해준다.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, UTF_8, true))) {
            bw.write(member.getId() + DELIMITER_ENCODING + member.getName() + DELIMITER_ENCODING + member.getAge());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> findAll() {
        ArrayList<Member> members = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH, UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] memberData = line.split(DELIMITER_DECODING);

                int age = Integer.parseInt(memberData[2].trim());
                members.add(new Member(memberData[0], memberData[1], age));
            }
            return members;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
