
/**
 *  计算一个字符串中小写字母的个数（提示：参阅 String 对象的 chars 方法）。
 * @author Lenovo
 *
 */
public class Question6 {

	public static int getLowCades(String str) {
		return (int) str.chars()
//				  	 	.filter(ch -> Character.isLowerCase(ch))// my answer
						.filter(Character::isLowerCase)// standard answer
				  	 	.count();
	}
	
	public static void main(String[] args) {
		System.out.println(getLowCades("afsdfAFSDF"));
	}
}
