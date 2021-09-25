/**
 * Filter的这个特性在生产环境中有很广泛的应用，如：修改请求和响应、防止xss攻击、包装二进制流使其可以多次读，等等。
 *
 * 实际工作中，我们都是使用 SpringBoot 进行业务开发，本目录总结三种 Filter 用法
 * 其实还有第四种，web.xml配置，不过用 SpringBoot 的自动装配或者 JavaConfig 更方便
 */


package com.dreamkite.demo.filter;