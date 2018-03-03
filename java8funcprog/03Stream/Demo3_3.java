

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 常用流操作
 * @author Lenovo
 *
 */
public class Demo3_3 {

	@Test
	public void testCollect() {
		List<String> list = Stream.of("a", "b", "c")
								  .collect(Collectors.toList());
		assertEquals(Arrays.asList("a", "b", "c"), list);
	}
	
	@Test
	public void testMap() {
		List<String> collected = Stream.of("a", "b", "c")
									   .map(string -> string.toUpperCase())
									   .collect(Collectors.toList());
		assertEquals(Arrays.asList("A", "B", "C"), collected);
	}
	
	@Test
	public void testFilter() {
		List<String> collected = Stream.of("a", "1abc", "abc1")
									.filter(str -> Character.isDigit(str.charAt(0)))
									.collect(Collectors.toList());
		assertEquals(Arrays.asList("1abc"), collected);
	}
	
	@Test
	public void testFlatMap() {
		List<String> collected = Stream.of(Arrays.asList("1", "2"), Arrays.asList("a", "b"))
									   .flatMap(list -> list.stream())
									   .collect(Collectors.toList());
		assertEquals(Arrays.asList("1", "2", "a", "b"), collected);
	}
	
	class Person {
		private String name;
		private int age;
		public Person(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
	
	@Test
	public void testMax() {
		List<Person> persons = Arrays.asList(new Person("小明", 22),
											 new Person("哇哈", 45),
											 new Person("喜羊", 12));
		Person maxAgePerson = persons.stream()
									 .max(Comparator.comparing(person -> person.getAge()))
									 .get();
		assertEquals(persons.get(1), maxAgePerson);
	}
	
	@Test
	public void testReduce() {
		int count = Stream.of(1, 2, 3)
						  .reduce(0, (acc, element) -> acc + element);
		assertEquals(6, count);
	}
}
