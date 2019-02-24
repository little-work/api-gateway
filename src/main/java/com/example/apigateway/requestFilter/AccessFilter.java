package com.example.apigateway.requestFilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {

    /**
     * 他决定过滤器在请求的那个生命周期执行，这里定义为pre  意思就是请求再被路由之前执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序，当请求在一个阶段中存在多个过滤器的时候，需要根据这个方法的
     * 返回值来依次执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行，在这里我们直接返回了true，因此该过滤器对所有的请求都会生效，
     * 实际我们可以利用该函数来确定有效范围
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑  我们这里通过ctx.setSendZuulResponse(fasle) 领zuul过滤该请求
     * 不对其路由  也可以通过ctx.setResponseStatusCode(401)返回错误码  也可以
     * ctx.setResponseBody(body)  对返回的body内容进行编辑
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx=RequestContext.getCurrentContext();
        HttpServletRequest request=ctx.getRequest();

        Object accessToken=request.getParameter("accessToken");

        if(accessToken==null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }

        return null;
    }
}
