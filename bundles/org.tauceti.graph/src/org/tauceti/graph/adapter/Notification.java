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
public interface Notification {

	int SET = 1;
	int UNSET = 2;
	int ADD = 3;
	int REMOVE = 4;
	int ADD_MANY = 5;
	int REMOVE_MANY = 6;
	int MOVE = 7;
	int REMOVING_ADAPTER = 8;
	int REFRESH_CHILDREN = 9;
	int EXECUTE = 10;
	int REORDER = 11;

	Object getSource();

	int getEventType();

	Object getFeature();

	Object getOldValue();

	Object getNewValue();
}
