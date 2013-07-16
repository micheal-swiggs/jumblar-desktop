application {
    title = 'JumblarDesktop'
    startupGroups = ['jumblarDesktop']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "locationHelp"
    'locationHelp' {
        model      = 'jumblardesktop.LocationHelpModel'
        view       = 'jumblardesktop.LocationHelpView'
        controller = 'jumblardesktop.LocationHelpController'
    }

    // MVC Group for "lock"
    'lock' {
        model      = 'jumblardesktop.LockModel'
        view       = 'jumblardesktop.LockView'
        controller = 'jumblardesktop.LockController'
    }

    // MVC Group for "lock"
    'lock' {
        model      = 'jumblardesktop.LockModel'
        view       = 'jumblardesktop.LockView'
        controller = 'jumblardesktop.LockController'
    }

    // MVC Group for "trayIcon"
    'trayIcon' {
        model      = 'jumblardesktop.TrayIconModel'
        view       = 'jumblardesktop.TrayIconView'
        controller = 'jumblardesktop.TrayIconController'
    }

    // MVC Group for "generate"
    'generate' {
        model      = 'jumblardesktop.GenerateModel'
        view       = 'jumblardesktop.GenerateView'
        controller = 'jumblardesktop.GenerateController'
    }

    // MVC Group for "signin"
    'signin' {
        model      = 'jumblardesktop.SigninModel'
        view       = 'jumblardesktop.SigninView'
        controller = 'jumblardesktop.SigninController'
    }

    // MVC Group for "register"
    'register' {
        model      = 'jumblardesktop.RegisterModel'
        view       = 'jumblardesktop.RegisterView'
        controller = 'jumblardesktop.RegisterController'
    }

    // MVC Group for "foo"
    'foo' {
        model      = 'jumblardesktop.FooModel'
        view       = 'jumblardesktop.FooView'
        controller = 'jumblardesktop.FooController'
    }

    // MVC Group for "jumblarDesktop"
    'jumblarDesktop' {
        model      = 'jumblardesktop.JumblarDesktopModel'
        view       = 'jumblardesktop.JumblarDesktopView'
        controller = 'jumblardesktop.JumblarDesktopController'
    }

}
