package eccrm.codeutils.server.base;

import eccrm.utils.codeutils.server.ServerCodeBuildConfig;
import eccrm.utils.codeutils.server.ServerCodeBuildEngine;

/**
 * @author miles
 * @datetime 2014/7/1 13:45
 */
public class Document {

    public static void main(String[] args) {
        ServerCodeBuildConfig serverConfig = new ServerCodeBuildConfig();
        //实体类
        serverConfig.setEntity("Document");
        //作者
        serverConfig.setAuthor("miles");
        //模块名称
        serverConfig.setModule("base");
        //包路径
        serverConfig.setPackagePath("eccrm.base.tenement");
        //工程路径
        serverConfig.setWorkspace("D:/workspace_intelij/eccrm_new/");
        //表名称
        serverConfig.setTableName("SYS_TENEMENT_DOC");

        //根据
        ServerCodeBuildEngine engine = new ServerCodeBuildEngine(serverConfig);
        engine.generateAll();
    }
}
