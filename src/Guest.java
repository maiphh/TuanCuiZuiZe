public class Guest extends User {
    public void displayByPrice() {
        (new ProductDatabase()).displayByPrice();
    }

    public void searchProductByCategory() {
        (new ProductDatabase()).displayByCategory();
    }

    public void viewAllProducts() {
        (new ProductDatabase()).displayAll();
    }
}
