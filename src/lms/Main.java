
package lms;

import lms.exception.ItemNotAvailableException;
import lms.exception.MemberNotFoundException;

import lms.model.Book;
import lms.model.DVD;
import lms.model.Magazine;
import lms.model.Staff;
import lms.model.Student;

import lms.service.Library;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        Book book = new Book(
                "B001", "Clean Code", "Robert C. Martin");

        DVD dvd = new DVD(
                "D001", "Introduction to Algorithms", 120);

        Magazine magazine = new Magazine(
                "M001", "Software Weekly", 25);

        Book headFirstJava = new Book(
                "B004", "Head First Java", "Kathy Sierra");

        library.addItem(book);
        library.addItem(dvd);
        library.addItem(magazine);
        library.addItem(headFirstJava);

        Student student = new Student(
                "S001", "Nadeesha Perera");

        Staff staff = new Staff(
                "ST001", "Mr. Kasun Silva");

        library.addMember(student);
        library.addMember(staff);

        System.out.println("\n--- Scenario 1: Successful Borrowing ---");
        attempt(library, "B001", "S001");

        System.out.println("\n--- Scenario 2: Already on Loan ---");
        attempt(library, "B001", "ST001");

        System.out.println("\n--- Scenario 3: Unknown Member ---");
        attempt(library, "B004", "M999");

        System.out.println("\n--- Scenario 4: Unknown Item ---");
        attempt(library, "X404", "S001");

        System.out.println("\n--- Scenario 5: Item Not on Loan ---");

        try {
            library.returnItem("D001");

        } catch (ItemNotAvailableException e) {
            System.out.println("Cannot return: " + e.getMessage());
        }

        System.out.println("\n--- Scenario 6: Invalid Book ---");

        try {
            Book invalidBook = new Book(
                    "B002", " ", "Someone");

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid book: " + e.getMessage());
        }

        System.out.println("\n--- Scenario 7: Borrowing Limit ---");

        attempt(library, "D001", "S001");
        attempt(library, "M001", "S001");
        attempt(library, "B004", "S001");

        System.out.println("\n--- Scenario 8: Returning Frees a Slot ---");

        try {
            library.returnItem("B001");

        } catch (ItemNotAvailableException e) {
            System.out.println("Cannot return: " + e.getMessage());
        }

        attempt(library, "B004", "S001");

        System.out.println("\n--- Reservations ---");

        library.reserveIfPossible(book, staff);
        library.reserveIfPossible(dvd, staff);

        System.out.println("\n--- Members Ranked by Activity ---");

        library.printMembersByActivity();
    }

    private static void attempt(
            Library library,
            String itemId,
            String memberId) {

        try {
            library.borrowItem(itemId, memberId);

        } catch (ItemNotAvailableException e) {
            System.out.println("Cannot borrow: " + e.getMessage());

        } catch (MemberNotFoundException e) {
            System.out.println("Unknown member: " + e.getMemberId());

        } finally {
            System.out.println(
                    "    (attempt finished: item "
                            + itemId + ", member " + memberId + ")");
        }
    }
}
