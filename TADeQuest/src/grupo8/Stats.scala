package grupo8

abstract class Stat(valor: Int){
  def get: Int = valor
  def +(inc: Int): Stat
  def set(setear: Int): Stat
}

case class HP(valor: Int = 0) extends Stat(valor){
  def +(inc: Int) = copy(valor + inc)
  def set(setear: Int) = copy(setear)
}

case class Fuerza(valor: Int) extends Stat(valor){
  def +(inc: Int) = copy(valor + inc)
  def set(setear: Int) = copy(setear)
}

case class Inteligencia(valor: Int) extends Stat(valor){
  def +(inc: Int) = copy(valor + inc)
  def set(setear: Int) = copy(setear)
}

case class Velocidad(valor: Int) extends Stat(valor){
  def +(inc: Int) = copy(valor + inc)
  def set(setear: Int) = copy(setear)
}

case class Stats(hp: Int = 0 ,fuerza: Int = 0 , velocidad: Int = 0, inteligencia: Int = 0 , restricciones: List[Stats => Stats] = List()) extends ModificadorDeStat{
  
  val stats: List[Stat] = List(HP(hp),Fuerza(fuerza),Inteligencia(inteligencia),Velocidad(velocidad))
  
  type Restriccion = Stats => Stats
  
  def getStats = stats
  
  def getStatPara(heroe: Heroe) = Set(this)
  
  def get(stat: Stat): Stat = stats.find { _ match {case stat => true; case _ => false} }.get
  
  def set(stat: Stat): Stats = stat match {
    case HP(v) => copy(hp=v)
    case Fuerza(v) => copy(fuerza=v)
    case Inteligencia(v) => copy(inteligencia=v)
    case Velocidad(v) => copy(velocidad=v)
  }
  
   def sumarStat(stat: Stat): Stats = set(get(stat)+stat.get)
    /* stat match {
    case HP(v) => copy(hp=v+hp)
    case Fuerza(v) => copy(fuerza=v+fuerza)
    case Inteligencia(v) => copy(inteligencia=v+inteligencia)
    case Velocidad(v) => copy(velocidad=v+velocidad)
  }*/
  
  def setRestriccion(res: Restriccion): Stats = copy(restricciones = restricciones.+:(res))
  
  def getRestricciones: List[Restriccion] = restricciones
  
  def getStatsFinales: Stats = {
    var statCopia = this
    for(r <- restricciones) statCopia = r(statCopia)
    statCopia
  }
  
  def +(stat: Stats): Stats = {
    var nuevoStat: Stats = this
    for( n <- stat.getStats) nuevoStat = nuevoStat.sumarStat(n)
    for(r <- restricciones) nuevoStat = nuevoStat.setRestriccion(r)
    nuevoStat
  }
}

abstract class ModificadorDeStat{
  def getStatPara(heroe: Heroe): Set[Stats] 
}