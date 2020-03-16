package cn.honghuroad.utils.wx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 序列化工具类
 * 
 * @author LIUX
 * @version 1.0
 * */
public class SerializeUtil {
	
	//对象序列化
	public static byte[] serialize(Object object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(baos);  
            close(oos);  
		}
		return null;
	}
		 
	//对象反序列化
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			close(bais);  
            close(ois);  
		}
		return null;
	}
	
	//对象列表序列化
	public static byte[] serializeList(List<Object> value) {  
        if (value == null) {  
            return null;
        }  
        ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;  
        try {  
            bos = new ByteArrayOutputStream();  
            os = new ObjectOutputStream(bos);  
            for(Object user : value){  
                os.writeObject(user);  
            }  
            os.writeObject(null);  
            return bos.toByteArray();  
        } catch (IOException e) {  
        	return null; 
        } finally {  
            close(os);  
            close(bos);  
        }  
        
    }  

    //释放IO流
    private static void close(Closeable closeable) {  
        if (closeable != null) {  
            try {  
                closeable.close();  
                closeable = null;
            } catch (Exception e) {  
                e.printStackTrace();
            }  
        }  
    }  
}  