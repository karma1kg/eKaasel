package bt.gov.voice.util;

import javax.swing.*;
import java.awt.*;

public class SystemTrayIcon {
	public static void displayTray(String message){
		if(!SystemTray.isSupported()){
			System.out.println("System Tray not supported");
			return;
		}
		SystemTray tray = SystemTray.getSystemTray();
		ImageIcon icon = null;
		icon = new ImageIcon("/resources/images/logo_client_large.png");
		Image image = icon.getImage();
		
		TrayIcon trayIcon = new TrayIcon(image,"CDB Message");
		try{
			tray.add(trayIcon);
		} catch (AWTException e){ // i dont want to get exception if it doesn't work
			e.printStackTrace();
		}
		trayIcon.displayMessage("CDB Notification",message,TrayIcon.MessageType.INFO);
		
		try{
			Thread.sleep(10000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		tray.remove(trayIcon);
	}
}