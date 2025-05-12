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

import java.util.ArrayList;
import java.util.List;

import org.tauceti.graph.graphics.DataAttribute;
import org.tauceti.graph.graphics.impl.DataAttributeImpl;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class AdapterSupport implements Adaptable {

	protected List<Adapter> adapters = new ArrayList<Adapter>();
	protected DataAttribute dataMap = new DataAttributeImpl();

	public AdapterSupport() {
	}

	@Override
	public List<Adapter> getAdapters() {
		return this.adapters;
	}

	@Override
	public void addAdapter(Adapter adapter) {
		if (adapter != null && !this.adapters.contains(adapter)) {
			this.adapters.add(adapter);
		}
	}

	@Override
	public void removeAdapter(Adapter adapter) {
		if (adapter != null && this.adapters.contains(adapter)) {
			this.adapters.remove(adapter);
		}
	}

	/**
	 * Notify all the adapters added to this object with an Notification event.
	 * 
	 * @param notifier
	 * @param eventType
	 * @param feature
	 * @param oldValue
	 * @param newValue
	 */
	@Override
	public void notify(Object notifier, int eventType, Object feature, Object oldValue, Object newValue) {
		notify(new NotificationImpl(notifier, eventType, feature, oldValue, newValue));
	}

	/**
	 * Notify all the adapters added to this object with an Notification event.
	 * 
	 * @param notification
	 */
	@Override
	public void notify(Notification notification) {
		for (Adapter adapter : getAdapters()) {
			adapter.notifySettingChanged(notification);
		}
	}

	@Override
	public String[] getDataKeys() {
		return this.dataMap.getDataKeys();
	}

	@Override
	public void setData(Object value) {
		setData("data", value);
	}

	@Override
	public <T> void setData(Class<T> clazz, T value) {
		if (clazz == null) {
			throw new IllegalArgumentException("Class is null.");
		}
		setData(clazz.getName(), value);
	}

	@Override
	public void setData(String key, Object value) {
		if (key == null) {
			throw new IllegalArgumentException("key is null.");
		}
		this.dataMap.setData(key, value);
	}

	@Override
	public Object getData() {
		return getData("data");
	}

	@Override
	public Object getData(String key) {
		if (key == null) {
			return null;
		}
		return this.dataMap.getData(key);
	}

	@Override
	public <T> T getData(Class<T> clazz) {
		if (clazz == null) {
			return null;
		}
		return getData(clazz.getName(), clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getData(String key, Class<T> clazz) {
		if (key == null || clazz == null) {
			return null;
		}
		T t = null;
		Object obj = this.dataMap.getData(key);
		if (obj != null) {
			if (clazz.isAssignableFrom(obj.getClass())) {
				t = (T) obj;
			}
		}
		return t;
	}
}
