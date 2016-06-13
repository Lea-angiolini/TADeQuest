package grupo8

import scala.collection.mutable

class Inventario {
  
  val items:mutable.Map[String,Item] = mutable.Map()
  val talismanes: collection.mutable.Set[Talisman] = collection.mutable.Set[Talisman]()
  
  private def itemAMano[T <: ItemDeUnaMano](item: T){
    this.items.remove("mano")
    if (this.items.contains("manoDer")) {
        this.items("manoIzq") = item.asInstanceOf[ItemDeUnaMano]
      } else {
        
        this.items("manoDer") = item.asInstanceOf[ItemDeUnaMano]
      }
  }
  
  def equipar[U <: Item](item: U) {
    
    item match {
      case s @ Sombrero(des,cal,acep) => this.items("sombrero") = s
      case a @ Armadura(des,cal,acep) => this.items("armadura") = a 
      case a @ ArmaDeUnaMano(des,cal,acep) => this.itemAMano(a)

      case e @ Escudo(des,cal,acep) => this.itemAMano(e)

      case a @ ArmaDeDosManos(des,cal,acep) =>
        this.items.remove("manoDer"); this.items.remove("manoIzq")
        this.items("mano") = a
      
      case t @ Talisman(des,cal,acep) => this.talismanes.add(t)
    }  
  }
  
  def calcularStat(heroe: Heroe):Stats = {
    var stat = new Stats(0,0,0,0)
    
    items.foreach(f => (stat += f._2.calcularStatpara(heroe)))
    
    stat
  }
  
  def calcularElementospermitidos(heroe: Heroe) {
    for (i <- items; if (!i._2.puedeUsar(heroe))) {items.remove(i._1)}
  }
}