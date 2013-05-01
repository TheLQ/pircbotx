/**
 * Copyright (C) 2010-2013 Leon Blakey <lord.quackstar at gmail.com>
 *
 * This file is part of PircBotX.
 *
 * PircBotX is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PircBotX is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PircBotX. If not, see <http://www.gnu.org/licenses/>.
 */
package org.pircbotx.hooks.events;

import org.pircbotx.Channel;
import org.pircbotx.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.pircbotx.hooks.Event;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.types.GenericUserModeEvent;

/**
 * Called when a user (possibly us) gets superop status granted in a channel. Note
 * that this isn't supported on all servers or may be used for something else
 * <p>
 * This is a type of mode change and therefor is also dispatched in a
 * {@link org.pircbotx.hooks.events.ModeEvent}
 * @author Leon Blakey <lord.quackstar at gmail.com>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SuperOpEvent<T extends PircBotX> extends Event<T> implements GenericUserModeEvent<T> {
	protected final Channel channel;
	protected final User source;
	protected final User recipient;
	protected final boolean isSuperOp;

	/**
	 * Default constructor to setup object. Timestamp is automatically set
	 * to current time as reported by {@link System#currentTimeMillis() }
	 * @param channel The channel in which the mode change took place.
	 * @param source The user that performed the mode change.
	 * @param recipient The nick of the user that got 'voiced'.
	 */
	public SuperOpEvent(T bot, Channel channel, User source, User recipient, boolean isSuperOp) {
		super(bot);
		this.channel = channel;
		this.source = source;
		this.recipient = recipient;
		this.isSuperOp = isSuperOp;
	}

	/**
	 * Respond by send a message in the channel to the user that set the mode
	 * (<b>Warning:</b> not to the user that got SuperOp status!) in
	 * <code>user: message</code>
	 * format
	 * @param response The response to send
	 */
	@Override
	public void respond(String response) {
		getChannel().send().message(getSource(), response);
	}
}
