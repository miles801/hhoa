package eccrm.freemarker.marketing.execute;

import eccrm.freemarker.TestEditPage;
import eccrm.utils.codeutils.CodeTemplateUtils;
import eccrm.utils.codeutils.EditPage;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import org.apache.log4j.Logger;
import org.junit.Before;

/**
 * @author miles
 * @datetime 13-12-31 下午4:46
 */
public class BaseTest {
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

    public void log(String info) {
        logger.info(info + "................");
    }
}
