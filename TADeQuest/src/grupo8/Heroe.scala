package grupo8

class Heroe(statBase: Stats) {
  
  var statActual: Stats = statBase
  var inventario: Inventario = new Inventario
  var trabajo: Trabajo = null
  
  def setTrabajo(trab: Trabajo) {
    this.trabajo = trab 
    calcularStat
  }
  
  def descartarTrabajo {
    this.trabajo = null
    calcularStat
  }
  
  def calcularStat {
    this.statActual = new Stats(0,0,0,0)
    this.statActual = this.statActual + this.statBase  
    this.statActual = this.statActual + this.inventario.calcularStat(this)
    if(this.trabajo != null)
      this.statActual = this.statActual + this.trabajo.getStats
  }
  
  def equipar[U <: Item](item: U){
    if(item.puedeUsar(this)){
      this.inventario.equipar(item)
      calcularStat
    }
  }
  
  def getStatBase: Stats = {
    this.statBase
  }
  
  def getStats: Stats = {
    this.statActual
  }
}