package lms.exception;

public class MemberNotFoundException extends RuntimeException {
    private final String memberId;
    public MemberNotFoundException(String memberId) {

        super("No member with id '"+memberId+" ' ");
        this.memberId = memberId;

    }

    public String getMemberId() {
        return memberId;
    }
}
