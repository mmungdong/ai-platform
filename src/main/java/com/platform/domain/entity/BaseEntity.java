package com.platform.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MungDong
 * @create 2024-03-23-11:42
 */
@Data
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = 8925514045582235838L;
    private ID id;


    @JsonFormat(pattern = "yyyy-MM-dd  HH:mm:ss")
    private Date updateAt = new Date();

}