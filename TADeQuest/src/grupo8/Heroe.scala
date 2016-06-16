package grupo8

case class Heroe(statBase: Stats, inventario: Inventario = new Inventario, var trabajo: Trabajo = new SinTrabajo) extends Copiable {
  
  var elementosConStat:Map[String,ModificadorDeStat]  = 
    Map("inventario" -> new Inventario, "trabajo" -> new SinTrabajo) //TODO Buscar un nombre mejor
  
  var statActual: Stats = statBase.limite()
  
  private def updateElementos(elemento: String, valor:ModificadorDeStat) {
    elementosConStat += (elemento -> valor)
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
    this.statActual.limite()
  }
  
  def equipar[U <: Item](item: U){
    updateElementos("inventario",this.getInventario.equipar(item))
  }
  override type A = Heroe
  override def copiar = copy(statBase = getStatBase.copiar, inventario = inventario.copiar, trabajo = trabajo.copiar)
  
  def getStatBase: Stats = statBase
  
  def getStats: Stats = this.statActual
  
  def getTrabajo: Trabajo = this.elementosConStat("trabajo").asInstanceOf[Trabajo]
  
  def getInventario: Inventario = this.elementosConStat("inventario").asInstanceOf[Inventario]
}