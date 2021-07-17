package ch.makery.address.view
import ch.makery.address.MainApp
import ch.makery.address.model.{Collectibles, User}
import scalafx.scene.control.{Label, PasswordField, TextField}
import scalafxml.core.macros.sfxml

import java.io.FileNotFoundException
import java.nio.file.NoSuchFileException

@sfxml
class LoginScreenController(private var unameInput: TextField,
                            private var pwInput: PasswordField,
                            loginMesg: Label) extends GeneralController(loginMesg) {

  // attempts to login user
  def attemptLogin(): Unit = {
    // checks for save file based on username
    val filename: String = unameInput.getText() + "_save.json"
    try { // try to access file
      val temp = os.read(os.pwd/filename)
      val parsed = ujson.read(temp)
      val uname: String = parsed("username").str
      val pw: String = parsed("password").str

      // loads values if username and password patches
      if (uname == unameInput.getText() && pw == pwInput.getText()) {
        User.username = uname
        User.pepeCounter = parsed("numberOfPepes").num.toInt
        User.perSecond = parsed("pepePS").num.toInt
        User.perClick = parsed("perClick").num.toInt
        User.pepega = parsed(Collectibles.pepega._name).bool
        User.pepeLaugh = parsed(Collectibles.pepeLaugh._name).bool
        User.peepoPooPoo = parsed(Collectibles.peepoPooPoo._name).bool
        MainApp.showScene(MainApp.gamePath)
      } else {
        showMessage("Wrong password")
      }
    } catch { // catches exceptions
      case e: NoSuchFileException => showMessage("User does not exist.")
      case e: FileNotFoundException => showMessage("Error accessing file.")
    }
  }

  def handleRegister(): Unit = {
    MainApp.showScene(MainApp.registerPath)
  }
}
