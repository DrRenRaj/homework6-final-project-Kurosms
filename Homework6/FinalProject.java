import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Availability: " + (isAvailable ? "Available" : "Checked Out");
    }
}

class Library {
    private ArrayList<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        for (Book checkedBook : books) {
            if (checkedBook.getIsbn().equals(book.getIsbn())) {
                return false; // Return false if ISBN already exists
            }
        }
        books.add(book);
        return true; // Return true if book was added successfully
    }

    public boolean removeBook(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean displayAllBooks() {
        if (books.isEmpty()) {
            return false;
        }
        System.out.println("All Books: ");
        for (Book book : books) {
            System.out.println(book);
        }
        return true;
    }

    public ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> results = new ArrayList<>();
        String searchTerm = title.toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(searchTerm)) {
                results.add(book);
            }
        }
        return results;
    }

    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> results = new ArrayList<>();
        String searchTerm = author.toLowerCase();

        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(searchTerm)) {
                results.add(book);
            }
        }
        return results;
    }

    public boolean checkOutBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Pre-populate the library with some books
        library.addBook(new Book("The Hunger Games", "Suzanne Collins", "246"));
        library.addBook(new Book("Diary of a Wimpy Kid", "Jeff Kinney", "135"));

        boolean running = true;
        while (running) {
            System.out.println("\nLibrary Menu: ");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Search by Title");
            System.out.println("5. Search by Author");
            System.out.println("6. Check Out Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            if (choice == 1) {
                addBook();
            } else if (choice == 2) {
                removeBook();
            } else if (choice == 3) {
                if (!library.displayAllBooks()) {
                    System.out.println("Library is Empty.");
                }
            } else if (choice == 4) {
                searchByTitle();
            } else if (choice == 5) {
                searchByAuthor();
            } else if (choice == 6) {
                checkOutBook();
            } else if (choice == 7) {
                returnBook();
            } else if (choice == 8) {
                running = false;
                System.out.println("You have exited the library menu.");
            } else {
                System.out.println("Invalid Choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void addBook() {
        System.out.print("Enter a Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter an Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        if (library.addBook(new Book(title, author, isbn))) {
            System.out.println("Book has been added successfully.");
        } else {
            System.out.println("ISBN already exists. Book not added.");
        }
    }

    private static void removeBook() {
        System.out.print("Enter ISBN to remove: ");
        String isbn = scanner.nextLine();

        if (library.removeBook(isbn)) {
            System.out.println("Book has been removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void searchByTitle() {
        System.out.print("Enter a Title to search: ");
        String title = scanner.nextLine();

        ArrayList<Book> results = library.searchByTitle(title);
        displayResults(results);
    }

    private static void searchByAuthor() {
        System.out.print("Enter an author to search: ");
        String author = scanner.nextLine();

        ArrayList<Book> results = library.searchByAuthor(author);
        displayResults(results);
    }

    private static void displayResults(ArrayList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Results: ");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private static void checkOutBook() {
        System.out.print("Enter ISBN to check out: ");
        String isbn = scanner.nextLine();

        if (library.checkOutBook(isbn)) {
            System.out.println("Book checked out successfully.");
        } else {
            System.out.println("Book not found or already checked out.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter ISBN to return: ");
        String isbn = scanner.nextLine();

        if (library.returnBook(isbn)) {
            System.out.println("Book has been returned successfully.");
        } else {
            System.out.println("Book not found or already returned.");
        }    
    }
}