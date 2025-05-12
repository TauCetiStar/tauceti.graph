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

import java.util.List;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public interface Adaptable {

	/**
	 * Get the list of Adapters added to this object which implements Adaptable interface.
	 * 
	 * @return
	 */
	List<Adapter> getAdapters();

	/**
	 * Add an Adapter to this object which implements Adaptable interface.
	 * 
	 * @param adapter
	 */
	void addAdapter(Adapter adapter);

	/**
	 * Remove an Adapter from this object which implements Adaptable interface.
	 * 
	 * @param adapter
	 */
	void removeAdapter(Adapter adapter);

	/**
	 * Notify all the adapters added to this object with an Notification event.
	 * 
	 * @param notifier
	 * @param eventType
	 * @param feature
	 * @param oldValue
	 * @param newValue
	 */
	void notify(Object notifier, int eventType, Object feature, Object oldValue, Object newValue);

	/**
	 * Notify all the adapters added to this object with an Notification event.
	 * 
	 * @param notification
	 */
	void notify(Notification notification);

	String[] getDataKeys();

	Object getData();

	Object getData(String key);

	<T> T getData(Class<T> clazz);

	<T> T getData(String key, Class<T> clazz);

	void setData(Object value);

	<T> void setData(Class<T> clazz, T value);

	void setData(String key, Object value);
}
