/*
 * Copyright (c) 2025 TauCeti.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Contributors:
 *     Yang Yang - initial API and implementation
 */
package org.tauceti.graph.util;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class UUIDGenerator implements IDGenerator {

	@Override
	public String getNextID() {
		UUID uuid = UUID.randomUUID();
		ByteBuffer byteBuffer = ByteBuffer.allocate(16);
		byteBuffer.putLong(uuid.getMostSignificantBits());
		byteBuffer.putLong(uuid.getLeastSignificantBits());
		String encodedString = Base64.getEncoder().withoutPadding().encodeToString(byteBuffer.array());
		encodedString = encodedString.replaceAll("/", "_").replaceAll("\\+", "_");
		return encodedString;
	}

	public static void main(String[] args) {
		UUIDGenerator gen = new UUIDGenerator();
		int i = 0;
		while (i < 1000) {
			String id = gen.getNextID();
			System.out.println("id = " + id);
			i++;
		}
	}
}
