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
package org.tauceti.graph.adapter;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class NotificationImpl implements Notification {

	protected Object source;
	protected int eventType;
	protected Object feature;
	protected Object oldValue;
	protected Object newValue;

	/**
	 * 
	 * @param source
	 * @param eventType
	 * @param feature
	 * @param oldValue
	 * @param newValue
	 */
	public NotificationImpl(Object source, int eventType, Object feature, Object oldValue, Object newValue) {
		this.source = source;
		this.eventType = eventType;
		this.feature = feature;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	@Override
	public Object getSource() {
		return this.source;
	}

	public void setSource(Object source) {
		this.source = source;
	}

	@Override
	public int getEventType() {
		return this.eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	@Override
	public Object getFeature() {
		return this.feature;
	}

	public void setFeature(Object feature) {
		this.feature = feature;
	}

	@Override
	public Object getOldValue() {
		return this.oldValue;
	}

	public void setOldValue(Object oldValue) {
		this.oldValue = oldValue;
	}

	@Override
	public Object getNewValue() {
		return this.newValue;
	}

	public void setNewValue(Object newValue) {
		this.newValue = newValue;
	}

	@Override
	public String toString() {
		return "NotificationImpl [source=" + this.source + ", eventType=" + this.eventType + ", feature=" + this.feature + ", oldValue=" + this.oldValue + ", newValue=" + this.newValue + "]";
	}
}
