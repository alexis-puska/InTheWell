package com.mygdx.mapper.event;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.domain.event.Message;
import com.mygdx.service.dto.level.event.MessageDTO;

public class MessageMapper {

	public Message toEntity(MessageDTO dto) {
		if (dto == null) {
			return null;
		}
		Message message = new Message();
		message.setEn(dto.getEn());
		message.setEs(dto.getEs());
		message.setFr(dto.getFr());
		message.setTimeout(dto.getTimeout());
		return message;
	}

	public List<Message> toMessages(List<MessageDTO> dtos) {
		if (dtos == null) {
			return null;
		}
		List<Message> list = new ArrayList<>();
		for (MessageDTO dto : dtos) {
			list.add(toEntity(dto));
		}
		return list;
	}

}
