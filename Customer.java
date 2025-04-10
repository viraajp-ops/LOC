
class Customer {
    private int id;
    private String name;
    private String password;
    private Point location;

    public Customer(int id, String name, String password, Point location) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.location = location;
    }

    public Order placeOrder(Restaurant restaurant, boolean isPriority, String itemName) {
        Order order = new Order(this, restaurant, isPriority, itemName);
        Dispatcher.getInstance().addOrder(order);
        return order;
    }

    public Point getLocation() {
        return this.location;
    }
}
