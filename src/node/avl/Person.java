package node.avl;

public class Person implements Comparable<Person> {

	private int age;
	private String name;
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Person(int age) {
		this.age = age;
	}
	
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	
	@Override
	public int compareTo(Person o) {
		
//		if (age > o.age) {
//			return 1;
//		} else if (age < o.age) {
//			return -1;
//		} else {
//			return 0;
//		}
		return age - o.age;
	}

	
	@Override
	public String toString() {
		return age + "";
	}
	
}
