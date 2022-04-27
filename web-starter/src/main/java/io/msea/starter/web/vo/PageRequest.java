package io.msea.starter.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author msea-admin
 * @create 2022-01-25 15:30
 */
@Data
public class PageRequest implements Serializable {
    private static final long serialVersionUID = 1415112481236195196L;

    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
