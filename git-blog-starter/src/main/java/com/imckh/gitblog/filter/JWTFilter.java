package com.imckh.gitblog.filter;

import com.imckh.gitblog.shiro.JWTToken;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * <p>Description:  preHandle->isAccessAllowed->isLoginAttempt->executeLogin </p>
 * JWT 整合，需要自定义自己的过滤器 JWTFilter，JWTFilter 继承了 BasicHttpAuthenticationFilter
 *
 * 该过滤器主要有三步：
 *
 * 1. 检验请求头是否带有 token ((HttpServletRequest) request).getHeader("Token") != null
 * 2. 如果带有 token，执行 shiro 的 login() 方法，将 token 提交到 Realm 中进行检验；
 *      如果没有 token，说明当前状态为游客状态（或者其他一些不需要进行认证的接口）
 * 3. 如果在 token 校验的过程中出现错误，如 token 校验失败，
 *      那么会将该请求视为认证不通过，则重定向到 /unauthorized/**
 *
 * 将跨域支持放到了该过滤器来处理
 * @author Cui Kaihui
 * @date 2019/5/20 22:09
 */
@Log4j2
public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 判断请求的请求头是否带上了token
        if (isLoginAttempt(request, response)) {
            // 如果存在, 则进入 executeLogin 方法执行登录, 检查 token 是否正确
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                // token 错误
                responseError(response, e.getMessage());
            }
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }

    /**
     * 判断用户是否想要登录
     *
     * 检查 header 是否包含 token 字段
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Token");
        return token != null;
    }

    /**
     * 执行login
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Token");
        JWTToken jwtToken = new JWTToken(token);

        // 提交给 realm 进行登录, 如果错误会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);

        // 如果没有抛出异常则代表登录成功, 返回 true
        return super.executeLogin(request, response);
    }

    /**
     * 对跨域提供支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));

        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 将非法请求跳转到 /unauthorized/**
     */
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, StandardCharsets.UTF_8);
            httpServletResponse.sendRedirect("/unauthorized/" + message);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
