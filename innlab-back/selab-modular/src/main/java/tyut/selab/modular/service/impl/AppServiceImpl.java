package tyut.selab.modular.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.common.constant.KeyConstants;
import tyut.selab.common.domain.Lz;
import tyut.selab.common.domain.R;
import tyut.selab.common.utils.ObjectUtils;
import tyut.selab.common.utils.RedisUtils;
import tyut.selab.common.utils.StringUtils;
import tyut.selab.framework.domain.entity.ResourceEntity;
import tyut.selab.framework.mapper.ResourceMapper;
import tyut.selab.framework.service.IResourceService;
import tyut.selab.modular.domain.vo.AppVo;
import tyut.selab.modular.service.IAppService;

import java.util.concurrent.ExecutionException;

/**
 * @ClassName: AppServiceImpl
 * @Description:
 * @Author: gmslymhn
 * @CreateTime: 2024-08-07 01:12
 * @Version: 1.0
 **/
@Service
public class AppServiceImpl implements IAppService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private IResourceService iResourceService;

    @Override
    public R getApp(){
        AppVo appVo = new AppVo();
        appVo.setUpdateContent("测试内容");
        appVo.setVersionNumber("1.4.4");
        appVo.setVersionName("创新实验室");
        appVo.setWhetherUpdate(true);
        ResourceEntity resourceEntity = resourceMapper.selectById(241);
        if (ObjectUtils.isNotNull(resourceEntity)){
            Lz lz = new Lz(resourceEntity.getPwd(),resourceEntity.getFId(),resourceEntity.getIsNewd());
            try {
                appVo.setUpdateUrl(iResourceService.getResourceByLz(lz).get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return R.success(appVo);
    }
}
