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
public class PositionDTO implements Serializable {

    private static final long serialVersionUID = -3148349064427411770L;
    private int x;
    private int y;
}
