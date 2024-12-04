package network.tcp.autocloseable;

public class ResourceCloseMainV1 {
    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외처리");
            e.printStackTrace();
        } catch (CloseException e) {
            System.out.println("CloseException 예외처리");
            e.printStackTrace();
        }
    }

    private static void logic() throws CallException, CloseException {
        ResourceV1 resource1 = new ResourceV1("resource1");
        ResourceV1 resource2 = new ResourceV1("resource2");

        resource1.call();
        resource2.callEx(); // CallException

        System.out.println("자원 정리"); // 아래는 호출이 안됨
        resource2.closeEx();
        resource1.closeEx();
    }
}