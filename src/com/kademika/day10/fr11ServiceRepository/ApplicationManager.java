package com.kademika.day10.fr11ServiceRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.*;


public class ApplicationManager{
    
    public ApplicationManager(){}
   
   public <T> T  getService(Class <T> service) throws IllegalAccessException, InstantiationException {
       T serv=null;
       if (service.isAnnotationPresent(Service.class)){
            serv = service.newInstance();
            for(Method mtd: service.getDeclaredMethods()){
                if(mtd.isAnnotationPresent(InitService.class)){
                    try {
                        mtd.invoke(serv);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
       return serv;
    }


}
