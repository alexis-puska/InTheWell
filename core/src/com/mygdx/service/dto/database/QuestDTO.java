package com.mygdx.service.dto.database;

import java.io.Serializable;
import java.util.List;

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

	private long id;
	private boolean light;
	private boolean life;
	private TextDTO titre;
	private TextDTO description;
	private int option;
	private int mode;
	private int remove;
	private boolean bombe;
	private boolean bombeUp;
	private int disguise;
	private int key;
	private List<Long> family;
	private List<RequiredDTO> require;

}
