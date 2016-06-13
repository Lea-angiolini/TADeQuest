package grupo8

class Heroe(statBase: Stats) {
  
  var elementosConStat:Map[String,ModificadorDeStat]  = 
    Map("statBase" -> statBase, "inventario" -> new Inventario, "trabajo" -> new SinTrabajo) //TODO Buscar un nombre mejor
  
  var statActual: Stats = statBase
  
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
  
  private def calcularStat {
    this.statActual = elementosConStat.flatMap( _._2.getStat(this)).fold(new Stats)(_ + _)
    this.statActual.limite()
  }
  
  def equipar[U <: Item](item: U){
    updateElementos("inventario",this.getInventario.equipar(item))
  }
  
  def getStatBase: Stats = this.elementosConStat("statBase").asInstanceOf[Stats]
  
  def getStats: Stats = this.statActual
  
  def getTrabajo: Trabajo = this.elementosConStat("trabajo").asInstanceOf[Trabajo]
  
  def getInventario: Inventario = this.elementosConStat("inventario").asInstanceOf[Inventario]
}