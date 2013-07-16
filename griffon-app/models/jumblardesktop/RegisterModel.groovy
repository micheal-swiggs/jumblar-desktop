package jumblardesktop

import groovy.beans.Bindable

class RegisterModel {
   // @Bindable String propName
	@Bindable String username
	@Bindable String email
	@Bindable String password
	@Bindable String confirm
	@Bindable String secretLocation
	
	@Bindable String validation
}