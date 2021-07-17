package ch.makery.address.view
import ch.makery.address.MainApp
import scalafx.scene.control.{Label, PasswordField, TextField}
import ch.makery.address.model.{Collectibles, User}
import scalafxml.core.macros.sfxml

import java.io.IOException

@sfxml
class RegisterController(private var unameInput: TextField,
                         private var pwInput: PasswordField,
                         private var repPwInput: PasswordField,
                         regMesg: Label) extends GeneralController(regMesg) {

  // attempts to register user after clicking register button
  def attemptRegister(): Unit = {
    // ensures all fields are filled
    if (pwInput.getText.nonEmpty && repPwInput.getText.nonEmpty && unameInput.getText.nonEmpty) {
      // checks if password == repeat password
      if (pwInput.getText == repPwInput.getText) {
        // creates json object with user info
        val toSave = ujson.Obj(
          "username" -> unameInput.getText(),
          "password" -> pwInput.getText(),
          "numberOfPepes" -> User.pepeCounter,
          "pepePS" -> User.perSecond,
          "perClick" -> User.perClick,
          Collectibles.pepega._name -> User.pepega,
          Collectibles.pepeLaugh._name -> User.pepeLaugh,
          Collectibles.peepoPooPoo._name -> User.peepoPooPoo,
        )
        // json filename
        val filename: String = unameInput.getText() + "_save.json"
        try { // tries to write to directory
          os.write(os.pwd/filename, toSave)
        } catch { // prints message if there are any IO exceptions
          case e: IOException => showMessage("An error occurred.")
        }
        showMessage("Successfully created account!")
      } else { // if password != repeat pw
        showMessage("Passwords do not match")
      }
    } else { // if not all the fields are filled
      showMessage("Please fill in all fields.")
    }
  }

  // shows login screen
  def showLogin(): Unit = {
    MainApp.showScene(MainApp.loginPath)
  }
}
