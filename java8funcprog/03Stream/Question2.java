import java.util.List;

import com.insightfullogic.java8.examples.chapter1.Artist;

/**
 * 迭代。修改如下代码，将外部迭代转换成内部迭代：
	int totalMembers = 0;
	for (Artist artist : artists) {
	Stream<Artist> members = artist.getMembers();
	totalMembers += members.count();
	}
 * @author Lenovo
 *
 */
public class Question2 {

	public static void main(String[] args) {
		
	}
	
	public static int getTotalMembers(List<Artist> artists) {
		return artists.stream()
					  .map(artist -> artist.getMembers().count())
//					  .reduce(0L, (total, e) -> total + e)// my answer
					  .reduce(0L, Long::sum)              // standard answer
					  .intValue();
					  
	}
}
