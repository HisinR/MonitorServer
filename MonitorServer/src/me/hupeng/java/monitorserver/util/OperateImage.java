package me.hupeng.java.monitorserver.util;

import java.awt.Rectangle;  
import java.awt.image.BufferedImage;  
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.util.Iterator;  
  


import javax.imageio.ImageIO;  
import javax.imageio.ImageReadParam;  
import javax.imageio.ImageReader;  
import javax.imageio.stream.ImageInputStream;  

public class OperateImage {  
	  


    // ===���е�x����  
    public static int x = 0;  
  
    public static int y = 0;  
  
    // ===���е���  
    public static int width = 288;  
    public static int height = 288;  
  
    public OperateImage() {  
    }  
  
    public static void init(int x, int y, int width, int height) {  
        OperateImage.x = x;  
        OperateImage.y = y;  
        OperateImage.width = width;  
        OperateImage.height = height;  
    }  
  
    /** 
     *  
     * ��ͼƬ�ü������Ѳü��군��ͼƬ���� �� 
     */  
  
    public static byte[] cut(byte[] byte_in) throws IOException {  
        ByteArrayInputStream is = null;  
        ImageInputStream iis = null;  
        try {  
            // ��ȡͼƬ�ļ�  
            is = new ByteArrayInputStream(byte_in);  
  
            /** 
             *  
             * ���ذ������е�ǰ��ע�� ImageReader �� Iterator����Щ ImageReader 
             *  
             * �����ܹ�����ָ����ʽ�� ������formatName - ��������ʽ��ʽ���� . 
             *  
             * (���� "jpeg" �� "tiff")�� �� 
             */  
            Iterator<ImageReader> it = ImageIO  
                    .getImageReadersByFormatName("jpg");  
  
            ImageReader reader = it.next();  
  
            // ��ȡͼƬ��  
            iis = ImageIO.createImageInputStream(is);  
  
            /** 
             *  
             * <p> 
             * iis:��ȡԴ��true:ֻ��ǰ���� 
             * </p> 
             * .�������Ϊ ��ֻ��ǰ�������� 
             *  
             * ��������ζ�Ű���������Դ�е�ͼ��ֻ��˳���ȡ���������� reader 
             *  
             * ���⻺���������ǰ�Ѿ���ȡ��ͼ����������ݵ���Щ���벿�֡� 
             */  
            reader.setInput(iis, true);  
  
            /** 
             *  
             * <p> 
             * ������ζ������н������ 
             * <p> 
             * .����ָ�����������ʱ�� Java Image I/O 
             *  
             * ��ܵ��������е���ת��һ��ͼ���һ��ͼ�������ض�ͼ���ʽ�Ĳ�� 
             *  
             * ������ ImageReader ʵ�ֵ� getDefaultReadParam �����з��� 
             *  
             * ImageReadParam ��ʵ���� 
             */  
            ImageReadParam param = reader.getDefaultReadParam();  
  
            /** 
             *  
             * ͼƬ�ü�����Rectangle ָ��������ռ��е�һ������ͨ�� Rectangle ���� 
             *  
             * �����϶��������(x��y)����Ⱥ͸߶ȿ��Զ���������� 
             */  
            Rectangle rect = new Rectangle(x, y, width, height);  
  
            // �ṩһ�� BufferedImage���������������������ݵ�Ŀ�ꡣ  
            param.setSourceRegion(rect);  
  
            /** 
             *  
             * ʹ�����ṩ�� ImageReadParam ��ȡͨ������ imageIndex ָ���Ķ��󣬲��� 
             *  
             * ����Ϊһ�������� BufferedImage ���ء� 
             */  
            BufferedImage bi = reader.read(0, param);  
  
            // ������ͼƬ  
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos); 
            return baos.toByteArray();
        } finally {  
            if (is != null)  
                is.close();  
            if (iis != null)  
                iis.close();  
        }
    }  
      

  
}  