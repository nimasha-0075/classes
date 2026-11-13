
package lms.model;

import lms.exception.ItemNotAvailableException;

public interface Loanable {

    void loan(Member member) throws ItemNotAvailableException;

    void returnItem() throws ItemNotAvailableException;
}
