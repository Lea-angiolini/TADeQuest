package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._
import scala.language.experimental.macros

class ForjandoUnHeroe {
  
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
  
  @Before
  def setup = {
   ashe = new Heroe(new Stats(100,30,40,60))
   ashe.setTrabajo(new Mago)
   coco = new Heroe(new Stats(100,100,10,10))
   coco.setTrabajo(new Guerrero)
   pepe = new Heroe(new Stats(100,31,20,15))
   pepe.setTrabajo(new Ladron)
   
   cascoVikingo = new Sombrero("Casco vikingo", x => new Stats(10,0,0,0), x => x.getStatBase.get("fuerza") > 30)
   palitoMagico = new ArmaDeUnaMano("Palito Mágico", x => new Stats(0,0,0,20), 
                     x => x.getTrabajo.getDescripcion == "Mago" || (x.getTrabajo.getDescripcion == "Ladron" && x.getStatBase.get("inteligencia") > 30))
   armaduraElegante = new Armadura("Armadura Elegante-Sport", x => new Stats(-30,0,30,0), x => true)
   arcoViejo = new ArmaDeDosManos("Arco Viejo", x => new Stats(0,2,0,0), x => true)
   escudoAntiRobo = new Escudo("Escudo Anti-Robo", x => new Stats(20,0,0,0), x => x.getTrabajo.getDescripcion == "Ladron" && x.getStatBase.get("fuerza") >= 20)
   talismanDedicacion = new Talisman("Talismán de Dedicacion", 
                         {x => val valor: Int = (x.getTrabajo.getValorStatPrincipal * 0.1).toInt
                           new Stats(valor,valor,valor,valor)}, x => true)
   
   talismanDelMinimalismo = new Talisman("Talisman del minimalismo", 
                         {x => val cantItems: Int = (x.getInventario.items.size) 
                           
                           new Stats(50-(10*cantItems),0,0,0)
                         
                         },x => true)
   vinchaDelBuffaloDeAgua = new Sombrero("vincha Del Buffalo De Agua",
                               
                               x=>
                                 if(x.getStatBase.get("fuerza") > x.getStatBase.get("inteligencia"))
                                    new Stats(0,0,0,30)
                                 else
                                   new Stats(10,10,10,0),
                                 x => x.getTrabajo.getDescripcion == "Sin trabajo" )
   
   var stat = Stats(1,1,1,1)
   stat setRestriccion { x => for (s <- x.getStats) {x.set(s._1, 1); println(x.get(s._1)) }}
   talismanMaldito = new Talisman("Talisman Maldito",(heroe) => stat, (heroe) => true)
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
  
  @Test
  def statMayoresA1() = {
    var burro = new Heroe(new Stats(100,100,40,0))
    burro.setTrabajo(new Guerrero)
    assertTrue(compararStats(burro.getStats, new Stats(110,115,40,1)))  
  }
  
  @Test
  def test_usarSombreroVikingo() = {
    ashe.equipar(cascoVikingo)
    assertEquals(100, ashe.getStats.get("hp"))
    coco.equipar(cascoVikingo)
    assertEquals(120, coco.getStats.get("hp"))
    pepe.equipar(cascoVikingo)
    assertEquals(105, pepe.getStats.get("hp"))
  }
  
  @Test
  def usaPalitoMagico() = {
    assertEquals(80, ashe.getStats.get("inteligencia"))
    ashe.equipar(palitoMagico)
    assertEquals(100, ashe.getStats.get("inteligencia"))
    ashe.equipar(palitoMagico)
    assertEquals(100, ashe.getStats.get("inteligencia"))
    ashe.equipar(palitoMagico)
    assertEquals(100, ashe.getStats.get("inteligencia"))
    
    assertEquals(1, coco.getStats.get("inteligencia"))
    coco.equipar(palitoMagico)
    assertEquals(1, coco.getStats.get("inteligencia"))
    
    pepe.equipar(palitoMagico)
    assertEquals(15, pepe.getStats.get("inteligencia"))
  }
  
  @Test
  def usaArmaduraElegante() = {
    ashe.equipar(armaduraElegante)
    assertTrue(compararStats(ashe.getStats, new Stats(70,10,70,80)))
    ashe.descartarTrabajo
    assertTrue(compararStats(ashe.getStats, new Stats(70,30,70,60)))
  }
  
  @Test
  def usaArcoViejo() = {
    coco.equipar(arcoViejo)
    assertTrue(compararStats(coco.getStats, new Stats(110,117,10,1)))
    coco.descartarTrabajo
    assertTrue(compararStats(coco.getStats, new Stats(100,102,10,10)))
   
  }
  
  @Test
  def usaEscudoAntiRobo() = {
   /* var a: List[Heroe] = List()//List(new Heroe(new Stats(hp = 8, fuerza=43)), new Heroe(new Stats(hp = 98)))
    println("el heroe mas grande es: " + a.maxBy(_.getStatBase.get("hp")).getStatBase.get("fuerza"))
  */
   }
  
  
  @Test
  def usaTalismanDeDedicacion() = {
    ashe.equipar(talismanDedicacion)
    assertTrue(compararStats(ashe.getStats, new Stats(102,12,42,82)))
    ashe.descartarTrabajo
    assertTrue(compararStats(ashe.getStats, new Stats(100,30,40,60)))
    
    pepe.equipar(talismanDedicacion)
    assertTrue(compararStats(pepe.getStats, new Stats(96,32,31,16)))
       
  }
  
  @Test
  def usaTalismanDelMinimalismo() = {
    ashe.descartarTrabajo
    ashe.equipar(talismanDelMinimalismo)
    assertTrue(compararStats(ashe.getStats, new Stats(150,30,40,60)))
  }
  
  @Test
  def usaVinchaDeBufaloDeAgua = {
    ashe.descartarTrabajo
    ashe.equipar(vinchaDelBuffaloDeAgua)
    
    assertTrue(compararStats(ashe.getStats, new Stats(110,40,50,60)))
    
    
    coco.descartarTrabajo
    coco.equipar(vinchaDelBuffaloDeAgua)
    
    assertTrue(compararStats(coco.getStats,new Stats(100,100,10,40)))
  }
  
  @Test
  def usaTalismanMaldito = {
    ashe.descartarTrabajo
    ashe.equipar(palitoMagico)
    ashe.equipar(talismanMaldito)
    ashe.equipar(talismanDelMinimalismo)
    
    assertTrue(compararStats(ashe.getStats, new Stats(1,1,1,1)))
  }
}
