import sun.misc.Launcher;
import java.net.URL;

/**
 * 类加载器的分类
 */
public class TestJDKClassLoader {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader()); //由于BootStrap ClassLoader是用c++写的，所以在返回该ClassLoader时会返回null
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName()); //sun.misc.Launcher$ExtClassLoader
        System.out.println(TestJDKClassLoader.class.getClassLoader().getClass().getName()); //sun.misc.Launcher$AppClassLoader

        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassloader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassloader.getParent();
        System.out.println("the bootstrapLoader : " + bootstrapLoader); //the bootstrapLoader : null
        System.out.println("the extClassloader : " + extClassloader);   //the extClassloader : sun.misc.Launcher$ExtClassLoader@677327b6
        System.out.println("the appClassLoader : " + appClassLoader);   //the appClassLoader : sun.misc.Launcher$AppClassLoader@18b4aac2

        System.out.println();
        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }

        System.out.println();
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));

    }
}