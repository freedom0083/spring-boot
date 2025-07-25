/*
 * Copyright 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.reactor.netty.autoconfigure.actuate.web.server;

import reactor.netty.http.server.HttpServer;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.actuate.autoconfigure.web.ManagementContextConfiguration;
import org.springframework.boot.actuate.autoconfigure.web.ManagementContextType;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementContextFactory;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.reactor.netty.autoconfigure.NettyReactiveWebServerAutoConfiguration;
import org.springframework.boot.web.server.reactive.ReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * {@link ManagementContextConfiguration @ManagementContextConfiguration} for Netty-based
 * reactive web endpoint infrastructure when a separate management context running on a
 * different port is required.
 *
 * @author Andy Wilkinson
 */
@ConditionalOnClass(HttpServer.class)
@ConditionalOnWebApplication(type = Type.REACTIVE)
@EnableConfigurationProperties(ManagementServerProperties.class)
@ManagementContextConfiguration(value = ManagementContextType.CHILD, proxyBeanMethods = false)
class NettyReactiveManagementChildContextConfiguration {

	@Bean
	static ManagementContextFactory reactiveWebChildContextFactory() {
		return new ManagementContextFactory(WebApplicationType.REACTIVE, ReactiveWebServerFactory.class,
				NettyReactiveWebServerAutoConfiguration.class);
	}

}
