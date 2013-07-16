package jumblardesktop

import static java.awt.TrayIcon.MessageType.*
import groovy.ui.Console

actions{
	action(id:'openAction',
		name: 'Open',
		closure: controller.openAction)
	action(id:'exitAction',
		name: 'Exit',
		closure: {x->System.exit(0)})
}
controller.sysTray = systemTray {
	   trayIcon = trayIcon(id: "trayIcon",
	 resource: "/ic_launcher.png",
	 actionPerformed: controller.openAction) {
	 popupMenu {
		menuItem(openAction)
		separator()
		menuItem(exitAction)
	 }
   }
	 controller.trayIcon = trayIcon
 }