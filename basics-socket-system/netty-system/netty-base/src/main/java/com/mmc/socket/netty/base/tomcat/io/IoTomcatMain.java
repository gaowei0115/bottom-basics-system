package com.mmc.socket.netty.base.tomcat.io;

import com.mmc.socket.netty.base.tomcat.io.core.IoRequest;
import com.mmc.socket.netty.base.tomcat.io.core.IoResponse;
import com.mmc.socket.netty.base.tomcat.io.core.IoServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @packageName：com.mmc.socket.netty.base.tomcat.io
 * @desrciption: 基于Socket io 模拟Tomcat启动类
 * @author: GW
 * @date： 2020/8/31 23:25
 * @history: (version) author date desc
 */
public class IoTomcatMain {

    private int port = 8081;
    private ServerSocket server;
    private ConcurrentMap<String, IoServlet> map = new ConcurrentHashMap<>(10);

    // 读取web-io.properties文件
    private Properties webIoProperty = new Properties();

    public void init () {
        try {
            String WEB_INF = this.getClass().getResource("").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web-io.properties");

            webIoProperty.load(fis);
            webIoProperty.keySet().forEach(k -> {
                String key = k.toString();
                if (key.endsWith("url")) {
                    String servletNamePre = key.replaceAll("\\.url$", "");
                    String uri = webIoProperty.getProperty(key);
                    String className = webIoProperty.getProperty(servletNamePre + ".className");
                    try {
                        IoServlet instance = (IoServlet) Class.forName(className).newInstance();
                        map.put(uri, instance);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        // 1. 加载配置文件
        init();
        try {
            server = new ServerSocket(port);
            System.out.println("基于Socket IO Tomcat 容器启动 port ： " + port);

            // 等待客户端连接
            while (true) {
                Socket client = server.accept();
                // 3. http请求处理
                process(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception {
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        IoRequest request = new IoRequest(is);
        IoResponse response = new IoResponse(os);

        if (map.containsKey(request.getUrl())) {
            map.get(request.getUrl()).service(request, response);
        } else {
            response.write("404 Not Found");
        }

        os.flush();
        os.close();

        is.close();
        client.close();
    }

    public static void main(String[] args) {
        IoTomcatMain main = new IoTomcatMain();
        main.start();
    }
}
