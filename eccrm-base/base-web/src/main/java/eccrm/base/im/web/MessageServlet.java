package eccrm.base.im.web;

import com.ycrl.core.context.SecurityContext;
import org.apache.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Michael
 */
@WebServlet(name = "MessageServlet", urlPatterns = "/servlet/message", asyncSupported = true)
public class MessageServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        Logger logger = Logger.getLogger(MessageServlet.class);
        logger.info("初始化消息处理机...");
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        // 启动异步
        final AsyncContext context = request.startAsync();
        // 设置超时时间
        MessageThread thread = new MessageThread(context, SecurityContext.getUserId());
        new Thread(thread, "Message--" + SecurityContext.getUserId()).start();
    }

}
