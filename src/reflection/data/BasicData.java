package reflection.data;

public class BasicData {
    public String publicFiled;
    private int privateFiled;

    public BasicData() {
        System.out.println("BasicData.BasicData()");
    }

    private BasicData(String data) {
        System.out.println("BasicData.BasicData(data): " + data);
    }

    public void call() {
        System.out.println("BasicData.call()");
    }

    public String hello(String str) {
        System.out.println("BasicData.hello(str): " + str);
        return str + " hello";
    }

    private void privateMethod() {
        System.out.println("BasicData.privateMethod()");
    }

    void defaultMethod() {
        System.out.println("BasicData.defaultMethod()");
    }

    protected void protectedMethod() {
        System.out.println("BasicData.protectedMethod()");
    }
}
