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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.tauceti.graph.adapter.Notification;
import org.tauceti.graph.extensions.SVGExtensionElement;
import org.tauceti.graph.graphics.BoundsAttribute;
import org.tauceti.graph.graphics.DisplayAttribute;
import org.tauceti.graph.graphics.FontAttribute;
import org.tauceti.graph.graphics.OverflowAttribute;
import org.tauceti.graph.graphics.Rectangle;
import org.tauceti.graph.util.NSMap;
import org.tauceti.graph.util.NSMapUtil;
import org.tauceti.graph.util.SVGConstants;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class SVG extends Figure implements FontAttribute, DisplayAttribute, BoundsAttribute, OverflowAttribute {

	public static enum FEATURES {
		VERSION, XML_NS, BASE_PROFILE, VIEW_PORT, VIEW_BOX, FONT_FAMILY, FONT_SIZE, ON_LOAD, ON_MOUSE_MOVE, ON_MOUSE_UP, EXTENSION_ELEMENTS
	}
	// X, Y, WIDTH, HEIGHT,

	protected Map<String, String> nsMap;
	protected String xmlns = SVGConstants.XMLNS_SVG;
	protected String version; // SVGConstants.VERSION_1_1;
	protected String baseProfile; // SVGConstants.BASE_PROFILE_FULL;

	// "display" attribute applies to: "svg", "g", "switch", "a", "foreignObject"
	// Initial: inline
	// Inherited: no
	// When setting "display" to none:
	// (1) it causes the container and all of its children to be invisible; thus, it acts on groups of elements as a group.
	// (2) the given element does not become part of the rendering tree.
	// (3) the element receives no events
	// (4) indicates that the given element and its children shall not be rendered directly (i.e., those elements are not present in the rendering
	// tree). Any value other than none or inherit indicates that the given element shall be rendered by the SVG user agent.
	// (5) do not take up space in text layout operations, do not receive events, and do not contribute to bounding box and clipping paths
	// calculations.
	// protected String display;

	protected int x, y, width, height;
	protected ViewBox viewBox;
	protected String fontFamily;
	protected int fontSize;
	protected String onLoad;
	protected String onMouseMove;
	protected String onMouseUp;
	protected List<SVGExtensionElement> extensionElements;

	protected Integer level;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public SVG(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		setData(NSMap.class, new NSMap());
		this.nsMap = new LinkedHashMap<String, String>();

		setNamespace(SVGConstants.XLINK_PREFIX, SVGConstants.XLINK_NAMESPACE);
		NSMapUtil.getNamespaceMap(this).put(SVGConstants.XLINK_PREFIX, SVGConstants.XLINK_NAMESPACE);
		NSMapUtil.getNamespaceMap(this).put(SVGConstants.TAUCETI_PREFIX, SVGConstants.TAUCETI_NAMESPACE);

		this.extensionElements = new ArrayList<SVGExtensionElement>();
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	// @Override
	// public String getDisplay() {
	// return this.display;
	// }
	//
	// @Override
	// public void setDisplay(String display) {
	// String oldValue = this.display;
	// this.display = SVG.checkDisplay(display);
	//
	// notify(this, Notification.SET, SVG.FEATURES.DISPLAY, oldValue, this.display);
	// }

	/**
	 * Value: inline | block | list-item | run-in | compact | marker | table | inline-table | table-row-group | table-header-group | table-footer-group | table-row | table-column-group | table-column |
	 * table-cell | table-caption | none | inherit
	 * 
	 * Initial: inline
	 * 
	 * Applies to: "svg", "g", "foreignObject", "switch", "a", graphics elements (including the "text" element) and text sub-elements (i.e., "tspan", "tref", "altGlyph", "textPath")
	 * 
	 * Inherited: no
	 * 
	 * @param display
	 * @return
	 */
	public static String checkDisplay(String display) {
		if (!"inline".equals(display) && !"block".equals(display) && !"list-item".equals(display) && !"run-in".equals(display) && !"compact".equals(display) && !"marker".equals(display) //
				&& !"table".equals(display) && !"inline-table".equals(display) && !"table-row-group".equals(display) && !"table-header-group".equals(display) && !"table-footer-group".equals(display) //
				&& !"table-row".equals(display) && !"table-column-group".equals(display) && !"table-column".equals(display) && !"table-cell".equals(display) && !"table-caption".equals(display) //
				&& !"none".equals(display) && !"inherit".equals(display) //
		) {
			display = "inline";
		}
		return display;
	}

	// ------------------------------------------------------------------------------------------------
	// x, y, width, height
	// ------------------------------------------------------------------------------------------------
	@Override
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.width, this.height);
	}

	/**
	 * 
	 * @param bound
	 */
	@Override
	public void setBounds(Rectangle bound) {
		Rectangle oldValue = new Rectangle(this.x, this.y, this.width, this.height);

		this.x = bound.getX();
		this.y = bound.getY();
		this.width = bound.getWidth();
		this.height = bound.getHeight();

		getTransform().translate(this.x, this.y);

		notify(this, Notification.SET, Group.FEATURES.BOUND, oldValue, bound);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		setBounds(new Rectangle(x, y, width, height));
	}

	// ------------------------------------------------------------------------------------------------
	// NS map
	// ------------------------------------------------------------------------------------------------
	public Map<String, String> getNamespaceMap() {
		return nsMap;
	}

	public void setNamespace(String prefix, String namespace) {
		if (prefix != null) {
			if (namespace == null) {
				removePrefix(prefix);
			} else {
				nsMap.put(prefix, namespace);
			}
		}
	}

	public String getNamespace(String prefix) {
		if (prefix != null) {
			return nsMap.get(prefix);
		}
		return null;
	}

	public void removePrefix(String prefix) {
		if (prefix != null) {
			nsMap.remove(prefix);
		}
	}

	public void removeNamespace(String namespace) {
		if (namespace != null) {
			for (Iterator<Entry<String, String>> itor = nsMap.entrySet().iterator(); itor.hasNext();) {
				Entry<String, String> entry = itor.next();
				String currNamespace = entry.getValue();
				if (namespace.equals(currNamespace)) {
					itor.remove();
				}
			}
		}
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		Object oldValue = this.version;
		this.version = version;

		notify(this, Notification.SET, SVG.FEATURES.VERSION, oldValue, version);
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		Object oldValue = this.xmlns;
		this.xmlns = xmlns;

		notify(this, Notification.SET, SVG.FEATURES.XML_NS, oldValue, xmlns);
	}

	public String getBaseProfile() {
		return baseProfile;
	}

	public void setBaseProfile(String baseProfile) {
		Object oldValue = this.baseProfile;
		this.baseProfile = baseProfile;

		notify(this, Notification.SET, SVG.FEATURES.BASE_PROFILE, oldValue, baseProfile);
	}

	public ViewBox getViewBox() {
		return viewBox;
	}

	public void setViewBox(ViewBox viewBox) {
		Object oldValue = this.viewBox;
		this.viewBox = viewBox;

		notify(this, Notification.SET, SVG.FEATURES.VIEW_BOX, oldValue, viewBox);
	}

	public void setViewBox(int x, int y, int width, int height) {
		setViewBox(new ViewBox(x, y, width, height));
	}

	// public int getX() {
	// return this.x;
	// }
	//
	// public void setX(int x) {
	// int oldValue = this.x;
	// this.x = x;
	//
	// if (oldValue != this.x) {
	// notify(this, Notification.SET, SVG.FEATURES.X, oldValue, this.x);
	// }
	// }

	// public int getY() {
	// return this.y;
	// }
	//
	// public void setY(int y) {
	// int oldValue = this.y;
	// this.y = y;
	//
	// if (oldValue != this.y) {
	// notify(this, Notification.SET, SVG.FEATURES.Y, oldValue, this.y);
	// }
	// }

	// public int getWidth() {
	// return this.width;
	// }
	//
	// public void setWidth(int width) {
	// int oldValue = this.width;
	// this.width = width;
	//
	// if (oldValue != this.width) {
	// notify(this, Notification.SET, SVG.FEATURES.WIDTH, oldValue, width);
	// }
	// }

	// public int getHeight() {
	// return this.height;
	// }
	//
	// public void setHeight(int height) {
	// int oldValue = this.height;
	// this.height = height;
	//
	// if (oldValue != this.height) {
	// notify(this, Notification.SET, SVG.FEATURES.HEIGHT, oldValue, height);
	// }
	// }

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		Object oldValue = this.fontFamily;
		this.fontFamily = fontFamily;

		notify(this, Notification.SET, SVG.FEATURES.FONT_FAMILY, oldValue, fontFamily);
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		Object oldValue = this.fontSize;
		this.fontSize = fontSize;

		notify(this, Notification.SET, SVG.FEATURES.FONT_SIZE, oldValue, fontSize);
	}

	// ------------------------------------------------------------------------------------------------
	// Functions
	// ------------------------------------------------------------------------------------------------
	public String getOnLoad() {
		return onLoad;
	}

	public void setOnLoad(String onLoad) {
		Object oldValue = this.onLoad;
		this.onLoad = onLoad;

		notify(this, Notification.SET, SVG.FEATURES.ON_LOAD, oldValue, onLoad);
	}

	public String getOnMouseMove() {
		return onMouseMove;
	}

	public void setOnMouseMove(String onMouseMove) {
		Object oldValue = this.onMouseMove;
		this.onMouseMove = onMouseMove;

		notify(this, Notification.SET, SVG.FEATURES.ON_MOUSE_MOVE, oldValue, onMouseMove);
	}

	public String getOnMouseUp() {
		return onMouseUp;
	}

	public void setOnMouseUp(String onMouseUp) {
		Object oldValue = this.onMouseUp;
		this.onMouseUp = onMouseUp;

		notify(this, Notification.SET, SVG.FEATURES.ON_MOUSE_UP, oldValue, onMouseUp);
	}

	// ------------------------------------------------------------------------------------------------
	// Extensions
	// ------------------------------------------------------------------------------------------------
	public List<SVGExtensionElement> getExtensionElements() {
		return extensionElements;
	}

	public boolean addExtensionElement(SVGExtensionElement scriptDefinition) {
		boolean result = false;
		if (scriptDefinition != null && !extensionElements.contains(scriptDefinition)) {
			scriptDefinition.setContainer(this);
			result = extensionElements.add(scriptDefinition);
		}

		notify(this, Notification.ADD, SVG.FEATURES.EXTENSION_ELEMENTS, null, scriptDefinition);
		return result;
	}

	public boolean removeExtensionElement(SVGExtensionElement scriptDefinition) {
		boolean result = false;
		if (scriptDefinition != null) {
			result = extensionElements.remove(scriptDefinition);
		}

		notify(this, Notification.REMOVE, SVG.FEATURES.EXTENSION_ELEMENTS, scriptDefinition, null);
		return result;
	}

	protected String overflow;

	@Override
	public String getOverflow() {
		return this.overflow;
	}

	@Override
	public void setOverflow(String overflow) {
		String oldValue = this.overflow;
		this.overflow = overflow;

		if (isChanged(oldValue, overflow)) {
			notify(this, Notification.SET, Figure.FEATURES.OVERFLOW, oldValue, overflow);
		}
	}
}
