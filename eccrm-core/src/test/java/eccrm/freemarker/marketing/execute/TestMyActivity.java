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
 * @datetime 13-12-30 上午10:16
 * 营销执行--我的活动
 */
public class TestMyActivity {
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


    //我的活动列表页面--执行中营销活动列表
    public void testExecuteActivity() throws Exception {
        Module module = new Module("marketing", "activity", "执行中营销活动");
        module.setAuthor("miles");
        module.setPermitOverride(true);
        module.setExtraPath("execute");

        ListPage listPage = new ListPage();
        listPage.addItem("主题", "活动类型", "类别", "所属项目", "渠道", "执行方式", "开始时间", "结束时间", "管理部门"
                , "负责人", "分配时间", "分配任务", "已执行任务", "状态", "操作");
        listPage.setTableHeaderText("执行中营销活动列表");
        listPage.setAllowCheckbox(false);
        module.setListPage(listPage);
        CodeTemplateUtils codeUtils = new CodeTemplateUtils(config, root);
        codeUtils.generateList(module);

    }


    //营销活动执行页面
    public void testMarketingActivityExecute() throws Exception {
        log("营销活动edit");
        Module module = new Module("marketing", "activity", "营销活动");
        module.setAuthor("miles");
        module.setPermitOverride(true);
        module.setExtraPath("execute");
        TabEditPage editPage = new TabEditPage();
        editPage.setDatepicker(true);
        editPage.setHeaderText("营销活动");
        editPage.addHeaderButton("收起:arrow-up", "统计分析:usd:app/marketing/execute/activity/executionAnalyze/list/executionAnalyze_list.jsp");
        //name:type:length:addOnIcon
        editPage.addFormRow(
                "主题:label", "subject:text:col-6-half", "活动类型:label", "code:select:col-2"
        ).addFormRow(
                "业务类别:label", "type1:select", "type2:select", "type3:select"
        ).addFormRow(
                "活动内容:label", "content:textarea:col-10"
        ).addFormRow(
                "所属项目:label", "projectName:text:col-2-half:remove", "开始时间:label",
                "startDate:date", "结束时间:label", "endDate:date:col-2"
        ).addFormRow(
                "分配时间:label", "assignedDateTime:text-only",
                "分配任务数量:label", "assignedTaskCount:text-only", "状态:label", "status:select:col-2"
        );
        editPage.addTab(
                "orderList:预约名单:app/marketing/execute/activity/orderList/list/orderList_list.jsp",
                "todayAssignedList:今日分配名单:app/marketing/execute/activity/todayAssignedList/list/todayAssignedList_list.jsp",
                "notCalledlist:未拨打名单:app/marketing/execute/activity/notCalledlist/list/notCalledlist_list.jsp",
                "needFollowList:需跟进名单:app/marketing/execute/activity/needFollowList/list/needFollowList_list.jsp",
                "closedList:结案名单:app/marketing/execute/activity/closedList/list/closedList_list.jsp",
                "recycledList:回收的名单:app/marketing/execute/activity/recycledList/list/recycledList_list.jsp",
                "allList:全部名单:app/marketing/execute/activity/allList/list/allList_list.jsp");
        module.setEditPage(editPage);
        codeTemplateUtils.generateEdit(module);
    }


    //预约名单tab
    public void testTabOrderList() throws Exception {
        log("预约名单tab");
        Module module = new Module("marketing", "orderList", "预约名单");
        module.setExtraPath("execute/activity");
        module.setAuthor("miles");
        TabListPage page = new TabListPage();
        page.setDatepicker(true);
        page.setConditionRows(2);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址:label", "address:text:col-2")
                .addQueryCondition("预约时间:label", "startDate:date-range", "预约事项:label", "orderItem:text:col-2");
        page.addTableHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,客户代码,联络次数,上次联络时间,预约时间,预约事项,联络方式,地址/号码,操作");
        page.setHeaderText("预约营销名单列表");
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateListPage(module);
        codeTemplateUtils.generateListPageJs(module);
    }


    //今日分配名单tab
    public void testTabTodayAssignedList() throws Exception {
        log("今日分配名单tab");
        Module module = new Module("marketing", "todayAssignedList", "今日分配名单");
        module.setExtraPath("execute/activity");
        module.setAuthor("miles");
        TabListPage page = new TabListPage();
        page.setConditionRows(1);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址/号码:label", "address:text:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addTableHeaderButton("顺序获取:filter");
        page.setHeaderText("今日分配名单列表");
        page.addItem("姓名,性别,证件类型,证件号码,客户代码,来源,手机,家庭电话,办公电话,E-MAIL,拨打次数,上次联系时间,联络结果,营销进度,营销结果,下次联络时间,操作");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }


    //未拨打名单名单tab
    public void testNotCalledlist() throws Exception {
        log("未拨打名单tab");
        Module module = new Module("marketing", "notCalledlist", "未拨打名单");
        module.setExtraPath("execute/activity");
        module.setAuthor("miles");
        TabListPage page = new TabListPage();
        page.setConditionRows(1);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址/号码:label", "address:text:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addTableHeaderButton("顺序获取:filter");
        page.setHeaderText("未拨打名单列表");
        page.addItem("姓名,性别,证件类型,证件号码,来源,客户代码,负责人,分配人,分配时间,操作");
        module.setListPage(page);
        page.setAllowCheckbox(false);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }


    //需跟进名单tab
    public void testTabNeedFollowList() throws Exception {
        log("需跟进名单tab");
        Module module = new Module("marketing", "needFollowList", "需跟进名单");
        module.setExtraPath("execute/activity");
        module.setAuthor("miles");
        TabListPage page = new TabListPage();
        page.setDatepicker(true);
        page.setConditionRows(2);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址:label", "address:text:col-2")
                .addQueryCondition("上次联络时间:label", "startDate:date-range", "上次联络结果:label", "contactResult:select:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,客户代码,联络次数,是否预约,上次联络时间,联络结果,营销进度,营销结果,下次联络时间,操作");
        page.setHeaderText("需跟进营销名单列表");
        page.addTableHeaderButton("结案:saved");
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }


    //结案名单tab
    public void testTabClosedList() throws Exception {
        log("结案名单tab");
        Module module = new Module("marketing", "closedList", "结案名单");
        module.setExtraPath("execute/activity");
        module.setAuthor("miles");
        TabListPage page = new TabListPage();
        page.setConditionRows(2);
        page.setDatepicker(true);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址:label", "address:text:col-2")
                .addQueryCondition("结案时间:label", "startDate:date-range", "上次联络结果:label", "contactResult:select:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,客户代码,分配时间,联络次数,营销结果,状态,结案时间,订单编号,订单状态,配送状态,支付状态");
        page.setHeaderText("结案名单列表");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }


    //回收的名单tab
    public void testTabAllList() throws Exception {
        log("所有的名单tab");
        Module module = new Module("marketing", "allList", "所有的名单");
        module.setExtraPath("execute/activity");
        module.setAuthor("miles");
        TabListPage page = new TabListPage();
        page.setDatepicker(true);
        page.setConditionRows(2);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "分配人:label", "assignedPerson:text:col-2")
                .addQueryCondition("分配时间:label", "startDate:date-range", "营销结果:label", "marketingResult:select:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,客户代码,分配时间,联络次数,是否预约,上次联络时间,联络结果,营销进度,营销结果,状态,操作");
        page.setHeaderText("全部营销名单列表");
        module.setListPage(page);
        page.setAllowCheckbox(false);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }


    //回收的名单tab
    public void testTabRecycledList() throws Exception {
        log("回收的名单tab");
        Module module = new Module("marketing", "recycledList", "回收的名单");
        module.setExtraPath("execute/activity");
        module.setAuthor("miles");
        TabListPage page = new TabListPage();
        page.setConditionRows(2);
        page.setDatepicker(true);
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "回收人:label", "recyclePerson:text:col-2")
                .addQueryCondition("回收时间:label", "startDate:date-range", "回收原因:label", "recycleReason:select:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,客户代码,分配时间,联络次数,最后联络时间,联络结果,营销进度,回收人,回收方式,回收时间,回收原因");
        page.setHeaderText("回收的名单列表");
        page.setAllowCheckbox(false);
        module.setListPage(page);
        module.setPermitOverride(true);
        codeTemplateUtils.generateList(module);
    }


    //营销活动执行统计分析页面
    public void testAnalyzeList() throws Exception {
        log("营销活动执行统计分析页面");
        Module module = new Module("marketing", "executionAnalyze", "营销活动执行统计分析");
        module.setExtraPath("execute/activity");
        module.setAuthor("miles");
        ListPage page = new ListPage();
        page.setDatepicker(false);
        page.setHighcharts(true);
        page.setHeaderText("营销活动执行统计分析");
        page.addHeaderButton("返回:arrow-left:'',back();");
        page.addItem("分配数,呼出量,成交量,成单率,销售金额,妥投金额,排名");
        page.setAllowCheckbox(false);
        page.setAllowPager(false);
        module.setListPage(page);
        module.setPermitOverride(false);
        codeTemplateUtils.generateList(module);
    }


    private void log(String info) {
        logger.info(info + "................");
    }
}
