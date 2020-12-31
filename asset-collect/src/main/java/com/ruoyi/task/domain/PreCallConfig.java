package com.ruoyi.task.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author guozeqi
 * @create 2020-12-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Accessors(chain = true)
public class PreCallConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String caseNoStr;
    private String importBatchNoStr;
    private String relation;
    private String exoNum;
    private String isFilter;
}
