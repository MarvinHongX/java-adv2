package anotation.java;

public class OverrideMain {
    static class A {
        public void call() {
            System.out.println("A.call()");
        }
    }

    static class B extends A {
//        @Override // 주석을 풀면 오버라이드 할 메서드 이름을 오타 내더라도 컴파일 시점에서 찾아준다.
        public void calllll() {
            System.out.println("B.call()");
        }
    }

    public static void main(String[] args) {
        A a = new B();
        a.call();
    }
}
