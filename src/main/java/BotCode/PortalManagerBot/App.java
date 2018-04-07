package BotCode.PortalManagerBot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class App extends ListenerAdapter{
    public static void main( String[] args ) throws LoginException, InterruptedException {
    	JDA bot = new JDABuilder(AccountType.BOT).setToken("NDI5MTI4NDE2NDk1Nzk2MjI2.DaqzSw.Yvn9SMSzfO4YCTQCmEuLjSgk0aU").buildBlocking();
    	bot.addEventListener(new App());
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
    	Message msg = e.getMessage();
    	MessageChannel msgChnl = e.getChannel();
    	User user = e.getAuthor();
    	if(!isBot(user) && receivedFromSudoPortal(msgChnl) && !isLink(msg)) {
    		System.out.println("deleting link");
    		msg.delete().queue();
    	}
		if(msg.getContentDisplay().equals("channelName")) {
    	System.out.println(msgChnl.getName());
		}
		
    }
    
    public boolean isBot(User user) {
    	return user.getName().equalsIgnoreCase("portalmanager");
    }
    
    public boolean isLink(Message msg) {
    	if(msg.getEmbeds().size() != 0) {
    		return true;
    	}
    	return false;
    }
    public boolean receivedFromSudoPortal(MessageChannel channel) {
    	return channel.getName().equalsIgnoreCase("testing-grounds");
    }
}
