package grupo8

import scala.util._

case class Tablon(misiones: Set[Mision] = Set[Mision]()) {
   
  def getMisiones = misiones
  
  def tieneMisiones = misiones.nonEmpty
  
  def tieneMision(mision: Mision) = misiones.contains(mision)
  
  def agregarMision(mision: Mision) = copy(misiones + mision)
  
  def sacarMision(mision: Mision) = copy(misiones - mision)

  def realizarMisionesConCriterio(criterio: Tablon => Option[Mision])(equipo:Equipo): Equipo = {
   
    criterio(this).fold(equipo)(m => sacarMision(m).realizarMisionesConCriterio(criterio)(equipo.realizarMision(m).get))

  }
  
//  def realizarMisiones[A](criterio: Tablon => Option[Mision])(elemento: A)(aplicar: Mision => A): A = {
//    
//    
//    criterio(this) match {
//      case Some(m) => sacarMision(m).realizarMisiones(criterio)(aplicar(m))(aplicar)
//      case None => elemento
//    }
//  }
}