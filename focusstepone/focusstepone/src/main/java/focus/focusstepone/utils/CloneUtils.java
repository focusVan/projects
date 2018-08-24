package focus.focusstepone.utils;

import java.io.*;

/**
 * focus Create in 16:46 2018/8/21
 */
public class CloneUtils {
    public static <T extends Serializable> T clone(T obj) throws IOException{
        // 拷贝产生的对象
        T clonedObj = null;
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            // 读取对象字节数据
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);

            bis  = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bis);
            // 返回新对象，并做类型转换
            clonedObj = (T)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                baos.close();
            }
            if (oos != null) {
                oos.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }

        return clonedObj;
    }
}
