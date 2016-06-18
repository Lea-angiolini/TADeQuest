package grupo8

case class Heroe(statBase: Stats, inventario: Inventario = new Inventario, var trabajo: Trabajo = new SinTrabajo) extends Copiable {
  
  statBase.setRestriccion(x => for(stat <- x.getStats
                                   if (stat._2 < 1)
                                ) x.set(stat._1, 1))

    var elementosConStat:Map[String,ModificadorDeStat]  = 
    Map("inventario" -> inventario, "trabajo" -> trabajo) //TODO Buscar un nombre mejor
  
  var statActual: Stats = statBase.getStatsFinales
  
  private def updateElementos(elemento: String, valor:ModificadorDeStat) {
    elementosConStat = elementosConStat + (elemento -> valor)
    getInventario.calcularElementospermitidos(this)
    calcularStat
  }
  
  def setTrabajo(trab: Trabajo) {
    updateElementos("trabajo",trab)
  }
  
  def descartarTrabajo {
    updateElementos("trabajo",new SinTrabajo)
  }
  
  private def calcularStat = {
    this.statActual = elementosConStat.flatMap( _._2.getStat(this)).fold(statBase)(_ + _)
    this.statActual = this.statActual.getStatsFinales
  }
  
  def equipar[U <: Item](item: U): Heroe = {
    updateElementos("inventario",this.getInventario.equipar(item))
    this
  }
  
  override type A = Heroe
  override def copiar = copy(statBase = getStatBase.copiar, inventario = getInventario.copiar, trabajo = getTrabajo.copiar)
  
  def getStatBase: Stats = statBase
  
  def getStats: Stats = {calcularStat; this.statActual}
  
  def getTrabajo: Trabajo = this.elementosConStat("trabajo").asInstanceOf[Trabajo]
  
  def getInventario: Inventario = this.elementosConStat("inventario").asInstanceOf[Inventario]
  
  def getStatPrincipal: Int = getTrabajo.getStatPrincipal match {
    case Some(x) if x != "" => this.statActual.getStats(x)
    case _ => 0
  }
}