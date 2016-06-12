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
      case Sombrero => this.items("sombrero") = item.asInstanceOf[Sombrero] //se castea porque segun scala tipo Sombrero no es igual a un tipo Sombrero
      case Armadura => this.items("armadura") = item.asInstanceOf[Armadura] //tampoco le gusta poner i @ Armadura
      case ArmaDeUnaMano => this.itemAMano(item.asInstanceOf[ItemDeUnaMano])

      case Escudo => this.itemAMano(item.asInstanceOf[Escudo])  //porque no le gusta el || arriba con el ArmaDeUnaMano

      case ArmaDeDosManos =>
        this.items("manoDer") = item.asInstanceOf[ItemDeDosManos]
        this.items("manoIzq") = this.items("manoDer")

    }
  }
  
  def calcularStat(heroe: Heroe):Stats = {
    var stat = new Stats(0,0,0,0)
    
    items.foreach(f => (stat += f._2.calcularStatpara(heroe)))
    
    stat
  }
}