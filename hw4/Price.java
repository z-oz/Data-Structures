package hw4;

public class Price implements Comparable<Price> {
	private int dollars;
	private int cents;
	
	public Price(int dollars, int cents) {
		this.dollars = dollars;
		this.cents = cents;
	}
	
	public String toString() {
		String answer = "$" + dollars + ".";
		if (cents < 10)
			answer = answer + "0" + cents;
		else
			answer = answer + cents;
		return answer;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		if (cents != other.cents)
			return false;
		if (dollars != other.dollars)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Price obj) {
		if (this.dollars == obj.dollars && this.cents == obj.cents) return 0;
		if (this.dollars == obj.dollars) {
			if (this.cents > obj.cents) return 1;
			else return -1;
		}
		if (this.dollars < obj.dollars) return -1;
		if (this.dollars > obj.dollars) return 1;
		return -2;
	}
	
}
