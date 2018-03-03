

import java.util.ArrayList;
import java.util.List;

/**
 * ³¢ÊÔÊ¹ÓÃÁ÷
 * @author Lenovo
 *
 */
public class Demo3_1 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("1112");
		list.add("222");
		list.add("333");
		list.add("444");
		
		System.out.println(list.stream()
							   .filter(str -> str.contains("2"))
							   .count());
	}
}

