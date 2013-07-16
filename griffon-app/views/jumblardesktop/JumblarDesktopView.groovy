package jumblardesktop

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

actions{
	action(id: 'registerAction',
		name: 'Register',
		mnemonic: 'R',
		accelerator: shortcut('R'),
		closure: controller.registerAction)
	action(id:'signinAction',
		name: 'Signin',
		mnemonic: 'S',
		accelerator: shortcut('S'),
		closure: controller.signinAction)
	
	action(id:'openAction',
		name: 'Open',
		closure: { x -> println "open action"}
		)
	action(id:'noneAction',
		name: 'None',
		closure: {x -> println "none action"}
		)
}

controller.jframe = application(
	name: 'JumblarDesktop',
	title: 'JumblarDesktop',
  preferredSize: [app.config.window.width, app.config.window.height],
  pack: true,
  //location: [50,50],
  locationByPlatform: true,
  iconImage:   imageIcon('/ic_launcher.png').image,) {
    // add content here
		   panel( id: 'tab') {
			   hbox{
				   button registerAction
				   button signinAction
			   }
		   }
			   
}
