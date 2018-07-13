package ie.soft8020.librarymanagement.domain;

import ie.soft8020.librarymanagement.util.Const;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class Adult extends Member {
	private List<Loan> loans;
	private List<Book> books;
	private Double finesOutstanding;
	private int loanLimit;
	private int loanLength;


	public Adult(String name, Date dateOfBirth) {
		super(name, dateOfBirth);
		// When creating a member there can be situations when the List of 'books'
		// or 'loans' have not been populated. We use an empty list in our constructor,
		// to prevent a NPE, generated by toString() in this scenario.
		loans = Collections.emptyList();
		books = Collections.emptyList();
		setLoanLimit(Const.LoanLimit.MAX_NUMBER_OF_BOOKS_FOR_ADULT);
		setLoanLength(Const.LoanLength.MAX_NUMBER_OF_DAYS_ADULT_CAN_BORROW);
	}

	@Override
	public List<Book> getBooks() {
		return books;
	}

	@Override
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public List<Loan> getLoans() {
		return loans;
	}

	@Override
	public void setLoans(List<Loan> loans) {
		this.loans = loans;
	}

	@Override
	public Double getFinesOutstanding() {
		return finesOutstanding;
	}

	@Override
	public void setFinesOutstanding(Double finesOutstanding) {
		this.finesOutstanding = finesOutstanding;
	}

	@Override
	public int getLoanLimit() {
		return loanLimit;
	}

	@Override
	public void setLoanLimit(int loanLimit) {
		this.loanLimit = loanLimit;
	}

	@Override
	public int getLoanLength() {
		return loanLength;
	}

	@Override
	public void setLoanLength(int loanLength) {
		this.loanLength = loanLength;
	}



	@Override
	public String toString() {
		String out = "Adult [memberID=" + getMemberID() + ", name=" + getName() + ", address=" + getAddress()
				+ ", dateOfBirth=" + getDateOfBirth() + ", loanLimit=" + getLoanLimit() + ", loanLength="
				+ getLoanLength() + ", finesOutstanding=" + getFinesOutstanding() + "\n"
				+ "books=[" + getListToString(getBooks()) + "]]\n"
				+ "loan=[" + getListToString(getLoans()) + "]]";
		return out;
	}

	private String getListToString(List<?> list) {
		return list.stream().map(e -> e.toString()).collect(Collectors.joining(","));
	}

}
