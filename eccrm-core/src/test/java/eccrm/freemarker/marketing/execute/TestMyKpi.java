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
 * @datetime 13-12-31 下午4:09
 * 营销活动--我的指标
 */
public class TestMyKpi {
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


    public void testMyKpiList() throws Exception {
        log("我的营销指标");
        Module module = new Module("marketing", "myKpi", "营销指标");
        module.setExtraPath("execute");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setHeaderText("我的指标");
        page.addHeaderButton("收起:arrow-up");
        page.addQueryCondition("月份:label", "startYear:select:col-1-half", "startMonth:select:col-1-half",
                "'--':text-only-center:col-1", "endYear:select:col-1-half", "endMonth:select:col-1-half");
        page.addHeaderButton("查询:search", "重置:repeat");
        page.addItem("月份,外呼电话量,转化率,销售金额,成交金额");
        page.setAllowCheckbox(false);
        page.setTableHeaderText("我的指标列表");
        page.setAllowPager(true);
        page.setConditionRows(1);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);

    }
}
