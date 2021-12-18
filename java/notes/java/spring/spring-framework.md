# Spring Framework

## Spring容器

- See：`AbstractApplicationContext#refresh`

### 生命周期钩子

#### 创建

1. `BeanFactoryPostProcessor`构造器
2. `BeanFactoryPostProcessor#postProcessBeanFactory`
3. `BeanPostProcessor`构造器
4. `InstantiationAwareBeanPostProcessor`构造器
5. `InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation`
6. Bean构造器
7. `InstantiationAwareBeanPostProcessor#postProcessPropertyValues`
8. 注入Bean属性
9. `BeanNameAware#setBeanName`
10. `BeanFactoryAware#setBeanFactory`
11. `BeanPostProcessor#postProcessBeforeInitialization`
12. `InitializingBean#afterPropertiesSet`
13. Bean的`initMethod`方法
15. `BeanPostProcessor#postProcessAfterInitialization`
16. `InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation`

#### 销毁

17. `DisposableBean#destroy`
14. Bean的`destroyMethod`方法

#### 更多钩子

- Lifecycle
- SmartLifecycle