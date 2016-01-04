package eccrm.base.customerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liuxq on 14-2-28.
 * 来电管理
 */
@Controller
public class CallManagement {

    //来电记录
    @RequestMapping(value = "/service/request/inboundlog")
    public String inboundlog() {
        return "customerService/callManagement/incomingRecords/list/incomingRecords_list";
    }

    //微信队列
    @RequestMapping(value = "/service/request/weixinqueue")
    public String weixinqueue() {
        return "customerService/callManagement/microChannel/list/microChannel_list";
    }

    //常见问题
    @RequestMapping(value = "/service/support/queskm")
    public String queskm() {
        return "customerService/support/oftenQuestion/list/oftenQuestion_list";
    }

    // 发送邮件
    @RequestMapping(value = "/service/support/sentoutmail")
    public String sentoutmail() {
        return "operating/communicate/Email/sendEmail/edit/sendEmail_edit";
    }

    // 发送短信
    @RequestMapping(value = "/service/support/sentoutmessage")
    public String sentoutmessage() {
        return "customerService/support/sendSMS/edit/sendSMS_edit";
    }
}
