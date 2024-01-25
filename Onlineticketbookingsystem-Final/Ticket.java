public class Ticket {
    private int ticketId;
    private Customer customer;
    private Seat seat;
    private double price;

    public Ticket(int ticketId, Customer customer, Seat seat, double price) {
        this.ticketId = ticketId;
        this.customer = customer;
        this.seat = seat;
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Seat getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }
}

