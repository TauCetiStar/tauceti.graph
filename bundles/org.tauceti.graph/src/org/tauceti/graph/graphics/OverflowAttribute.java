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
package org.tauceti.graph.graphics;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public interface OverflowAttribute {

	/*- 
	 * The overflow attribute sets what to do when an element's content is too big to fit in its block formatting context. 
	 * It has the same parameter values and meaning as the css overflow property, however, the following additional points apply:
	 * 
	 * 1. If it has a value of visible, the attribute has no effect (i.e., a clipping rectangle is not created).
	 * 
	 * 2. If the overflow property has the value hidden or scroll, a clip of the exact size of the SVG viewport is applied.
	 * 
	 * 3. When scroll is specified on an <svg> element, a scrollbar or panner is normally shown for the SVG viewport whether or not any of its content is clipped.
	 * 
	 * 4. Within SVG content, the value auto implies that all rendered content for child elements must be visible, either through a scrolling mechanism, or by rendering with no clip.
	 * 
	 * Note:
	 * 1. Although the initial value for overflow is auto, it is overwritten in the User Agent style sheet for the <svg> element 
	 *    when it is not the root element of a stand-alone document, the <pattern> element, and the <marker> element to be hidden by default.
	 *    
	 * 2. As a presentation attribute, overflow can be used as a CSS property. See the css opacity property for more information.
	 * 
	 * 3. As a presentation attribute, it can be applied to any element but it has effect only on the following eight elements: 
	 *    <foreignObject>, <iframe>, <image>, <marker>, <pattern>, <symbol>, <svg>, and <text>
	 */
	String AUTO 	= "auto";
	String VISIBLE 	= "visible";
	String HIDDEN 	= "hidden";
	String SCROLL 	= "scroll";

	String getOverflow();

	void setOverflow(String overflow);
}
