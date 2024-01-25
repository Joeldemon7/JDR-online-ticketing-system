import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Customer {
    private int ID;
    private String name;
    private String address;
    private String email;
    private String password;
    private List<Ticket> selectedSeats;

    public Customer(int ID, String name, String address, String email, String password) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.selectedSeats = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Ticket> getSelectedSeats() {
        return selectedSeats;
    }

    public void selectSeat(Ticket ticket) {
        selectedSeats.add(ticket);
    }

    public void purchaseTicket(Ticket ticket) {
        selectedSeats.remove(ticket);
    }

    public void viewSelectedSeats() {
        System.out.println("Selected Seats:");
        for (Ticket ticket : selectedSeats) {
            System.out.println("Ticket ID: " + ticket.getTicketId() +
                    ", Seat Number: " + ticket.getSeat().getSeatNumber() +
                    ", Price: £" + ticket.getPrice());
        }
    }
}





