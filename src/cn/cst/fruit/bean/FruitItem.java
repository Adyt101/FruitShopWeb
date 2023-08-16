package cn.cst.fruit.bean;

public class FruitItem {
    String number;
    String name;
    String price;
    String unit;
    public FruitItem(){

    }
    public FruitItem(String number, String name, String price, String unit){
        this.number = number;
        this.name = name;
        this.price = price;
        this.unit =unit;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getUnit() {
        return unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}