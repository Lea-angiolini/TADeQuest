package grupo8

case class Stats(hp: Int = 0,fuerza: Int = 0, velocidad: Int = 0, inteligencia: Int = 0,  var restricciones: List[Stats => Unit] = List()) extends ModificadorDeStat{
  var mapStats: Map[String,Int] = Map("hp"-> hp, "fuerza"->fuerza, "velocidad"->velocidad, "inteligencia"->inteligencia)  
  
  type Restriccion = Stats => Unit
  type A = Stats
  
  override def copiar: Stats = copy(get("hp"),get("fuerza"),get("velocidad"),get("inteligencia"),restricciones)
  
  def getStats = {
    mapStats
  }
  
  def getStat(heroe: Heroe) = {
    Set(this)
  }
  
  def get(nombre:String) = {
    mapStats(nombre)
  }
  
  def set(nombre:String, value: Int) {
    if(mapStats.contains(nombre))
      mapStats += (nombre -> value)
  }
  
  def setRestriccion(res: Restriccion){
    restricciones = restricciones ::: (List(res))
  }
  
  def getRestricciones: List[Restriccion] = restricciones
  
  def getStatsFinales: Stats = {
    var statCopia = copiar
    for(r <- restricciones) r(statCopia)
    statCopia
  }
  
  def +(stat: Stats): Stats = {
    val nuevoStat: Stats = stat.copiar
    for( n <- stat.getStats;
         m <- mapStats;
         if (m._1 == n._1) 
        ) nuevoStat.set(n._1, n._2 + m._2) 
    for(r <- restricciones) nuevoStat.setRestriccion(r)
    nuevoStat
  }
}

abstract class ModificadorDeStat extends Copiable {
  def getStat(heroe: Heroe): Set[Stats] 
}