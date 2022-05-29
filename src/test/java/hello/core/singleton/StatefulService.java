package hello.core.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    // 무상태하게 작성하려면 상태를 유지하는 필드를 공유하지 않도록 한다.
//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        return; price;
//    }

    public int getPrice() {
        return price;
    }

}
