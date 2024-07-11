package tyut.selab.framework.service;

import org.springframework.web.multipart.MultipartFile;
import tyut.selab.common.domain.R;
import tyut.selab.framework.domain.vo.CookieVo;

/**
 * @ClassName: IResourceService
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 16:05
 * @Version: 1.0
 **/
public interface IResourceService {
    R addResource(MultipartFile file, String fileDescription,Integer type);

    R addResourceForCloud(Integer resourceId);

    R cacheCookie(CookieVo cookieVo);

    R getResourceLz(Integer resourceId);
}
