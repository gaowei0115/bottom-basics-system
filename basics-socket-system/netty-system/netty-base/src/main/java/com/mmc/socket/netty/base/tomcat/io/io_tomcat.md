# 基于基础IO模式手写Tomcat
> 1. 定义Request和Response请求响应实体接口
> > 1.1 定义IoRequest对象实现Request接口
        
            IoRequest模拟实现Socket模式请求对象
> > 1.2 定义IoResponse对象实现Response接口
        
            IoResponse模拟实现Socket模式响应对象
> 2. 抽象Servlet 实现
        IoServlet
>