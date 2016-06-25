package grupo8

import scala.util._

case class Tablon(misiones: Set[Mision] = Set[Mision]()) {
  
  def getMisiones = misiones
  
  def tieneMisiones = misiones.nonEmpty
  
  def tieneMision(mision: Mision) = misiones.contains(mision)
  
  def agregarMision(mision: Mision) = copy(misiones + mision)
  
  def sacarMision(mision: Mision) = copy(misiones - mision)
  
//  def elegirMision(equipo: Equipo, criterio: (Equipo,Equipo) => Boolean): Try[Mision] = {
//        
//    val resultados: Set[(Mision,Try[Equipo])] = for(m <- misiones) yield ((m,m.realizarTareas(equipo)))
//
//    val resultadosFiltrados: Set[(Mision,Equipo)] = for(res <- resultados; if res._2.isSuccess) yield ((res._1,res._2.get))
//    
//    Try(resultadosFiltrados.reduce(
//                      (provisoria,siguiente) =>      
//                        if(criterio(provisoria._2,siguiente._2))
//                          provisoria
//                        else
//                          siguiente)._1)
//  }
  
    
}