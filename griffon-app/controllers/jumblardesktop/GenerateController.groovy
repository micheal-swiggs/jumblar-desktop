package jumblardesktop

import com.jumblar.core.controllers.*;
import java.awt.datatransfer.StringSelection
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

class GenerateController {
    // these will be injected by Griffon
    def model
    def view

	def jframe
	String currentPassword
	String currentHiddenPassword
	
	def trayIconController
	def windowManager
	
	void mvcGroupInit(Map args){
		if (!app.controllers.trayIcon){
			def (m,v,c) = createMVCGroup('trayIcon')
		}
		trayIconController = app.controllers.trayIcon
		windowManager = app.windowManager
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
	
	def closeOperation = { evt = null ->
		println 'close operation'
	}
	
	def showFromTray(){
		withMVCGroup ('lock'){ m,v,c ->
			if (!c.codePrompt()){
				reset()
				signinAction()
			} else {
				openAction()
			}
		}
	}
	
	def reset(){
		currentPassword = null
		model.hashBase = null
	}
	
	def show(){
		while (true){
			boolean canBreak = false
			withMVCGroup ('lock'){ m,v,c ->
				c.show()
				if (c.hasLockCode()) canBreak = true
			}
			if (canBreak) break
		}
		windowManager.show (windowManager.findWindow("GenerateView"))
		
	}
	
	def hide(){
		windowManager.hide (windowManager.findWindow("GenerateView"))
	}
	
	def setLocation (loc){
		windowManager.findWindow("GenerateView").setLocation (loc)
	}
	
	def generateAction = { evt = null ->
		model.notification = "Generating password...."
		if(model.spice == null) model.spice = ""
		PhraseController pc = new PhraseController();
		currentPassword = 
			pc.generatePhrase (model.hashBase, model.spice, model.length.toInteger())
		setHiddenPassword (currentPassword)
		model.notification = " "
	}
	
	def setHiddenPassword(pword){
		def result
		def pSize = pword.size()
		switch (pSize){
			case 0..5:
				result = "*"*pSize
				break
			case 6:
				result = "*** ***"
				break
			case 7:
				result = "** *** **"
				break
			case 8: 
				result = "**** ****"; break;
			case 9: 
				result = "*** *** ***"; break;
			case 10:
				result = "*** **** ***"; break;
			case 11: 
				result = "**** *** ****"; break;
			case 12: 
				result = "**** **** ****"; break;
			case 13:
				result = "*** **** *** ***"; break;
			case 14: 
				result = "*** **** *** ****"; break;
			case 15:
				result = "**** **** *** ****"; break;
			case 16:
				result = "**** **** **** ****"; break;
			case 17:
				result = "*** **** *** **** ***"; break;
			case 18:
				result = "**** *** **** *** ****"; break;
			case 19:
				result = "**** **** *** **** ****"; break;
			case 20:
				result = " **** ***** **** *** ****"; break;
			default:
				result = ""
				for (int i=0; i<pSize; i++){
					if(i%4 == 3){
						result += "* "
					}else{
						result += "*"
					}
				}				
		}
		currentHiddenPassword = result
		model.result = result
		
	}
	
	def copyAction = { evt = null ->
		copyPassword();
	}
	
	def copyPassword(){
		
		StringSelection selection = new StringSelection(currentPassword);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);
		model.notification = "Password in clipboard."
		Thread.start{
			Thread.sleep(3000)
			model.notification = " "
		}
	}
	
	def generateCloseAction = { evt = null ->
		generateAction()
		copyPassword();
		trayIconController.notifyPasswordInClipboard()
		minimize()
	}
	
	def minimize(){
		hideAction()
		jframe.setVisible(false)
		jframe.dispose()
	}
	
	def showAction = { evt = null ->
		int idex = 0;
		def buf = ""
		for (int i=0; i<currentHiddenPassword.size(); i++){
			if (currentHiddenPassword[i] != "*"){
				buf += " "
			} else {
				buf += currentPassword[idex]
				idex++
			}
		}
		model.result = buf
	}
	
	def hideAction = { evt = null ->
		model.result = currentHiddenPassword
	}
	
	def exitAction = { evt = null ->
		System.exit(0)
	}
	
	def openAction = { evt = null ->
		jframe.setVisible (true)
	}
	
	def signinAction = { evt = null ->
		def signinController = app.controllers.signin
		if (!signinController){
			def (m,v,c) = createMVCGroup ('signin')
			signinController = c
		}
		signinController.setLocation (jframe.location)
		signinController.show()
		hide()
	}
	
	def registerAction = { evt = null ->
		def registerController = app.controllers.register
		if (!registerController){
			def (m, v, c) = createMVCGroup ('register')
			registerController = c
		}
		registerController.setLocation (jframe.location)
		registerController.show()
		hide()
	}
}
