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
 * @datetime 13-12-31 下午4:05
 * 营销执行--公共名单
 */
public class TestCommonList {
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


    public void testList() throws Exception {
        log("营销名单池");
        Module module = new Module("marketing", "orderPool", "名单池");
        module.setExtraPath("execute");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setConditionRows(2);
        page.setHeaderText("营销名单-名单池");
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址/号码:label", "address:text:col-2")
                .addQueryCondition("营销活动:label", "marketingActivity:text:col-2-half:remove", "营销名单:label", "marketingList:text:col-2-half:remove");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.setTableHeaderText("营销名单列表");
        page.addTableHeaderButton("顺序获取:filter");
        page.addItem("姓名,性别,证件类型,证件号码,来源,营销活动,营销名单");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);

    }
}
