package eccrm.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Http请求的工具类，提供从请求中获取参数的工具方法
 *
 * @author miles
 * @datetime 2014/7/6 11:59
 */
public class HttpRequestUtils {

    /**
     * 从request中获取指定名称的String类型的参数值
     *
     * @param propertyName 参数名称
     */
    public static String getStringProperty(HttpServletRequest request, String propertyName) {
        return request.getParameter(propertyName);
    }

    /**
     * 从request中获取参数的int值，如果参数不存在或者值的类型不是整形，则返回null
     *
     * @param propertyName 参数名称
     */
    public static Integer getIntProperty(HttpServletRequest request, String propertyName) {
        String property = getStringProperty(request, propertyName);
        if (property != null && property.matches("\\d+")) {
            return Integer.parseInt(property);
        }
        return null;
    }


    /**
     * 判断一个请求是否为ajax请求（判断请求头中是否有X-Requested-With）
     * 一般来说jquery等框架默认的会给ajax请求添加该头信息，而手动实现的ajax请求则没有
     * 是ajax请求：true，不是ajax请求：false
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null;
    }
}
