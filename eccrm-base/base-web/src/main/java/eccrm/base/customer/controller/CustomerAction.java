package eccrm.base.customer.controller;

import com.ycrl.core.web.BaseController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/customer")
// 根路径
public class CustomerAction extends BaseController {
    public static final String PAGE_TYPE = "pageType";
    private Logger logger = Logger.getLogger(CustomerAction.class);


    //联系人列表页面
    @RequestMapping(value = "/linkman")
    public String linkman_list(Model model) {
        logger.info("查询");
        return "customer/linkman/list/linkman_list";
    }

}
