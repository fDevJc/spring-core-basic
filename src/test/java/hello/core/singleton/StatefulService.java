package hello.core.singleton;

public class StatefulService {
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name);
        System.out.println("price = " + price);

        //문제의 코드
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
