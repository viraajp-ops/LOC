class DeliveryAgent {
    int id;
    Point location;
    DriverStatus status;

    public DeliveryAgent(int id, Point location) {
        this.id = id;
        this.location = location;
        this.status = DriverStatus.FREE;
    }

    public void assignOrder(Order order) {
        if (this.status == DriverStatus.FREE) {
            this.status = DriverStatus.BUSY;
            order.assignedDriver = this;
            order.updateStatus(OrderStatus.DEPLOYED);
        }
    }

    public void deliverOrder(Order order) {
        Point destination = order.getCustomerLocation();
        int x = location.x;
        int y = location.y;
        int destX = destination.x;
        int destY = destination.y;

        while (x != destX || y != destY) {
            if (x < destX) x++;
            else if (x > destX) x--;

            if (y < destY) y++;
            else if (y > destY) y--;

            this.location = new Point(x, y);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        order.updateStatus(OrderStatus.DELIVERED);
        this.status = DriverStatus.FREE;
    }

    public void setBusy(boolean busy) {
        this.status = busy ? DriverStatus.BUSY : DriverStatus.FREE;
    }

    public boolean isFree() {
        return this.status == DriverStatus.FREE;
    }
}

