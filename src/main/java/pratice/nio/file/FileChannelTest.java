package pratice.nio.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/11
 */
public class FileChannelTest {

    static final byte[] text_byte = new byte[1024];

    static {
        for (int i = 0; i < 1024; i++) {
            text_byte[i] = 'A';
        }
    }

    private int stop = 1024 * 1024 / 2;

    @Test
    public void test_file() throws Exception {

        Thread.sleep(20 * 1000);

        FileOutputStream fos = new FileOutputStream(new File("copy.txt"));

        //开辟通道
        FileChannel outChannel = fos.getChannel();

        //分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        //循环将输入读入到缓冲区
        for (int i = 0; i < 1024 * 1024; i++) {

            if (i == stop) {
                Thread.sleep(20 * 1000);
            }

            buffer.put(text_byte);
            //将缓冲区有读模式切换到写模式
            buffer.flip();
            //将缓冲区中的数据通过输出通道写出到膜裱
            outChannel.write(buffer);
            //清空缓冲区
            buffer.clear();

        }

        outChannel.close();

    }

    @Test
    public void testDirectBuffer() throws Exception {

        ByteBuffer bufferDirect = ByteBuffer.allocateDirect(1024 * 1024 * 1024);

        Thread.sleep(1000 * 20);

        ByteBuffer bufferHeap = ByteBuffer.allocate(1024 * 1024 * 1024);

        Thread.sleep(1000 * 20);

        System.out.println("xx");

        InputStream inputStream = new ByteArrayInputStream(bufferDirect.array());

        Thread.sleep(1000 * 20);
    }

}
