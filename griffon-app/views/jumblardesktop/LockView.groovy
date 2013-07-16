package jumblardesktop

actions {
    action(id: 'okAction',
       name: 'Ok',
       closure: controller.okAction,
       mnemonic: 'K',
       shortDescription: 'Ok'
    )
}

model.notification = " "

panel(id: 'content') {
    migLayout (layoutConstraints: 'fill')
	
	label(text:" ")
    label('Enter 3 character protection code:', constraints: 'grow')
	label(text:" ", constraints: 'wrap')
	
	label(text:" ")
	passwordField(columns: 20,	constraints: 'grow',
		text: bind('lock', source: model, mutual:true))
	label(text:" ", constraints: 'wrap')
	
	label(text:" ")
	label(text: bind('notification', source: model, mutual:true),
		constraints: 'span 2, wrap')
	
    panel(constraints: 'dock south') {
        gridLayout(cols: 2, rows: 1)
        button(okAction)
    }
    
	/*
    keyStrokeAction(component: current,
        keyStroke: "ESCAPE",
        condition: "in focused window",
        action: cancelAction)
     */
}
