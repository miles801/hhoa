package eccrm.base.customerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class WorkOrderChli {
	/*
	 * 工单处理----工单跟踪
	 * 
	 */
//	    @RequestMapping(value="service/worksheet/mywftrace")
//	public String worderorderprocess() {
//		return "customerService/worderorderprocess/workordertrack/list/workordertrack_list";
//		
//	}
	    /**
	     * 工单处理---工单查询
	     */
	    @RequestMapping(value="service/worksheet/querysheet")
	    public String workorderQuery() {
			return "customerService/worderorderprocess/workorderquery/list/workorderquery_list";
			
		}
	    /**
	     * 工单处理----工单回访
	     */
	    @RequestMapping(value="service/accept/servicevisit")
	    public String workorderhuifang() {
			return "customerService/worderorderprocess/workordervisit/list/workordervisit_list";
			
		}

}
