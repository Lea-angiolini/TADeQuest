package grupo8

import scala.util._

case class Equipo(nombre: String, heroes: List[Heroe] = List(), pozoComun: Int = 0){
  
  def adherirAlPozoComun(valor: Int): Equipo = copy(pozoComun = pozoComun+valor)
 
  def getNombre: String = nombre
  
  def getHeroes: List[Heroe] = heroes
  
  def vender(item: Item): Equipo = {
    adherirAlPozoComun(item.getValor)
  }
    
  def mejoresHeroesSegun(cuantificador: (Heroe => Int)): List[Heroe] = {  
    heroes.filter { cuantificador(_) == heroes.map { cuantificador(_) }.max }    
  }
  
  def mejorHeroeSegun(cuantificador: (Heroe => Int)): Option[Heroe] = { 
    mejoresHeroesSegun(cuantificador) match {
      case h :: Nil => Some(h)
      case _ => None
    }
  }
  
  def obtenerItem(item: Item): Equipo = {
    val funcion = { heroe: Heroe => heroe.equipar(item).getStatPrincipal.getOrElse(0) - heroe.getStatPrincipal.getOrElse(0) }
    
    mejorHeroeSegun(funcion) match {
      case Some(h)  => if (funcion(h) > 0 ) {copy(heroes = heroes.filterNot(x => x.id == h.id).+:(h.equipar(item)))} else {vender(item)}
      case None => {vender(item)}
    }
  }
 
  def obtenerMiembro(heroe: Heroe): Equipo = {
    if(!heroes.exists {_.id == heroe.id})
      copy(heroes = heroes.+:(heroe))
    else
      this
  }
  
  def reemplazarMiembro(miembroNuevo: Heroe, miembroViejo: Heroe): Equipo = {
    var listaNueva = heroes.filter { _.id != miembroViejo.id }
    copy(heroes = listaNueva.+:(miembroNuevo)) 
  } 
  
  def lider():Option[Heroe] = {
    mejorHeroeSegun(heroe => heroe.getStatPrincipal.getOrElse(0))
  }
  
 /* def realizarMision(mision: Mision) {
     //var nuevosHeroes = heroes.map { _.copiar }  
     //var a = for(t <- mision.getTareas.toList) yield {  for(h <- heroes) yield (t.puedeRealizarla(this, h))}
     
    intentarMision(mision, heroes.map { _.copiar }) match 
    {
      case Success(e) => {intentarMision(mision, heroes)
                          mision.darGanancias(this)}
      case Failure(e) => println(e)
    }
      
  }
  
  def intentarMision(mision: Mision, heroesAProbar: List[Heroe]): Try[Equipo] = {
    
     for(t <- mision.getTareas)  {
     var heroesCapaces = for(h <- heroesAProbar;
                             if t.puedeRealizarla(this,h))
                         yield(h)
       
     var mejorHeroe = Try(mejoresHeroesSegunDe(h => t.getFacilidad(this,h).get,heroesCapaces).head)
     if(mejorHeroe.isSuccess)
       t.realizarla(mejorHeroe.get)
     else 
       return Failure(new MisionFallidaException(t))
     }
     return Success(this)
  }*/

}