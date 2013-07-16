package jumblardesktop
import com.jumblar.desktop.Main

actions{
	action(id:'continueAction',
		name: 'Continue',
		mnemonic: 'C',
		closure: controller.continueAction)
	action(id:'pasteAction',
		name: 'P',
		closure: controller.pasteAction)
	action(id:'registerAction',
		name: 'Register',
		closure: controller.registerAction)
	action(id:'generateAction',
		name: 'Generate',
		closure: controller.generateAction)
}

def prefs = Main.getPreferences()
if (prefs != null){
	model.username = prefs[0]
	model.email = prefs[1]
}
model.validation = " "

controller.jframe = application(title: 'Jumblar',
	name: 'SigninView',
  preferredSize: [app.config.window.width, app.config.window.height],
  pack: true,
  //location: [50,50],
  locationByPlatform: true,
  iconImage:   imageIcon('/ic_launcher.png').image,) {
		   menuBar{
			   menu("File"){
				   menuItem(registerAction)
				   controller.generateMenuItem = menuItem(generateAction)
			   }
		   }
			   
		   panel ( border: titledBorder (title: 'Signin')){
			   migLayout(layoutConstraints: 'fill')
			   
			   panel (constraints: 'north, wrap'){
				   migLayout (layoutConstraints: 'fill')
				   
				   label(text: 'Username: ', constraints:'right')
				   textField(columns: 30, constraints: 'grow, wrap',
					   text: bind('username', source: model, mutual:true))
				   
				   label(text: 'Email: ', constraints:'right')
				   textField(columns: 30, constraints: 'grow, wrap',
					   text: bind('email', source: model, mutual:true))
					   
				   label(text: 'Password: ', constraints:'right')
				   passwordField(columns: 30, constraints: 'grow, wrap',
					   text: bind('password', source:model, mutual:true))
					   
				   label(text: 'Secret Location: ', constraints: 'right')			   
				   passwordField(columns: 30, constraints: 'span 2',
					   text: bind('secretLocation', source:model, mutual:true))
				   button pasteAction, constraints: 'wrap'
				   
			   
				   button continueAction, constraints: 'dock south'
				   label(text: bind('validation', source: model), constraints: 'dock south')
		   
				   }
		   }
}
