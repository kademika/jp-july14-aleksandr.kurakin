package com.kademika.day10.fr11ServiceRepository;

import java.lang.reflect.Method;

/**
 * Created by kurakinaleksandr on 10.08.14.
 */
public class Launcher {

    static ServiceRepository serviceRepository = new ServiceRepository();


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        serviceRepository.add(new Service1("1"));
        serviceRepository.add(new Service1("2"));
        serviceRepository.add(new Service1("3"));
        if (serviceRepository.get(0).getClass().isAnnotationPresent(Service.class)) {
            System.out.println("Check for ServiceMarker 1 member");
        };

        Service1 serv = new Service1("one");
        for (Method mtd : serv.getClass().getDeclaredMethods()) {
            if (mtd.isAnnotationPresent(InitService.class)) {
                System.out.println("yes, in " + mtd.getName());
            }
        }
        ApplicationManager applicationManager = new ApplicationManager();
        applicationManager.getService(serv.getClass());//

    }


}
