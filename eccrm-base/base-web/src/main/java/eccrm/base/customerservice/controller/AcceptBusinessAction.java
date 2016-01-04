package eccrm.base.customerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author miles
 * @datetime 14-2-25 下午10:49
 * 业务受理
 */
@Controller
public class AcceptBusinessAction {

    /**
     * 服务受理
     */
    @RequestMapping(value = "/service/request/accept")
    public String accept() {
        return "customerService/accept/accept";
    }

    /**
     * 客户档案
     */
    @RequestMapping(value = "/service/accept/customer", method = RequestMethod.GET)
    public String customerArchive() {
        return "customerService/accept/customerArchive";
    }

    /**
     * 联络历史
     */
    @RequestMapping(value = "/service/accept/contact/history", method = RequestMethod.GET)
    public String contactHistory() {
        return "customerService/accept/contactHistory";
    }

    /**
     * 联络历史
     */
    @RequestMapping(value = "/service/accept/order/history", method = RequestMethod.GET)
    public String orderHistory() {
        return "customerService/accept/orderHistory";
    }

    /**
     * 服务历史
     */
    @RequestMapping(value = "/service/accept/server/history", method = RequestMethod.GET)
    public String serviceHistory() {
        return "customerService/accept/serverHistory";
    }

    /**
     * 业务机会
     */
    @RequestMapping(value = "/service/accept/business/opportunity", method = RequestMethod.GET)
    public String businessOpportunity() {
        return "customerService/accept/businessOpportunity";
    }

    /**
     * 产品/服务
     */
    @RequestMapping(value = "/service/accept/production", method = RequestMethod.GET)
    public String production() {
        return "customerService/accept/production";
    }

    /**
     * 订单信息
     */
    @RequestMapping(value = "/service/accept/order", method = RequestMethod.GET)
    public String orderInformation() {
        return "customerService/accept/order";
    }

    /**
     * 客户服务
     */
    @RequestMapping(value = "/service/accept/customer/service", method = RequestMethod.GET)
    public String customerService() {
        return "customerService/accept/customerService";
    }
    /**
     * 客户服务==>>来电管理:
     *
     */
    /**
     * 来电跟踪
     */
    @RequestMapping(value = "/service/request/servicelog", method = RequestMethod.GET)
    public String servicelog() {
        return "customerService/callManagement/incomingtrack/list/incomingtrack_list";
    }

    /**
     * 呼损列表
     */
    @RequestMapping(value = "/service/request/giveupinbound", method = RequestMethod.GET)
    public String giveupinbound() {
        return "customerService/callManagement/callloss/list/callloss_list";
    }

    /**
     * 留言管理
     */
    @RequestMapping(value = "/service/request/noteleft", method = RequestMethod.GET)
    public String noteleft() {
        return "customerService/callManagement/leavemanage/list/leavemanage_list";
    }

    /**
     * 邮件队列
     */
    @RequestMapping(value = "/service/request/emailqueue", method = RequestMethod.GET)
    public String emailqueue() {
        return "customerService/callManagement/emailqueue/list/emailqueue_list";
    }

    /**
     * 短信队列
     */
    @RequestMapping(value = "/service/request/messagequeue", method = RequestMethod.GET)
    public String messagequeue() {
        return "customerService/callManagement/notequeue/list/notequeue_list";
    }

    /**
     * 服务历史
     */
    @RequestMapping(value = "/service/request/contactlog", method = RequestMethod.GET)
    public String contactlog() {
        return "customerService/accept/contactHistory";
    }

    /**
     * 微信客服
     */

    @RequestMapping(value = "service/request/wxaccept")
    public String wechatservice() {
        return "wechat/customer/service/list/service_list";
    }


}
