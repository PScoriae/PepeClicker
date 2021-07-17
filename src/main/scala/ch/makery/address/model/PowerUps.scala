package ch.makery.address.model
import ch.makery.address.model.Collectibles.NewCollectibles

object PowerUps {
  // powerups that boost clicks
  class Type1PU(_name: String, _cost: Int, val _ability: Int) extends NewCollectibles(_name, _cost)
  // powerups that boost passive
  class Type2PU(_name: String, _cost: Int, val _ability: Int) extends NewCollectibles(_name, _cost)

  val pepeGun = new Type1PU("Pepe Gun", 50, 5)
  val feelsGoodMan = new Type2PU("FeelsGoodMan", 200, 10)
  val pepeStare = new Type2PU("Pepe Stare", 500, 20)
}
