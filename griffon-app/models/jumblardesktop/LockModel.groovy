package jumblardesktop

class LockModel {
    @Bindable String title
    @Bindable int width = 0
    @Bindable int height = 0
    @Bindable boolean resizable = true
    @Bindable boolean modal = true
	
	@Bindable String lock
	@Bindable String notification
	
	@Bindable int attempts = 0
}
