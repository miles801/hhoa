package eccrm.freemarker;

import eccrm.utils.codeutils.CodeTemplateUtils;
import eccrm.utils.codeutils.EditPage;
import eccrm.utils.codeutils.ListPage;
import eccrm.utils.codeutils.Module;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.junit.Before;
import org.junit.Test;

/**
 * @author miles
 * @datetime 13-12-23 下午2:24
 * 列表页面
 */
public class TestActivtyListPage {
    CodeTemplateUtils codeTemplateUtils;


    @Before
    public void setUp() throws Exception {
        Configuration config = new Configuration();
        String root = System.getProperty("user.dir") + "/web\\src\\main\\webapp\\app\\";
        root = root.replaceAll("eccrm-core", "").replaceAll("//", "");
        config.setTemplateLoader(new ClassTemplateLoader(EditPage.class, "/"));
        codeTemplateUtils = new CodeTemplateUtils(config, root);
    }

    @Test
    public void testGenerate() throws Exception {
        Module module = new Module("customer", "contact", "客户联络方式");
        module.setAuthor("Michael");
        ListPage listPage = new ListPage();
        listPage.setConditionRows(0);
        listPage.addItem("事项内容,创建人,创建时间".split(","));
        listPage.addTableHeaderButton("新增,删除".split(","));
        module.setPermitOverride(true);
        module.setParam(false);
        module.setModal(true);
        module.setListPage(listPage);
        codeTemplateUtils.generateModuleJs(module);
    }
}
