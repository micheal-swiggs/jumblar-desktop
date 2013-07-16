package jumblardesktop

import groovy.beans.Bindable

class SigninModel {
   // @Bindable String propName
	@Bindable String username
	@Bindable String email
	@Bindable String password
	@Bindable String secretLocation
	
	@Bindable String validation
}