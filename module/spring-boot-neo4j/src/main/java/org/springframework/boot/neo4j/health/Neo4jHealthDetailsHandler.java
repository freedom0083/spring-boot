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

package org.springframework.boot.neo4j.health;

import org.neo4j.driver.summary.DatabaseInfo;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.summary.ServerInfo;

import org.springframework.boot.health.contributor.Health;
import org.springframework.util.StringUtils;

/**
 * Handle health check details for a Neo4j server.
 *
 * @author Stephane Nicoll
 */
class Neo4jHealthDetailsHandler {

	/**
	 * Add health details for the specified {@link ResultSummary} and {@code edition}.
	 * @param builder the builder to use
	 * @param healthDetails the health details of the server
	 */
	void addHealthDetails(Health.Builder builder, Neo4jHealthDetails healthDetails) {
		ResultSummary summary = healthDetails.getSummary();
		ServerInfo serverInfo = summary.server();
		builder.up()
			.withDetail("server", healthDetails.getVersion() + "@" + serverInfo.address())
			.withDetail("edition", healthDetails.getEdition());
		DatabaseInfo databaseInfo = summary.database();
		if (StringUtils.hasText(databaseInfo.name())) {
			builder.withDetail("database", databaseInfo.name());
		}
	}

}
