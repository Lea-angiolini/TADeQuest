package grupo8

case class Heroe(id: String, statBase: Stats, inventario: Inventario = new Inventario, trabajo: Option[Trabajo] = None) {
  
  statBase.setRestriccion(x => {var nuevoStats = x
                            for((k,v) <- x.getStats;
                                   if (v < 1)
                                ) nuevoStats = x.set(k,1);
                               nuevoStats})
  
  lazy val statActual: Stats = calcularStat
  
  def setTrabajo(trab: Trabajo): Heroe = copy(trabajo = Some(trab))
  
  def descartarTrabajo: Heroe = copy(trabajo = None)
  
  private def calcularStat: Stats = {
    val elementosConStat: List[ModificadorDeStat] = List(statBase, inventario, trabajo.getOrElse(new Stats))
    elementosConStat.flatMap { _.getStatPara(this) }.reduce(_ + _).getStatsFinales
  }
  
  def equipar[U <: Item](item: U): Heroe = {if (item.puedeUsar(this)) 
                                              copy(inventario = inventario.equipar(item))
                                            else
                                              this}
  
  def getStatBase: Stats = statBase
  
  def getStats: Stats = this.statActual
  
  def getTrabajo: Option[Trabajo] = this.trabajo
  
  def getInventario: Inventario = this.inventario
  
  def getStatPrincipal: Option[Int] =  getTrabajo match {
    case Some(t) => Some(t.getValorStatPrincipal)
    case None => None
  }
}