import java.util.Scanner;

public class LibraryManager {
    public static void main(String[] args) {
        Book[] books = new Book[2];
        Member[] members = new Member[2];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < books.length; i++) {
            System.out.print("Enter Book Title: ");
            String title = sc.nextLine();
            System.out.print("Enter Author: ");
            String author = sc.nextLine();
            books[i] = new Book(title, author);
        }
        for (int i = 0; i < members.length; i++) {
            System.out.print("Enter Member Name: ");
            String name = sc.nextLine();
            members[i] = new Member(name);
        }
        members[0].borrowBook(books[0]);
        members[1].borrowBook(books[1]);
        members[0].returnBook(books[0].getBookId(), books);
        for (Book b : books) b.displayBookInfo();
        for (Member m : members) m.displayMemberInfo();
        sc.close();
    }
}

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private static int bookCounter = 1;
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.bookId = generateBookId();
        this.isAvailable = true;
    }
    private static String generateBookId() {
        return "B" + String.format("%03d", bookCounter++);
    }
    public boolean issueBook() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }
    public boolean returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            return true;
        }
        return false;
    }
    public void displayBookInfo() {
        System.out.printf("Book ID: %s, Title: %s, Author: %s, Available: %b\n", bookId, title, author, isAvailable);
    }
    public String getBookId() { return bookId; }
}

class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;
    private static int memberCounter = 1;
    public Member(String name) {
        this.memberName = name;
        this.memberId = generateMemberId();
        this.booksIssued = new String[5];
        this.bookCount = 0;
    }
    private static String generateMemberId() {
        return "M" + String.format("%03d", memberCounter++);
    }
    public boolean borrowBook(Book book) {
        if (book.issueBook() && bookCount < booksIssued.length) {
            booksIssued[bookCount++] = book.getBookId();
            return true;
        }
        return false;
    }
    public boolean returnBook(String bookId, Book[] books) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId) && book.returnBook()) {
                for (int i = 0; i < bookCount; i++) {
                    if (booksIssued[i] != null && booksIssued[i].equals(bookId)) {
                        booksIssued[i] = null;
                        bookCount--;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void displayMemberInfo() {
        System.out.printf("Member ID: %s, Name: %s, Books Issued: %d\n", memberId, memberName, bookCount);
    }
}
