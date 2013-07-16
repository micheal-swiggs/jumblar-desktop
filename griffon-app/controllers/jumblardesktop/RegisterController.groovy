package jumblardesktop

import com.jumblar.core.controllers.BaseController;
import com.jumblar.core.domain.HashBase;
import com.jumblar.core.domain.SimpleJumble;
import com.jumblar.desktop.Main;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;

class RegisterController {
    // these will be injected by Griffon
    def model
    def view
	def jframe
	
	def windowManager
	def generateMenuItem
	
	def isUploading
	
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
		model.confirm = ""
		model.secretLocation = ""
		windowManager.show (windowManager.findWindow("RegisterView"))
		checkHashBaseExists()
	}
	
	def hide(){
		windowManager.hide (windowManager.findWindow("RegisterView"))
	}
	
	def setLocation(loc){
		windowManager.findWindow ("RegisterView").setLocation (loc)
	}
	
	def checkHashBaseExists(){
		if (!app.controllers.generate || !app.models.generate.hashBase){
			generateMenuItem.setVisible (false)
			return
		}
		generateMenuItem.setVisible (true)
	}
	
	def helpAction = { evt = null ->
		withMVCGroup ('locationHelp'){ m,v,c ->
			c.show()
		}
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
	
	def continueAction = { evt = null ->
		model.validation = " "
		if (!validateUsername()) return
		if (!validateEmail()) return
		if (!validatePassword()) return
		if (!validateSecretLocation()) return
		
		register()
	}
	
	def generateAction = { evt = null ->
		def generateController = app.controllers.generate
		generateController.setLocation (jframe.location)
		generateController.show()
		hide()
	}
	
	
	def pasteAction = { evt = null ->
		String data = null
		try{
			data = (String) Toolkit.getDefaultToolkit()
				.getSystemClipboard().getData(DataFlavor.stringFlavor)
		} catch (Exception e){
			println e
		}
		if (data != null) model.secretLocation = data;
	}
	
	def uploadingFeedback(){
		def base = "Uploading new account info......"
		Thread.start{
			while(isUploading){
				model.validation = base
				for (int i=0; i<10;i++){
					Thread.sleep(333)
					model.validation += "."
				}
			}
		}
	}
	
	def register(){
		int N = Main.getN()
		int r = Main.getR()
		int p = Main.getP()
		int keyLength = Main.getKeyLength()
		model.validation = "Uploading new account info......"
		/** The following is for user registration. */
		BaseController bc = new BaseController();
		isUploading = true
		uploadingFeedback()
		SimpleJumble simpleJumble = bc.createNewPGPEntry(
			model.username, model.email, "", model.password, model.secretLocation,
				N, r, p, keyLength);
		isUploading = false
		Main.storePreferences (model.username, model.email)
		openGenerate (simpleJumble.getHashBase())
		model.validation = " "
	}
	
	def openGenerate(HashBase hashBase){
		def generateController = app.controllers.generate
		if (!generateController){
			def (m,v,c) = createMVCGroup ('generate')
			generateController = c
		}
		app.models.generate.hashBase = hashBase
		generateController.setLocation(jframe.location)
		generateController.show()
		hide()
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
		if ("${model.password}".equals("${model.confirm}")) return true
		model.validation = "Passwords do not match."
		return false
	}
	
	boolean validateSecretLocation(){
		if (!model.secretLocation || model.secretLocation.size() == 0){
			model.validation = "Enter valid secret location."
			return false
		}
		return true
	}
}
