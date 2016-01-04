package eccrm.codeutils.server.base;

import eccrm.utils.codeutils.server.ServerCodeBuildConfig;
import eccrm.utils.codeutils.server.ServerCodeBuildEngine;

/**
 * @author miles
 * @datetime 14-3-13 下午3:14
 */
public class TestRegion {

    public void testRegion() throws Exception {
        ServerCodeBuildConfig serverConfig = new ServerCodeBuildConfig();
        //实体类
        serverConfig.setEntity("Region");
        //作者
        serverConfig.setAuthor("miles");
        //模块名称
        serverConfig.setModule("base");
        //包路径
        serverConfig.setPackagePath("eccrm.base.region");

        serverConfig.setDynamicTree(true);

        //工程路径
        serverConfig.setWorkspace("D:/workspace_intelij/eccrm_new/");
        //表名称
        serverConfig.setTableName("SYS_REGION");

        //根据
        ServerCodeBuildEngine engine = new ServerCodeBuildEngine(serverConfig);
        engine.generateAll();
    }
}
