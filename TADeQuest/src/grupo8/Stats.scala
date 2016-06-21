package grupo8

abstract class Stat

case object HP extends Stat
case object Fuerza extends Stat
case object Inteligencia extends Stat
case object Velocidad extends Stat

case class Stats(hp: Int = 0 ,fuerza: Int = 0 , velocidad: Int = 0, inteligencia: Int = 0 , restricciones: List[Stats => Stats] = List()) extends ModificadorDeStat{
  
  val stats: Map[Stat,Int] = Map(HP -> hp,Fuerza -> fuerza, Inteligencia -> inteligencia, Velocidad -> velocidad)
  
  type Restriccion = Stats => Stats
  
  def getStats = stats
  
  def getStatPara(heroe: Heroe) = Set(this)
  
  def get[T <: Stat](stat: T): Int = stats.get(stat).get
  
  def set(stat: Stat, valor: Int): Stats = stat match {
    case HP => copy(hp=valor)
    case Fuerza => copy(fuerza=valor)
    case Inteligencia => copy(inteligencia=valor)
    case Velocidad => copy(velocidad=valor)
  }
  
  def sumarStat(stat: Stat, valor: Int): Stats = set(stat, get(stat)+valor)
  
  def setRestriccion(res: Restriccion): Stats = copy(restricciones = restricciones.+:(res))
  
  def getRestricciones: List[Restriccion] = restricciones
  
  def getStatsFinales: Stats = {
    var statCopia = this
    for(r <- restricciones) statCopia = r(statCopia)
    statCopia
  }
  
  def +(stat: Stats): Stats = {
    var nuevoStat: Stats = this
    for( (k,v) <- stat.getStats) nuevoStat = nuevoStat.sumarStat(k,v)
    for(r <- stat.getRestricciones) nuevoStat = nuevoStat.setRestriccion(r)
    nuevoStat
  }
}

abstract class ModificadorDeStat{
  def getStatPara(heroe: Heroe): Set[Stats] 
}