class Restaurant {
    int id;
    String name;
    Point location;
    Map<String, Integer> menu;

    public Restaurant(int id, String name, Point location, Map<String, Integer> menu) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.menu = menu;
    }

    public boolean acceptOrder(Order order) {
        order.updateStatus(OrderStatus.ACCEPTED);
        return true;
    }
}
