package eccrm.base.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author miles
 * @datetime 14-2-19 上午11:10
 */
@Controller
@RequestMapping("/customer/bi/analysis")
public class CustomerAnalysisAction {

    public final static String CATEGORY = "customer/analysis/category/category";
    public final static String TREND = "customer/analysis/trend/trend";
    public final static String LIVENESS = "customer/analysis/liveness/liveness";
    public final static String CONTRIBUTION = "customer/analysis/contribution/contribution";
    public final static String NEW_MEMBER = "customer/analysis/newmember/newmember";
    public final static String OPPORTUNITY = "customer/analysis/opportunity/opportunity";


    @RequestMapping(value = "/category/", method = RequestMethod.GET)
    public String category() {
        return CATEGORY;
    }

    @RequestMapping(value = "/trend/", method = RequestMethod.GET)
    public String trend() {
        return TREND;
    }

    @RequestMapping(value = "/liveness/", method = RequestMethod.GET)
    public String liveness() {
        return LIVENESS;
    }

    @RequestMapping(value = "/opportunity/", method = RequestMethod.GET)
    public String opportunity() {
        return OPPORTUNITY;
    }

    @RequestMapping(value = "/contribution/", method = RequestMethod.GET)
    public String contribution() {
        return CONTRIBUTION;
    }

    @RequestMapping(value = "/newmember/", method = RequestMethod.GET)
    public String newmember() {
        return NEW_MEMBER;
    }
}
