package com.ps.utils;

import java.io.File;
import java.io.IOException;

public class DOSutils {

    public static void main(String[] args) throws InterruptedException {
        //启动开启 zookeeper  和  kafka
        DOSutils.startZookeeper("E:\\soft\\zookeeper-3.4.14");
//       DOSutils.startKafka("E:\\soft\\kafka_2.12-2.3.0");
    }





    private static boolean zookeeperIsConnected = false;

    /**
     * 打开zookeeper
     * 参数为安装目录
     */
    public static boolean startZookeeper(String path)   {


        File file = new File(path);

        String cmd = "cmd /c start .\\bin\\zkServer.cmd";

        cmd(file, cmd);

        return false;
    }

    /**
     * 打开  kafka
     * 参数为安装目录
     */
    public static boolean startKafka(String path) {


        File file = new File(path);

        String cmd = "cmd /c start .\\bin\\windows\\kafka-server-start.bat .\\config\\server.properties";

        cmd(file, cmd);


        return true;

    }

    /**
     * 自定义执行命令行 , path为文件路径 , 在哪里打开cmd命令 ,cmd为命令行
     */
    public static void executeCmd(String path, String cmd) {
        File file = new File(path);
        String[] cmds = {"cmd", "/c", "start", cmd};
        cmd(file, cmds);
    }


    private static Process cmd(File file, String cmd) {
        Process exec = null;
        try {

            exec = Runtime.getRuntime().exec(cmd, null, file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return exec;
    }

    private static void cmd(File file, String[] cmd) {
        try {
            Runtime.getRuntime().exec(cmd, null, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}