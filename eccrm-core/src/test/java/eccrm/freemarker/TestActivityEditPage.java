package eccrm.freemarker;

import eccrm.utils.codeutils.CodeTemplateUtils;
import eccrm.utils.codeutils.EditPage;
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
public class TestActivityEditPage {
    private CodeTemplateUtils codeUtils;

    @Before
    public void setUp() throws Exception {
        Configuration config = new Configuration();
        String root = System.getProperty("user.dir") + "/web\\src\\main\\webapp\\app\\";
        root = root.replaceAll("eccrm-core", "").replaceAll("//", "");
        config.setTemplateLoader(new ClassTemplateLoader(TestActivityEditPage.class, "/"));
        codeUtils = new CodeTemplateUtils(config, root);
    }


    //生成营销活动
    @Test
    public void testGenerateActivity() throws Exception {
        Module module = new Module("base", "customer", "客户");
        module.setAuthor("Michael");
        module.setPermitOverride(true);

        EditPage editPage = new EditPage();
        //name:type:length:addOnIcon
        editPage.setSaveAndNew(false);
        editPage.setDatepicker(true);
        editPage.addFormRow(
                "姓名:label:col-1-half,name:text,性别:label:col-1-half,sex:select,出生日期:label:col-1-half,birthday:text".split(",")
        ).addFormRow(
                "证件号码:label:col-1-half,cordNo:text:col-6-half,婚姻状况:label:col-1-half,marriage:radio".split(",")
        ).addFormRow(
                "国籍:label:col-1-half,name:text,民族:label:col-1-half,sex:select,客户类型:label:col-1-half,birthday:select".split(",")
        );
        module.setEditPage(editPage);
        codeUtils.generateEdit(module);
    }


}
