/*
Freeware License, some rights reserved

Copyright (c) 2023 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.prospring6.twelve.spring;

import com.apress.prospring6.twelve.spring.config.TaskSchedulingConfig;
import com.apress.prospring6.twelve.spring.config.TaskSchedulingConfig2;
import com.apress.prospring6.twelve.spring.config.TaskSchedulingConfig3;
import com.apress.prospring6.twelve.spring.config.TaskSchedulingConfig4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

import java.io.IOException;

/**
 * Created by iuliana on 29/10/2022
 */
public class CarTaskSchedulerDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarTaskSchedulerDemo.class);

    public static void main(String... args) throws IOException {
        try (var ctx = initCtx(args)) {
            //Arrays.stream(ctx.getBeanDefinitionNames()).forEach(b -> LOGGER.info("{} of type {} ", b, ctx.getBean(b)));
            try {
                var taskScheduler = ctx.getBean(ScheduledAnnotationBeanPostProcessor.DEFAULT_TASK_SCHEDULER_BEAN_NAME);
                LOGGER.info(" >>>> 'taskScheduler' found: {}", taskScheduler.getClass());
            } catch (NoSuchBeanDefinitionException nbd) {
                LOGGER.debug("No 'taskScheduler' configured!");
            }

            try {
                //Arrays.stream(ctx.getBeanDefinitionNames()).forEach(b -> LOGGER.info("{} of type {} ", b, ctx.getBean(b)));
                var taskExecutor = ctx.getBean("taskExecutor");
                LOGGER.info(" >>>> 'taskExecutor' found: {}", taskExecutor.getClass());
            } catch (NoSuchBeanDefinitionException nbd) {
                LOGGER.debug("No 'taskExecutor' configured!");
            }
            System.in.read();
        }
    }

    private static GenericApplicationContext initCtx(String... args){
        if (args.length == 0) {
            return new AnnotationConfigApplicationContext(TaskSchedulingConfig.class);
        } else if (args.length == 1) {
            if (args[0].equals("1")) {
                return new AnnotationConfigApplicationContext(TaskSchedulingConfig.class);
            } else  if (args[0].equals("2")) {
                return new AnnotationConfigApplicationContext(TaskSchedulingConfig2.class);
            } else  if (args[0].equals("3")) {
                return new AnnotationConfigApplicationContext(TaskSchedulingConfig3.class);
            } else  if (args[0].equals("4")) {
                return new AnnotationConfigApplicationContext(TaskSchedulingConfig4.class);
            }
        }
        return null;
    }
}
