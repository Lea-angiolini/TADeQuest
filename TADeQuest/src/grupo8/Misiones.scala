package grupo8

import scala.util._

case class Mision(tareas: Set[Tarea], ganancias: Equipo => Equipo){
  
  def getTareas = tareas
  
  def darGanancias(equipo: Equipo): Equipo = ganancias(equipo)
  
  def realizarTareas(equipo: Equipo): Try[Equipo] = {
    
    val estadoFinal = tareas.foldLeft(Try(equipo))((estadoAnt,tareaActual) => {
      estadoAnt match {
        case Failure(e) => Failure(e)
        case Success(e) => tareaActual.realizarla(e)
      }})
 
    estadoFinal match{
      case Failure(excep) => Failure(excep)
      case Success(equipo) => Success(darGanancias(equipo))
    }
  }
}


abstract class Tarea(descripcion: String, facilidad: (Equipo,Heroe) => Option[Int], cambios: (Equipo,Heroe) => Equipo){

  def getDescripcion = descripcion
  
  def puedeRealizarla(equipo: Equipo, heroe: Heroe): Boolean = {
    facilidad(equipo, heroe) match {
      case Some(int) => true
      case None => false
    }
  }
  
  def facilidadPara(equipo:Equipo, heroe: Heroe): Option[Int] = {
    facilidad(equipo, heroe)
  }
  
  def realizarla(equipo: Equipo): Try[Equipo] = {
    val cuantificador = facilidad(equipo,_:Heroe)
    val heroe = Try(equipo.mejoresHeroesSegun(cuantificador(_).get).head)
    
    heroe match {
      case Success(h) => Success(cambios(equipo,h))
      case Failure(f) => Failure(new TareaFallidaException(this,equipo)) 
    }
  }
}

object pelearContraMonstruo extends Tarea("Pelear contra Monstruo", 
                                          {(e,h) => e.lider match {
                                                case Some(h) if(h.getTrabajo.getOrElse(null) == Guerrero) => Some(20)
                                                case _ => Some(10)}}, 
                                           {(e,h) => if(h.getStats.get(Fuerza) < 20) 
                                                         e.reemplazarMiembro(h.sumarStatBase(HP, -10),h) 
                                                     else
                                                       e
                                           })

object forzarPuerta extends Tarea("Forzar Puerta",
                                  {(e,h) => Some(h.getStats.get(Inteligencia) + 10 * e.getHeroes.count(_.getTrabajo.getOrElse(null) == Ladron))},                
                                  {(e,h) => h.getTrabajo match {
                                    case Some(t) if t == Ladron || t == Mago => e
                                    case _ => e.reemplazarMiembro(h.sumarStatBase(Fuerza, 1).sumarStatBase(HP, -5),h)}})

class robarTalisman(talisman: Talisman) extends Tarea("Robar Talismán",
                                  {(e,h) => {e.lider match {
                                    case Some(lider) if lider.getTrabajo.getOrElse(null) == Ladron => Some(h.getStats.get(Velocidad))
                                    case _ => None}}},
                                  {(e,h) => e.obtenerItem(talisman) })


object nadiePuedeHacerla extends Tarea("Nadie puede hacerla",
                                      {(e,h) => None},
                                      {(e,h) => e})
