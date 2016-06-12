package grupo8

import scala.collection.mutable

class Inventario {
  
  val items:mutable.Map[String,Item] = mutable.Map()
  val talismanes: collection.mutable.Set[Talisman] = collection.mutable.Set[Talisman]()
  
  private def itemAMano[T <: ItemDeUnaMano](item: T){
    if (!this.items.contains("manoIzq")) {
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
        this.items("manoDer") = a
        this.items("manoIzq") = this.items("manoDer")
      
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