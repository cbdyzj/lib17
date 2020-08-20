package draft.json;

public class Banana {

    private String type;

    private String color;

    public Banana(String type, String color) {
        this.type = type;
        this.color = color;
    }

    public Banana() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Banana{" +
                "type='" + type + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
