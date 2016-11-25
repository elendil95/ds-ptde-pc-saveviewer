package illgirni.ds.ptde.pc.saveviewer.ioc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import illgirni.ds.ptde.pc.saveviewer.ioc.InjectionContext;

/**
 * Marker for a class, which can by instantiated with {@link InjectionContext}.
 * 
 * @author illgirni
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {

}
