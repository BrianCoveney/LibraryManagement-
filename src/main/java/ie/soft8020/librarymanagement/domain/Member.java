package ie.soft8020.librarymanagement.domain;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public abstract class Member {

	@Id
	private int memberID;

	private String name;
	private String address;
	private Date dateOfBirth;
	private int loanLimit;
	private int loanLength;
	private Double finesOutstanding;
	private List<Book> books;
	private List<Loan> loans;

	public Member() {
		// When creating a member there can be situations when the list of 'books' 
		// or 'loans' are not be populated. We use an empty list in our constructor,
		// to prevent a NPE generated by toString() in this scenario.
		books = Collections.<Book>emptyList();
		loans = Collections.<Loan>emptyList();
	}
	
	public abstract Double calculateFines();
	
	
	public List<Loan> getLoans() {
		return loans;
	}

	public void setLoans(List<Loan> loan) {
		this.loans = loan;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getLoanLimit() {
		return loanLimit;
	}

	public void setLoanLimit(int loanLimit) {
		this.loanLimit = loanLimit;
	}

	public int getLoanLength() {
		return loanLength;
	}

	public void setLoanLength(int loanLength) {
		this.loanLength = loanLength;
	}

	public Double getFinesOutstanding() {
		return finesOutstanding;
	}

	public void setFinesOutstanding(Double finesOutstanding) {
		this.finesOutstanding = calculateFines();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}