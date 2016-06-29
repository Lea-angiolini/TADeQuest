package grupo8

import scala.util._

case class Tablon(misiones: Set[Mision] = Set[Mision]()) {
   
  def getMisiones = misiones
  
  def tieneMisiones = misiones.nonEmpty
  
  def tieneMision(mision: Mision) = misiones.contains(mision)
  
  def agregarMision(mision: Mision) = copy(misiones + mision)
  
  def sacarMision(mision: Mision) = copy(misiones - mision)

//  def realizarMisionesConCriterio(equipo:Equipo, criterio: (Equipo,Equipo) => Boolean): Equipo = {
//   
//    equipo.elegirMision(this, criterio)
//                    .fold(equipo)(m => sacarMision(m).realizarMisionesConCriterio(equipo.realizarMision(m).get,criterio))
//
//  }
  
  def realizarMisiones[A](elemento: A)(criterio: (A,Tablon) => Option[Mision])(aplicar: (A,Mision) => A): A = {
    
    criterio(elemento,this).fold(elemento)(m => sacarMision(m).realizarMisiones(aplicar(elemento,m))(criterio)(aplicar))

  }
}