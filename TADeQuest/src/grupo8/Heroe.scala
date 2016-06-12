package grupo8

class Heroe(statBase: Stats) {
  
  var statActual: Stats = null
  var inventario: Inventario = new Inventario
  var trabajo: Trabajo = null
  
  def setTrabajo(trab: Trabajo) {
    this.trabajo = trab  
  }
  
  def descartarTrabajo {
    this.trabajo = null
  }
  
  def calcularStat {
    var statActual = new Stats(0,0,0,0)
    this.statActual += statBase     
    this.statActual += this.inventario.calcularStat(this)
    if(this.trabajo != null)
      this.statActual += this.trabajo.getStats
  }
  
  def equipar[U <: Item](item: U){
    if(item.puedeUsar(this))
      this.inventario.equipar(item)
  }
  
  def getStatBase: Stats = {
    this.statBase
  }
}