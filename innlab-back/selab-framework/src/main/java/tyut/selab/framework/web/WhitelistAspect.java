package tyut.selab.framework.web;

/**
 * @ClassName: WhitelistAspect
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2026-02-26 23:42
 * @Version: 1.0
 **/

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import tyut.selab.common.annotation.WhitelistAnnotation;
import tyut.selab.framework.config.SecurityConfig;

import java.util.*;

/**
 * 白名单注解处理器
 */
@Component
public class WhitelistAspect {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SecurityConfig securityConfig;


    /**
     * 初始化时扫描所有Controller，收集白名单路径
     */
    @PostConstruct
    public void init() {
        List<String> whitelistPaths = new ArrayList<>();
        List<String> whitelistGetPaths = new ArrayList<>();

        // 获取所有RequestMapping处理器
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = entry.getValue();
            RequestMappingInfo mappingInfo = entry.getKey();

            // 检查方法或类上是否有@Whitelist注解
            WhitelistAnnotation methodWhitelist = handlerMethod.getMethodAnnotation(WhitelistAnnotation.class);
            WhitelistAnnotation classWhitelist = handlerMethod.getBeanType().getAnnotation(WhitelistAnnotation.class);

            if ((methodWhitelist != null && methodWhitelist.enabled()) ||
                    (classWhitelist != null && classWhitelist.enabled())) {

                // 获取路径模式
                Set<String> patterns = mappingInfo.getPatternValues();

                // 获取请求方法
                Set<RequestMethod> methods = mappingInfo.getMethodsCondition().getMethods();

                // 如果没有指定方法或包含GET方法，添加到GET白名单
                boolean hasGetMethod = methods.isEmpty() || methods.contains(RequestMethod.GET);

                for (String pattern : patterns) {
                    if (hasGetMethod) {
                        whitelistGetPaths.add(pattern);
                    } else {
                        whitelistPaths.add(pattern);
                    }
                }
            }
        }

        // 更新SecurityConfig中的白名单
        updateWhitelists(whitelistPaths, whitelistGetPaths);
    }


    /**
     * 更新白名单配置
     */
    private void updateWhitelists(List<String> newPaths, List<String> newGetPaths) {
        // 这里需要通过反射或其他方式更新SecurityConfig中的静态数组
        // 由于是静态数组，需要特殊处理

        // 方法1：使用反射（不推荐，但可行）
        try {
            // 合并原有的白名单和新发现的白名单
            Set<String> allPaths = new LinkedHashSet<>(Arrays.asList(securityConfig.getOriginalWhitelist()));
            allPaths.addAll(newPaths);

            Set<String> allGetPaths = new LinkedHashSet<>(Arrays.asList(securityConfig.getOriginalWhitelistGet()));
            allGetPaths.addAll(newGetPaths);

            // 更新SecurityConfig中的数组
            securityConfig.updateWhitelists(
                    allPaths.toArray(new String[0]),
                    allGetPaths.toArray(new String[0])
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to update whitelists", e);
        }
    }
}
