package com.mygdx.service.dto.level;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StartPointObjetsDTO implements Serializable {

    private static final long serialVersionUID = 9116647666198572784L;
    private int x;
    private int y;
}
