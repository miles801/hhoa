package eccrm.freemarker.marketing.execute;

import eccrm.freemarker.TestEditPage;
import eccrm.utils.codeutils.CodeTemplateUtils;
import eccrm.utils.codeutils.EditPage;
import eccrm.utils.codeutils.ListPage;
import eccrm.utils.codeutils.Module;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * @author miles
 * @datetime 13-12-31 上午10:12
 * 营销活动查询
 */
public class TestMarketingActivity {
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


    //营销活动查询
    public void testMarketingActivityQuery() throws Exception {
        Module module = new Module("marketing", "activityQuery", "营销活动查询");
        module.setAuthor("miles");
        module.setPermitOverride(true);
        module.setExtraPath("activity");

        ListPage listPage = new ListPage();
        listPage.setHeaderText("营销活动查询");
        listPage.setConditionRows(2);
        listPage.addQueryCondition("项目:label", "project:text:col-2-half:remove", "类型:label", "type:select", "执行阶段:label", "executeState:select:col-2")
                .addQueryCondition("活动名称:label", "activityName", "负责人:label", "manager:text:col-2-half:remove");
        listPage.addQueryBarButton("查询:search", "高级查询:asterisk", "重置:repeat");
        listPage.addItem("主题", "活动类型", "类别", "所属项目", "渠道", "执行方式", "开始时间", "结束时间", "管理部门"
                , "负责人", "状态", "实际结束时间");
        listPage.setTableHeaderText("营销活动查询列表");
        listPage.setAllowCheckbox(false);
        module.setListPage(listPage);
        CodeTemplateUtils codeUtils = new CodeTemplateUtils(config, root);
        codeUtils.generateList(module);

    }


    //营销记录查询
    public void testMarketingRecordQuery() throws Exception {
        Module module = new Module("marketing", "marketingRecord", "营销记录");
        module.setAuthor("miles");
        module.setPermitOverride(true);
        module.setExtraPath("execute");

        ListPage listPage = new ListPage();
        listPage.setHeaderText("营销记录查询");
        listPage.setConditionRows(2);
        listPage.setDatepicker(true);
        listPage.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址/号码:label", "address:text:col-2")
                .addQueryCondition("结案时间:label", "startDate:date-range", "营销结果:label", "marketingResult:select:col-2");
        listPage.addQueryBarButton("查询:search", "高级查询:asterisk", "重置:repeat");
        listPage.addItem("姓名,性别,证件类型,证件号码,来源,营销活动,营销名单,分配时间,联络次数,营销结果,状态,结案时间,订单编号,订单状态,配送状态,支付状态");
        listPage.setTableHeaderText("营销记录查询列表");
        listPage.setAllowCheckbox(false);
        module.setListPage(listPage);
        CodeTemplateUtils codeUtils = new CodeTemplateUtils(config, root);
        codeUtils.generateList(module);

    }

    private void log(String info) {
        logger.info(info + "................");
    }
}
