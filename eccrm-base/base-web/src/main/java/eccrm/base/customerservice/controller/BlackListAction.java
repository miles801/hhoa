package eccrm.base.customerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlackListAction {
	/**
	 * 客户服务---黑名单
	 */
	// 业务规则
	@RequestMapping(value ="channel/blacklist")
	public String businessrules() {
		return "customerService/blacklist/businessrules/list/businessrules_list";

	}

	// 业务黑名单
	@RequestMapping(value = "channel/blacklist/busilistset")
	public String businessblacklist() {
		return "customerService/blacklist/businessblacklist/list/businessblacklist_list";

	}

	// 黑名单审核
	@RequestMapping(value = "channel/blacklist/check")
	public String blacklistapply() {
		return "customerService/blacklist/blacklistapply/list/blacklistapply_list";

	}

	// 黑名单管理 channel/blacklist/record
	@RequestMapping(value = "channel/blacklist/record")
	public String blacklistmanager() {
		return "customerService/blacklist/blacklistmanager/list/blacklistmanager_list";

	}
	//黑名单查询channel/blacklist/querylist
	@RequestMapping(value = "channel/blacklist/querylist")
	public String blacklistwquery() {
		return "customerService/blacklist/blacklistquery/list/blacklistquery_list";

	}
	//便签本home/tools/notebook
	@RequestMapping(value="home/tools/notebook")
	public String padbookString() {
		return "home/padbooklist/edit/padbooklist_edit";
		
	}

}
