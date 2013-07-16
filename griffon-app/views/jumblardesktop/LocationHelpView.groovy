package jumblardesktop

actions {
    action(id: 'cancelAction',
       name: 'Cancel',
       closure: controller.hide,
       mnemonic: 'C',
       shortDescription: 'Cancel'
    )
    action(id: 'okAction',
       name: 'Ok',
       closure: controller.hide,
       mnemonic: 'K',
       shortDescription: 'Ok'
    )
}

panel(id: 'content') {
    migLayout (layoutConstraints: 'fill')
	
	label(text:" ")
    label('Getting your secret location.', constraints: 'grow')
	label(text:" ", constraints: 'wrap')
	
	label(text:" ")
	label(text:"1. Visit google maps and search for your secret location.")
	label(text:" ", constraints: 'wrap')
	
	label(text:" ")
	label(text:"2. Right click on your secret point, then select \"What's here?\".")
	label(text:" ", constraints: 'wrap')
	
	label(text:" ")
	label(text:"3. Copy the location coordinates from the search bar and paste it into Jumblar.")
	label(text:" ", constraints: 'wrap')
    panel(constraints: 'dock south') {
        gridLayout(cols: 2, rows: 1)
        button(okAction)
    }
    
}
