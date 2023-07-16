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
package com.apress.prospring6.six.hybrid;

import com.apress.prospring6.six.config.BasicDataSourceCfg;
import com.apress.prospring6.six.config.MariaDBErrorCodesTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import static com.apress.prospring6.six.QueryConstants.PARAMETRIZED_FIND_NAME;

/**
 * Created by iuliana.cosmina on 10/05/2022
 */

interface SingerDao {
    String findNameById(Long id);
}

class JdbcSingerDao implements SingerDao, InitializingBean {
    private static Logger LOGGER = LoggerFactory.getLogger(JdbcSingerDao.class);
    private JdbcTemplate jdbcTemplate;

    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        var jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        var errorTranslator = new MariaDBErrorCodesTranslator();
        errorTranslator.setDataSource(dataSource);

        jdbcTemplate.setExceptionTranslator(errorTranslator);
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on SingerDao");
        }
    }

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject(PARAMETRIZED_FIND_NAME, String.class, id);
    }
}

@Import(BasicDataSourceCfg.class)
@Configuration
public class SpringDatasurceCfg {

    @Autowired
    DataSource dataSource;

    @Bean
    public SingerDao singerDao(){
        JdbcSingerDao dao = new JdbcSingerDao();
        dao.setDataSource(dataSource);
        return dao;
    }
}
