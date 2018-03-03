import java.text.SimpleDateFormat;

import javax.swing.text.DateFormatter;

/**
 * 线程安全的dateFormatter类
 * @author Lenovo
 *
 */
public class SaveDateFormatter {

	public static ThreadLocal<DateFormatter> formatter = 
										ThreadLocal.withInitial(
										() -> new DateFormatter(new SimpleDateFormat("dd-MM-yyyy")));
}
