package jumblardesktop

import com.jumblar.core.controllers.BaseController;
import com.jumblar.core.domain.HashBase;
import com.jumblar.core.domain.SimpleJumble;
import com.jumblar.desktop.Main;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

class SigninController {
    // these will be injected by Griffon
    def model
    def view
	
	def jframe
	def generateMenuItem
	def windowManager
	void mvcGroupInit (Map args){
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
	
	def show(){
		model.password = ""
		model.secretLocation = ""
		windowManager.show (windowManager.findWindow("SigninView"))
		checkHashBaseExists()
	}
	
	def hide(){
		windowManager.hide (windowManager.findWindow("SigninView"))
	}
	
	def setLocation (loc){
		windowManager.findWindow("SigninView").setLocation (loc)
	}
	
	def checkHashBaseExists(){
		if (!app.controllers.generate || !app.models.generate.hashBase){
			generateMenuItem.setVisible(false)
			return
		}
		generateMenuItem.setVisible(true)
	}
	
	def registerAction = { evt = null ->
		def registerController = app.controllers.register
		if (!registerController){
			def (m,v,c) = createMVCGroup ('register')
			registerController = c
		}
		registerController.setLocation (jframe.location)
		registerController.show()
		hide()
	}
	
	def pasteAction = { evt = null ->
		println "pasteAction"		
		String data = null;
		try{
			data = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
		} catch (Exception e){
			println e
		}
		if (data != null ) model.secretLocation = data;
	}
	
	def continueAction = { evt = null ->
		model.validation = " "
		if (!validateUsername()) return
		if (!validateEmail()) return
		if (!validatePassword()) return
		if (!validateSecretLocation()) return
		signin()
	}
	
	def signin(){
		println "signin"
		BaseController bc = new BaseController()
		SimpleJumble sj = bc.computeHashBase(model.username, model.email,
			"", model.password, model.secretLocation)
		Main.storePreferences(model.username, model.email)
		openGenerate (sj.getHashBase())
	}
	
	def openGenerate(HashBase hashBase){
		println "openGenerate"
		def generateController = app.controllers.generate
		if (!generateController){
			def (m,v,c) = createMVCGroup ('generate')
			generateController = c
		}
		
		app.models.generate.hashBase = hashBase
		generateController.setLocation (jframe.location)
		generateController.show()
		hide()		
	}
	
	def generateAction = { evt = null ->
		windowManager.findWindow ("GenerateView").setLocation (jframe.location)
		windowManager.show (windowManager.findWindow("GenerateView"))
		windowManager.hide (windowManager.findWindow("SigninView"))
	}
	
	boolean validateUsername(){
		if (model.username == null || model.username.size() == 0){
			model.validation = "Username empty."
			return false
		}
		return true
	}
	
	boolean validateEmail(){
		if (!model.email || model.email.size() == 0){
			model.validation = "Email empty."
			return false
		}
		return true
	}
	
	boolean validatePassword(){
		return true //allow empty password
	}
	
	boolean validateSecretLocation(){
		if (!model.secretLocation || model.secretLocation.size() == 0){
			model.validation = "Enter valid secret location."
			return false
		}
		return true
	}
}
