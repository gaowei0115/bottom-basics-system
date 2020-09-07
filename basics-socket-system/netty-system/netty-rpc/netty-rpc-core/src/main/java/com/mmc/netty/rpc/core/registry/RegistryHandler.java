package com.mmc.netty.rpc.core.registry;

import com.mmc.netty.rpc.core.protocol.AutoProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @packageName：com.mmc.netty.rpc.core.registry
 * @desrciption:
 * @author: GW
 * @date： 2020/9/5 21:46
 * @history: (version) author date desc
 */
public class RegistryHandler extends ChannelInboundHandlerAdapter {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(RegistryHandler.class);

    public static ConcurrentMap<String, Object> registryMap = new ConcurrentHashMap<>();

    /**
     * 注册服务保存
     */
    private List<String> classNames = new ArrayList<>();

    public RegistryHandler() {
        // 扫描provider服务类
        scannerClass("com.mmc.netty.rpc.core.provide");
        doRegistry();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AutoProtocol protocol = (AutoProtocol) msg;
        Object result = new Object();
        if (registryMap.containsKey(protocol.getClassName())) {
            Object clazz = registryMap.get(protocol.getClassName());
            Method method = clazz.getClass().getMethod(protocol.getMethodName(), protocol.getParamTypes());
            result = method.invoke(clazz, protocol.getParams());
        }
        ctx.write(result);
        ctx.flush();
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("rpc registry rpc cause: {}", cause.getMessage(), cause);
        ctx.close();
    }

    private void doRegistry() {
        if (classNames.size() == 0) {
            return;
        }
        if (!classNames.isEmpty()) {
            classNames.forEach(className -> {
                try {
                   Class<?> instance = Class.forName(className);
                   Class<?> inter = instance.getInterfaces()[0];
                   registryMap.put(inter.getName(), instance.newInstance());
                } catch (Exception e) {
                    logger.error("加载class异常", e);
                }
            });
        }
    }

    private void scannerClass(String s) {

//        logger.debug(this.getClass().getResource("catcom/netty/").getPath());
        String path = this.getClass().getClassLoader().getResource("").getPath() + s.replaceAll("\\.", "/");
        File dirFile = new File(path);
        if (dirFile.isFile()) {
            return;
        }
        logger.debug(" >> {}", dirFile.listFiles().length);
        for (File file : dirFile.listFiles()) {
            if (file.isDirectory()) {
                scannerClass(s + "." + file.getName());
            } else {
                classNames.add(s + "." + file.getName().replace(".class", "").trim());
            }
        }
    }

    public static void main(String[] args) {
        RegistryHandler handler = new RegistryHandler();
        handler.scannerClass("com.mmc.netty.rpc.core.provide");
    }
}
