package eccrm.base.im.web;

import com.michael.cache.core.CacheProvider;
import com.ycrl.core.SystemContainer;
import com.ycrl.utils.gson.GsonUtils;
import com.ycrl.utils.string.StringUtils;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;

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
        CacheProvider cacheProvider = SystemContainer.getInstance().getBean(CacheProvider.class);
        if (cacheProvider == null) {
            return;
        }
        String messageKey = "message:" + userId + ":";
        int i = 0;
        while (i++ < 1000) {
            String messages = cacheProvider.popList(messageKey);
            if (StringUtils.isNotEmpty(messages)) {
                System.out.println("获取到消息:"+messages);
                GsonUtils.printData((HttpServletResponse) context.getResponse(), messages);
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
