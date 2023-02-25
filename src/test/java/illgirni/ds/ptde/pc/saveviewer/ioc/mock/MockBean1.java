package illgirni.ds.ptde.pc.saveviewer.ioc.mock;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;

@Bean
public class MockBean1 {

  @Inject
  private MockBean2 beanRef1;

  private MockBean2 beanRef2;

  @Inject
  private MockBean1 beanRef3;

  public MockBean2 getBeanRef1() {
    return beanRef1;
  }

  public MockBean2 getBeanRef2() {
    return beanRef2;
  }

  public MockBean1 getBeanRef3() {
    return beanRef3;
  }

}
