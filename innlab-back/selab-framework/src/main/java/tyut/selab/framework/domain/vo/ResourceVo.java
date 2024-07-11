package tyut.selab.framework.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import tyut.selab.common.domain.Lz;
import tyut.selab.framework.domain.entity.ResourceEntity;

/**
 * @ClassName: ResourceVo
 * @Description: 资源类
 * @Author: gmslymhn
 * @CreateTime: 2024-02-27 20:19
 * @Version: 1.0
 **/
@Data
@Schema(description = "资源")
public class ResourceVo{

    /**
     * 静态资源id
     */
    @Schema(description = "资源Id")
    private Integer resourceId;
    /**
     * 资源名称
     */
    @NotBlank(message = "文件名称不能为空")
    @Schema(description = "资源名称")
    @Size(min=1, max=20,message="文件名称必须在 1 ~ 20 字符之间")
    private String resourceName;
    /**
     * 资源描述
     */
    @Schema(description = "资源描述")
    @Size(min=1, max=200,message="资源描述必须在 1 ~ 200 字符之间")
    private String resourceDescription;
    /**
     * 资源类型 (0文本 1图片 2视频)
     */
    @Schema(description = "资源类型 0文字 1图片 2视频 3md文档")
    @NotBlank(message = "资源类型不能为空")
    @Size(max=1,message="资源类型必须为 1 字符")
    private Integer resourceType;

    /**
     * 资源base64形式
     */
    @Schema(description = "资源的base64形式")
    private String resourceBase64;

    /**
     * 资源base64形式
     */
    @Schema(description = "资源的蓝奏云")
    private Lz lz;

    public ResourceVo(ResourceEntity resourceEntity){
        Lz lz =new Lz();
        this.setResourceId(resourceEntity.getResourceId());
        this.setResourceName(resourceEntity.getResourceName());
        this.setResourceDescription(resourceEntity.getResourceDescription());
        this.setResourceType(resourceEntity.getResourceType());
        lz.setPwd(resourceEntity.getPwd());
        lz.setIsNewd(resourceEntity.getIsNewd());
        lz.setFId(resourceEntity.getFId());
        this.setLz(lz);
    }
}
