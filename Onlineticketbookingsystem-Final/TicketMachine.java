import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TicketMachine {
    private static Map<Integer, Customer> customers = new HashMap<>();
    private static Map<Integer, Seat> seats = new HashMap<>();
    private static Map<String, Ticket> tickets = new HashMap<>();
    private static int customerIdCounter = 1;
    private static int seatNumberCounter = 1;
    private static int ticketIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize seats
        initializeSeats();

        while (true) {
            displayMenu();

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    selectSeat(scanner);
                    break;
                case 3:
                    reserveTicket(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void initializeSeats() {
        for (int i = 1; i <= 10; i++) {
            seats.put(i, new Seat(i));
        }
    }

    private static void displayMenu() {
        System.out.println("1. Create Account");
        System.out.println("2. Select a Seat");
        System.out.println("3. Reserve Ticket");
        System.out.println("4. Exit");
        System.out.println("Enter your choice:");
    }

    private static void createAccount(Scanner scanner) {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your address:");
        String address = scanner.nextLine();

        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        Customer newCustomer = new Customer(customerIdCounter, name, address, email, password);
        customers.put(customerIdCounter, newCustomer);

        System.out.println("Account created successfully!");
        customerIdCounter++;
    }

    private static void selectSeat(Scanner scanner) {
        System.out.println("Enter your customer ID:");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        if (customers.containsKey(customerId)) {
            Customer customer = customers.get(customerId);

            System.out.println("Available Seats:");
            displayAvailableSeats();

            System.out.println("Enter the seat number you want to select:");
            int seatNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (seats.containsKey(seatNumber) && !seats.get(seatNumber).isBooked()) {
                Seat selectedSeat = seats.get(seatNumber);
                customer.selectSeat(new Ticket(ticketIdCounter++, customer, selectedSeat, 50.0));
                System.out.println("Seat selected successfully!");
            } else {
                System.out.println("Invalid seat selection. Please try again.");
            }
        } else {
            System.out.println("Invalid customer ID. Please create an account first.");
        }
    }
    
    private static void reserveTicket(Scanner scanner) {
    System.out.println("Enter your customer ID:");
    int customerId = scanner.nextInt();
    scanner.nextLine();

    if (customers.containsKey(customerId)) {
        Customer customer = customers.get(customerId);
        customer.viewSelectedSeats();
        System.out.println("Do you want to reserve the selected seat? (yes/no)");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            Ticket selectedTicket = customer.getSelectedSeats().get(customer.getSelectedSeats().size() - 1);
            if (selectedTicket != null) {
                selectedTicket.getSeat().bookSeat();
                tickets.put(customer.getEmail(), selectedTicket);
                customer.purchaseTicket(selectedTicket);

                // Print customer details after ticket reservation
                System.out.println("Customer Details:");
                System.out.println("Customer ID: " + customer.getID());
                System.out.println("Customer Name: " + customer.getName());
                System.out.println("Selected Seat: " + selectedTicket.getSeat().getSeatNumber());
                System.out.println("Ticket Price: £" + selectedTicket.getPrice());

                System.out.println("Ticket reserved successfully!");
            } else {
                System.out.println("No seat selected. Please select a seat first.");
            }
        } else {
            System.out.println("Reservation canceled.");
        }
    } else {
        System.out.println("Invalid customer ID. Please create an account first.");
    }
}


    private static void displayAvailableSeats() {
        for (Map.Entry<Integer, Seat> entry : seats.entrySet()) {
            if (!entry.getValue().isBooked()) {
                System.out.println("Seat Number: " + entry.getKey());
            }
        }
    }
}









