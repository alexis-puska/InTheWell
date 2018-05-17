package com.mygdx.service.dto.level;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.service.dto.level.event.EventDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LevelDTO extends IdentifiableDTO {

	private int next;
	private boolean showPlatform;
	private int background;
	private int verticalPlateform;
	private int horizontalPlateform;
	private List<LevelNameDTO> name;
	private List<DecorDTO> decor;
	private List<EventDTO> event;
	private List<DoorDTO> door;
	private List<LockDTO> lock;
	private List<PickDTO> pick;
	private List<PlatformDTO> platform;
	private List<RayonDTO> rayon;
	private List<TeleporterDTO> teleporter;
	private List<VortexDTO> vortex;
	private List<EnnemieDTO> ennemies;
	private PositionDTO startPlayers;
	private PositionDTO startEffectObjets;
	private PositionDTO startPointObjets;
	private List<ItemDTO> items;

	public LevelDTO(int id, boolean showPlatform, int background, int verticalPlateform, int horizontalPlateform) {
		super(id);
		this.showPlatform = showPlatform;
		this.background = background;
		this.verticalPlateform = verticalPlateform;
		this.horizontalPlateform = horizontalPlateform;
		this.next = 0;
		this.decor = new ArrayList<>();
		this.event = new ArrayList<>();
		this.door = new ArrayList<>();
		this.lock = new ArrayList<>();
		this.pick = new ArrayList<>();
		this.platform = new ArrayList<>();
		this.rayon = new ArrayList<>();
		this.teleporter = new ArrayList<>();
		this.vortex = new ArrayList<>();
		this.ennemies = new ArrayList<>();
		this.startPlayers = null;
		this.startEffectObjets = null;
		this.startPointObjets = null;
		this.items = new ArrayList<>();
	}

	public LevelDTO(int id) {
		super(id);
		this.showPlatform = true;
		this.background = 1;
		this.verticalPlateform = 0;
		this.horizontalPlateform = 0;
		this.next = 0;
		this.decor = new ArrayList<>();
		this.event = new ArrayList<>();
		this.door = new ArrayList<>();
		this.lock = new ArrayList<>();
		this.pick = new ArrayList<>();
		this.platform = new ArrayList<>();
		this.rayon = new ArrayList<>();
		this.teleporter = new ArrayList<>();
		this.vortex = new ArrayList<>();
		this.ennemies = new ArrayList<>();
		this.startPlayers = null;
		this.startEffectObjets = null;
		this.startPointObjets = null;
		this.items = new ArrayList<>();
	}

}
