package grupo8

class Trabajo(descripcion: String, statBase: Stats, statPrincipal: Stat) extends ModificadorDeStat{
   
  def getStatPara(heroe: Heroe): Set[Stats] = Set(statBase)
  
  def getStatPrincipal: Option[Stat] = Some(statPrincipal)
  
  def getDescripcion: String = descripcion
  
  def getValorStatPrincipal: Int = statBase.get(statPrincipal)
}

object Guerrero extends Trabajo("Guerrero", new Stats(10,15,0,-10), Fuerza)
object Mago extends Trabajo("Mago", new Stats(0,-20,0,20), Inteligencia)
object Ladron extends Trabajo("Ladrón", new Stats(-5,0,10,0), Velocidad)