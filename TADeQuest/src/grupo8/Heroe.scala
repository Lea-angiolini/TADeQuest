package grupo8

class Heroe(statBase: Stats) {
  
  var statActual: Stats = statBase
  var inventario: Inventario = new Inventario
  var trabajo: Trabajo = new SinTrabajo
  
  def setTrabajo(trab: Trabajo) {
    this.trabajo = trab 
    calcularStat
    inventario.calcularElementospermitidos(this)
  }
  
  def descartarTrabajo {
    this.trabajo = new SinTrabajo
    calcularStat
    inventario.calcularElementospermitidos(this)
  }
  
  def calcularStat {
    this.statActual = new Stats(0,0,0,0)
    this.statActual = this.statActual + this.statBase  
    this.statActual = this.statActual + this.inventario.calcularStat(this)
    this.statActual = this.statActual + this.trabajo.getStats
  }
  
  def equipar[U <: Item](item: U){
    if(item.puedeUsar(this)){
      this.inventario.equipar(item)
      calcularStat
    }
  }
  
  def getStatBase: Stats = this.statBase
  
  def getStats: Stats = this.statActual
  
  def getTrabajo: Trabajo = this.trabajo
  
  def getInventario: Inventario = this.inventario
}