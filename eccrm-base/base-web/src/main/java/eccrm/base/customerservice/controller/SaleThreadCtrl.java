package eccrm.base.customerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * 销售管理---销售线索
 * @author Administrator
 *
 */
@Controller
public class SaleThreadCtrl {
	/**
	 * 线索管理
	 */
	//sfa/oppor/maintainopp
@RequestMapping("sfa/oppor/maintainopp")
	public String saleManager() {
		return "sale/salethread/threadmanager/list/threadmanager_list";
		
	}
	/**
	 * 线索跟踪
	 * @return
	 */
@RequestMapping(value="sfa/oppor/traceopp")
	public String threadstack() {
		return "sale/salethread/threadtrack/list/threadtrack_list";
		
	}
	/**
	 * 线索查询sfa/oppor/queryopp
	 * @return
	 */
@RequestMapping(value="sfa/oppor/queryopp")
	public String threadquery() {
		return "sale/salethread/threadquery/list/threadquery_list";
		
	}

	
	//--------------销售机会------------------------
	//sfa/chance/maintainchance机会管理
@RequestMapping(value="sfa/chance/maintainchance")
	public String salechange() {
		return "sale/salechance/chancemanager/list/chancemanager_list";
		
	}
	//sfa/chance/tracechance	机会跟踪
@RequestMapping(value="sfa/chance/tracechance")
	public String jihuitrack() {
		return "sale/salechance/chancetrack/list/chancetrack_list";
		
	}
	//sfa/chance/querychance机会查询
@RequestMapping(value="sfa/chance/querychance")
	public String changequery() {
		return "sale/salechance/chancequery/list/chancequery_list";
		
	}

    //预警提示
    @RequestMapping("/sfa/order/saleswarn")
    public String saleSaleswarn() {
        return "sale/promptsWarning/list/promptsWarning_list";

    }

    //销售合同
    @RequestMapping("/sale/contract/")
    public String saleContract() {
        return "sale/contract/list/contract_list";

    }
}
