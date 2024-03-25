package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

 class ApplicationContextinfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

     @Test
     @DisplayName("모든 빈 출력하기") //스프링 기본 설정(내부)까지 다 나옴
     void findAllBean() { //public 설정 안해도됨
         String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //bin 정리된 이름을 등록함
         for (String beanDefinitionName : beanDefinitionNames) {
             Object bean = ac.getBean(beanDefinitionName); //타입 지정을 안해서 Object 타입으로 나옴
             System.out.println("bean = "+beanDefinitionName + " object = "+bean);
         }
     }

     @Test
     @DisplayName("애플리케이션 빈 출력하기") //내가 설정한 것만 나오게
     void findApplicationBean() { //public 설정 안해도됨
         String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //bin 정리된 이름을 등록함
         for (String beanDefinitionName : beanDefinitionNames) {
             BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//getBeanDefinition:빈 하나하나에 대한 메타데이터 정보

             //Role ROLE_APPLICATION:직접 등록한 애플리케이션 빈
             //Role ROLE_INFRASTRUCTURE:스프링 내부에서 사용하는 빈

             //내부에 등록한 빈들이 아니라 내가 애플리케이션을 개발하기 위해서 등록한 빈, 외부 라이브러리를 출력하기 위함
             if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                 Object bean = ac.getBean(beanDefinitionName);
                 System.out.println("name = "+beanDefinitionName + " object = "+bean);
             }
         }
     }
}
