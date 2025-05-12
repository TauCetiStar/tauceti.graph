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
package org.tauceti.graph;

import java.util.List;

import org.tauceti.graph.adapter.Adaptable;
import org.tauceti.graph.extensions.Attributes;
import org.tauceti.graph.extensions.Scripts;
import org.tauceti.graph.extensions.Style;
import org.tauceti.graph.graphics.DataAttribute;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public interface IFigure extends Adaptable {

	String getId();

	Scripts getScripts();

	void setScripts(Scripts scripts);

	public Style getStyle();

	void setStyle(Style style);

	Attributes getAttributes();

	void setAttributes(Attributes attributes);

	DataAttribute getDataAttribute();

	void setDataAttribute(DataAttribute dataAttribute);

	Object getInput();

	boolean isVisible();

	void setVisible(boolean isVisible);

	IFigure getParent();

	void setParent(Figure parent);

	List<IFigure> getChildren();

	void add(IFigure child);

	void add(IFigure child, int index);

	void remove(IFigure child);
}
