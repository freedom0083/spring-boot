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

package org.springframework.boot.actuate.info;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.entry;

/**
 * Tests for {@link Info}.
 *
 * @author Stephane Nicoll
 */
class InfoTests {

	@Test
	void infoIsImmutable() {
		Info info = new Info.Builder().withDetail("foo", "bar").build();
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(info.getDetails()::clear);
	}

	@Test
	void infoTakesCopyOfMap() {
		Info.Builder builder = new Info.Builder();
		builder.withDetail("foo", "bar");
		Info build = builder.build();
		builder.withDetail("biz", "bar");
		assertThat(build.getDetails()).containsOnly(entry("foo", "bar"));
	}

}
