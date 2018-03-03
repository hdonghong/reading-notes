import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 在一个字符串列表中，找出包含最多小写字母的字符串。对于空列表，返回 Optional<String> 对象
 * @author Lenovo
 */
public class Question7 {

	public static Optional<String> getMostLowCase(List<String> list) {
		return list.stream()
				   .max(Comparator.comparing(
						str -> str.toString().chars().filter(Character::isLowerCase).count()));// my answer
	//					str -> Question6.getLowCades(str)));// my answer
	//					Question6::getLowCades));// standard answer
		
	}
}
