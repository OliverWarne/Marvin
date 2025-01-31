package com.liav.bot.listeners;

import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MentionEvent;
import sx.blah.discord.handle.obj.IMessage;

import com.liav.bot.interaction.commands.Command;
import com.liav.bot.interaction.commands.CommandHandler;
import com.liav.bot.main.Bot;
import com.liav.bot.util.AutomodUtil;
import com.liav.bot.util.storage.CommandStorage;

/**
 * {@link IListener} which checks for when this bot is {@link MentionEvent
 * mentioned } After recieving the mention, checks if it is a {@link Command}
 * (as specified in {@link CommandStorage}), and
 * {@link CommandHandler#executeCommand(int, IMessage) executes it}.
 * 
 * @author Liav
 * @see IMessage
 * @see CommandHandler
 */
public class MentionListener implements IListener<MentionEvent> {

	@Override
	public void handle(MentionEvent event) {
		try {
			if (AutomodUtil.isAdmin(event.getMessage().getAuthor(), event
			        .getMessage().getGuild())) {
				System.out.println("Mentioned.");
				event.getMessage().delete();
				Bot.sendMessage(
				        event.getMessage()
				                .getContent()
				                .substring(
				                        event.getMessage().getContent()
				                                .indexOf(">") + 2), event
				                .getMessage().getChannel());
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
