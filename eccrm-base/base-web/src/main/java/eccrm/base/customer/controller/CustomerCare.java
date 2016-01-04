package eccrm.base.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by liuxq on 14-3-4.
 * 客户关怀
 */
@Controller
public class CustomerCare {

    //关怀记录
    @RequestMapping(value = "/service/custcare/carelog", method = RequestMethod.GET)
    public String carelog() {
        return "customerService/customerCare/customerPlan/careRecord/list/careRecord_list";
    }

    //关怀计划
    @RequestMapping(value = "/service/custcare/careplan", method = RequestMethod.GET)
    public String careplan() {
        return "customerService/customerCare/customerPlan/list/customerPlan_list";
    }

    //  新建关怀
    @RequestMapping(value = "/service/custcare/carecreate", method = RequestMethod.GET)
    public String carecreate() {
        return "customerService/customerCare/createCare/edit/createCare_edit";
    }

    //  客户关怀
    @RequestMapping(value = "/service/custcare/docustcare", method = RequestMethod.GET)
    public String docustcare() {
        return "customerService/customerCare/customersCare/list/customersCare_list";
    }
}
