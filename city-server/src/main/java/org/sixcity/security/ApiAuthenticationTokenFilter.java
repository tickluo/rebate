package org.sixcity.security;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.sixcity.api.exception.TokenInvalidException;
import org.sixcity.security.service.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import wrapper.ResettableStreamHttpServletRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ApiAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest(
                (HttpServletRequest) request);
        // get params from wrappedRequest
        JSONObject params = null;
        if (request.getContentType() == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("appToken", request.getParameter("appToken"));
            params = new JSONObject(map);
        } else {
            String jsonString = IOUtils.toString(wrappedRequest.getReader());
            params = JSONObject.parseObject(jsonString);
            wrappedRequest.resetInputStream();
        }
        boolean ssoAccess = false;
        if (params != null && params.containsKey("appToken")) {
            String tempAppKey = ApiTokenUtil.getAppKeyByToken(params.getString("appToken"));
            if (SecurityContextHolder.getContext().getAuthentication() == null && tempAppKey != null) {

                UserDetails userDetails = this.userDetailsService.loadUserByAppKey(tempAppKey);

                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(wrappedRequest));
                    //logger.info("authenticated user " + username + ", setting security context");
                    ssoAccess = true;
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else if (SecurityContextHolder.getContext().getAuthentication() != null && tempAppKey != null) {
                ssoAccess = true;
            }
        }
        if (!ssoAccess && !request.getRequestURL().toString().contains("/api/rebate/getAppToken")) {
            throw new TokenInvalidException("");
        }
        chain.doFilter(wrappedRequest, response);
    }

}
