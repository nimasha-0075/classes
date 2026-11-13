
package lms.model;

import lms.exception.ItemNotAvailableException;

public abstract class LibraryItem implements Loanable {

    private String id;
    private String title;
    private boolean isBorrowed;
    private Member borrower;

    public LibraryItem(String id, String title) {
        setId(id);
        setTitle(title);
        this.isBorrowed = false;
        this.borrower = null;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public final void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Id cannot be blank");
        }

        this.id = id;
    }

    public final void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }

        this.title = title;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }

    @Override
    public void loan(Member member) throws ItemNotAvailableException {

        if (isBorrowed) {
            throw new ItemNotAvailableException(
                    title + " is already on loan.");
        }

        if (member.getBorrowedCount() >= member.getMaxBorrowLimit()) {
            throw new ItemNotAvailableException(
                    member.getName() + " has reached the limit of "
                            + member.getMaxBorrowLimit() + " items.");
        }

        setBorrowed(true);
        borrower = member;
        member.incrementBorrowedCount();

        System.out.println(getTitle() + " loaned to "
                + member.getName());
    }

    @Override
    public void returnItem() throws ItemNotAvailableException {

        if (!isBorrowed) {
            throw new ItemNotAvailableException(
                    title + " is not on loan.");
        }

        borrower.decrementBorrowedCount();

        borrower = null;
        setBorrowed(false);

        System.out.println(getTitle() + " returned.");
    }

    public abstract double calculateLateFee(int daysLate);

    @Override
    public String toString() {

        return getClass().getSimpleName()
                + "{id='" + id
                + "', title='" + title
                + "', borrowed=" + isBorrowed + "}";
    }
}
