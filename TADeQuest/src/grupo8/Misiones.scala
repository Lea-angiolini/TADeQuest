package grupo8

import scala.util._

case class Mision(tareas: Set[/*Tarea*/Trabajo], ganancias: Equipo => Unit) extends Copiable{
  
  type A = Mision
  def getTareas = tareas
  
  override def copiar = copy(tareas,ganancias)
  
  def darGanancias(equipo: Equipo) = ganancias(equipo)
}


abstract class Tarea(descripcion: String, facilidad: (Equipo,Heroe) => Option[Int], cambios: Heroe => Unit){

  def getFacilidad = facilidad
  
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
  
  def realizarla(heroe: Heroe) = cambios(heroe)
  
  
}
/*
class pelearContraMonstruo() extends Tarea("Pelear contra Monstruo", {(e,h) => e.lider() match {
                                                                      case Some(h) if(h.getTrabajo.descripcion == "Guerrero") => Some(20)
                                                                      case _ => Some(10)
                                                                      }} , 
                                            {(x) => x.getStatBase.set("hp", x.getStatBase.get("hp") - 25)})*/