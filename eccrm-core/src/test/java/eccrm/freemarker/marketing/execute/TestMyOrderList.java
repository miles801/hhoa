package eccrm.freemarker.marketing.execute;

import eccrm.freemarker.TestEditPage;
import eccrm.utils.codeutils.*;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * @author miles
 * @datetime 13-12-31 下午2:39
 * 营销执行--我的名单--我的名单菜单
 */
public class TestMyOrderList {
    private Configuration config = null;
    private String root;
    private Logger logger = Logger.getLogger(TestEditPage.class);
    CodeTemplateUtils codeTemplateUtils;


    public void setUp() throws Exception {
        config = new Configuration();
        root = System.getProperty("user.dir") + "/eccrm-web\\src\\main\\webapp\\app\\";
        config.setTemplateLoader(new ClassTemplateLoader(EditPage.class, "/"));
        codeTemplateUtils = new CodeTemplateUtils(config, root);
    }

    private void log(String info) {
        logger.info(info + "................");
    }


    //回收的名单tab
    public void testAllOrderList() throws Exception {
        log("我的营销名单");
        Module module = new Module("marketing", "calllist", "我的名单");
        module.setExtraPath("execute");
        module.setAuthor("miles");
        ListPageTree page = new ListPageTree();
        page.setType(PageType.MARKETING_EXECUTE_ORDER.getType());
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }


    public void testAllList() throws Exception {
        log("我的所有名单");
        Module module = new Module("marketing", "all", "我的所有名单");
        module.setExtraPath("execute/calllist");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setHeaderText("我的营销名单");
        page.setConditionRows(2);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "营销活动:label", "marketingActivity:select:col-2")
                .addQueryCondition("营销名单:label", "marketingOrder:text:col-2-half:remove", "营销进度:label", "marketingProcess:select");
        page.setTableHeaderText("营销名单列表");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,营销活动,营销名单,分配时间,联络次数,是否预约,上次联络时间,联络结果,营销进度,营销结果,状态,操作");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);

    }


    //预约名单
    public void testAppointmentList() throws Exception {
        log("预约名单");
        Module module = new Module("marketing", "appointment", "预约名单");
        module.setExtraPath("execute/calllist");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setHeaderText("我的营销名单");
        page.setDatepicker(true);
        page.setConditionRows(2);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址/号码:label", "address:text:col-2")
                .addQueryCondition("预约时间:label", "startDate:date-range", "预约事项:label", "marketingReason:text:col-2");
        page.setTableHeaderText("营销名单列表");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,营销活动,营销名单,上次联络时间,联络结果,营销进度,营销结果,预约时间,预约事项,联系方式,地址/号码,操作");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }


    //预约名单
    public void testNotCalled() throws Exception {
        log("未拨打名单");
        Module module = new Module("marketing", "notCalled", "未拨打名单");
        module.setExtraPath("execute/calllist");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setConditionRows(3);
        page.setDatepicker(true);
        page.setHeaderText("我的营销名单");
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "分配:label", "assigned:text:col-2")
                .addQueryCondition("分配时间:label", "startDate:date-range", "营销活动:label", "marketingActivity:text:col-2:remove")
                .addQueryCondition("营销名单:label", "marketingList:text:col-2-half:remove");
        page.setTableHeaderText("营销名单列表");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,客户代码,负责人,分配人,分配时间,操作");
        page.addTableHeaderButton("顺序获取:filter");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }


    //需跟进名单
    public void testNeedFollow() throws Exception {
        log("需跟进名单");
        Module module = new Module("marketing", "needFollow", "需跟进名单");
        module.setExtraPath("execute/calllist");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setDatepicker(true);
        page.setConditionRows(3);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址/号码:label", "address:text:col-2")
                .addQueryCondition("下次联络时间:label", "startDate:date-range", "营销进度:label", "marketingProcess:select:col-2")
                .addQueryCondition("营销结果:label", "marketingResult:select");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,营销活动,营销名单,联络次数,是否预约,上次联络时间,联络结果,营销进度,营销结果,下次联络时间,操作");
        page.addTableHeaderButton("顺序获取:filter");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }


    //结案名单
    public void testClosed() throws Exception {
        log("结案名单");
        Module module = new Module("marketing", "closed", "结案名单");
        module.setExtraPath("execute/calllist");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setDatepicker(true);
        page.setConditionRows(2);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址/号码:label", "address:text:col-2")
                .addQueryCondition("结案时间:label", "startDate:date-range", "营销结果:label", "marketingResult:select:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,营销活动,营销名单,分配时间,联络次数,营销结果,状态,结案时间,订单编号,订单状态,配送状态,支付状态");
        page.addTableHeaderButton("顺序获取:filter");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }
}
