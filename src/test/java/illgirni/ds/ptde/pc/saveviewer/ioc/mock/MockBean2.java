package illgirni.ds.ptde.pc.saveviewer.ioc.mock;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;

@Bean
public class MockBean2 {

  @Inject
  private MockBean1 beanRef;

}
