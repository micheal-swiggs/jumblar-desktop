package jumblardesktop

import com.jumblar.desktop.JumblarShutdownHandler;

class JumblarDesktopController {
    // these will be injected by Griffon
    def model
    def view
	
	def jframe
	def windowManager
    // void mvcGroupInit(Map args) {
    //    // this method is called after model and view are injected
    // }
	
	void mvcGroupInit (Map args){
		windowManager = app.windowManager
		app.addShutdownHandler( new JumblarShutdownHandler())
	}

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
		windowManager.show (windowManager.findWindow("JumblarDesktop"))
	}
	
	def hide(){
		windowManager.hide (windowManager.findWindow("JumblarDesktop"))
	}
	
	def setLocation (loc){
		windowManager.findWindow("JumblarDesktop").setLocation (loc)
	}
	
	def registerAction = { evt = null ->
		println "registerAction"
		def registerController = app.controllers.register
		if(!registerController){
			def (m, v, c) = createMVCGroup('register')
			registerController = c
		}
		registerController.setLocation(jframe.location)
		registerController.show()
		hide()
	}
	
	def signinAction = { evt = null ->
		println "signinAction"
		def signinController = app.controllers.signin
		if (!signinController){
			def (m,v,c) = createMVCGroup('signin')
			signinController = c
		}
		signinController.setLocation (jframe.location)
		signinController.show()
		hide()
	}
}
