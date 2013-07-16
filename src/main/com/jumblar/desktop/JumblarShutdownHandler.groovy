package com.jumblar.desktop

import griffon.core.*
import java.awt.Window

class JumblarShutdownHandler implements ShutdownHandler{
	
	boolean canShutdown(GriffonApplication app) {
		def w = Window.windows.find{it.focused}
		
		if (w != null){
			Thread.start{
				Thread.sleep(500)
				w.setVisible(false)
				w.dispose()
			}
		}
		return !Main.isTrayIconActive()
	}
	void onShutdown(GriffonApplication app) {
		//println "Shutting down..."
	}
}