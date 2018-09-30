package com.zyk.jhtest.common.service;

import com.zyk.jhtest.common.utils.RequestUtil;
import com.zyk.jhtest.common.utils.WebServerAttributeUtil;
import io.github.jhipster.config.JHipsterProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zb on 2018/2/11.
 */
@Service
public class SendMailService {

    private final Logger logger = LoggerFactory.getLogger(SendMailService.class);

    private final JavaMailSender mailSender;
    private final JHipsterProperties jHipsterProperties;

    public SendMailService(JavaMailSender mailSender, JHipsterProperties jHipsterProperties) {
        this.mailSender = mailSender;
        this.jHipsterProperties = jHipsterProperties;
    }

    /**
     * 发送邮件
     * @author zb
     * @date 2018-02-11
     * @param toPath  接收者邮箱
     * @param subject 邮件标题
     * @param text  邮件内容
     * @param filePath 附件
     */
    public void sendMail(String toPath,String subject,String text,String filePath) {
        try{
            final MimeMessage mimeMessage = mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
            message.setFrom(jHipsterProperties.getMail().getFrom(),"ERR-huaweigate");
            message.setTo(toPath);
            message.setSubject(subject);
            message.setText(text,true);
            if(StringUtils.isNotBlank(filePath)){
                FileSystemResource file = new FileSystemResource(new File(filePath));
                String fileName = filePath.trim().substring(filePath.lastIndexOf("/")+1);
                message.addAttachment(fileName, file);
            }
            mailSender.send(mimeMessage);
        }catch(Exception e){
            logger.error("",e, logger);
        }
    }

    //仅用于发送日志邮件
//    private final String[] toMailPaths = {"gaohzh@qdum.com","15589721080@163.com","1251134350@qq.com"};
    private final String[] toMailPaths = {"devops-team@webpowerchina.com","15589721080@163.com","1251134350@qq.com"};
//    private final String[] toMailPaths = {"15589721080@163.com"};
    private final String webServerUrl="/usr/local";

    /**
     * 发送邮件
     * @author zb
     * @date 2018-02-11
     * @param filePath 附件
     */
    public void sendLogMailAndEveryDay(String filePath) {
        try{
            String IP = RequestUtil.getLocalHostLANAddress();//获取当前ip
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timeStamp = simpleDateFormat.format(new Date());
            final String mailSubject = "[huaweigate"+timeStamp+"]每日日志报告汇总";
            final String mailContent = "<br /><br />[Process"+timeStamp+"]每日日志报告汇总,详情见附件<br />";
            String webServerAttribute = WebServerAttributeUtil.attributeToStringForHtml(webServerUrl);
            for(int i=0; i<toMailPaths.length;i++ ){
                String toMailPath = toMailPaths[i];
                final MimeMessage mimeMessage = mailSender.createMimeMessage();
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);
                message.setFrom(jHipsterProperties.getMail().getFrom(),"huaweigate-zip");
                message.setTo(toMailPath);
                message.setSubject(mailSubject);
                message.setText("服务器IP: "+ IP + webServerAttribute + "content" + mailContent,true);
                if(StringUtils.isNotBlank(filePath)){
                    FileSystemResource file = new FileSystemResource(new File(filePath));
                    String fileName = filePath.trim().substring(filePath.lastIndexOf("/")+1);
                    message.addAttachment(fileName, file);
                }
                mailSender.send(mimeMessage);
            }
        }catch(Exception e){
            logger.error("",e, logger);
        }
    }

    /**
     * 发送邮件
     * @author zb
     * @date 2018-02-11
     * @param mailContent  邮件内容
     */
    @Async
    public void sendErrorLogMail(String mailContent) {
        try{
            String IP = RequestUtil.getLocalHostLANAddress();//获取当前ip
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timeStamp = simpleDateFormat.format(new Date());
            final String mailSubject = "[huaweigate"+timeStamp+"]ERROR日志报告";
            String webServerAttribute = WebServerAttributeUtil.attributeToStringForHtml(webServerUrl);
            for(int i=0; i < toMailPaths.length; i++ ) {
                String toMailPath = toMailPaths[i];
                final MimeMessage mimeMessage = mailSender.createMimeMessage();
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setFrom(jHipsterProperties.getMail().getFrom(),"huaweigate-error");
                message.setTo(toMailPath);
                message.setSubject(mailSubject);
                message.setText("服务器IP :"+ IP + webServerAttribute + mailContent, true);
                mailSender.send(mimeMessage);
            }
        }catch(Exception e){
            logger.error("",e, logger);
        }

    }
    /**
     * 发送邮件
     * @author zb
     * @date 2018-02-11
     * @param mailContent  邮件内容
     */
    @Async
    public void sendErrorLogMail(String subject,String mailContent) {
        try{
            String IP = RequestUtil.getLocalHostLANAddress();//获取当前ip
            String webServerAttribute = WebServerAttributeUtil.attributeToStringForHtml(webServerUrl);
            for(int i=0; i < toMailPaths.length; i++ ) {
                String toMailPath = toMailPaths[i];
                final MimeMessage mimeMessage = mailSender.createMimeMessage();
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setFrom(jHipsterProperties.getMail().getFrom(),subject);
                message.setTo(toMailPath);
                message.setSubject(subject);
                message.setText("服务器IP :"+ IP + webServerAttribute + mailContent, true);
                mailSender.send(mimeMessage);
            }
        }catch(Exception e){
            logger.error("",e, logger);
        }

    }

    /**
     * 发送邮件
     * @author zb
     * @date 2018-02-11
     * @param mailContent  邮件内容
     */
    @Async
    public void sendTimerLogMail(String mailContent) {
        try{
            String IP = RequestUtil.getLocalHostLANAddress();//获取当前ip
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timeStamp = simpleDateFormat.format(new Date());
            final String mailSubject = "[huaweigate"+timeStamp+"]Timer日志报告";
            String webServerAttribute = WebServerAttributeUtil.attributeToStringForHtml(webServerUrl);
            for(int i=0; i < toMailPaths.length; i++ ) {
                String toMailPath = toMailPaths[i];
                final MimeMessage mimeMessage = mailSender.createMimeMessage();
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                message.setFrom(jHipsterProperties.getMail().getFrom(),"huaweigate-error");
                message.setTo(toMailPath);
                message.setSubject(mailSubject);
                message.setText("服务器IP :"+ IP + webServerAttribute + mailContent, true);
                mailSender.send(mimeMessage);
            }
        }catch(Exception e){
            logger.error("",e, logger);
        }

    }


}
