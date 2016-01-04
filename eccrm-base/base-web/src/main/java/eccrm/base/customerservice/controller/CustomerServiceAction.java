package eccrm.base.customerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 客户服务
 * 		及来电管理中的来电统计模块
 */
@Controller
public class CustomerServiceAction {

	/**
	 * 客户服务==》业务咨询   service/request/acceptreq/busiconsult
	 */
	@RequestMapping(value = "/service/request/acceptreq/busiconsult")
	public String busiconsult() {
		return "customerService/customerService/edit/customerService_edit";
	}
	
	/**
	 * 客户服务==》业务受理   service/request/accept/busisiservice
	 */
	@RequestMapping(value = "/service/request/accept/busisiservice")
	public String busisiservice() {
		return "customerService/customerService/edit/customerService_edit";
	}
	
	/**
	 * 客户服务==》投诉受理  service/request/accept/complservice
	 */
	@RequestMapping(value = "/service/request/accept/complservice")
	public String complservice() {
		return "customerService/customerService/edit/customerService_edit";
	}
	
	/**
	 * 客户服务==》故障报修   service/request/accept/maintservice
	 */
	@RequestMapping(value = "/service/request/accept/maintservice")
	public String maintservice() {
		return "customerService/customerService/edit/customerService_edit";
	}
	
	/**
	 * 客户服务==》服务记录
	 */
	@RequestMapping(value = "/service/request/accept/servicetrace")
	public String servicetrace() {
		return "customerService/customerService/serviceList/list/serviceList_list";
	}
	/**
	 * 客户服务==》服务分析
	 */
	@RequestMapping(value = "/service/request/accept/servicestat")
	public String servicestat() {
		return "customerService/customerService/serviceList/edit/serviceList_edit";
	}
	
	
  //===========================================================================
	/**
     * 来电管理==》来电统计
     */
    @RequestMapping(value = "/service/request/inboundStatis")
    public String inboundStatis() {
        return "customerService/callManagement/callinStatics/edit/callinStatics_edit";
    }
	
    //===========================================================================
    /**
     * 销售管理==》销售提醒
     */
    @RequestMapping(value = "/sfa/chance/traceremind")
    public String traceremind() {
        return "sale/traceremind/edit/traceremind_edit";
    }
    
    /**
     * 销售管理==》报价单
     */
    @RequestMapping(value = "/sfa/quotation/quoationlist")
    public String quoationlist() {
    	return "sale/quotationlist/priceListTab/list/priceListTab_list";
    }
    
    //===========================================================================
    /**
     * 产品管理==》上架管理
     */
    @RequestMapping(value = "/sfa/production/release")
    public String release() {
    	return "product/release/list/release_list";
    }
    
    /**
     * 产品管理==》产品查询
     */
    @RequestMapping(value = "/sfa/production/goodsquery")
    public String goodsquery() {
    	return "product/goodsquery/list/goodsquery_list";
    }
    
    //===========================================================================
    /**
     * 销售配送==>发货查询
     */
    @RequestMapping(value = "/sfa/delivery/orderlist")
    public String orderlist() {
    	return "distribution/orderlist/list/orderlist_list";
    }
    
    /**
     * 销售配送==>发货管理
     */
    @RequestMapping(value = "/sfa/delivery/taskprint")
    public String taskprint() {
    	return "distribution/taskprint/edit/taskprint_edit";
    }
    
    /**
     * 销售配送==>应收款管理
     */
    @RequestMapping(value = "/sfa/expense/logisreceive")
    public String logisreceive() {
    	return "distribution/expense/orderlist/list/orderlist_list";
    }
    
    /**
     * 销售配送==>应付款管理
     */
    @RequestMapping(value = "/sfa/expense/logispay")
    public String logispay() {
    	return "distribution/expense/logispay/list/logispay_list";
    }
    
    
}
