package tyut.selab.common.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: lz
 * @Description: 蓝奏云
 * @Author: gmslymhn
 * @CreateTime: 2024-05-23 14:58
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lz implements Serializable  {

    @TableField("lz_pwd")
    private String pwd;
    @TableField("lz_f_id")
    private String fId;
    @TableField("lz_is_newd")
    private String isNewd;
}
