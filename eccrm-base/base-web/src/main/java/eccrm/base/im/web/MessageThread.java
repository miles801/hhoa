package eccrm.base.im.web;

import com.ycrl.utils.gson.GsonUtils;
import eccrm.base.im.MessagePool;
import org.apache.log4j.Logger;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * @author Michael
 */
public class MessageThread implements Runnable {

    private AsyncContext context;
    private String userId;

    public MessageThread(AsyncContext context, String userId) {
        this.context = context;
        this.userId = userId;
    }

    @Override
    public void run() {
        MessagePool messagePool = MessagePool.getInstance();
        Logger logger = Logger.getLogger(MessageThread.class);
        int i = 0;
        Random random = new Random();
        while (i++ < 200) {
//            logger.info("读取(" + userId + ")的消息....");
            /*List<Message> messages = messagePool.pop(userId);
            if (messages != null && !messages.isEmpty()) {
                logger.info("读取到(" + userId + ")的消息，即将返回响应...");
                GsonUtils.printData((HttpServletResponse) context.getResponse(), messages);
                context.complete();
                return;
            }*/
            int d=random.nextInt(1000);
            if (d > 990) {
                GsonUtils.printData((HttpServletResponse) context.getResponse(), d);
                context.complete();
                return;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 自然结束，没有读取到消息
        GsonUtils.printSuccess((HttpServletResponse) context.getResponse());
        context.complete();
    }

}
