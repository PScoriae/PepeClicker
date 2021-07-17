package ch.makery.address.view
import ch.makery.address.model.Collectibles.NewCollectibles
import ch.makery.address.model.PowerUps.{Type1PU, Type2PU}
import ch.makery.address.model.User
import scalafx.event.ActionEvent
import scalafx.scene.control.Label

// special abstract controller for game and collectibles controller
abstract class ActionController(mesg: Label) extends GeneralController(mesg) {
  def buyItem(action: ActionEvent): Unit
  def refreshLabels(): Unit
  def buyPU(pepe: Type1PU): Unit = {
    if (User.pepeCounter >= pepe._cost) {
      User.pepeCounter -= pepe._cost
      User.perClick += pepe._ability
      refreshLabels()
      showMessage("Purchase Successful!")
    } else {
      showMessage("Insufficient Pepes!")
    }
  }
  // buy power-ups
  def buyPU(pepe: Type2PU): Unit = {
    if (User.pepeCounter >= pepe._cost) {
      User.pepeCounter -= pepe._cost
      User.perSecond += pepe._ability
      refreshLabels()
      showMessage("Purchase Successful!")
    } else {
      showMessage("Insufficient Pepes!")
    }
  }
  //  buy collectibles
  def buyCol(pepe: Boolean, col: NewCollectibles): Boolean = {
    if (pepe.equals(false)) {
      if (User.pepeCounter >= col._cost) {
        User.pepeCounter -= col._cost
        refreshLabels()
        showMessage("Purchase Successful!")
        true // updates status to owned
      } else {
        showMessage("Insufficient Pepes!")
        false // keeps status as not owned
      }
    } else {
      showMessage(s"${col._name} already owned!")
      true // keeps status as owned
    }
  }
}
