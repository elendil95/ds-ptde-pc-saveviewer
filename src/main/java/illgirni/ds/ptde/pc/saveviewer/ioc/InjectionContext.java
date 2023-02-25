package illgirni.ds.ptde.pc.saveviewer.ioc;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Bean;
import illgirni.ds.ptde.pc.saveviewer.ioc.annotations.Inject;

/**
 * Simple inversion of control implementation: We don't want to constantly create new Objects or
 * pass Objects around. This can be used to create new Objects of classes annotated with
 * {@link Bean}. The instances are cached in this context. So, this context provides
 * <i>singleton</i> instances when asked for the same type multiple times. Property values of the
 * instances are automatically "injected" with instances of the respective property's type (as long
 * as the type is also annotated with {@link Bean}). Only properties annotated with {@link Inject}
 * are automatically set. The property values are also kept in this context's cache.
 * <p/>
 * <i>Why is this implemented?</i>
 * <ul>
 * <li>See above.</li>
 * <li>A library like Spring would be overkill.</li>
 * </ul>
 * 
 * @author illgirni
 *
 */
public class InjectionContext {

  /**
   * The instance cache of this context.
   */
  private Map<Class<?>, Object> beans = new HashMap<>();

  /**
   * Creates a new instance of the given type or gets the matching instance from this context's
   * cache. The type has to be annotated with {@link Bean}. Properties of the type, annotated with
   * {@link Inject}, get a value either from this context's cache or as a new instance that is then
   * also placed in this context's cache.
   * 
   * @param beanType The bean type.
   * @return The instance.
   */
  @SuppressWarnings("unchecked")
  public <T> T getBean(final Class<T> beanType) {
    if (isBeanType(beanType)) {
      final Object bean = beans.get(beanType);

      if (bean != null) {
        return (T) bean;

      } else {
        return addBean(beanType);

      }

    } else {
      throw new IllegalArgumentException("Not a bean type: " + beanType);
    }

  }

  /**
   * Adds a new instance of the given type to this context's cache. Field values are injected, too.
   * This may result in further creation and placement of instances in this cache.
   * 
   * @param beanType The type to instantiate.
   * @return The created instance.
   */
  private <T> T addBean(final Class<T> beanType) {
    try {
      final T bean = beanType.newInstance();
      // For circular references put this in the content immediately.
      beans.put(beanType, bean);

      // Inject values into bean.
      Class<?> currentType = beanType;

      while (currentType != null) {
        for (final Field beanField : currentType.getDeclaredFields()) {
          if (beanField.isAnnotationPresent(Inject.class)) {
            final Object fieldValue = getBean(beanField.getType());

            if (fieldValue != null) {
              if (!beanField.isAccessible()) {
                beanField.setAccessible(true);
              }

              beanField.set(bean, fieldValue);

            } else {
              throw new RuntimeException("There is no bean to inject into " + beanType.getName()
                  + "." + beanField.getName());
            }
          }
        }

        currentType = currentType.getSuperclass();
      }

      return bean;

    } catch (InstantiationException | IllegalAccessException e) {
      throw new RuntimeException("Cannot create new bean of type " + beanType, e);
    }
  }

  /**
   * Checks if the given type is a type which this context can handle. This is the case when it is
   * annotated with {@link Bean} and not abstract.
   * 
   * @param beanType The type.
   * @return {@code true} when this context can handle the type.
   */
  private boolean isBeanType(Class<?> beanType) {
    return beanType.isAnnotationPresent(Bean.class)
        && !Modifier.isAbstract(beanType.getModifiers());
  }

}
