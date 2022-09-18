public class Product {
    private String pID;
    private String name;
    private String description;
    private String category;
    private Double price;
    private int quantity;

    // private static int currentID = (new ProductDatabase()).getCount();
    private static int currentID = 0;
    // private static int currentID = 0;

    public Product() {
    }

    public void setID(String pID) {
        this.pID = pID;
    }

    public Product(String name, String description, String category, String price, String quantity) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = Double.parseDouble(price);
        this.quantity = Integer.parseInt(quantity);
        currentID++;
        this.pID = "p" + Integer.toString(currentID);
    }

    public Product(String[] productData) {
        this.pID = productData[0];
        this.name = productData[1];
        this.description = productData[2];
        this.category = productData[3];
        this.price = Double.parseDouble(productData[4]);
        this.quantity = Integer.parseInt(productData[5]);
        currentID++;
    }

    public void displayGeneralInfo() {
        System.out.printf("%s %s %.2f\n", this.pID, this.name, this.price);
    }

    public void displayDetailInfo() {
    }

    public Double getPrice() {
        return this.price;
    }

    public String getID() {
        return this.pID;
    }

    public String getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity -= quantity;
    }

    @Override
    public String toString() {
        return pID + "," + name + "," + description + "," + category + "," + String.format("%.2f", price) + ","
                + quantity;
    }

    // For admin only
    // updatePrice()
}
