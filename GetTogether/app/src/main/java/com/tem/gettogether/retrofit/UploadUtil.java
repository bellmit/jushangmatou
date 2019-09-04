package com.tem.gettogether.retrofit;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.utils.BitnapUtils;
import com.ybm.app.common.WindowToast.ToastTips;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import cc.duduhuo.custoast.CusToast;

/**
 * 上传文件到服务器类
 *
 * @author tom
 */
public class UploadUtil {
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 1000;// 超时时间
    private static final String CHARSET = "utf-8";// 设置编码

    /**
     * Android上传文件到服务端
     *
     * @param file       需要上传的文件
     * @param RequestURL 请求的rul
     * @return 返回响应的内容
     */
    public static ImageDataBean uploadFile(byte[] bytess ,File file, String RequestURL) {
        ImageDataBean imageDataBean = null;
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString();// 边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "application/x-www-form-urlencoded";// 内容类型
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true);// 允许输入流
            conn.setDoOutput(true);// 允许输出流
            conn.setUseCaches(false);// 不允许使用缓存
            conn.setRequestMethod("POST");// 请求方式
            conn.setRequestProperty("Charset", CHARSET);// 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            if (file != null) {
                /**
                 * 当文件不为空，把文件包装并且上传
                 */
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                StringBuffer sb = new StringBuffer();

                /**
                 * 这里重点注意： name里面的值为服务端需要key 只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的 比如:abc.png
                 */

                dos.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[is.available()];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * 获取响应码 200=成功 当响应成功，获取响应的流
                 */
                int res = conn.getResponseCode();
                Log.e(TAG, "response code:" + res);
                // if(res==200)
                // {
                Log.e(TAG, "request success");
                InputStream input = conn.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1) {
                    sb1.append((char) ss);
                }
                result = sb1.toString();
                Log.e(TAG, "result : " + result);
                JSONObject jsonObject = new JSONObject(result);
                String status = jsonObject.optString("status");
                String msg = jsonObject.optString("msg");
                if(status.equals("1")){
                    Gson gson = new Gson();
                     imageDataBean = gson.fromJson(result, ImageDataBean.class);
                }else{
                     CusToast.showToast(msg);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imageDataBean;
    }

}