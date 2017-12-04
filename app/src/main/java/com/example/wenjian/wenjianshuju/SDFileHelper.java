package com.example.wenjian.wenjianshuju;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/12/4.
 */

class SDFileHelper {
    private Context context;
    public SDFileHelper(Context context) {
super();
this.context = context;
    }

    public void saveFileToSD(String filename, String filecontent) throws IOException {
                     //如果手机已插入sd卡中，且APP具有读写sd卡的权限
        if ( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ){
            filename = Environment.getExternalStorageDirectory().getCanonicalPath()
                    +"/" +filename;
            //这里就不要用openFileOutput了,那个是往手机内存中写数据的
            FileOutputStream outputStream = new FileOutputStream(filename);
            //将String字符串以字节流的形式写入到输出流中
            outputStream.write(filecontent.getBytes());
           //关闭输出流
            outputStream.close();
        }else
            Toast.makeText(context,"SD卡不存在或者不可读写",Toast.LENGTH_SHORT).show();
    }

    //读取sd卡文件的方法
    public String readFromSD(String filename2) throws IOException {
        StringBuilder sb = new StringBuilder("");
        if ( Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ){
            filename2 = Environment.getExternalStorageDirectory().getCanonicalPath()+
                    "/"+filename2;
            //打开文件输入流
            FileInputStream inputStream =new FileInputStream(filename2);
            byte[] temp = new byte[1024];
            int len = 0;
            //读取文件内容：
            while ((len = inputStream.read(temp))>0){
                sb.append(new String(temp,0,len));
            }
            //关闭输入流
            inputStream.close();
        }
        return sb.toString();
    }
}
