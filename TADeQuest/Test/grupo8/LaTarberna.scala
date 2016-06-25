package grupo8

import org.junit.Before
import org.junit.Test
import org.junit.Assert._

class LaTarberna extends TAdeQuestPrueba {
  
  var equipo: Equipo = null
  var misionfalla: Mision = null
  var mision1: Mision = null
  var mision2: Mision = null
  var mision3: Mision = null
  var tablon: Tablon = null
  
  @Before
  override def setup{
    super.setup
    
    equipo = new Equipo("Los 3 chiflados", List(ashe,coco,pepe))
    
    misionfalla = new Mision(Set(nadiePuedeHacerla), {_.adherirAlPozoComun(999999999)})
    mision1 = new Mision(Set(forzarPuerta), {_.adherirAlPozoComun(200)})
    mision2 = new Mision(Set(pelearContraMonstruo), {_.adherirAlPozoComun(400)})
    mision3 = new Mision(Set(new robarTalisman(talismanDedicacion)), {_.adherirAlPozoComun(1000)})
    
  }
  
  @Test
  def mejorMision  {
    
    // La mision3 no la pueden realizar porque el lider no es un ladrón
    tablon = new Tablon(Set(misionfalla,mision1,mision2,misionfalla,mision3))
    
    val mejorMision = equipo.elegirMision(tablon, (e1,e2) => e1.pozoComun > e2.pozoComun)
    
    
    assertEquals(0, equipo.pozoComun)//el equipo no sufre cambios
    assertEquals("Pelear contra Monstruo", mejorMision.get.tareas.head.getDescripcion)
  }
  
  @Test
  def heroesEntrenan  {
      
    tablon = new Tablon(Set(mision2,mision1))
    
    equipo = new Equipo("Los 3 chiflados", List(ashe,coco,pepe))
    
    equipo = equipo.entrenar(tablon, (e1,e2) => e1.pozoComun > e2.pozoComun)
    
    assertEquals(600, equipo.pozoComun)
  }
  
  @Test
  def heroesNoEntrenanEquipoNoSufreEfecto  {
      
   tablon = new Tablon(Set(misionfalla))
    
    equipo = new Equipo("Los 3 chiflados", List(ashe,coco,pepe))
    
    equipo = equipo.entrenar(tablon, (e1,e2) => e1.pozoComun > e2.pozoComun)
    
    assertEquals(0, equipo.pozoComun)
  }
  
  @Test
  def heroesEntrenanListaVaciaNoRompe  {
      
    tablon = new Tablon(Set())
    
    equipo = new Equipo("Magios", List(ashe,coco,pepe))
    
    equipo = equipo.entrenar(tablon, (e1,e2) => e1.pozoComun > e2.pozoComun)
    
    assertEquals(0, equipo.pozoComun)
  }

}