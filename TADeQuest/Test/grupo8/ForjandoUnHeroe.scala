package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class ForjandoUnHeroe extends TAdeQuestPrueba{
    
  @Test
  def statMayoresA1() = {
    var burro = new Heroe("burro",new Stats(100,100,40,0))
    burro = burro.setTrabajo(Guerrero)
    assertTrue(compararStats(burro.getStats, new Stats(110,115,40,1)))  
  }
  
  @Test
  def test_usarSombreroVikingo() = {
    ashe = ashe.equipar(cascoVikingo)
    assertEquals(100, ashe.getStats.get(HP))
    coco = coco.equipar(cascoVikingo)
    assertEquals(120, coco.getStats.get(HP))
    pepe = pepe.equipar(cascoVikingo)
    assertEquals(105, pepe.getStats.get(HP))
  }
  
  @Test
  def usaPalitoMagico() = {
    assertEquals(80, ashe.getStats.get(Inteligencia))
    ashe = ashe.equipar(palitoMagico)
    assertEquals(100, ashe.getStats.get(Inteligencia))
    ashe = ashe.equipar(palitoMagico)
    assertEquals(100, ashe.getStats.get(Inteligencia))
    ashe = ashe.equipar(palitoMagico)
    assertEquals(100, ashe.getStats.get(Inteligencia))
    
    assertEquals(1, coco.getStats.get(Inteligencia))
    coco = coco.equipar(palitoMagico)
    assertEquals(1, coco.getStats.get(Inteligencia))
    
    pepe = pepe.equipar(palitoMagico)
    assertEquals(15, pepe.getStats.get(Inteligencia))
  }
  
  @Test
  def usaArmaduraElegante() = {
    ashe = ashe.equipar(armaduraElegante)
    assertTrue(compararStats(ashe.getStats, new Stats(70,10,70,80)))
    ashe = ashe.descartarTrabajo
    assertTrue(compararStats(ashe.getStats, new Stats(70,30,70,60)))
  }
  
  @Test
  def usaArcoViejo() = {
    coco = coco.equipar(arcoViejo)
    assertTrue(compararStats(coco.getStats, new Stats(110,117,10,1)))
    coco = coco.descartarTrabajo
    assertTrue(compararStats(coco.getStats, new Stats(100,102,10,10)))
   
  }
  
  @Test
  def usaEscudoAntiRobo() = {
    pepe = pepe.equipar(escudoAntiRobo)
    assertTrue(compararStats(pepe.statActual, new Stats(95,31,30,15)))
    
    coco = coco.equipar(escudoAntiRobo)
    assertTrue(compararStats(coco.statActual, new Stats(130,115,10,1)))
    
   }
  
  
  @Test
  def usaTalismanDeDedicacion() = {
    ashe = ashe.equipar(talismanDedicacion)
    assertTrue(compararStats(ashe.getStats, new Stats(102,12,42,82)))
    ashe = ashe.descartarTrabajo
    assertTrue(compararStats(ashe.getStats, new Stats(100,30,40,60)))
    
    pepe = pepe.equipar(talismanDedicacion)
    assertTrue(compararStats(pepe.getStats, new Stats(96,32,31,16)))
       
  }
  
  @Test
  def usaTalismanDelMinimalismo() = {
    ashe = ashe.descartarTrabajo
    ashe = ashe.equipar(talismanDelMinimalismo)
    assertTrue(compararStats(ashe.getStats, new Stats(150,30,40,60)))
  }
  
  @Test
  def usaVinchaDeBufaloDeAgua = {
    ashe = ashe.descartarTrabajo
    ashe = ashe.equipar(vinchaDelBuffaloDeAgua)
    
    assertTrue(compararStats(ashe.getStats, new Stats(110,40,50,60)))
    
    
    coco = coco.descartarTrabajo
    coco = coco.equipar(vinchaDelBuffaloDeAgua)
    
    assertTrue(compararStats(coco.getStats,new Stats(100,100,10,40)))
  }
  
  @Test
  def usaTalismanMaldito = {
    ashe = ashe.descartarTrabajo
    ashe = ashe.equipar(palitoMagico)
    ashe = ashe.equipar(talismanMaldito)
    ashe = ashe.equipar(talismanDelMinimalismo)
    
    assertTrue(compararStats(ashe.getStats, new Stats(1,1,1,1)))
  }
  
  @Test
  def usaEspadaDeLaVida = {
    assertTrue(compararStats(coco.getStats, new Stats(110,115,10,1)))
    coco = coco.equipar(espadaDelaVida)
 
    assertTrue(compararStats(coco.getStats, new Stats(110,110,10,1)))
    coco = coco.equipar(talismanMaldito)
    assertTrue(compararStats(coco.getStats, new Stats(1,1,1,1)))
  }
  
//  @Test
//  def asd = {
//    var equipo = new Equipo("los patos", List(ashe,coco))
//    var mision = new Mision(Set(new pelearContraMonstruo), equipo => equipo.obtenerMiembro(pepe))
//    
//    equipo.realizarMision(mision)
//    println(ashe.getTrabajo)
//    ashe.setTrabajo(new Ladron)
//    println(ashe)
//  }
}
