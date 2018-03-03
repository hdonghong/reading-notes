

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;

/**
 * 常用流操作。实现如下函数
 * @author Lenovo
 *
 */
public class Question1 {

	/**
	 * a. 编写一个求和函数，计算流中所有数之和。例如， int addUp(Stream<Integer>numbers) ；
	 * @param numbers
	 * @return
	 */
	public static int addUp(Stream<Integer> numbers) {
		return numbers.reduce(0, (acc, element) -> acc + element);
	}
	
	/**
	 * 编写一个函数，接受艺术家列表作为参数，返回一个字符串列表，其中包含艺术家的姓名和国籍；
	 * @param artists
	 * @return
	 */
	public static List<String> artistsInfo(List<Artist> artists) {
		return artists.stream()
					  .map(artist -> artist.getName() + " " + artist.getNationality())        // my answer
//					  .flatMap(artist -> Stream.of(artist.getName(), artist.getNationality()))// standard answer
					  .collect(Collectors.toList());
	}
	
	/**
	 * 编写一个函数，接受专辑列表作为参数，返回一个由最多包含 3 首歌曲的专辑组成的列表。
	 * @param albums
	 * @return
	 */
	public static List<Album> getAlbums(List<Album> albums) {
		return albums.stream()
					 .filter(album -> album.getTrackList().size() <= 3)
					 .collect(Collectors.toList());
	}
	
	
	public static void main(String[] args) {
		Artist a1 = new Artist("小王", "中国");
		Artist a2 = new Artist("大龟", "日本");
		Artist a3 = new Artist("小棒", "韩国");
		List<Artist> list = Stream.of(a1, a2, a3).collect(Collectors.toList());
		System.out.println("origin: " + list.size());
		artistsInfo(list).forEach(System.out::println);
	}
}
