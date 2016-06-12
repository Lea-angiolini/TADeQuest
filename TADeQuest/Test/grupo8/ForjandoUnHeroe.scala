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
  
  
}