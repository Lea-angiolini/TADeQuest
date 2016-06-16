package grupo8

abstract class StatsStandard(hp: Int ,fuerza: Int, velocidad: Int, inteligencia: Int) extends ModificadorDeStat{
  var mapStats: Map[String,Int] = Map("hp"-> hp, "fuerza"->fuerza, "velocidad"->velocidad, "inteligencia"->inteligencia)  

  type A = StatsStandard
  
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
  
  def +(stat: StatsStandard): StatsStandard = {
    val nuevoStat: StatsStandard = stat.copiar
    for( n <- stat.getStats;
         m <- mapStats;
         if (m._1 == n._1) 
        )  
    {
      nuevoStat.set(n._1, n._2 + m._2) 
    }
    nuevoStat
  }
  
  
  def limite(min: Int = 1) = {
    for(m <- mapStats){
      if(m._2 < min)
        set(m._1, min)
    }
    this
  }
}

case class Stats(hp: Int = 0,fuerza: Int = 0, velocidad: Int = 0, inteligencia: Int = 0) extends StatsStandard(hp,fuerza,velocidad,inteligencia){
  override def copiar = copy()
}

case class StatsConAdhesion(hp: Int = 0,fuerza: Int = 0, velocidad: Int = 0, inteligencia: Int = 0, adhesion: (StatsStandard, StatsStandard) => StatsStandard) extends StatsStandard(hp,fuerza,velocidad,inteligencia){
  override def copiar = copy()
  
  override def +(stat: StatsStandard): StatsStandard = {
    println("f")
    adhesion(this,stat)
  }
}

abstract class ModificadorDeStat extends Copiable {
  def getStat(heroe: Heroe): Set[StatsStandard] 
}