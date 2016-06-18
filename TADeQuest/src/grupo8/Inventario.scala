package grupo8

import scala.collection.mutable

case class Inventario(items:mutable.Map[String,Item] = mutable.Map(), talismanes: collection.mutable.Set[Talisman] = collection.mutable.Set[Talisman]()) extends ModificadorDeStat {
  
  type A = Inventario
  
  override def copiar = copy(mutable.Map() ++ items , mutable.Set() ++ talismanes)
  
  /*val items:mutable.Map[String,Item] = mutable.Map()
  val talismanes: collection.mutable.Set[Talisman] = collection.mutable.Set[Talisman]()*/
  
  private def itemAMano[T <: ItemDeUnaMano](item: T){
    this.items.remove("mano")
    if (this.items.contains("manoDer")) {
        this.items("manoIzq") = item.asInstanceOf[ItemDeUnaMano]
      } else {
        
        this.items("manoDer") = item.asInstanceOf[ItemDeUnaMano]
      }
  }
  
  def equipar[U <: Item](item: U): Inventario = {
    
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
    this
  }
  
  def getStat(heroe: Heroe):Set[Stats] = {   
    val i = (for(i <- items) yield (i._2.calcularStatpara(heroe))).toSet 
    val t = (for(t <- talismanes) yield (t.calcularStatpara(heroe)))
    i.++(t)
  }
  
  def calcularElementospermitidos(heroe: Heroe) {
    for (i <- items; if (!i._2.puedeUsar(heroe))) {items.remove(i._1)}
  }
  
  def getTalismanes = talismanes
}