package network.tcp.autocloseable;

public class ResourceCloseMainV2 {
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
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;

        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callEx(); // CallException
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        } finally {
            System.out.println("자원 정리");
            if (resource2 != null) {
                resource2.closeEx(); // CloseException (핵심 예외인 CallException 이 사라짐...)
            }
            if (resource1 != null) {
                resource1.closeEx(); // 호출이 (또) 안됨..
            }
        }
    }
}
