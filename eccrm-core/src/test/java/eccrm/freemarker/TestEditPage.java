package eccrm.freemarker;

import eccrm.utils.codeutils.*;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * @author miles
 * @datetime 13-12-23 下午2:24
 * 列表页面
 */
public class TestEditPage {
    private Configuration config = null;
    private String root;
    private Logger logger = Logger.getLogger(TestEditPage.class);


    public void setUp() throws Exception {
        config = new Configuration();
        root = System.getProperty("user.dir") + "/eccrm-web\\src\\main\\webapp\\app\\";
        config.setTemplateLoader(new ClassTemplateLoader(EditPage.class, "/"));
    }


    public void testInit() throws Exception {
        Assert.assertNotNull(config);
    }

    //生成营销项目

    public void testGenerateMarketing() throws Exception {
        Module module = new Module("marketing", "project", "营销项目");
        module.setAuthor("miles");
        module.setPermitOverride(true);
        module.setExtraPath("plan");
        TabEditPage editPage = new TabEditPage();
        editPage.setSaveAndNew(true);
        editPage.setDatepicker(true);
        //name:type:length:addOnIcon
        editPage.addFormRow(
                "项目名称:label", "name:text:col-6-half", "编号:label", "code:text:col-2"
        ).addFormRow(
                "类型:label", "type:text"
        ).addFormRow(
                "阶段:label", "stage:select", "开始时间:label", "startDate:date", "结束时间:label", "endDate:date:col-2"
        ).addFormRow(
                "所属部门:label", "deptId:text:col-2-half:remove", "负责人:label", "leaderId:text:col-2-half:remove"
        ).addFormRow(
                "项目简介:label", "description:textarea:col-10"
        ).addFormRow(
                "执行方案:label", "executeSchema:textarea:col-10"
        ).addFormRow(
                "备注:label", "remark:textarea:col-10"
        );
        editPage.addTab("document:文档附件:#", "operateLogs:操作附件:#");
        module.setEditPage(editPage);

        ListPage listPage = new ListPage();
        listPage.addQueryCondition(
                "项目名称:label", "projectName:text", "类型:label", "type:select", "执行阶段:label", "stage:select:col-2"
        );
        listPage.addQueryCondition(
                "所属部门:label", "dept:text:col-2-half:remove", "负责人:label", "manager:text:col-2-half:remove"
        );
        listPage.addQueryBarButton("查询:search", "高级查询:asterisk", "重置:repeat");
        listPage.addTableHeaderButton("增加:plus:marketing/plan/project/add", "删除:remove:'':remove();");
        listPage.addItem("项目名称", "编号", "类型", "阶段", "开始时间", "结束时间", "所属部门", "负责人", "状态", "操作");
        module.setListPage(listPage);
        CodeTemplateUtils codeUtils = new CodeTemplateUtils(config, root);
        codeUtils.generateAll(module);
    }

    //模块名称

    public void testName() throws Exception {
        //

    }

}
