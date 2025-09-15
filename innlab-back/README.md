# 创新实验室对外网站（后端）
```
tyut.selab    
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
│       └── pom.xml      // 配置文件
├── framework         // 框架核心（用户系统+资源系统）
│       └── config                        // 系统配置
│       └── controller                    // 你肯定懂
│       └── domain                      // 实体类 entity vo
│       └── jwt             		      // jwt实体类，Serurity部分重写配置在这个文件夹内（需学习SpringSecurity）
│       └── interceptor                   // 拦截器
│       └── listener                 		  // 监听器
│       └── mapper                 		  	// mapper
│       └── security                      // 权限控制
│       └── service                       // 系统服务
│       └──    web                           // 项目Security集成（需学习SpringSecurity,本模块核心，非必须勿动！！！）
│       │       └── SecurityUtils                           // 项目Security工具类，如果二次开发会用到本工具类
│       └── pom.xml      // 配置文件
├── admin       // 后台服务
│       └── config                        // 配置信息配置
│       └── Application                      // 启动类
│       └── pom.xml      // 配置文件
├── modular     // 业务模块
│       └── controller                    // 你肯定懂
│       └── domain                      // 实体类 entity vo
│       └── domain                        // 实体类
│       └── mapper                     // 你肯定懂
│       └── service                        // 你肯定懂
│       └── pom.xml      // 配置文件
├── selab-resources      // 静态资源（非模块）
│       └── Image                     存图片文件夹
│       └──    Cache                     缓存目录文件夹
│       │        └── Image                     缓存图片文件夹
│       │        └── Video                     缓存视频文件夹
│       └── Video                     存视频文件夹
├── selab-xxxxxx      // 其他模块
├── pom.xml      // 配置文件
```
**补充说明**：项目整体模块架构如下，项目由多个模块组成，模块加载顺序如下图。

- 打开项目时注意项目所在根目录时innlab-back，勿将GitHub项目拉下来的目录当作根目录，
- 项目存在多个pom.xml文件，其中一个总的pom文件，各模块各一个pom文件，如需添加新模块时需做好配置。
- 如需添加新模块本质是添加modular[1，2，3，4]模块，需要学好Maven进行配置即可（添加模块还是很简单的）。
- 依赖导入方面，项目用的都是最新的大版本依赖，以来方面可能与过去（SpringBoot 2）版本存在冲突，注意分别。

![流程图.png](.\selab-resources\Image\流程图.png)


```
springboot:3.2.2整合
mybatis-plus：未使用旧有的xml配置（如需修改代码需懂得mybatis-plus基本语法）
security：具体得去学习，这是个安全框架
jwt：
Redis：
knife4j：访问地址http://127.0.0.1:8081/doc.html
Druid：访问地址 http://127.0.0.1:8081/druid/login.html
xxl-job：http://127.0.0.1:8080/xxl-job-admin
```

