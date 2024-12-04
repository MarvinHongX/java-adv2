package network.tcp.autocloseable;

public class ResourceCloseMainV4 {
    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외처리"); // CallException (close 도중에 생긴 부가적인 예외들은 suppressed 에 담김)
            Throwable[] suppressed = e.getSuppressed();
            for (Throwable throwable : suppressed) {
                System.out.println("suppressedEx = " + throwable);
            }
            e.printStackTrace();
        } catch (CloseException e) {
            System.out.println("CloseException 예외처리");
            e.printStackTrace();
        }
    }

    private static void logic() throws CallException, CloseException {
        // try with resources 는 try 이후 바로 자원을 정리함. 그 후 catch 호출.
        try (ResourceV2 resource1 = new ResourceV2("resource1");
             ResourceV2 resource2 = new ResourceV2("resource2")) {
            resource1.call();
            resource2.callEx();  // CallException
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        }

    }
}
