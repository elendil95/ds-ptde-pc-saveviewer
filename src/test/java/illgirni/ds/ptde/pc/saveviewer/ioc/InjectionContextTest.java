package illgirni.ds.ptde.pc.saveviewer.ioc;

import illgirni.ds.ptde.pc.saveviewer.ioc.InjectionContext;
import illgirni.ds.ptde.pc.saveviewer.ioc.mock.MockBean1;

import org.junit.Test;

public class InjectionContextTest {
    
    @Test
    @SuppressWarnings("unused")
    public void testGetBean() {
        InjectionContext context = new InjectionContext();
        MockBean1 bean = context.getBean(MockBean1.class);
        
        //too lazy to do this stuff...
    }
}
