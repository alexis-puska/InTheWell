package com.mygdx.service.dto.database;

import java.io.Serializable;
import java.util.List;

import com.mygdx.enumeration.GameKeyEnum;
import com.mygdx.enumeration.GameModeEnum;
import com.mygdx.enumeration.GameOptionEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestDTO implements Serializable {

	private static final long serialVersionUID = -6487941132256363742L;

	private int id;
	private boolean light;
	private boolean life;
	private TextDTO titre;
	private TextDTO description;
	private GameOptionEnum option;
	private GameModeEnum mode;
	private int remove;
	private boolean bombe;
	private boolean bombeUp;
	private int disguise;
	private GameKeyEnum key;
	private List<Integer> family;
	private List<RequiredDTO> require;

}
