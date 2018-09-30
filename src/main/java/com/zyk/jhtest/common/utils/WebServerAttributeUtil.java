package com.zyk.jhtest.common.utils;

import java.io.File;

/**
 * ==================================================
 * 创建人：gaohzh
 * 创建时间：2018/2/28
 * ==================================================
 * 类说明：
 * <p>
 * ==================================================
 *
 * @version 1.0 2018/2/28
 */
public class WebServerAttributeUtil {

    public static String attributeToString(String fileUrl) {
        File disk = new File(fileUrl);

        StringBuffer webServer = new StringBuffer();
        //当前路径下硬盘总空间
        webServer.append("\r\n硬盘总空间：").append(disk.getTotalSpace() / (1024 * 1024 * 1024) + "GB");

        //当前路径下硬盘空闲空间
        webServer.append("\r\n硬盘空闲空间：").append(disk.getFreeSpace() / (1024 * 1024 * 1024) + "GB");

        //当前路径下硬盘已用空间
        webServer.append("\r\n硬盘已用空间：").append((disk.getTotalSpace() - disk.getFreeSpace()) / (1024 * 1024 * 1024) + "GB");

        //当前虚拟机最大内存
        webServer.append("\r\n虚拟机最大内存：").append(Runtime.getRuntime().maxMemory() / (1024 * 1024) + "MB");

        //当前空闲内存
        webServer.append("\r\n虚拟机空闲内存：").append(Runtime.getRuntime().freeMemory() / (1024 * 1024) + "MB");

        //当前虚拟机占用内存
        webServer.append("\r\n虚拟机占用内存：").append(Runtime.getRuntime().totalMemory() / (1024 * 1024) + "MB");

        return webServer.toString();
    }

    public static String attributeToStringForHtml(String fileUrl) {
        File disk = new File(fileUrl);

        StringBuffer webServer = new StringBuffer();
        //当前路径下硬盘总空间
        webServer.append("<br />硬盘总空间：").append(disk.getTotalSpace() / (1024 * 1024 * 1024) + "GB<br />");

        //当前路径下硬盘空闲空间
        webServer.append("<br />硬盘空闲空间：").append(disk.getFreeSpace() / (1024 * 1024 * 1024) + "GB<br />");

        //当前路径下硬盘已用空间
        webServer.append("<br />硬盘已用空间：").append((disk.getTotalSpace() - disk.getFreeSpace()) / (1024 * 1024 * 1024) + "GB<br />");

        //当前虚拟机最大内存
        webServer.append("<br />虚拟机最大内存：").append(Runtime.getRuntime().maxMemory() / (1024 * 1024) + "MB<br />");

        //当前空闲内存
        webServer.append("<br />虚拟机空闲内存：").append(Runtime.getRuntime().freeMemory() / (1024 * 1024) + "MB<br />");

        //当前虚拟机占用内存
        webServer.append("<br />虚拟机占用内存：").append(Runtime.getRuntime().totalMemory() / (1024 * 1024) + "MB<br />");

        return webServer.toString();
    }
//    public static void main(String[] args){
//        WebServerAttributeUtil webServerAttributeUtil=new WebServerAttributeUtil();
//        System.out.println(webServerAttributeUtil.attributeToString("/users/"));
//
//    }
}
