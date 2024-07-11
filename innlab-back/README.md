# 创新实验室对外网站（后端）
```
tyut.selab.foreign    
├── common            // 工具类
│       └── annotation                    // 自定义注解
│       └── config                        // 全局配置 redis序列化等
│       └── constant                      // 通用常量 
│       └── core                          // 核心控制 basemapper redis page等
│       └── domain                        // 实体类 
│       └── enums                         // 通用枚举
│       └── exception                     // 通用异常
│       └── filter                        // 过滤器处理 sql注入 json过滤
│       └── utils                         // 通用类处理
│       └── validator                     // 校验注解 Xss校验
├── framework         // 框架核心
│       └── api                   		    // api服务
│       └── config                        // 系统配置
│       └── expander                      // 拓展服务
│       └── handler             		      // 处理服务
│       └── interceptor                   // 拦截器
│       └── listener                 		  // 监听器
│       └── mapper                 		  	// mapper
│       └── security                      // 权限控制
│       └── service                       // 系统服务
│       └── web                           // 前端控制
├── admin       // 后台服务
│       └── config                        // knife4j配置+验证配置
│       └── controller                      // 不懂
├── modular     // 业务模块
│       └── controller                    // 不懂
│       └── core                        // 核心控制 权限控制 路径控制....
│       └── domain                      // 实体类 entity vo
│       └── core                          // 核心控制
│       └── domain                        // 实体类
│       └── enums                         // 异常枚举 emmmmm感觉没接口好用
│       └── mapper                     // 不懂
│       └── service                        // 不懂
│       └── task                         // 定时任务
│       └── system                     // 登陆验证 。。。。。
├── selab-resources      // 静态资源（非模块）
│       └── Image                     存图片
│       └── Markdown                     存md
│       └── Video                     存视频
├── selab-xxxxxx      // 其他模块

```

```
springboot:3.2.2整合
mybatis-plus：
security：
jwt：
Redis：
knife4j：访问地址http://127.0.0.1:8081/doc.html
Druid：访问地址 http://127.0.0.1:8081/druid/login.html
xxl-job：http://127.0.0.1:8080/xxl-job-admin
```

