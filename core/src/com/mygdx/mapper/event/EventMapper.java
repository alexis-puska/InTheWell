package com.mygdx.mapper.event;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.event.Event;
import com.mygdx.service.dto.level.event.EventDTO;

public class EventMapper {

	public Event toEntity(EventDTO dto) {
		if (dto == null) {
			return null;
		}
		Event event = new Event();
		return event;
	}

	public List<Event> toEvents(List<EventDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Event> list = new ArrayList<>();
		for (EventDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
