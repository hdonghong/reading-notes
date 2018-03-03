import java.awt.Button;
import java.util.function.Predicate;

/**
 * 第三题
 * 类型判断
 * @author Lenovo
 *
 */
public class TypeArgue {

	// a.可以
	static Runnable r = () -> System.out.println("haha");
	
	// b.可以
	static Button button = new Button();
	
	// c.不可以，The method check(Predicate<Integer>) is ambiguous for the type TypeArgue
	static interface IntPred { boolean test(Integer value); }
	static boolean check(Predicate<Integer> predicate) { return predicate.test(1); }
	static boolean check(IntPred predicate) { return predicate.test(1); }
	
	public static void main(String[] args) {
		new Thread(r).start();
		
		button.addActionListener(
				event -> System.out.println(event));
		
//		check((v) -> v > 0);
	}
}
