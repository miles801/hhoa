package eccrm.base.user.web;

import com.google.gson.JsonObject;
import com.ycrl.core.SystemContainer;
import com.ycrl.core.context.SecurityContext;
import com.ycrl.utils.gson.GsonUtils;
import com.ycrl.utils.gson.JsonObjectUtils;
import eccrm.base.user.service.*;
import eccrm.base.user.vo.UserVo;
import eccrm.core.security.LoginInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * @author miles
 * @datetime 2014/3/20 11:06
 */
@Controller
@Scope("prototype")
public class LoginCtrl {
    private Logger logger = Logger.getLogger(LoginCtrl.class);
    @Resource
    private UserService userService;
    @Resource
    private LoginLogService loginLogService;


    /**
     * 用户登录
     * 必须传入username和password属性
     * 默认为admin/eccrm
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public void login(HttpServletResponse response, HttpServletRequest request) {
        // 获得登录信息
        JsonObject param = GsonUtils.wrapDataToEntity(request, JsonObject.class);
        String username = JsonObjectUtils.getStringProperty(param, "username");
        if (username == null) {
            throw new IllegalArgumentException("用户登录时,没有获得用户名!");
        }
        String password = JsonObjectUtils.getStringProperty(param, "password");
        if (password == null) {
            throw new IllegalArgumentException("用户登录时,没有获得密码!");
        }
        String code = JsonObjectUtils.getStringProperty(param, "code");
        if (code == null) {
            throw new IllegalArgumentException("用户登录时,没有获得租户编号!");
        }

        // 验证登录
        ValidateResult result = userService.validate(username, password, code);
        LoginErrorCode loginCode = result.getCode();
        if (loginCode == LoginErrorCode.SUCCESS) {
            // 登录成功

            // 获得员工信息
            UserVo userVo = userService.findById(result.getUserId());
            String employeeId = userVo.getEmployeeId();
            if (StringUtils.isEmpty(employeeId)) {
                throw new RuntimeException("当前登录用户[" + username + "]没有关联员工!请与管理员联系!");
            }
            String employeeName = userVo.getEmployeeName();
            // 设置登录信息到session以及上下文中
            HttpSession session = request.getSession();
            session.setAttribute(LoginInfo.HAS_LOGIN, true);
            session.setAttribute(LoginInfo.TENEMENT, result.getTenementId());
            session.setAttribute(LoginInfo.USER, employeeId);
            session.setAttribute(LoginInfo.USERNAME, username);
            session.setAttribute(LoginInfo.EMPLOYEE, employeeId);
            session.setAttribute(LoginInfo.EMPLOYEE_NAME, employeeName);
            session.setAttribute(LoginInfo.LOGIN_DATETIME, new Date().getTime());
            SecurityContext.set(employeeId, username, "1");
            SecurityContext.setEmpId(employeeId);
            SecurityContext.setEmpName(employeeName);

            // 添加到在线用户列表中
            OnlineUserPool pool = OnlineUserPool.getInstance();
            pool.add(userVo);

            // 加载角色的全部菜单和权限
            // 查询登录用户权限
            LoginSuccessEvent event = SystemContainer.getInstance().getBean(LoginSuccessEvent.class);
            if (event != null) {
                event.execute(request, userVo);
            }

            //写入Cookie
            response.addCookie(new Cookie("eccrmContext.id", employeeId));
            try {
                // 员工名称
                response.addCookie(new Cookie("eccrmContext.employeeName", URLEncoder.encode(URLEncoder.encode(employeeName, "utf-8"), "utf-8")));
                // 用户名称
                response.addCookie(new Cookie("eccrmContext.username", URLEncoder.encode(URLEncoder.encode(username, "utf-8"), "utf-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            GsonUtils.printJson(response, "success", true);
        } else {
            // 登录失败，写入失败信息
            int errorCode = loginCode.getValue();
            JsonObject json = new JsonObject();
            json.addProperty("code", errorCode);
            json.addProperty("fail", loginCode.getMessage());
            GsonUtils.printJsonObject(response, json);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "redirect:/index.html";
    }
}
