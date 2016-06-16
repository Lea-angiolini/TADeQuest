package grupo8

case class Equipo(nombre: String, var heroes: List[Heroe] = List()) extends Copiable{
  
  type A = Equipo
  override def copiar = copy(heroes = heroes.map(_.copiar))
  
  var pozoComun: Int = 0
   
  def pozoComun_+=(valor: Int) = pozoComun += valor
 
  def getNombre: String =   nombre
  
  def getHeroes: List[Heroe] = heroes
  
  def mejorHeroeSegun(cuantificador: (Heroe => Int)): Option[(Heroe, Int)] = {  
    if(heroes.nonEmpty){
       val a = (for(h <- heroes) yield {(h, cuantificador(h))})
       val b = a.maxBy[Int](_._2)
       return Some(b)
    }
    return None
  }
  
  def vender(item: Item) {
    //pozoComun_+=(item.valor)
  }
  def ObtenerItem(item: Item){
    val mejorHeroe = mejorHeroeSegun { heroe => heroe.copiar.equipar(item).getStatPrincipal - heroe.getStatPrincipal }
    mejorHeroe match {
      case Some(x) if x._2 > 0 => x._1.equipar(item)
      case _ => vender(item)
    }
  }
}