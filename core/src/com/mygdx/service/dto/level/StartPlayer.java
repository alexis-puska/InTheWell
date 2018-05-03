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
public class StartPlayer implements Serializable {

    private static final long serialVersionUID = -6867679477182974708L;
    private int x;
    private int y;
}
