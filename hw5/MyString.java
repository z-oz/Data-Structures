package hw5;

/**
 * The only purpose of this class is implement a String class with a very simple and predictable hashCode.
 * The hashCode of any word is the distance of the first letter in the word from the letter 'a'.
 * 
 * For example, "Apple" and "after" both have hashcode 0, while "fast" and "Fog" both have hashcode 5.
 * 
 * @author wmarrero
 *
 */
public class MyString implements Comparable<MyString>{
	private String val;
	
	public MyString(String s) {
		val = s;
	}
	
	public boolean equals(Object o2) {
		if (o2 == null)
			return false;
		if (o2 == this)
			return true;
		if (o2.getClass() != this.getClass())
			return false;
		MyString s2 = (MyString) o2;
		return this.val.equals(s2.val);
	}
	
	@Override
	public int hashCode() {
		int hash = val.toUpperCase().charAt(0) - 'A';
		return hash;
	}
	
	@Override
	public String toString() {
		return val;
	}

	@Override
	public int compareTo(MyString arg0) {
		return val.compareTo(arg0.val);
	}
}
