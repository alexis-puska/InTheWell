package com.mygdx.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.Level;
import com.mygdx.mapper.event.EventMapper;
import com.mygdx.service.dto.level.LevelDTO;

public class LevelMapper {

	private PositionMapper positionMapper;
	private LevelNameMapper levelNameMapper;
	private DecorMapper decorMapper;
	private EventMapper eventMapper;
	private DoorMapper doorMapper;
	private LockMapper lockMapper;
	private PickMapper pickMapper;
	private PlatformMapper platformMapper;
	private RayonMapper rayonMapper;
	private TeleporterMapper teleporterMapper;
	private VortexMapper vortexMapper;
	private EnnemieMapper ennemieMapper;
	private ItemMapper itemMapper;

	public LevelMapper() {
		positionMapper = new PositionMapper();
		levelNameMapper = new LevelNameMapper();
		decorMapper = new DecorMapper();
		eventMapper = new EventMapper();
		doorMapper = new DoorMapper();
		lockMapper = new LockMapper();
		pickMapper = new PickMapper();
		platformMapper = new PlatformMapper();
		rayonMapper = new RayonMapper();
		teleporterMapper = new TeleporterMapper();
		vortexMapper = new VortexMapper();
		ennemieMapper = new EnnemieMapper();
		itemMapper = new ItemMapper();
	}

	public Level toEntity(LevelDTO dto) {
		if (dto == null) {
			return null;
		}
		Level level = new Level();
		level.setBackground(dto.getBackground());
		level.setNext(dto.getNext());
		level.setHorizontalPlateform(dto.getHorizontalPlateform());
		level.setShowPlatform(dto.isShowPlatform());
		level.setVerticalPlateform(dto.getVerticalPlateform());
		level.setName(levelNameMapper.toLevelNames(dto.getName()));
		level.setDecor(decorMapper.toDecors(dto.getDecor()));
		level.setEvent(eventMapper.toEvents(dto.getEvent()));
		level.setLock(lockMapper.toLocks(dto.getLock()));
		level.setDoor(doorMapper.toDoors(dto.getDoor(), level));
		level.setPick(pickMapper.toPicks(dto.getPick()));
		level.setPlatform(platformMapper.toPlatforms(dto.getPlatform()));
		level.setRayon(rayonMapper.toRayons(dto.getRayon()));
		level.setTeleporter(teleporterMapper.toTeleporters(dto.getTeleporter()));
		level.setVortex(vortexMapper.toVortexs(dto.getVortex()));
		level.setEnnemies(ennemieMapper.toEnnemies(dto.getEnnemies()));
		level.setItems(itemMapper.toItems(dto.getItems()));
		level.setStartPlayers(positionMapper.toEntity(dto.getStartPlayers()));
		level.setStartEffectObjets(positionMapper.toEntity(dto.getStartEffectObjets()));
		level.setStartPointObjets(positionMapper.toEntity(dto.getStartPointObjets()));
		level.setStartPlayers(positionMapper.toEntity(dto.getStartPlayers()));
		return level;
	}

	public List<Level> toLevels(List<LevelDTO> dtos) {
		List<Level> list = new ArrayList<>();
		if (dtos == null) {
			return list;
		}
		for (LevelDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
