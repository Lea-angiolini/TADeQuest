package grupo8

class Trabajo(descripcion: String, statBase: Stats, statPrincipal: String) {
  
  def getStats: Stats = {
    statBase
  }
  
  def getStatPrincipal: String = {
    statPrincipal
  }
}