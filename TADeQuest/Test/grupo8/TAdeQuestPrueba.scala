package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class TAdeQuestPrueba {
  
   //(hp: Int,fuerza: Int, velocidad: Int, inteligencia: Int)
  var ashe: Heroe = null
  var coco: Heroe = null
  var pepe: Heroe = null
  var cascoVikingo: Sombrero = null
  var palitoMagico: ArmaDeUnaMano = null
  var armaduraElegante: Armadura = null
  var arcoViejo: ArmaDeDosManos = null
  var escudoAntiRobo: Escudo = null
  var talismanDedicacion: Talisman = null
  var talismanDelMinimalismo: Talisman = null
  var vinchaDelBuffaloDeAgua: Sombrero = null
  var talismanMaldito: Talisman = null
  var espadaDelaVida: ArmaDeDosManos = null
  
  var pikachu: Heroe = null

  @Before
  def setup = {
   ashe = new Heroe("ashe",new Stats(100,30,40,60))
   ashe = ashe.setTrabajo(Mago)
   coco = new Heroe("coco", new Stats(100,100,10,10))
   coco = coco.setTrabajo(Guerrero)
   pepe = new Heroe("pepe",new Stats(100,31,20,15))
   pepe = pepe.setTrabajo(Ladron)
   
   cascoVikingo = new Sombrero("Casco vikingo", x => new Stats(10,0,0,0), x => x.getStatBase.get(Fuerza) > 30)
   
   palitoMagico = new ArmaDeUnaMano("Palito Mágico", x => new Stats(0,0,0,20), 
                     x => x.getTrabajo.getOrElse(null) == Mago || (x.getTrabajo.getOrElse(null) == Ladron && x.getStatBase.get(Inteligencia) > 30))
   
   armaduraElegante = new Armadura("Armadura Elegante-Sport", x => new Stats(-30,0,30,0), x => true)
   
   arcoViejo = new ArmaDeDosManos("Arco Viejo", x => new Stats(0,2,0,0), x => true)
   
   escudoAntiRobo = new Escudo("Escudo Anti-Robo", x => new Stats(20,0,0,0), x => x.getTrabajo.getOrElse(null) != Ladron && x.getStatBase.get(Fuerza) >= 20)
   
   talismanDedicacion = new Talisman("Talismán de Dedicacion", 
                         {x => {val valor: Int = ((x.getTrabajo match {case Some(t) => t.getValorStatPrincipal
                                                                      case None => 0}) * 0.1).toInt
                                new Stats(valor,valor,valor,valor)}},
                         {x => true})
   
   talismanDelMinimalismo = new Talisman("Talisman del minimalismo", 
                         {x => val cantItems: Int = (x.getInventario.items.size) 
                           
                           new Stats(50-(10*cantItems),0,0,0)
                         
                         },x => true)
   
   vinchaDelBuffaloDeAgua = new Sombrero("vincha Del Buffalo De Agua",
                               
                               x=>
                                 if(x.getStatBase.get(Fuerza) > x.getStatBase.get(Inteligencia))
                                    new Stats(0,0,0,30)
                                 else
                                   new Stats(10,10,10,0),
                                 x => x.getTrabajo.isEmpty)
   
   talismanMaldito = new Talisman("Talisman Maldito",
                                   (heroe) => Stats(1,1,1,1, List({ x =>Stats(1,1,1,1)})), 
                                   (heroe) => true)
   
   espadaDelaVida = new ArmaDeDosManos("Espada de la Vida",
                                       heroe => Stats(restricciones = List({x => x.set(Fuerza,x.get(HP))})),
                                       heroe => true)
                                       
  
                                      
   
   pikachu = new Heroe("pikachu",new Stats(100,100,10,10))//Stats(hp: Int,fuerza: Int, velocidad: Int, inteligencia: Int)
   
   //Trabajo(descripcion: String, statBase: Stats, statPrincipal: String)

  }
   
  def compararStats(s1: Stats, s2: Stats):Boolean = {
    var estado: Boolean = true
   for(a <- s1.getStats;
       b <- s2.getStats;
       if(a._1 == b._1))
   {if(!(a._2 == b._2)){
     println(a._1 + " del primero es: " + a._2)
     println(b._1 + " del segundo es: " + b._2)
     println("")
     estado = false}
   }
   if(!estado) println("-------------------------------------------------")
   estado
  }
}