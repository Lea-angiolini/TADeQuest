package grupo8

case class Trabajo(descripcion: String, statBase: Stats, statPrincipal: String) extends ModificadorDeStat{
  
  type A = Trabajo
  override def copiar = copy(statBase = statBase.copiar)
  
  def getStat(heroe: Heroe): Set[StatsStandard] = Set(statBase)
  
  def getStatPrincipal: Option[String] = Some(statPrincipal)
  
  def getDescripcion: String = descripcion
  
  def getValorStatPrincipal: Int = statBase.get(statPrincipal)
}

class Guerrero extends Trabajo("Guerrero", new Stats(10,15,0,-10),"fuerza")

class Mago extends Trabajo("Mago", new Stats(0,-20,0,20),"inteligencia")

class Ladron extends Trabajo("Ladrón", new Stats(-5,0,10,0),"velocidad")

class SinTrabajo extends Trabajo("Sin trabajo", new Stats(0,0,0,0),""){
  override def getStatPrincipal: Option[String] = None
  override def getValorStatPrincipal: Int = 0
  }