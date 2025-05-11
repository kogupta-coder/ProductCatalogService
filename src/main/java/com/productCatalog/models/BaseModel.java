package com.productCatalog.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public  abstract class BaseModel {

    Long id;
    Date createdAt;
    Date lastModifiedAt;


}
