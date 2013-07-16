package jumblardesktop

import javax.swing.WindowConstants

actions{
	action(id:'generateAction',
		name: 'Generate',
		closure: controller.generateAction)
	action(id:'copyAction',
		name: 'Copy',
		closure: controller.copyAction)
	action(id:'generateCloseAction',
		name: 'Generate & Close',
		closure: controller.generateCloseAction)
	action(id:'showAction',
		name: 'Show',
		closure: controller.showAction)
	action(id:'hideAction',
		name: 'Hide',
		closure: controller.hideAction)
	action(id: 'signinAction',
		name: 'Signin',
		closure: controller.signinAction)
	action(id: 'registerAction',
		name: 'Register',
		closure: controller.registerAction)
	action(id: 'exitAction',
		name: 'Exit',
		closure: {x->System.exit(0)}
		)
	
}

model.length = "20"
model.notification = " "
model.result = " "

controller.jframe = application(title: 'Jumblar',
	name: "GenerateView",
  preferredSize: [app.config.window.width, app.config.window.height],
  pack: true,
  //location: [50,50],
  locationByPlatform: true,
  iconImage:   imageIcon('/ic_launcher.png').image,) {
    // add content here
		   
		   menuBar{
			   menu('File'){
				   menuItem (signinAction)
				   menuItem (registerAction)
				   separator()
				   menuItem (exitAction)
			   }
		   }
		   
		   panel (border: titledBorder (title: 'Generate')){
			   migLayout (layoutConstraints: 'fill')
			   
			   panel (constraints: 'north, wrap'){
				   migLayout (layoutConstraints: 'fill')
				   label(text: 'Spice: ', constraints: 'right')
				   textField(columns: 30, constraints: 'span 3, wrap',
					   text: bind('spice', source: model, mutual:true))
				   
				   label(text: 'Length: ', constraints: 'right')
				   textField(columns: 30, constraints: 'span 3, wrap',
					   text: bind('length', source: model, mutual:true))
				   
				   label(text: " ", constraints: 'right')
				   label(text: bind('notification', source: model), constraints: 'grow, wrap')
				   			   
				   
			   
			   }
			   
			   button generateAction, constraints: 'left'
			   button copyAction, constraints: 'left'
			   button generateCloseAction, constraints: 'right, wrap'
			   
			   label(text: bind('result', source: model), constraints: 'span 3, wrap')
			   button showAction, constraints: 'left'
			   button hideAction, constraints: 'left, wrap'
			   
		   		
		   }
		   
		   
		   
}
