#springBoot练习项目

#项目结构

    packege->
        common->  // 通用包，包含基本配置，工具类，处理器，枚举类等
            config-> // 配置
            constants-> // 枚举类
            exception-> // 异常
            filter-> // 过滤器
                JwtAuthenticationTokenFilter-> // token校验过滤器
            handle-> // 处理工具，包含切面，拦截器，通用异常处理器等
                GlobalExceptionHandler-> // 通过异常处理（处理全局抛出的异常）
                RestAuthenticationEntryPointHandler-> // 无访问权限（403）及登录校验失败处理器
                RestRequestLogAspectHandler-> //　请求记录切面，记录正常请求日志详情及返回信息的时间戳，异常请求不会经过该切面
            properties-> // 资源读取，读取配置中的数据
                PropertiesConfig-> // 总配置类，余下文件为配置数据读取器
            response-> // 响应类
                DataResult-> // 通用的结果返回类，用于响应数据
            utils-> // 工具类
        controller-> 控制器
        dao-> // 存放mapper及xml文件
        service-> // 接口及接口实现类
        entity->　// 实体类及vo，dto
        Application-> // 启动类
        
        resources-> // 资源目录
            static-> // 静态资源目录
            templates-> // 静态模板目录
            application.yml-> // 配置文件
        
        test-> // 测试包
        
    pom.xml-> // 依赖配置
    README.md-> // 介绍文件 

#任务周期                                                   
    任务：                                                                       完成情况
    a: 整合springBoot，添加常用依赖，运行hello boot，测试包文件                         完成
    b: 基础权限搭建，结合springSecurity、jwt，实现用户登录（验证码，短信），Oauth2登录    1/4
    c: 日志整合，实现日志文件序列化，文件自动分割，调试日志和业务日志分开打印                0
    d: 配置文件不同环境动态加载                                                          0
    e: 配合mybatis-puls做数据库读写分离                                                  0
    f: quartz任务调度，通过页面动态管理任务（分布式应用下任务多执行问题）                   0
    g: 常用工具类整合：excel导入导出、汉语转拼音、邮件、ftp/ftps上传下载功能               0
    h: 尝试整合active工作流                                                            0
    i: 集成微信运用，及第三方支付                                                       0