package com.apress.prospring6.three.alias;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Runner {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(Singer.class);
        ctx.refresh();

        var s = ctx.getBeansOfType(Singer.class);

    }
}
