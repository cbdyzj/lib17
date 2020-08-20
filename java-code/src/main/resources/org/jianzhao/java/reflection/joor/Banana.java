package draft.reflection.joor;

import draft.reflection.joor.Fruit;

public class Banana extends Fruit {

    private Float price;

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Banana{" +
                "price='" + price + '\'' +
                "} " + super.toString();
    }
}
