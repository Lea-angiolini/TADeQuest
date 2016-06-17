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
  
  def obtenerMiembro(heroe: Heroe){
    this.heroes = this.getHeroes ::: List(heroe)
  }
  
  def reemplazarMiembro(miembroNuevo: Heroe, miembroViejo: Heroe){
    this.heroes = this.getHeroes.filter(_ != miembroViejo)
    this.obtenerMiembro(miembroNuevo) 
  }
  
  def mejorHeroeSegun2(cuantificador: (Heroe => Int)): Option[(Heroe, Int)] = {
    //TODO mejorar esta shit D:!!!!
    val a = (for(h <- heroes) yield {(h, cuantificador(h))})
    val ilKapo = a.maxBy[Int](_._2)
     
    
    val c = (for(h <- heroes if ilKapo._1 != h ) yield {(h, cuantificador(h))})
    val otroKapo = c.maxBy[Int](_._2)
     
    if(ilKapo._2 == otroKapo._2){
      return None
    }else{
      return Some(ilKapo)
    }
  }
  
  def lider():Option[(Heroe,Int)] = {
    this.mejorHeroeSegun { heroe => heroe.getStatPrincipal }//TODO y si hay mas de uno?
    //this.mejorHeroeSegun2 { heroe => heroe.getStatPrincipal }
  }
}