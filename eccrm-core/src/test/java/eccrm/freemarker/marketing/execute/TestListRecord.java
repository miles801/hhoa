package eccrm.freemarker.marketing.execute;

import eccrm.utils.codeutils.Module;
import eccrm.utils.codeutils.TabEditPage;
import eccrm.utils.codeutils.TabListPage;
import org.junit.Test;

/**
 * @author miles
 * @datetime 13-12-31 下午4:45
 * 营销执行--我的名单--名单记录
 */
public class TestListRecord extends BaseTest {

    //营销名单记录编辑页面
    public void testMarketingListRecordEdit() throws Exception {
        log("营销名单记录编辑页面");
        Module module = new Module("marketing", "listRecord", "营销名单记录");
        module.setAuthor("miles");
        module.setPermitOverride(true);
        module.setExtraPath("execute");
        TabEditPage editPage = new TabEditPage();
        editPage.setDatepicker(true);
        editPage.setHeaderText("营销记录详细");
        //name:type:length:addOnIcon
        editPage.addFormRow(
                "姓名:label", "name", "性别:label", "sex(男,女):radio", "客户号:label", "consumerNo:text:col-2"
        ).addFormRow(
                "证件类型:label", "certificateType:select", "证件号码:label", "certificateNo:text", "生日:label", "birthday:text:col-2"
        ).addFormRow(
                "学历:label", "education", "职业:label", "career:select", "收入范围:label", "incomingRange:select:col-2"
        ).addFormRow(
                "婚姻状况:label", "marry(已婚,未婚):radio", "级别:label", "level:select", "负责人:label", "manager:text:col-2"
        ).addFormRow(
                "详细地址:label", "province:select:col-2", "city:select:col-2", "region:select:col-2", "address:text:col-4"
        ).addFormRow(
                "邮编:label", "postcode"
        ).addFormRow(
                "手机:label", "mobilePhone",
                "家庭电话:label", "homeTel",
                "办公电话:label", "officeTel:text:col-2"
        );
        editPage.addTab("contactHistory:联络记录:app/marketing/execute/activity/orderList/list/orderList_list.jsp",
                "orderHistory:订购历史:app/marketing/execute/activity/todayAssignedList/list/todayAssignedList_list.jsp",
                "serverOrder:服务单:app/marketing/execute/activity/notCalledlist/list/notCalledlist_list.jsp",
                "businessOpportunity:业务机会:app/marketing/execute/activity/needFollowList/list/needFollowList_list.jsp",
                "taskItems:任务事项:app/marketing/execute/activity/closedList/list/closedList_list.jsp",
                "operationLogs:操作历史:app/marketing/execute/activity/recycledList/list/recycledList_list.jsp");
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
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址:label", "address:text:col-2")
                .addQueryCondition("预约时间:label", "startDate:date-range", "预约事项:label", "orderItem:text:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
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
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址/号码:label", "address:text:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addTableHeaderButton("顺序获取:filter");
        page.setHeaderText("今日分配名单列表");
        page.addItem("姓名,性别,证件类型,证件号码,客户代码,来源,手机,家庭电话,办公电话,E-MAIL,拨打次数,上次联系时间,联络结果,营销进度,营销结果,下次联络时间,操作");
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
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "地址/号码:label", "address:text:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addTableHeaderButton("顺序获取:filter");
        page.setHeaderText("未拨打名单列表");
        page.addItem("姓名,性别,证件类型,证件号码,来源,客户代码,负责人,分配人,分配时间,操作");
        module.setListPage(page);
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
        page.setDatepicker(true);
        page.setHeaderText("结案名单");
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
        page.addQueryCondition("姓名:label", "name", "证件号码:label", "IDNo", "分配人:label", "assignedPerson:text:col-2")
                .addQueryCondition("分配时间:label", "startDate:date-range", "营销结果:label", "marketingResult:select:col-2");
        page.addHeaderButton("查询:search", "高级查询:asterisk", "重置:repeat");
        page.addItem("姓名,性别,证件类型,证件号码,来源,客户代码,分配时间,联络次数,是否预约,上次联络时间,联络结果,营销进度,营销结果,状态,操作");
        page.setHeaderText("全部营销名单列表");
        module.setListPage(page);
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

}
