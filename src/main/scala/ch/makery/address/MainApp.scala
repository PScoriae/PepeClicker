package ch.makery.address
import ch.makery.address.model.{Collectibles, User}
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, NoDependencyResolver}
import scalafx.scene.image.Image
import javafx.{scene => jfxs}

import java.io.IOException
import java.util.{Timer, TimerTask}

object MainApp extends JFXApp {
  // transform path of RootLayout.fxml to URI for resource location.
  val rootResource = getClass.getResourceAsStream("view/RootLayout.fxml")
  // initialize the loader object.
  val loader = new FXMLLoader(null, NoDependencyResolver)
  // Load root layout from fxml file.
  loader.load(rootResource)
  // retrieve the root component BorderPane from the FXML
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  // initialize stage
  stage = new JFXApp.PrimaryStage {
    title = "Pepe The Frog"
    scene = new Scene {
      root = roots
    }
    icons += new Image(getClass.getResourceAsStream("/img/peepoPooPoo.png"))
  }
  // safely ends passive pepe counter
  stage.setOnCloseRequest(event => endSession())

  // passive pepes
  val timer = new Timer()
  val passivePepe = new TimerTask {
    override def run(): Unit = {
      User.pepeCounter += User.perSecond

    }
  }
  timer.schedule(passivePepe, 1000L, 1000L)

  // safely ends passive pepes
  def endPassivePepe(): Unit = {
    timer.cancel()
    passivePepe.cancel()
  }

  // actions for save function
  def handleSave(): Unit = {
    // only saves if user is logged in
    if (!User.username.equals("")) {
      // determine save filename based on username
      val filename: String = User.username + "_save.json"
      try { // try to access save file and load into memory
        val saveFile = os.read(os.pwd/filename)
        val data = ujson.read(saveFile)
        // update with current user values
        data("numberOfPepes") = User.pepeCounter
        data("pepePS") = User.perSecond
        data("perClick") = User.perClick
        data(Collectibles.pepega._name) = User.pepega
        data(Collectibles.pepeLaugh._name) = User.pepeLaugh
        data(Collectibles.peepoPooPoo._name) = User.peepoPooPoo
        os.remove(os.pwd/filename) // delete save file
        os.write(os.pwd/filename, data) // write new save file
      } catch { // catch any IO exceptions
        case e: IOException => println("Error trying to delete or save the file.")
      }
    }
  }

  // function to handle ending game session
  def endSession(): Unit = {
    endPassivePepe()
    handleSave()
  }

  // redundant code for scene switching
  def showScene(location: String) = {
    val resource = getClass.getResourceAsStream(location)
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource)
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    MainApp.roots.setCenter(roots)
  }

  // various scenes go here
  // actions for display main game screen window
  val path = "view/"
  val gamePath: String = path + "Game.fxml"
  val collectiblesPath: String = path + "Collectibles.fxml"
  val loginPath: String = path + "LoginScreen.fxml"
  val registerPath: String = path + "Register.fxml"

  // displays login screen on startup
  showScene(loginPath)
}
