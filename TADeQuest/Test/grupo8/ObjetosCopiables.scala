package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._


class ObjetosCopiables extends TAdeQuestPrueba{
  
  @Test
  def copiarUnHeroe = {
    var heroe = new Heroe(new Stats(10,10,10,10))
    var heroeCopiar = heroe.copiar
    
    heroe.setTrabajo(new Guerrero)
    heroe.equipar(armaduraElegante)
    
    assertTrue(compararStats(new Stats(2,2,2,10).getStatsFinales,new Stats(1,1,1,0) + new Stats(0,0,0,1) + new Stats(1,1,1,9)))
    assertFalse(compararStats(heroe.statActual, heroeCopiar.statActual))
    assertTrue(compararStats(heroe.statActual, new Stats(1,25,40,1)))
    }
  }