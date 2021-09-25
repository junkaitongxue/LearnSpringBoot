/**
 * 比如对特定的URL检查用户是否登录，打印处理用户请求耗时的时间等，可以用拦截器来实现。
 * 这里需要注意拦截器跟过滤器的差别：
 * -- 截器可以获取IOC容器中的各个bean，而过滤器就不行，这点很重要，在拦截器里注入一个service，可以调用业务逻辑
 * -- 过滤器是在请求进入容器后，但请求进入servlet之前进行预处理的。请求结束返回也是，是在servlet处理完后，返回给前端之前。
 * 详情见：https://www.cnblogs.com/panxuejun/p/7715917.html
 *
 * @author dreamkite
 */
package com.dreamkite.demo.HandlerInterceptor;