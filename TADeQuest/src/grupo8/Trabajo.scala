package grupo8

case class Trabajo(descripcion: String, statBase: Stats, statPrincipal: Stat) extends ModificadorDeStat{
   
  def getStatPara(heroe: Heroe): Set[Stats] = Set(statBase)
  
  def getStatPrincipal: Option[Stat] = Some(statPrincipal)
  
  def getDescripcion: String = descripcion
  
  def getValorStatPrincipal: Int = statBase.get(statPrincipal).get
}

class Guerrero extends Trabajo("Guerrero", new Stats(10,15,0,-10),new Fuerza(15))

class Mago extends Trabajo("Mago", new Stats(0,-20,0,20),new Inteligencia(20))

class Ladron extends Trabajo("Ladrón", new Stats(-5,0,10,0),new Velocidad(10))