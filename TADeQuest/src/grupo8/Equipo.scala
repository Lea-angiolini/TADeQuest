package grupo8

import scala.util._

case class Equipo(nombre: String, var heroes: List[Heroe] = List(), var pozoComun: Int = 0) extends Copiable{
  
  type A = Equipo
  override def copiar = copy(heroes = heroes.map(_.copiar), pozoComun = pozoComun)
     
  def pozoComun_+=(valor: Int) = pozoComun += valor
 
  def getNombre: String =   nombre
  
  def getHeroes: List[Heroe] = heroes
  
  def vender(item: Item) {
    //pozoComun_+=(item.valor)
  }
  
  def ObtenerItem(item: Item){
    val funcion = { heroe: Heroe => heroe.copiar.equipar(item).getStatPrincipal - heroe.getStatPrincipal }
    
    val mejorHeroe = mejoresHeroesSegun { funcion }
    mejorHeroe match {
      case x :: _ if funcion {x} > 0 => x.equipar(item)
      case _ => vender(item)
    }
  }
  
  def obtenerMiembro(heroe: Heroe){
    this.heroes = this.getHeroes ::: List(heroe)
  }
  
  def reemplazarMiembro(miembroNuevo: Heroe, miembroViejo: Heroe){
    this.heroes = this.getHeroes.filter(_ != miembroViejo)
    this.obtenerMiembro(miembroNuevo) 
  }
  
  private def mejoresHeroesSegun(cuantificador: (Heroe => Int), heroes: List[Heroe]): List[Heroe] = {  
    heroes.filter { cuantificador(_) == heroes.map { cuantificador(_) }.max }       
  }
  
  def mejoresHeroesSegun(cuantificador: (Heroe => Int)): List[Heroe] = {  
    mejoresHeroesSegun(cuantificador,heroes)      
  }
  
  def lider():Option[Heroe] = {
    val lista = this.mejoresHeroesSegun { heroe => heroe.getStatPrincipal }
    
    return lista match {
      case valor :: Nil => Some(valor)
      case _ => None
    }  
  }
  
  def realizarMision(mision: Mision) {
     var nuevosHeroes = heroes.map { _.copiar }
     
     var algo = for(t <- mision.getTareas) yield {
     var heroesCapaces = for(h <-nuevosHeroes;
                           if t.puedeRealizarla(this,h))
                       yield(h)
       
     var mejorHeroe = Try(mejoresHeroesSegun(h => t.getFacilidad(this,h).get,heroesCapaces).head)
     if(mejorHeroe.isSuccess)
       {t.realizarla(mejorHeroe.get); println("mejor heroe " + mejorHeroe); println("lista de heroes " + nuevosHeroes)}
     else 
       return
     }
     heroes = nuevosHeroes
  }
  
  def intentarMision(){}
  
}