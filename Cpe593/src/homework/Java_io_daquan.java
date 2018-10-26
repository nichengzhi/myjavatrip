package homework;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Java_io_daquan
{
        public static void main(String[] args)
        {
                
        }
        /**
         * 使用FileWriter类写文本文件
         */
        public static void writeMethod1()
        {
                String fileName="convexhullpoints.dat";
                try
                {
                        //使用这个构造函数时，如果存在kuka.txt文件，
                        //则先把这个文件给删除掉，然后创建新的kuka.txt
                        FileWriter writer=new FileWriter(fileName);
                        writer.write("Hello Kuka:\n");
                        writer.write("  My name is coolszy!\n");
                        writer.write("  I like you and miss you。");
                        writer.close();
                } catch (IOException e)
                {
                        e.printStackTrace();
                }
        }
        /**
         * 使用FileWriter类往文本文件中追加信息
         */
        public static void writeMethod2()
        {
                String fileName="C:\\kuka.txt";
                try
                {
                        //使用这个构造函数时，如果存在kuka.txt文件，
                        //则直接往kuka.txt中追加字符串
                        FileWriter writer=new FileWriter(fileName,true);
                        SimpleDateFormat format=new SimpleDateFormat();
                        String time=format.format(new Date());
                        writer.write("\n\t"+time);
                        writer.close();
                } catch (IOException e)
                {
                        e.printStackTrace();
                }
        }
        //注意：上面的例子由于写入的文本很少，使用FileWrite类就可以了。但如果需要写入的
        //内容很多，就应该使用更为高效的缓冲器流类BufferedWriter。
        /**
         * 使用BufferedWriter类写文本文件
         */
        public static void writeMethod3()
        {
                String fileName="convexhullpoints.dat";
                try
                {
                        BufferedWriter out=new BufferedWriter(new FileWriter(fileName));
                        out.write("Hello Kuka:");
                        out.newLine();  //注意\n不一定在各种计算机上都能产生换行的效果
                        out.write("  My name is coolszy!\n");
                        out.write("  I like you and miss you。");
                        out.close();
                } catch (IOException e)
                {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
        /**
         * 使用FileReader类读文本文件
         */
        public static void readMethod1()
        {
                String fileName="C:/kuka.txt";
                int c=0;
                try
                {
                        FileReader reader=new FileReader(fileName);
                        c=reader.read();
                        while(c!=-1)
                        {
                                System.out.print((char)c);
                                c=reader.read();
                        }
                        reader.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        
        /**
         * 使用BufferedReader类读文本文件
         */
        public static void readMethod2()
        {
                String fileName="c:/kuka.txt";
                String line="";
                try
                {
                        BufferedReader in=new BufferedReader(new FileReader(fileName));
                        line=in.readLine();
                        while (line!=null)
                        {
                                System.out.println(line);
                                line=in.readLine();
                        }
                        in.close();
                } catch (IOException e)
                {
                        e.printStackTrace();
                }
        }
}
