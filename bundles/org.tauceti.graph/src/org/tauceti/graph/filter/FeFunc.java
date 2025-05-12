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
package org.tauceti.graph.filter;

import org.tauceti.graph.FilterComponent;
import org.tauceti.graph.adapter.Notification;

/*-
<feComponentTransfer x="0%" y="0%" width="100%" height="100%" in="convolveMatrix1" result="componentTransfer">
	<feFuncR type="table" tableValues="1 0 1"/>
	<feFuncG type="table" tableValues="0 1 0"/>
	<feFuncB type="table" tableValues="1 0 1"/>
	<feFuncA type="table" tableValues="0 1"/>
	</feComponentTransfer>
*/
/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class FeFunc extends FilterComponent {

	public static enum FEATURES {
		FUNC_TAG_NAME, FUNC_TYPE, FUNC_TABLE_VALUES
	}

	protected String tagName;
	protected String type;
	protected String tableValues;

	public FeFunc() {
	}

	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		String oldValue = this.tagName;
		this.tagName = tagName;

		if ((oldValue == null && this.tagName != null) || (oldValue != null && !oldValue.equals(this.tagName))) {
			notify(this, Notification.SET, FEATURES.FUNC_TAG_NAME, oldValue, this.tagName);
		}
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		String oldValue = this.type;
		this.type = type;

		if ((oldValue == null && this.type != null) || (oldValue != null && !oldValue.equals(this.type))) {
			notify(this, Notification.SET, FEATURES.FUNC_TYPE, oldValue, this.type);
		}
	}

	public String getTableValues() {
		return this.tableValues;
	}

	public void setTableValues(String tableValues) {
		String oldValue = this.tableValues;
		this.tableValues = tableValues;

		if ((oldValue == null && this.tableValues != null) || (oldValue != null && !oldValue.equals(this.tableValues))) {
			notify(this, Notification.SET, FEATURES.FUNC_TABLE_VALUES, oldValue, this.tableValues);
		}
	}
}
