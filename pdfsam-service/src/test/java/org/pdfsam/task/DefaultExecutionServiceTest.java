/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 18/ago/2014
 * Copyright 2013-2014 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as 
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.task;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sejda.core.service.TaskExecutionService;
import org.sejda.model.parameter.base.TaskParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Andrea Vacondio
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DefaultExecutionServiceTest {

    @Inject
    private TaskExecutionService service;
    @Inject
    private DefaultExecutionService victim;

    @Configuration
    static class Config {
        @Bean
        public TaskExecutionService service() {
            return mock(TaskExecutionService.class);
        }

        @Bean
        public DefaultExecutionService victim() {
            return new DefaultExecutionService();
        }
    }

    @Test
    public void execute() {
        TaskParameters params = mock(TaskParameters.class);
        victim.submit("moduleId", params);
        verify(service).execute(params);
    }
}
