public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Dispatcher dispatcher = Dispatcher.getInstance();

        Map<String, Integer> menu1 = new HashMap<>();
        menu1.put("Pizza", 250);
        menu1.put("Burger", 150);
        Restaurant r1 = new Restaurant(1, "Domino's", new Point(3, 4), menu1);
        dispatcher.addRestaurant(r1);

        Map<String, Integer> menu2 = new HashMap<>();
        menu2.put("Pasta", 200);
        menu2.put("Salad", 100);
        Restaurant r2 = new Restaurant(2, "Olive Bistro", new Point(7, 1), menu2);
        dispatcher.addRestaurant(r2);

        DeliveryAgent d1 = new DeliveryAgent(1, new Point(0, 0));
        DeliveryAgent d2 = new DeliveryAgent(2, new Point(5, 5));
        dispatcher.addDriver(d1);
        dispatcher.addDriver(d2);

        Customer c1 = new Customer(1, "Alice", "pass123", new Point(10, 10));
        Customer c2 = new Customer(2, "Bob", "secret456", new Point(2, 3));

        while (true) {
            System.out.println("Enter 1 to place order, 2 to dispatch orders, 0 to exit: ");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("Enter customer id (1 or 2): ");
                int cid = scanner.nextInt();
                Customer customer = (cid == 1) ? c1 : c2;

                System.out.println("Enter restaurant id (1 or 2): ");
                int rid = scanner.nextInt();
                Restaurant restaurant = (rid == 1) ? r1 : r2;

                System.out.println("Menu: " + restaurant.menu.keySet());
                System.out.println("Enter item name: ");
                String itemName = scanner.next();

                System.out.println("Priority order? (true/false): ");
                boolean priority = scanner.nextBoolean();

                Order order = customer.placeOrder(restaurant, priority, itemName);
                System.out.println("Order placed. Total fee: " + order.calculateFee());

            } else if (choice == 2) {
                dispatcher.dispatchOrders();
            } else if (choice == 0) {
                break;
            }
        }

        scanner.close();
    }
}