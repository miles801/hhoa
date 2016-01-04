package eccrm.codeutils.server.base;

import eccrm.utils.codeutils.server.ServerCodeBuildConfig;
import eccrm.utils.codeutils.server.ServerCodeBuildEngine;

/**
 * @author miles
 * @datetime 14-3-13 下午3:14
 */
public class TestEmployee {

    public void testEmployee() throws Exception {
        ServerCodeBuildConfig serverConfig = new ServerCodeBuildConfig();
        //实体类
        serverConfig.setEntity("Employee");
        //作者
        serverConfig.setAuthor("miles");
        //模块名称
        serverConfig.setModule("base");
        //包路径
        serverConfig.setPackagePath("eccrm.base.employee");
        //工程路径
        serverConfig.setWorkspace("D:/workspace_intelij/eccrm_new/");
        //表名称
        serverConfig.setTableName("SYS_EMPLOYEE");

        //根据
        ServerCodeBuildEngine engine = new ServerCodeBuildEngine(serverConfig);
        engine.generateAll();
    }

    public void testUserGroup() throws Exception {
        ServerCodeBuildConfig serverConfig = new ServerCodeBuildConfig();
        //实体类
        serverConfig.setEntity("UserGroup");
        //作者
        serverConfig.setAuthor("miles");
        //模块名称
        serverConfig.setModule("base");
        //包路径
        serverConfig.setPackagePath("eccrm.base.user");
        //工程路径
        serverConfig.setWorkspace("D:/workspace_intelij/eccrm_new/");
        //表名称
        serverConfig.setTableName("EC_SYS_USER_GROUP");

        //根据
        ServerCodeBuildEngine engine = new ServerCodeBuildEngine(serverConfig);
        engine.generateAll();
    }
}
