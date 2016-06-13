package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

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
   arcoViejo = new ArmaDeDosManos("Arco Viejo", x => new Stats(0,20,0,0), x => true)
   escudoAntiRobo = new Escudo("Escudo Anti-Robo", x => new Stats(20,0,0,0), x => x.getTrabajo.getDescripcion == "Ladron" && x.getStatBase.get("fuerza") >= 20)
   talismanDedicacion = new Talisman("Talismán de Dedicacion", 
                         {x => val valor: Int = (x.getTrabajo.getValorStatPrincipal *1.1).toInt
                           new Stats(valor,valor,valor,valor)}, x => true)
   
   talismanDelMinimalismo = new Talisman("Talisman del minimalismo", 
                         {x => val cantItems: Int = (x.inventario.items.size) 
                           
                           new Stats(50,-10*cantItems,-10*cantItems,-10*cantItems)
                         
                         },x => true)
   vinchaDelBuffaloDeAgua = new Sombrero("vincha Del Buffalo De Agua",
                               
                               x=>
                                 if(x.getStatBase.get("fuerza") > x.getStatBase.get("inteligencia"))
                                    new Stats(0,0,0,30)
                                 else
                                   new Stats(10,10,10,0),
                                 x => x.trabajo == null )
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
    assertEquals(120, ashe.getStats.get("inteligencia"))
    ashe.equipar(palitoMagico)
    assertEquals(120, ashe.getStats.get("inteligencia"))
    
    assertEquals(0, coco.getStats.get("inteligencia"))
    coco.equipar(palitoMagico)
    assertEquals(0, coco.getStats.get("inteligencia"))
    
    pepe.equipar(palitoMagico)
    assertEquals(15, pepe.getStats.get("inteligencia"))
  }
  
  @Test
  def usaArmaduraElegante() = {
    
  }
  
  @Test
  def usaArcoViejo() = {
    
  }
  
  @Test
  def usaEscudoAntiRobo() = {
    
  }
  
  @Test
  def usaTalismanDeDedicacion() = {
    
  }
  
  @Test
  def usaTalismanDelMinimalismo() = {
    
  }
  
  @Test
  def usaVinchaDeBufaloDeAgua = {
    
  }
  
}