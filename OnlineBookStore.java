import java.util.*;

class Book {
    private String title;
    private double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private List<Book> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Book book) {
        items.add(book);
    }

    public List<Book> getItems() {
        return items;
    }
}

class Order {
    private List<Book> items;
    private double totalPrice;

    public Order(List<Book> items) {
        this.items = items;
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        totalPrice = 0;
        for (Book book : items) {
            totalPrice += book.getPrice();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Book> getItems() {
        return items;
    }
}

public class OnlineBookStore {
    private static List<Book> bookCatalog = new ArrayList<>();

    public static void main(String[] args) {
        initializeCatalog();

        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        System.out.println("Welcome to the Online Book Store!");

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayBooks();
                    int bookIndex = scanner.nextInt();
                    if (bookIndex >= 0 && bookIndex < bookCatalog.size()) {
                        Book selectedBook = bookCatalog.get(bookIndex);
                        cart.addItem(selectedBook);
                        System.out.println(selectedBook.getTitle() + " added to cart.");
                    } else {
                        System.out.println("Invalid book selection.");
                    }
                    break;

                case 2:
                    displayCart(cart);
                    break;

                case 3:
                    placeOrder(cart);
                    cart = new ShoppingCart();
                    break;

                case 4:
                    System.out.println("Thank you for using the Online Book Store!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void initializeCatalog() {
        bookCatalog.add(new Book("Book 1", 20.0));
        bookCatalog.add(new Book("Book 2", 15.0));
        bookCatalog.add(new Book("Book 3", 25.0));
        // Add more books
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Browse Books");
        System.out.println("2. View Cart");
        System.out.println("3. Place Order");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayBooks() {
        System.out.println("\nAvailable Books:");
        for (int i = 0; i < bookCatalog.size(); i++) {
            Book book = bookCatalog.get(i);
            System.out.println(i + ". " + book.getTitle() + " - $" + book.getPrice());
        }
        System.out.print("Enter the number of the book to add to cart: ");
    }

    private static void displayCart(ShoppingCart cart) {
        List<Book> cartItems = cart.getItems();
        if (cartItems.isEmpty()) {
            System.out.println("\nYour cart is empty.");
        } else {
            System.out.println("\nYour Cart:");
            for (int i = 0; i < cartItems.size(); i++) {
                Book book = cartItems.get(i);
                System.out.println(i + ". " + book.getTitle() + " - $" + book.getPrice());
            }
        }
    }

    private static void placeOrder(ShoppingCart cart) {
        List<Book> cartItems = cart.getItems();
        if (cartItems.isEmpty()) {
            System.out.println("\nYour cart is empty. Cannot place an empty order.");
        } else {
            Order order = new Order(cartItems);
            System.out.println("\nOrder Details:");
            for (Book book : cartItems) {
                System.out.println("- " + book.getTitle() + " - $" + book.getPrice());
            }
            System.out.println("Total Price: $" + order.getTotalPrice());
            System.out.println("Order placed successfully!");
        }
    }
}

