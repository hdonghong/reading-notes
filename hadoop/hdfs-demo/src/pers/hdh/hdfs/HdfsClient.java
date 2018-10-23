package pers.hdh.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * HdfsClient class<br/>
 *
 * @author hdonghong
 * @since 2018/10/23
 */
public class HdfsClient {

    public static void main(String[] args) throws Exception {
        // 1.获取文件系统
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop2:9000"), conf, "root");

        // 2.上传文件
        fs.copyFromLocalFile(
                new Path("C:/Users/Lenovo/OneDrive/personal/reading-notes/hadoop/hdfs-demo/resource/hello.txt"),
                new Path("/hello.txt")
        );

        // 3.关闭连接
        fs.close();
    }

    /**  HDFS获取文件系统 */
    @Test
    public void getFs() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop2:9000"), conf, "root");

        fs.close();
    }

    /** 文件上传 */
    @Test
    public void copyFromLocal() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("dfs.replication", "2");
        // 参数优先级：客户端代码 > classpath下的用户自定义配置文件 > 服务器的默认配置
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop2:9000"), conf, "root");

        fs.copyFromLocalFile(
                new Path("C:/Users/Lenovo/OneDrive/personal/reading-notes/hadoop/hdfs-demo/resource/hello.txt"),
                new Path("/hello2.txt")
        );

        fs.close();
    }

    /** 文件下载 */
    @Test
    public void get() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop2:9000"), conf, "root");

        /*
         * delSrc: 是否删除源文件
         * src：源文件路径
         * dst：下载到的本地路径
         * useRawLocalFileSystem：否开启文件效验
         * */
        fs.copyToLocalFile(
                false,
                new Path("/hello2.txt"),
                new Path("C:/Users/Lenovo/OneDrive/personal/reading-notes/hadoop/hdfs-demo/resource/hello2.txt"),
                true
        );

        fs.close();
    }

    /** 目录创建 */
    @Test
    public void mkdir() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop2:9000"), conf, "root");

        Assert.assertTrue(
                fs.mkdirs(new Path("/18/10/23"))
        );

        fs.close();
    }

    /** 文件删除 */
    @Test
    public void rmOrRmdir() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop2:9000"), conf, "root");

        Assert.assertTrue(
                fs.delete(new Path("/18"), true)
        );

        fs.close();
    }

    /** 文件名更改，没有mv功能 */
    @Test
    public void rename() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop2:9000"), conf, "root");

        Assert.assertTrue(
                fs.rename(new Path("/hello2.txt"), new Path("/hello.txt2"))
        );

        fs.close();
    }

    /** 文件详情查看 */
    @Test
    public void listFiles() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop2:9000"), conf, "root");

        RemoteIterator<LocatedFileStatus> fileIterator = fs.listFiles(new Path("/"), true);
        // 自问自答：为什么返回迭代器而不是list，明显list更方便？
        // list更消耗内存，需要同时存储结果文件，如果文件过大，会占用大内存

        while (fileIterator.hasNext()) {
            LocatedFileStatus fileStatus = fileIterator.next();

            System.out.println("---------------文件详情---------------");
            // 获取文件名称
            System.out.println(fileStatus.getPath().getName());
            // 获取长度
            System.out.println(fileStatus.getLen());
            // 获取权限
            System.out.println(fileStatus.getPermission());
            // 组
            System.out.println(fileStatus.getGroup());

            // 获取存储的块信息
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                // 获取块存储的主机节点
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
        }

        fs.close();
    }

    /** 文件和文件夹判断 */
    @Test
    public void listStatus() throws URISyntaxException, IOException, InterruptedException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop2:9000"), conf, "root");

        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));

        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isDirectory()) {
                System.out.println("d:" + fileStatus.getPath().getName());
            } else {
                System.out.println("-:" + fileStatus.getPath().getName());
            }
        }

        fs.close();
    }
}
