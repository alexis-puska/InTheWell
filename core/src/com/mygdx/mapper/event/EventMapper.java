package com.mygdx.mapper.event;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.event.Event;
import com.mygdx.service.dto.level.event.EventDTO;

public class EventMapper {

	private MessageMapper messageMapper;
	private EnableElementMapper enableElementMapper;

	public EventMapper() {
		messageMapper = new MessageMapper();
		enableElementMapper = new EnableElementMapper();
	}

	public Event toEntity(EventDTO dto) {
		if (dto == null) {
			return null;
		}
		Event event = new Event();
		event.setD(dto.getD());
		event.setDarknessValue(dto.getDarknessValue());
		event.setExplosion(dto.isExplosion());
		event.setIceValue(dto.getIceValue());
		event.setId(dto.getId());
		event.setMirror(dto.isMirror());
		event.setMultiOption(dto.isMultiOption());
		event.setNear(dto.isNear());
		event.setNightmare(dto.isNightmare());
		event.setNinja(dto.isNinja());
		event.setNoMoreEnnemie(dto.isNoMoreEnnemie());
		event.setOnBirth(dto.isOnBirth());
		event.setOnDeath(dto.isOnDeath());
		event.setOnLevelEnter(dto.isOnLevelEnter());
		event.setOnlyOnce(dto.isOnlyOnce());
		event.setSong(dto.getSong());
		event.setSound(dto.getSound());
		event.setTime(dto.isTime());
		event.setTimeAttackeOption(dto.isTimeAttackeOption());
		event.setTimeout(dto.getTimeout());
		event.setTriggered(dto.isTriggered());
		event.setX(dto.getX());
		event.setY(dto.getY());
		event.setEnableElement(enableElementMapper.toEnableElements(dto.getEnableElement()));
		event.setMessage(messageMapper.toMessages(dto.getMessage()));
		return event;
	}

	public List<Event> toEvents(List<EventDTO> dtos) {
		List<Event> list = new ArrayList<>();
		if (dtos == null) {
			return list;
		}
		for (EventDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
