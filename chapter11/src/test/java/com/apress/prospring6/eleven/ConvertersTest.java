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
package com.apress.prospring6.eleven;

import com.apress.prospring6.eleven.domain.Blogger;
import com.apress.prospring6.eleven.domain.SimpleBlogger;
import com.apress.prospring6.eleven.formatter.FormattingServiceCfg;
import com.apress.prospring6.eleven.converter.bean.ConverterCfg;
import com.apress.prospring6.eleven.property.editor.CustomEditorCfg;
import com.apress.prospring6.eleven.property.editor.CustomRegistrarCfg;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.ConversionService;

/**
 * Created by iuliana on 20/08/2022
 */
public class ConvertersTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertersTest.class);

    @Test // the old way
    public void testCustomPropertyEditorRegistrar() {
        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class, CustomRegistrarCfg.class)) {
            var springBlogger = ctx.getBean("springBlogger", Blogger.class);
            LOGGER.info("SpringBlogger info: {}" , springBlogger);

            var awsBlogger  = ctx.getBean("awsBlogger", Blogger.class);
            LOGGER.info("AwsBlogger info: {}" , awsBlogger);
        }
    }

    @Test // also the old way
    public void testLocalDateEditor() {
        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class, CustomEditorCfg.class)) {
            var springBlogger = ctx.getBean("springBlogger", Blogger.class);
            LOGGER.info("SpringBlogger info: {}" , springBlogger);

            var awsBlogger  = ctx.getBean("awsBlogger", Blogger.class);
            LOGGER.info("AwsBlogger info: {}" , awsBlogger);
        }
    }

    @Test
    public void testFormattingConverterBean() {
        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class, ConverterCfg.class)) {
            var springBlogger = ctx.getBean("springBlogger", Blogger.class);
            LOGGER.info("SpringBlogger info: {}" , springBlogger);

            var awsBlogger  = ctx.getBean("awsBlogger", Blogger.class);
            LOGGER.info("AwsBlogger info: {}" , awsBlogger);
        }
    }

    @Test
    public void testConvertingToSimpleBlogger() {
        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class, ConverterCfg.class)) {
            var springBlogger = ctx.getBean("springBlogger", Blogger.class);
            LOGGER.info("SpringBlogger info: {}" , springBlogger);

            var conversionService = ctx.getBean(ConversionService.class);
            var simpleBlogger = conversionService.convert(springBlogger, SimpleBlogger.class);
            LOGGER.info("simpleBlogger info: {}" , simpleBlogger);

        }
    }

    @Test
    public void testFormattingConversionService() {
        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class, FormattingServiceCfg.class)) {
            var springBlogger = ctx.getBean("springBlogger", Blogger.class);
            LOGGER.info("SpringBlogger info: {}" , springBlogger);

            var awsBlogger  = ctx.getBean("awsBlogger", Blogger.class);
            LOGGER.info("AwsBlogger info: {}" , awsBlogger);
        }
    }


}

