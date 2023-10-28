package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); // 자기자신을 내부에 스테틱으로...

    // static 영역에 요거 하나만 딱 만들어져서 올라감

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
