package eccrm.freemarker.marketing.execute;

import eccrm.utils.codeutils.ListPage;
import eccrm.utils.codeutils.Module;
import org.junit.Test;

/**
 * @author miles
 * @datetime 14-1-7 上午2:41
 * 营销管理--营销监控
 */
public class TestMarketingMonitor extends BaseTest {

    /**
     * 员工营销监控分析
     *
     * @throws Exception
     */

    public void testEmployeeMarketingMonitorAnalyze() throws Exception {
        log("员工营销监控分析");
        Module module = new Module("marketing", "employeeAnalyze", "员工营销监控");
        module.setExtraPath("monitor");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.addQueryCondition("营销项目:label", "project:text:col-2-half:remove", "营销活动:label", "activity:text:col-2-half:remove", "用户组:label", "usergroup:text:col-2:remove")
                .addQueryCondition("姓名:label", "employeeName:text:col-2-half:remove", "工号:label", "employeeNo:text:col-2-half:remove");
        page.addQueryBarButton("查询:search", "重置:repeat");
        page.addTableHeaderButton("导出:export");
        page.setConditionRows(2);
        page.addItem("姓名,工号,性别,岗位,工作时长,分配名单数,首拨名单量,拨打名单量,拨打次数,无效名单数,线上成交量,线上成交金额,成长率,通话时长,通话利用率");
        page.setHeaderText("员工今日营销监控分析列表");
        page.setAllowCheckbox(false);
        page.setAllowPager(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }

    /**
     * 营销活动监控分析
     */

    public void testActivityMonitorAnalyze() throws Exception {
        log("营销活动监控分析");
        Module module = new Module("marketing", "activityAnalyze", "营销活动监控分析");
        module.setExtraPath("monitor");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setHeaderText("营销活动监控");
        page.addQueryCondition("营销项目:label", "project:text:col-2-half:remove",
                "营销活动:label", "activity:text:col-2-half:remove");
        page.addQueryBarButton("查询:search", "重置:repeat");
        page.setTableHeaderText("团队销售达成分析");
        page.addItem("用户组,分配名单数,呼出量,接通量,成交量,平均通话时长,预期金额,销售金额,达成率");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(false);//谨慎覆写，该文件生成后需要手动更改文件报表内容
        codeTemplateUtils.generateList(module);
    }

    /**
     * 营销名单统计分析
     */

    public void testGroupMarketingAnalyze() throws Exception {
        log("组营销业绩统计分析");
        Module module = new Module("marketing", "groupAnalyze", "组营销业绩统计分析");
        module.setExtraPath("monitor");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setHeaderText("组营销业绩统计分析");
        page.addQueryCondition("营销项目:label", "project:text:col-2-half:remove",
                "营销活动:label", "activity:text:col-2-half:remove",
                "用户组:label", "usergroup:text:col-2:remove");
        page.addQueryBarButton("查询:search", "重置:repeat");
        page.setTableHeaderText("员工销售业绩");
        page.addItem("用户,分配名单数,回收量,有效名单量,呼出量,接通量,接通率,平均通话时长,成交量,成单率,销售金额,妥投金额");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(false);//谨慎覆写，该文件生成后需要手动更改文件报表内容
        codeTemplateUtils.generateList(module);
    }

    /**
     * 营销名单状态统计分析
     */

    public void testMarketingListAnalyze() throws Exception {
        log("营销名单统计分析");
        Module module = new Module("marketing", "marketingListAnalyze", "营销名单统计分析");
        module.setExtraPath("monitor");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setHeaderText("营销名单状态统计分析");
        page.addQueryCondition("营销项目:label", "project:text:col-2-half:remove",
                "营销活动:label", "activity:text:col-2-half:remove");
        page.addQueryBarButton("查询:search", "重置:repeat");
        page.setTableHeaderText("营销名单状态分析");
        page.addItem("分配记录数,空号,错号,传真,停机,客户考虑中,订单报价中,订单配送中,成功,拒绝访谈,产品已购买,价格高,品牌不认可");
        page.setAllowCheckbox(false);
        page.setAllowPager(false);
        module.setListPage(page);
        module.setPermitOverride(false);//谨慎覆写，该文件生成后需要手动更改文件报表内容
        codeTemplateUtils.generateList(module);
    }
}
