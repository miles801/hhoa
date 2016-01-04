package eccrm.base.message.web;

import com.ycrl.core.web.BaseController;
import eccrm.base.message.Message;
import eccrm.base.message.MessageHandler;
import eccrm.base.message.parser.JsonParser;
import eccrm.base.message.parser.MessageHandlerContainer;
import eccrm.base.message.parser.MessageParser;
import eccrm.base.parameter.service.impl.BusinessParamMessageHandler;
import eccrm.base.parameter.service.impl.SysParamMessageHandler;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 消息接口
 *
 * @author Michael
 */
@Controller
@RequestMapping(value = "/core/message")
public class MessageCtrl extends BaseController {
    private Logger logger = Logger.getLogger(MessageCtrl.class);

    public MessageCtrl() {
        // 初始化&注册消息解析器
        MessageHandlerContainer mpc = MessageHandlerContainer.getInstance();
        JsonParser parser = new JsonParser();
        mpc.register(BusinessParamMessageHandler.TYPE, parser, new BusinessParamMessageHandler());    // 业务参数
        mpc.register(SysParamMessageHandler.TYPE, parser, new SysParamMessageHandler());    // 系统参数

    }


    /**
     * 接收从其他地方发过来的消息，必须具有参数messageType和消息主体（流）
     * 使用push.png实际上是一种hack的写法，用于跳过单点登录和本地系统的登录验证器
     *
     * @throws IOException
     */
    @RequestMapping(value = {"/push", "/push.png"}, params = {"messageType"}, method = RequestMethod.POST)
    public void accept(@RequestParam String messageType, HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 解析消息
        MessageParser parser = MessageHandlerContainer.getInstance().getParser(messageType);
        if (parser == null) {
            logger.error("消息类型为[" + messageType + "]的消息解析器未注册.....");
            return;
        }
        Message message = parser.parse(IOUtils.toString(request.getInputStream(),"utf-8"));

        // 处理消息
        MessageHandler handler = MessageHandlerContainer.getInstance().getHandler(messageType);
        if (handler == null) {
            logger.error("消息类型为[" + messageType + "]的消息处理器未注册...");
            return;
        }
        handler.invoke(message);
    }

}
