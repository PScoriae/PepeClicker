package ch.makery.address.view
import ch.makery.address.MainApp
import ch.makery.address.model.{PowerUps, User}
import scalafx.event.ActionEvent
import javafx.scene.Node
import scalafx.scene.control.Label
import scalafxml.core.macros.sfxml

@sfxml
class GameController (private var pepePS: Label, _numberOfPepes: Label, message: Label)
  extends ActionController(message) {

  // initialises game labels
  refreshLabels()

  // increases count based on per click value
  def increment(): Unit = {
    User.pepeCounter += User.perClick
  }

  // handles pepe click event
  def handleBonkPress(): Unit = {
    increment()
    refreshLabels()
  }

  // switch to collectible screen
  def handleCollectibles(): Unit = {
    MainApp.showScene(MainApp.collectiblesPath)
  }

  // refreshes counter labels with new count value
  def refreshLabels(): Unit = {
    _numberOfPepes.setText(User.pepeCounter.toString + " Pepes")
    pepePS.setText(User.perSecond.toString + " Passive Pepes Per Second")
  }

  // runs relevant code block based on which buy button is pressed
  def buyItem(action: ActionEvent) {
    // get id of which button was pressed
    var source:Node = action.getSource.asInstanceOf[Node]
    val pu = source.getId()
    pu match { // buy relevant item based on button's fx:id
      case "pepeGun" => buyPU(PowerUps.pepeGun)
      case "fgm" => buyPU(PowerUps.feelsGoodMan)
      case "pepeStare" => buyPU(PowerUps.pepeStare)
    }
  }
}
