package tyut.selab.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tyut.selab.common.utils.RedisUtils;

/**
 * @ClassName: LZCommandLineRunner
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2025-06-20 16:08
 * @Version: 1.0
 **/

@Slf4j
@Component
public class LZCommandLineRunner implements CommandLineRunner {

    @Autowired
    private RedisUtils redisUtils;
    @Override
    public void run(String... args) throws Exception {
        log.info("--------------------------------------蓝奏云上传模块执行--------------------------------------");
        redisUtils.setCacheObject("Resource_Folder_Id","9922980");
        redisUtils.setCacheObject("Resource_Cookie","ylogin=3400031; phpdisk_info=UGcHMVc1Um4PPQNgWjFVBgZkATFeDgJkBjBVMw86AzRZal9pVzcHOw49UDAIWwFpVTQEMw1sUWIHYlU0BWZWbFA3BzxXYFJmD2kDZlplVT0GNQFlXmMCYQZjVWcPawM2WTxfZVczB28OPVA5CGABUlU0BDMNYlE1BzFVMgU3VmZQZAc0VzU%3D; uag=0cb02a08b733cb8746c46ab5aa1e121f; tfstk=fLsEi9mArkEeHAbk_Cty_ropbmKpVnFbUgOWETXkdBAHVWQZZ1ftFBtBANRPs1R3pWFda0fNnUA5V3MdWs6mAMapV0-p23VbGoZjp9KJqwh_xSGLjLpzV2vHZKWJb5VbGoZ_-AxzTS_5p8F9bLdMKLxkqRuMeCvnK_AHSfv2H3AlZ32wjKJJEDYn-ADM3bIFKTmwheVKG9sTA59J8IXak0olwmTpiORZqSjwL7AcKCooqBmTbbXNdWoACHIfgK1QjD5VEtQk7glggQINIMblBXPkBafBjBt0-A-Cv9bDriV-rs-FaEjH7YmejhRkbdj0Fqd1bB1lYF2j0UtGcExh5PFBPHJVZMCEU0fcCtspWgPqsQQBHhvP2-0yaUjPbbpGvFSR8b0y-dpwGRyM0y12wW7z_A0-yFxvQIwBd43J-dpwGRyZy4LMHdRbdp1..; PHPSESSID=e3ofq2m8esth277rj1enf0tojlh0ebvl; __tins__21412745=%7B%22sid%22%3A%201716441642047%2C%20%22vd%22%3A%201%2C%20%22expires%22%3A%201716443442047%7D; __51cke__=; __51laig__=1; folder_id_c=9922980");
        log.info("------------------------------------蓝奏云上传模块执行成功！------------------------------------");
    }
}
