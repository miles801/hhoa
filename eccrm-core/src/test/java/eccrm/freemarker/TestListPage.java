package eccrm.freemarker;

import eccrm.utils.codeutils.ListPage;
import eccrm.utils.codeutils.Module;
import eccrm.utils.codeutils.ModuleThreadLocal;
import eccrm.utils.codeutils.Row;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author miles
 * @datetime 13-12-23 下午2:24
 * 列表页面
 */
public class TestListPage {
    private Configuration config = null;
    private String root;


    public void setUp() throws Exception {
        config = new Configuration();
        root = System.getProperty("user.dir") + "/eccrm-web\\src\\main\\webapp\\app\\";
        config.setTemplateLoader(new ClassTemplateLoader(TestListPage.class, "/"));
    }


    public void testInit() throws Exception {
        Assert.assertNotNull(config);
    }

    //生成营销项目

    public void testGenerateMarketing() throws Exception {
        Module module = new Module("marketing", "marketing", "营销项目");
        module.setPermitOverride(true);
        module.setAuthor("miles");
        ListPage listPage = new ListPage();
        listPage.addQueryCondition(new Row(
                "项目名称:label", "projectName",
                "类型:label", "type:select",
                "执行阶段:label", "stage:select"
        ));
        listPage.addQueryCondition(new Row(
                "所属部门:label",
                "dept",
                "负责人:label",
                "manager"
        ));
        listPage.addQueryBarButton("查询:search", "高级查询:asterisk", "重置:repeat");
        listPage.addTableHeaderButton("新建:plus:marketing/add", "删除:remove");
        listPage.addItem("项目名称", "编号", "类型", "阶段", "开始时间", "结束时间", "所属部门", "负责人", "状态", "操作");
        module.setListPage(listPage);

        generateAll(module);
    }
/*

    //生成销售记录
    
    public void testGenerateSalesRecord() throws Exception {
        Module module = new Module("salesRecord", "销售记录");
        module.setAuthor("miles");
        module.setEditUrl("salesRecord/add");
        ListPage listPage = new ListPage();
        listPage.addButtonRow("姓名", "证件号码", "负责人")
                .addButtonRow("营销结果");
        listPage.addButton("查询", "高级查询", "重置");
        listPage.addListButton("添加", "删除");
        listPage.addItem("姓名", "性别", "证件类型", "证件号码", "联系目的", "联系次数", "最后联络时间", "负责人", "营销结果", "备注");
        module.setListPage(listPage);

        generateAll(module);
    }

    //生成操作日志
    
    public void testGenerateOperationLogs() throws Exception {
        Module module = new Module("operationLogs", "操作日志");
        module.setAuthor("miles");
        module.setEditUrl("operationLogs/add");
        ListPage listPage = new ListPage();
        listPage.addButtonRow("执行人", "操作时间");
        listPage.addButton("查询", "高级查询", "重置");
        listPage.addListButton("添加", "删除");
        listPage.addItem("序号", "执行人", "任务类型", "开始时间", "结束时间", "耗时", "状态", "处理说明");
        module.setListPage(listPage);

        generateAll(module);
    }

    //生成组指标设置
    
    public void testGenerateGroupKpiConfig() throws Exception {
        Module module = new Module("groupKpiConfig", "组指标设置");
        module.setAuthor("miles");
        module.setEditUrl("groupKpiConfig/add");
        ListPage listPage = new ListPage();
        listPage.setDatepicker(true);
        listPage.addButtonRow("用户组", "负责人", "月份");
        listPage.addButtonRow("状态");
        listPage.addButton("查询", "重置");
        listPage.addListButton("批量设置", "设置", "删除");
        listPage.addItem("用户组", "负责人", "月份", "外呼电话量", "转化率", "销售金额", "成交金额", "状态", "操作");
        module.setListPage(listPage);

        generateAll(module);
    }
    //生成员工指标设置
    
    public void testGenerateEmployeeKpiConfig() throws Exception {
        Module module = new Module("employeeKpiConfig", "员工指标设置");
        module.setAuthor("miles");
        module.setEditUrl("employeeKpiConfig/add");
        ListPage listPage = new ListPage();
        listPage.setDatepicker(true);
        listPage.addButtonRow("用户组", "姓名", "月份");
        listPage.addButtonRow("状态");
        listPage.addButton("查询", "重置");
        listPage.addListButton("批量设置", "设置", "删除");
        listPage.addItem("姓名", "工号", "月份", "外呼电话量", "转化率", "销售金额", "成交金额", "状态", "操作");
        module.setListPage(listPage);

        generateAll(module);
    }

    //生成执行团队
    
    public void testGenerateOperateTeam() throws Exception {
        Module module = new Module("operateTeam", "执行团队");
        module.setEditUrl("operateTeam/add");
        ListPage listPage = new ListPage();
        listPage.setSearch(false);
        listPage.addListButton()
        listPage.addItem("用户组", "上级组", "负责人", "状态");
        module.setListPage(listPage);

        generateAll(module);
    }

    //生成产品
    
    public void testGenerateProduction() throws Exception {
        Module module = new Module("production", "产品");
        module.setAuthor("miles");
        module.setEditUrl("production/add");
        ListPage listPage = new ListPage();
        listPage.setSearch(false);
        listPage.addItem("产品名称/编号", "类别", "规格", "销售方式", "状态");
        module.setListPage(listPage);

        generateAll(module);
    }

    //生成行销活动
    
    public void testGenerateSellActivity() throws Exception {
        Module module = new Module("sellActivity", "行销活动");
        module.setEditUrl("sellActivity/add");
        module.setAuthor("miles");
        ListPage listPage = new ListPage();
        listPage.setSearch(false);
        listPage.addItem("活动名称", "活动类型", "开始时间", "结束时间", "描述", "状态");
        module.setListPage(listPage);

        generateAll(module);
    }

    //生成话术模板
    
    public void testGenerateWordsArtTemplate() throws Exception {
        Module module = new Module("wordsArtTemplate", "话术模板");
        module.setEditUrl("wordsArtTemplate/add");
        module.setAuthor("miles");
        ListPage listPage = new ListPage();
        listPage.setSearch(false);
        listPage.addItem("主题", "类型", "引用方式", "名称", "状态");
        module.setListPage(listPage);

        generateAll(module);
    }

    //生成活动规则
    
    public void testGenerateActiveRule() throws Exception {
        Module module = new Module("activeRule", "活动规则");
        module.setEditUrl("activeRule/add");
        module.setAuthor("miles");
        ListPage listPage = new ListPage();
        listPage.setSearch(false);
        listPage.addItem("规则名称", "运算符", "规则值");
        module.setListPage(listPage);

        generateAll(module);
    }

    //生成相关知识
    
    public void testGenerateRelatedKnowledge() throws Exception {
        Module module = new Module("relatedKnowledge", "相关知识");
        module.setAuthor("miles");
        module.setEditUrl("relatedKnowledge/add");
        ListPage listPage = new ListPage();
        listPage.setSearch(false);
        listPage.addItem("标题", "有效期", "关键字", "状态");
        module.setListPage(listPage);

        generateAll(module);
    }
*/

    /**
     * 生成所有的页面以及js
     *
     * @param module
     * @throws Exception
     */
    public void generateAll(Module module) throws Exception {
        generateModuleJs(module);
        generateListPage(module);
        generateListPageJs(module);
    }

    /**
     * 生成模块的js
     *
     * @param module
     * @throws Exception
     */
    private void generateModuleJs(Module module) throws Exception {
        String templateName = "freemarker/module_js.ftl";
        Template template = config.getTemplate(templateName);
        String path = root + module.getName() + "/" + module.getEntity() + "/" + module.getEntity() + ".js";
        createFile(path);
        template.process(module, new PrintWriter(new FileOutputStream(path)));
    }

    /**
     * 生成列表页面
     *
     * @param module
     * @throws Exception
     */
    private void generateListPage(Module module) throws Exception {
        String templateName = "freemarker/page_list.ftl";
        Template template = config.getTemplate(templateName);
        String path = root + module.getName() + "/" + module.getEntity() + "/list/" + module.getEntity() + "_list.jsp";
        createFile(path);
        template.process(module, new PrintWriter(new FileOutputStream(path)));
        return;
    }

    /**
     * 生成列表页面对应的js
     *
     * @param module
     * @throws Exception
     */
    private void generateListPageJs(Module module) throws Exception {
        String templateName = "freemarker/page_list_js.ftl";
        Template template = config.getTemplate(templateName);
        String path = root + module.getName() + "/" + module.getEntity() + "/list/" + module.getEntity() + "_list.js";
        createFile(path);
        template.process(module, new PrintWriter(new FileOutputStream(path)));
        return;
    }

    /**
     * 根据path判断文件是否存在，如果不存在则创建
     *
     * @param path
     */
    private void createFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Module module = ModuleThreadLocal.getModule();
            if (module != null && !module.isPermitOverride()) {
                throw new RuntimeException("文件[" + file.getPath() + "]已经存在，不执行生成操作!");
            }
        }
    }

}
