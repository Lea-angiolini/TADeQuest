package grupo8

class Equipo(nombre: String, heroes: List[Heroe] = List()) {
  var pozoComun: Int = 0
   
  def pozoComun_+=(valor: Int) = pozoComun += valor
 
  def getNombre: String =   nombre
  
  def getHeroes: List[Heroe] = heroes
  
  def mejorHeroeSegun(cuantificador: (Heroe => Int)): Option[Heroe] = {  
    if(heroes.nonEmpty){
       val a = (for(h <- heroes) yield {(h, cuantificador(h))})
       val b = a.maxBy[Int](_._2)
       return Some(b._1)
    }
    return None
  }
}