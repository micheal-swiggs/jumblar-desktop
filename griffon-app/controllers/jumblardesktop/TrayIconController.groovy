package jumblardesktop

import java.awt.TrayIcon
import com.jumblar.desktop.Main

class TrayIconController {
    // these will be injected by Griffon
    def model
    def view
	
	def sysTray
	def trayIcon
	
	void mvcGroupInit (Map args){
		Main.setTrayIconActive(true)
	}
    // void mvcGroupInit(Map args) {
    //    // this method is called after model and view are injected
    // }

    // void mvcGroupDestroy() {
    //    // this method is called when the group is destroyed
    // }

    /*
        Remember that actions will be called outside of the UI thread
        by default. You can change this setting of course.
        Please read chapter 9 of the Griffon Guide to know more.
       
    def action = { evt = null ->
    }
    */
	
	def notifyPasswordInClipboard(){
		trayIcon.displayMessage ("Password in clipboard.",null, TrayIcon.MessageType.NONE)
	}
	
	def openAction = { evt = null ->
		app.controllers.generate.showFromTray()
	}
}
