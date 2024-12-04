package webservice;

import io.member.Member;
import io.member.MemberRepository;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.servlet.annotation.Mapping;

import java.util.List;

import static util.MyLogger.log;

public class MemberController {
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Mapping("/")
    public void home(HttpResponse response) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
            .append("<head>")
            .append("</head>")
            .append("<body>")
            .append("<h1>Member Manager</h1>")
            .append("<ul>")
                .append("<li><a href=\"/members\">Member List</a></li>")
                .append("<li><a href=\"/add-member-form\">Add member</a></li>")
            .append("</ul>")
            .append("</body>")
            .append("</html>");

        response.writeBody(sb.toString());
    }

    @Mapping("/members")
    public void members(HttpResponse response) {
        List<Member> members = memberRepository.findAll();

        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
            .append("<head>")
            .append("</head>")
            .append("<body>")
            .append("<h1>Member List</h1>")
            .append("<ul>");
        for (Member member : members) {
            sb.append("<li>")
                .append("ID: ").append(member.getId())
                .append(", Name: ").append(member.getName())
                .append(", Age: ").append(member.getAge())
                .append("</li>");
        }
        sb.append("</ul>")
            .append("<a href=\"/\">Back to Home</a>")
            .append("</body>")
            .append("</html>");

        response.writeBody(sb.toString());
    }

    @Mapping("/add-member-form")
    public void addMemberForm(HttpResponse response) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>")
                .append("<head>")
                .append("</head>")
                .append("<body>")
                .append("<h1>Add Member</h1>")
                .append("<form method=\"post\" action=\"/add-member\">")
                .append("<div>")
                    .append("<label for=\"name\">ID: </label>")
                    .append("<input type=\"text\" name=\"id\">")
                .append("</div>")
                .append("<div>")
                    .append("<label for=\"name\">Name: </label>")
                    .append("<input type=\"text\" name=\"name\">")
                .append("</div>")
                .append("<div>")
                    .append("<label for=\"age\">Age: </label>")
                    .append("<input type=\"text\" name=\"age\"><br>")
                .append("</div>")

                .append("<div>")
                .append("<input type=\"submit\" value=\"Add\"><br>")
                .append("</div>")
                .append("</form>")

                .append("<a href=\"/\">Back to Home</a>")
                .append("</body>");

        response.writeBody(sb.toString());
    }

    @Mapping("/add-member")
    public void addMember(HttpRequest request, HttpResponse response) {
        log("MemberController.addMember");
        log("request = " + request);

        String id = request.getQueryParameter("id");
        String name = request.getQueryParameter("name");
        int age = Integer.parseInt(request.getQueryParameter("age"));

        Member member = new Member(id, name, age);
        memberRepository.add(member);

        response.writeBody("<h1>member added successfully!</h1>");
        response.writeBody("<a href=\"/members\">Member List</a>");
    }
}
