public class ProductManager {
    public static void main(String[] args) {
        Product[] products = new Product[2];
        products[0] = new Product("P001", "Pen", 10, 50, "SupplierA", "Stationery");
        products[1] = new Product("P002", "Notebook", 50, 5, "SupplierB", "Stationery");
        products[0].reduceStock(45);
        products[1].addStock(10);
        for (Product p : products) p.displayProductInfo();
        System.out.println("Total Inventory Value: " + Product.calculateTotalInventoryValue(products));
        System.out.println("Low Stock Products:");
        Product.findLowStockProducts(products);
    }
}

class Product {
    private String productId;
    private String productName;
    private double price;
    private int quantity;
    private String supplierName;
    private String category;
    public Product(String id, String name, double price, int qty, String supplier, String category) {
        this.productId = id;
        this.productName = name;
        this.price = price;
        this.quantity = qty;
        this.supplierName = supplier;
        this.category = category;
    }
    public void addStock(int qty) {
        if (qty > 0) quantity += qty;
    }
    public void reduceStock(int qty) {
        if (qty > 0 && quantity >= qty) quantity -= qty;
    }
    public boolean isLowStock() {
        return quantity < 10;
    }
    public double calculateProductValue() {
        return price * quantity;
    }
    public void updatePrice(double newPrice) {
        if (newPrice > 0) price = newPrice;
    }
    public void displayProductInfo() {
        System.out.printf("ID: %s, Name: %s, Price: %.2f, Qty: %d, Supplier: %s, Category: %s, LowStock: %b\n",
            productId, productName, price, quantity, supplierName, category, isLowStock());
    }
    public static double calculateTotalInventoryValue(Product[] products) {
        double total = 0;
        for (Product p : products) total += p.calculateProductValue();
        return total;
    }
    public static void findLowStockProducts(Product[] products) {
        for (Product p : products) {
            if (p.isLowStock()) p.displayProductInfo();
        }
    }
}
