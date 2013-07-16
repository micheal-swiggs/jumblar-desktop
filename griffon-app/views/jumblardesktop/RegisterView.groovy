package jumblardesktop

actions{
	action(id:'continueAction',
		name: 'Continue',
		mnemonic: 'C',
		closure: controller.continueAction)
	action(id:'signinAction',
		name: 'Signin',
		closure: controller.signinAction)
	action(id: 'pasteAction',
		name: 'P',
		closure: controller.pasteAction)
	action(id: 'generateAction',
		name: 'Generate',
		closure: controller.generateAction)
	action(id: 'helpAction',
		name: '?',
		closure: controller.helpAction)
}

model.validation = " "

controller.jframe = application(title: 'Jumblar',
	name: 'RegisterView',
  preferredSize: [app.config.window.width, app.config.window.height],
  pack: true,
  //location: [50,50],
  locationByPlatform: true,
  iconImage:   imageIcon('/ic_launcher.png').image,) {
    // add content here
    //label('Content Goes Here') // delete me
		   menuBar{
			   menu('File'){
				   menuItem(signinAction)
				   controller.generateMenuItem = menuItem (generateAction)
			   }
		   }
			  
		   panel (border: titledBorder (title: 'Register')){
			   migLayout(layoutConstraints: 'fill')
			   
			   panel (constraints: 'north, wrap'){
				   migLayout (layoutConstraints: 'fill')
			   
				   label(text: 'Username: ', constraints: 'right')
				   textField(columns: 30, constraints: 'grow, wrap',
					   text: bind('username', source: model, mutual:true))
				   
				   label(text: 'Email: ', constraints: 'right')
				   textField(columns: 30, constraints: 'grow, wrap',
					   text: bind('email', source: model, mutual:true))
				   
				   label(text: 'Password: ', constraints: 'right')
				   passwordField(columns: 30, constraints: 'grow, wrap',
					   text: bind('password', source:model, mutual:true))
				   
				   label(text: 'Confirm: ', constraints: 'right')
				   passwordField(columns: 30, constraints: 'grow, wrap',
					   text: bind('confirm', source:model, mutual:true))
				   
				   label(text: 'Secret Location: ', constraints: 'right')
				   passwordField (columns: 30, constraints: 'span 2',
					   text: bind('secretLocation', source:model, mutual:true))
				   button pasteAction
				   button helpAction, constraints: 'wrap'
				   
				   button continueAction, constraints: 'dock south'
				   
				   label(text: bind('validation', source:model), constraints: 'dock south')
			   
			   }
		   }
		   
}
