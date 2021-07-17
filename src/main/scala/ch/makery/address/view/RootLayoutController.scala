package ch.makery.address.view
import ch.makery.address.MainApp
import ch.makery.address.model.{Collectibles, User}
import scalafxml.core.macros.sfxml
import scalafx.application.Platform

import java.io.IOException

@sfxml
class RootLayoutController {
  // actions for save function
  def handleSave(): Unit = {
    MainApp.handleSave()
  }

  // actions for logout
  def handleLogOut(): Unit = {
    MainApp.endSession()
    MainApp.showScene(MainApp.loginPath)
  }

  // actions for closing
  def handleClose(): Unit = {
    MainApp.endSession()
    Platform.exit() // safely closes
  }
}
