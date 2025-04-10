class Dispatcher {
    private static Dispatcher instance = null;
    List<Order> orders = new ArrayList<>();
    List<DeliveryAgent> drivers = new ArrayList<>();
    List<Restaurant> restaurants = new ArrayList<>();

    private Dispatcher() {}

    public static Dispatcher getInstance() {
        if (instance == null) instance = new Dispatcher();
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addDriver(DeliveryAgent driver) {
        drivers.add(driver);
    }

    public void addRestaurant(Restaurant r) {
        restaurants.add(r);
    }

    public void dispatchOrders() {
        List<Order> priority = orders.stream().filter(o -> o.isPriority && o.status == OrderStatus.ACCEPTED).toList();
        List<Order> normal = orders.stream().filter(o -> !o.isPriority && o.status == OrderStatus.ACCEPTED).toList();

        List<Order> all = new ArrayList<>();
        all.addAll(priority);
        all.addAll(normal);

        for (Order order : all) {
            DeliveryAgent nearest = null;
            double minDist = Double.MAX_VALUE;
            for (DeliveryAgent driver : drivers) {
                if (driver.isFree()) {
                    double dist = Point.distance(driver.location, order.restaurant.location);
                    if (dist < minDist) {
                        minDist = dist;
                        nearest = driver;
                    }
                }
            }

            if (nearest != null) {
                nearest.assignOrder(order);
                nearest.deliverOrder(order);
            }
        }
    }
}
