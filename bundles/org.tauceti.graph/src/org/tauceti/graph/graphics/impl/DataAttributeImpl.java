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
package org.tauceti.graph.graphics.impl;

import org.tauceti.graph.graphics.DataAttribute;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class DataAttributeImpl implements DataAttribute {

	protected String[] keys;
	protected Object[] values;

	public DataAttributeImpl() {
	}

	@Override
	public String[] getDataKeys() {
		return this.keys;
	}

	@Override
	public Object getData(String key) {
		if (this.keys == null) {
			return null;
		}
		for (int i = 0; i < this.keys.length; i++) {
			if (this.keys[i].equals(key)) {
				return this.values[i];
			}
		}
		return null;
	}

	@Override
	public void setData(String key, Object value) {
		if (value == null) {
			if (this.keys == null) {
				return;
			}
			int index = 0;
			while (index < this.keys.length && !this.keys[index].equals(key)) {
				index++;
			}
			if (index == this.keys.length) {
				return;
			}

			if (this.keys.length == 1) {
				this.keys = null;
				this.values = null;

			} else {
				String[] newKeys = new String[this.keys.length - 1];
				Object[] newValues = new Object[this.values.length - 1];

				System.arraycopy(this.keys, 0, newKeys, 0, index);
				System.arraycopy(this.keys, index + 1, newKeys, index, newKeys.length - index);
				System.arraycopy(this.values, 0, newValues, 0, index);
				System.arraycopy(this.values, index + 1, newValues, index, newValues.length - index);

				this.keys = newKeys;
				this.values = newValues;
			}
			return;
		}

		if (this.keys == null) {
			this.keys = new String[] { key };
			this.values = new Object[] { value };
			return;
		}

		for (int i = 0; i < this.keys.length; i++) {
			if (this.keys[i].equals(key)) {
				this.values[i] = value;
				return;
			}
		}

		String[] newKeys = new String[this.keys.length + 1];
		Object[] newValues = new Object[this.values.length + 1];

		System.arraycopy(this.keys, 0, newKeys, 0, this.keys.length);
		System.arraycopy(this.values, 0, newValues, 0, this.values.length);

		newKeys[this.keys.length] = key;
		newValues[this.values.length] = value;

		this.keys = newKeys;
		this.values = newValues;
	}
}
