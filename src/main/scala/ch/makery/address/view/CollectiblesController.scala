package ch.makery.address.view
import ch.makery.address.MainApp
import ch.makery.address.model.Collectibles._
import ch.makery.address.model.{Collectibles, User}
import javafx.scene.Node
import scalafx.event.ActionEvent
import scalafx.scene.control.Label
import scalafxml.core.macros.sfxml

@sfxml
class CollectiblesController(private var numberOfPepes: Label, usrMsg: Label,
                             private var pepegaName: Label, private var pepegaCost: Label,
                             private var pepegaOwned: Label, private var peepoPooPooName: Label,
                             private var peepoPooPooCost: Label, private var peepoPooPooOwned: Label,
                             private var pepeLaughName: Label, private var pepeLaughCost: Label,
                             private var pepeLaughOwned: Label)
  extends ActionController(usrMsg) {

  // updates labels with item names
  refreshLabels()

  updateColLabels(pepegaName, pepegaCost, pepega)
  updateColLabels(peepoPooPooName, peepoPooPooCost, peepoPooPoo)
  updateColLabels(pepeLaughName, pepeLaughCost, pepeLaugh)

  // updates collectible labels with respective properties
  def updateColLabels(name: Label, cost: Label, pepe: NewCollectibles): Unit = {
    name.setText(pepe._name)
    cost.setText(pepe._cost.toString)
  }

  // switch to game screen
  def handleGame(): Unit = {
    MainApp.showScene(MainApp.gamePath)
  }

  // refresh current pepe count
  def refreshLabels(): Unit = {
    numberOfPepes.setText(User.pepeCounter.toString + " Pepes")
    if (User.pepega) {pepegaOwned.setText("Owned")}
    if (User.peepoPooPoo) {peepoPooPooOwned.setText("Owned")}
    if (User.pepeLaugh) {pepeLaughOwned.setText("Owned")}
  }

  // buy appropriate item depending on which button was pressed
  def buyItem(action: ActionEvent): Unit = {
    // get id of which button was pressed
    var source:Node = action.getSource.asInstanceOf[Node]
    val pu = source.getId()
    pu match {
      case "pepega" => User.pepega = buyCol(User.pepega, Collectibles.pepega)
      case "peepoPooPoo" => User.peepoPooPoo = buyCol(User.peepoPooPoo, Collectibles.peepoPooPoo)
      case "pepeLaugh" => User.pepeLaugh = buyCol(User.pepeLaugh, Collectibles.pepeLaugh)
     }
  }
}
