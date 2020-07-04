package com.ruoyi.assetspackage.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * CollectionrecordimprotFlow对象 t_lc_collectionrecordimprot_flow
 * 
 * @author guozeqi
 * @date 2020-05-12
 */
@Data
public class CollectionrecordimprotFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String id;

    private String importBatchNo;

    private String orgId;

    private String orgName;

    private String total;


}
