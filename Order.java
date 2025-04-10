
class Order {
    static int counter = 1;
    int id;
    private Customer customer;
    Restaurant restaurant;
    boolean isPriority;
    OrderStatus status;
    DeliveryAgent assignedDriver;
    String itemName;

    public Order(Customer customer, Restaurant restaurant, boolean isPriority, String itemName) {
        this.id = counter++;
        this.customer = customer;
        this.restaurant = restaurant;
        this.isPriority = isPriority;
        this.status = OrderStatus.INITIATED;
        this.itemName = itemName;
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    public double calculateFee() {
        Integer basePrice = restaurant.menu.get(itemName);
        if (basePrice == null) return 0.0;

        double distance = Point.distance(customer.getLocation(), restaurant.location);
        double slope = 1.70 ; // calculated from regression
        double intercept = 19.78; // calculated from regression
        double deliveryCharge = slope * distance + intercept;

        double total = basePrice + deliveryCharge;
        return isPriority ? total * 1.5 : total;
    }

    public Point getCustomerLocation() {
        return customer.getLocation();
    }
}
