package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    //조회할때는 getInstance로 호출, 외부에서 호출되지 않게 private으로 설정
    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
