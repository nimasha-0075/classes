package lms;

import lms.model.Book;
import lms.model.DVD;
import lms.model.Staff;
import lms.model.Student;
import lms.service.Library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addItem(new Book("B001", "Clean Code", "Robert C. Martin"));
        library.addItem(new Book("B002", "Effective Java", "Joshua Bloch"));
        library.addItem(new DVD("D001", "Introduction to Algorithms", 120));

        library.addMember(new Student("M001", "Nadeesha Perera"));
        library.addMember(new Staff("M002", "Mr. Kasun Silva"));

        library.listItems();
        library.listMembers();
        library.printAllLateFees(3);

        try {
            new Book("B003", "", "Nobody");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected invalid book: " + e.getMessage());
        }
    }
}