package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class LaTarberna extends TAdeQuestPrueba {
      
  @Test
  def mejorMision  {
    var equipo = new Equipo("Los 3 chiflados", List(ashe,coco,pepe))
    
    val misionfalla = new Mision(Set(nadiePuedeHacerla), {_.adherirAlPozoComun(999999999)})
    val mision1 = new Mision(Set(forzarPuerta), {_.adherirAlPozoComun(200)})
    val mision2 = new Mision(Set(pelearContraMonstruo), {_.adherirAlPozoComun(400)})
    val mision3 = new Mision(Set(new robarTalisman(talismanDedicacion)), {_.adherirAlPozoComun(1000)})
    // La mision3 no la pueden realizar porque el lider no es un ladrón
    var taberna = new Taberna(new Tablon(Set(misionfalla,mision1,mision2,misionfalla,mision3)))
    
    val mejorMision = taberna.getTablon.elegirMision2(equipo, (e1,e2) => e1.pozoComun > e2.pozoComun)

    assertEquals("Pelear contra Monstruo", mejorMision.get.tareas.head.getDescripcion)
  } 

}