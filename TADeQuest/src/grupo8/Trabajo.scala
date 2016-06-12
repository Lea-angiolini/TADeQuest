package grupo8

class Trabajo(descripcion: String, statBase: Stats, statPrincipal: String) {
  
  def getStats: Stats = statBase
  
  def getStatPrincipal: String = statPrincipal
  
  def getDescripcion: String = descripcion
  
  def getValorStatPrincipal: Int = statBase.get(statPrincipal)
}

class Guerrero extends Trabajo("Guerrero", new Stats(10,15,0,-10),"fuerza")

class Mago extends Trabajo("Mago", new Stats(0,-20,0,20),"inteligencia")

class Ladron extends Trabajo("Ladrón", new Stats(-5,0,10,0),"velocidad")