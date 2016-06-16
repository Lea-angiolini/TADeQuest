package grupo8

abstract class Item(descripcion: String, calculoStat: Heroe => StatsStandard, acepta: Heroe => Boolean) {//TODO ver si la funcion tiene que retornar algo
  
  def calcularStatpara(heroe: Heroe): StatsStandard = this.calculoStat(heroe)
  
  def puedeUsar(heroe: Heroe): Boolean = this.acepta(heroe)

}

case class Talisman(descripcion: String, calculoStat: (Heroe) => StatsStandard, acepta: Heroe => Boolean) extends Item(descripcion, calculoStat, acepta)
case class Sombrero(descripcion: String, calculoStat: (Heroe) => StatsStandard, acepta: Heroe => Boolean) extends Item(descripcion, calculoStat, acepta)
case class Armadura(descripcion: String, calculoStat: (Heroe) => StatsStandard, acepta: Heroe => Boolean) extends Item(descripcion, calculoStat, acepta)

abstract class ItemDeUnaMano(descripcion: String, calculoStat: (Heroe) => StatsStandard, acepta: Heroe => Boolean) extends Item(descripcion, calculoStat, acepta)
abstract class ItemDeDosManos(descripcion: String, calculoStat: (Heroe) => StatsStandard, acepta: Heroe => Boolean) extends ItemDeUnaMano(descripcion, calculoStat, acepta)


case class ArmaDeUnaMano(descripcion: String, calculoStat: (Heroe) => StatsStandard, acepta: Heroe => Boolean) extends ItemDeUnaMano(descripcion, calculoStat, acepta)//Arma de una mano
case class Escudo(descripcion: String, calculoStat: (Heroe) => StatsStandard, acepta: Heroe => Boolean) extends ItemDeUnaMano(descripcion, calculoStat, acepta)//Escudo de una mano
case class ArmaDeDosManos(descripcion: String, calculoStat: (Heroe) => StatsStandard, acepta: Heroe => Boolean) extends ItemDeDosManos(descripcion, calculoStat, acepta)//Arma de dos manos