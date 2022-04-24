package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    /**
     * private 생성자로 다른곳에서 생성못하도록
     */
    private SingletonService() {
    }

}
