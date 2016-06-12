package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class ForjandoUnHeroe {
  
  var ashe: Heroe = null
  var coco: Heroe = null
  var cascoVikingo: Sombrero = null
  
  @Before
  def setup = {
   ashe = new Heroe(new Stats(100,30,40,60)) 
   coco = new Heroe(new Stats(100,100,10,10))
   
   cascoVikingo = new Sombrero("Casco vikingo", x => new Stats(10,0,0,0), x => x.getStatBase.get("fuerza") > 30)
  }
  
  @Test
  def test_usarSombrero() = {
    ashe.equipar(cascoVikingo)
    assertEquals(ashe.getStatBase.get("hp"), 100)
    coco.equipar(cascoVikingo)
    assertEquals(coco.getStatBase.get("hp"), 110)
  }
  
}