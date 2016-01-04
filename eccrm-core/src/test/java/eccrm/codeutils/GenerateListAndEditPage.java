package eccrm.codeutils;

import eccrm.utils.codeutils.*;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * 生成编辑页面和列表页面，即对应的js
 *
 * @author miles
 * @datetime 13-12-23 下午2:24
 */
public class GenerateListAndEditPage {
    private Configuration config = null;
    private String root;
    private Logger logger = Logger.getLogger(GenerateListAndEditPage.class);

    @Before
    public void setUp() throws Exception {
        config = new Configuration();
        root = "D:/workspace/lr/hh-oa/web\\src\\main\\webapp\\app\\";
        config.setTemplateLoader(new ClassTemplateLoader(EditPage.class, "/"));
    }


    @Test
    public void testGenerateListAndEditPage() throws Exception {
        Module module = new Module("oa", "blackList", "黑户");
        module.setPermitOverride(true);
        module.setAuthor("Michael");

        // 编辑页面
        setEditPage(module);

        // 列表页面
        setListPage(module);

        CodeTemplateUtils codeUtils = new CodeTemplateUtils(config, root);
        codeUtils.generateAll(module);
    }

    private void setListPage(Module module) {
        ListPage listPage = new ListPage();
        listPage.addQueryCondition(new Row(
                "客户名称:label", "name",
                "客户类型:label", "type:select"
        ));
        listPage.addQueryBarButton("查询:search", "高级查询:asterisk", "重置:repeat");
        listPage.addTableHeaderButton("新建:plus:marketing/add", "删除:remove");
        listPage.addItem("客户名称", "客户类型", "客户信息", "原因", "关键字", "状态", "操作");
        module.setListPage(listPage);
    }

    private void setEditPage(Module module) {
        EditPage editPage = new EditPage();
        editPage.setSaveAndNew(true);
        editPage.setDatepicker(true);
        //name:type:length:addOnIcon
        editPage.addFormRow(
                "客户名称:label", "name:text:col-2-half", "客户类型:label", "code:text:col-2-half"
        ).addFormRow(
                "客户信息:label", "type:text:col-6-half"
        ).addFormRow(
                "原因:label", "reason:text:col-6-half"
        ).addFormRow(
                "关键字:label", "keywords:text:col-6-half"
        );
        module.setEditPage(editPage);
    }

}
