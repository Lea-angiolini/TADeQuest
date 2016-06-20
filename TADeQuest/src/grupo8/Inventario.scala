package grupo8

import scala.collection.mutable

case class Inventario(items: Map[String,Item] = Map(), talismanes: Set[Talisman] = Set[Talisman]()) extends ModificadorDeStat {
    
  private def itemAMano[T <: ItemDeUnaMano](item: T): Map[String, Item] = {  
    var nuevosItems = items - "mano"
    if (this.items.contains("manoDer")) 
        nuevosItems = this.items + ("manoIzq" -> item)
    else
        nuevosItems = this.items + ("manoDer" -> item)
    nuevosItems
  }
  
  def equipar[U <: Item](item: U): Inventario = {
    
    item match {
      case s @ Sombrero(des,cal,acep) => copy(items+("sombrero" -> s))
      case a @ Armadura(des,cal,acep) => copy(items+("armadura" -> a))
      case a @ ArmaDeUnaMano(des,cal,acep) => copy(items = itemAMano(a))

      case e @ Escudo(des,cal,acep) => copy(items = itemAMano(e))

      case a @ ArmaDeDosManos(des,cal,acep) => copy(items - "manoDer" - "manoIzq" + ("mano" -> a))
      
      case t @ Talisman(des,cal,acep) => copy(talismanes = talismanes + t)
    }  
  }
  
  def getStatPara(heroe: Heroe):Set[Stats] = {   
    val i = (for(i <- items) yield (i._2.calcularStatpara(heroe))).toSet 
    val t = (for(t <- talismanes) yield (t.calcularStatpara(heroe)))
    i.++(t)
  }
  
  def getTalismanes = talismanes
  
  def getItems = items
}