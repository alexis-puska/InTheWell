package com.mygdx.service.dto.level.event;

import java.io.Serializable;
import java.util.List;

import com.mygdx.service.dto.level.IdentifiableDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDTO extends IdentifiableDTO implements Serializable {

	private static final long serialVersionUID = 1088138268216464188L;

	
	/***************************************
	 * TRIGGER
	 ***************************************/
	
	//nombre de fois déclanchable
	private boolean onlyOnce; 
	private boolean triggered;
	
	//declanchement par proximité
	private boolean near;
	private int x;
	private int y;
	private int d;
	
	//declanchement par decompte
	private boolean time;
	private int timeout;
	
	//condition declanchement
	private boolean explosion;
	private boolean noMoreEnnemie; // plus d'ennemie dans le niveau
	private boolean onBirth; //spawn joueur
	private boolean onDeath; //mort du joueur
	private boolean onLevelEnter;
	
	//option
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
	
	public EventDTO(int id, int x, int y) {
		super(id);
		this.x = x;
		this.y = y;
		this.darknessValue = -1;
		this.iceValue = -1;
	}
	
	/*
	 * Dans le code du jeu il y a les triggers 
	 * $t_timer
	 * $t_pos
	 * $attach
	 * $do
	 * $end
	 * $birth
	 * $death
	 * $exp
	 * $enter
	 * $night
	 * $mirror
	 * $multi
	 * $ninja
	 * Et pour les actions ils y a 
	 * $e_score
	 * $e_spec
	 * $e_ext
	 * $e_bad
	 * $e_kill
	 * $e_tuto
	 * $e_msg
	 * $e_killMsg
	 * $e_pointer
	 * $e_killPt
	 * $e_mc
	 * $e_pmc
	 * $e_music
	 * $e_add
	 * $e_rem
	 * $e_itemLine
	 * $e_goto
	 * $e_hide
	 * $e_hideBorders
	 * $e_ctrigger
	 * $e_portal
	 * $e_setVar
	 * $e_openPortal
	 * $e_darkness
	 * $e_fakelid
	 */

}
