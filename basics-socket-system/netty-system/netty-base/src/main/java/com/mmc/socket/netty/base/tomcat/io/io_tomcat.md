# 基于基础IO模式手写Tomcat
> 1. 定义Request和Response请求响应实体接口
> > 1.1 定义IoRequest对象实现Request接口
        
            IoRequest模拟实现Socket模式请求对象
            初始化对象传入Socket输入流
> > 1.2 定义IoResponse对象实现Response接口
            
            IoResponse模拟实现Socket模式响应对象
            初始化对象传入Socket输出流
            创建write写出方法
            
> 2. 抽象Servlet 实现
    
        BaseServlet抽象Servlet实现
        定义doService方法
        根据模拟http请求GET和POST方法分发到doGet和doPost方法中。
        
        IoServlet继承BaseServlet实现
        
> 3. 模拟实现两个具体Servlet
    
    FirstIoServlet和SecondServlet
    分别接收到请求之后输出一段内容。
    
> 4. 模拟web.xml容器启动初始化配置文件
    
    配置uri与具体servlet处理类对应关系。
    
> 5. 服务主启动方法
        
    1. init 初始化服务
        加载配置文件，放入本地缓存中。
    2. 启动服务
        使用ServerSocket 开启创建服务端，使用单线程阻塞死循环方式处理请求。
    3. 处理请求
        根据接收Socket处理请求消息，并模拟写入响应消息。