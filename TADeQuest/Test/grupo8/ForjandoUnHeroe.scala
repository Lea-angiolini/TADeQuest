package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class ForjandoUnHeroe extends TAdeQuestPrueba{
    
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
    pepe.equipar(escudoAntiRobo)
    assertTrue(compararStats(pepe.statActual, new Stats(95,31,30,15)))
    
    coco.equipar(escudoAntiRobo)
    assertTrue(compararStats(coco.statActual, new Stats(130,115,10,1)))
    
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
  
  @Test
  def usaEspadaDeLaVida = {
    assertTrue(compararStats(coco.getStats, new Stats(110,115,10,1)))
    coco.equipar(espadaDelaVida)
    assertTrue(compararStats(coco.getStats, new Stats(110,110,10,1)))
    coco.equipar(talismanMaldito)
    assertTrue(compararStats(coco.getStats, new Stats(1,1,1,1)))
  }
  
  @Test
  def asd = {
    var equipo = new Equipo("los patos", List(ashe,coco))
    var mision = new Mision(Set(new pelearContraMonstruo), equipo => equipo.obtenerMiembro(pepe))
    
    equipo.realizarMision(mision)
    println(ashe.getTrabajo)
    ashe.setTrabajo(new Ladron)
    println(ashe)
  }
}
