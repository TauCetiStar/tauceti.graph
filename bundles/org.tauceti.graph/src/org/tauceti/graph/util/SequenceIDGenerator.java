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

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class SequenceIDGenerator implements IDGenerator {

	protected String prefix;
	protected long sequenceID = 0;

	public SequenceIDGenerator() {
		this("wwt_");
	}

	public SequenceIDGenerator(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public synchronized String getNextID() {
		String nextId = String.valueOf(++this.sequenceID);
		if (this.prefix != null) {
			nextId = this.prefix + nextId;
		}
		return nextId;
	}
}
