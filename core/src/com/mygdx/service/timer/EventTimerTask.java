package com.mygdx.service.timer;

import java.util.TimerTask;

import com.mygdx.domain.event.Event;
import com.mygdx.enumeration.EventNotificationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventTimerTask extends TimerTask {

	private boolean runned;
	private Event event;

	public EventTimerTask(final Event event) {
		super();
		this.event = event;
	}

	@Override
	public void run() {
		this.event.enable(EventNotificationType.TIMEOUT);
	}
}
