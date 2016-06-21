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
      case x :: _  => if (funcion {x} > 0 ) {x.equipar(item)} else {vender(item)}
      case _ => {vender(item)}
    }
    
  }
  
  def obtenerMiembro(heroe: Heroe){
    this.heroes = this.getHeroes ::: List(heroe)
  }
  
  def reemplazarMiembro(miembroNuevo: Heroe, miembroViejo: Heroe){
    this.heroes = this.getHeroes.filter(_ != miembroViejo)
    this.obtenerMiembro(miembroNuevo) 
  }
  
  private def mejoresHeroesSegunDe(cuantificador: (Heroe => Int), listHeroes: List[Heroe]): List[Heroe] = {  
    listHeroes.filter { cuantificador(_) == listHeroes.map { cuantificador(_) }.max }       
  }
  
  def mejoresHeroesSegun(cuantificador: (Heroe => Int)): List[Heroe] = {  
    mejoresHeroesSegunDe(cuantificador,heroes)      
  }
  
  def lider():Option[Heroe] = {
    val lista = this.mejoresHeroesSegun { heroe => heroe.getStatPrincipal }
    
    return lista match {
      case valor :: Nil => Some(valor)
      case _ => None
    }  
  }
  
  def realizarMision(mision: Mision) {
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
  }

}