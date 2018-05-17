package com.mygdx.domain.event;

import java.util.List;

import com.mygdx.service.dto.level.event.EnableElementDTO;
import com.mygdx.service.dto.level.event.MessageDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Event {

	private boolean onlyOnce;
	private boolean triggered;

	// declanchement par proximité
	private boolean near;
	private int x;
	private int y;
	private int d;

	// declanchement par decompte
	private boolean time;
	private int timeout;

	// condition declanchement
	private boolean explosion;
	private boolean noMoreEnnemie; // plus d'ennemie dans le niveau
	private boolean onBirth; // spawn joueur
	private boolean onDeath; // mort du joueur
	private boolean onLevelEnter;

	// option;
	private boolean mirror;
	private boolean nightmare;
	private boolean timeAttackeOption;
	private boolean multiOption;
	private boolean ninja;

	/***************************************
	 * ACTION
	 ***************************************/
	private List<EnableElementDTO> enableElement;
	private List<MessageDTO> message;
	private String song;
	private String sound;
	private int darknessValue;
	private int iceValue;
}
