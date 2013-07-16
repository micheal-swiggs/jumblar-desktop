package jumblardesktop

import java.awt.Window
import griffon.transform.Threading
import java.awt.Dimension
import java.awt.Toolkit
import com.jumblar.desktop.Main

class LockController {
    def model
    def view
    def builder

    protected dialog
	def codeGuess = false
	private correctGuess = false
	
	boolean codePrompt(Window window){
		codeGuess = true
		correctGuess = false
		show (window)
		codeGuess = false
		def result = correctGuess
		println "result: ${result}"
		correctGuess = false
		return result
	}
	
	
    @Threading(Threading.Policy.INSIDE_UITHREAD_SYNC)
    void show(Window window) {
        window = window ?: Window.windows.find{it.focused}
        if(!dialog || dialog.owner != window) {
            app.windowManager.hide(dialog)
            dialog = builder.dialog(
                owner: window,
                title: model.title,
                resizable: model.resizable,
                modal: model.modal) {
                container(view.content)
            }
            if(model.width > 0 && model.height > 0) {
                dialog.preferredSize = [model.width, model.height]
            }
            dialog.pack()
        }
		int x,y
		if (window != null){
			x = window.x + (window.width - dialog.width) / 2
			y = window.y + (window.height - dialog.height) / 2
		} else {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			x = screenSize.getWidth() / 2 - dialog.width / 2
			y = screenSize.getHeight() / 2 - dialog.height / 2
		}
        dialog.setLocation(x, y)
        app.windowManager.show(dialog)
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_SYNC)
    def hide = { evt = null ->
		println "hide dialog"
        app.windowManager.hide(dialog)
        dialog = null
    }
	
	@Threading(Threading.Policy.INSIDE_UITHREAD_SYNC)
	def okAction = { evt = null ->
		if (!codeGuess){
			if (!model.lock || model.lock.size() <3){
				model.notification = "Code must be 3 characters long."
				return
			}
			app.windowManager.hide (dialog)
			dialog = null
			Main.setProtectionCode ("${model.lock}")
		} else {
			if (!model.lock.equals( Main.getProtectionCode())){
				model.notification = "Incorrect protection code."
				model.attempts += 1
				if (model.attempts > 2){
					correctGuess = false
					app.windowManager.hide (dialog)
					dialog = null
				}
			} else {
				correctGuess = true
				model.attempts = 0
				app.windowManager.hide (dialog)
				dialog = null
			}
		}
	}
	
	boolean hasLockCode(){
		return model.lock != null
	}
}
