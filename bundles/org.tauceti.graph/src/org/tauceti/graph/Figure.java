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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tauceti.graph.adapter.Adapter;
import org.tauceti.graph.adapter.Notification;
import org.tauceti.graph.adapter.NotificationImpl;
import org.tauceti.graph.extensions.Attributes;
import org.tauceti.graph.extensions.Scripts;
import org.tauceti.graph.extensions.Style;
import org.tauceti.graph.extensions.impl.AttributesImpl;
import org.tauceti.graph.extensions.impl.ScriptsImpl;
import org.tauceti.graph.extensions.impl.StyleImpl;
import org.tauceti.graph.graphics.BackgroundAttribute;
import org.tauceti.graph.graphics.BorderAttribute;
import org.tauceti.graph.graphics.DataAttribute;
import org.tauceti.graph.graphics.DisplayAttribute;
import org.tauceti.graph.graphics.Transform;
import org.tauceti.graph.graphics.TransformAttribute;
import org.tauceti.graph.graphics.VisibilityAttribute;
import org.tauceti.graph.graphics.impl.DataAttributeImpl;
import org.tauceti.graph.proxy.ProxyHelper;
import org.tauceti.graph.util.ColorUtil;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public abstract class Figure implements IFigure, VisibilityAttribute, BackgroundAttribute, BorderAttribute, TransformAttribute, DisplayAttribute {

	public static enum FEATURES {
		ID, TYPE, WIDGET, WIDGET_STYLE, VISIBILITY, HAS_BOUNDS, //
		FILL_COLOR, FILL_RULE, FILL_OPACITY, //
		OPACITY, PATTERN, FILTER, CLIP_PATH, CLIP_PATH_URL, //
		STROKE_COLOR, STROKE_WIDTH, STROKE_OPACITY, STROKE_DASH_ARRAY, STROKE_LINE_CAP, //
		TRANSFORM, ATTRIBUTE_MAP, //
		PARENT, CHILDREN, DISPLAY, //
		OVERFLOW //
	}

	protected Object input;
	protected String id;
	protected String type;
	protected String widget;
	protected int widgetStyle;

	// visibility applies to individual graphics elements.
	// visibility is not an inheritable property.
	// (1) Setting 'visibility' to hidden on a 'g' will make its children invisible as long as the children do not specify their own 'visibility'
	// properties as visible.
	// (2) With 'visibility' set to hidden, processing occurs as if the element were part of the rendering tree and still taking up space, but not
	// actually rendered onto the canvas. This distinction has implications for the 'tspan', 'tref' and 'altGlyph' elements, event processing, for
	// bounding box calculations and for calculation of clipping paths.
	// (3) if 'visibility' is set to hidden, the text string is used for text layout (i.e., it takes up space) even though it is not rendered on the
	// canvas.
	// (4) if 'visibility' is set to hidden, the element might still receive events, depending on the value of property "pointer-events"
	// (5) if 'visibility' is to hidden, the geometry of the graphics element still contributes to bounding box and clipping path calculations.
	protected String visibility;
	protected String display = "inline"; // inline or none
	protected boolean hasBounds = true;

	// background
	protected String fillColor; // fill color
	protected String fillRule; // fill rule
	protected double fillOpacity = -1; // [0, 1] - [completely transparent, completely opaque]
	protected double opacity = -1; // [0, 1] - [completely transparent, completely opaque]
	protected Pattern pattern;
	protected Filter filter;
	protected ClipPath clipPath;
	protected String clipPathURL;

	// border
	protected String strokeColor; // border color
	protected int strokeWidth; // border width (px)
	protected double strokeOpacity = -1; // [0, 1] - [completely transparent, completely opaque]
	protected String strokeDasharray;
	protected String strokeLineCap;

	protected Transform transform = new Transform();
	protected Map<String, String> attributesMap = new HashMap<String, String>();

	// containment
	protected Figure parent;
	protected List<IFigure> children = new ArrayList<IFigure>();
	protected boolean isProxy;

	protected Scripts scripts;
	protected Style style;
	protected Attributes attributes;
	// Data in this DataAttribute is different from data in Adaptable. Data in this DataAttribute is serialized by SVGDOMWriter.
	protected DataAttribute dataAttribute;

	public Figure() {
		/*-
		setData(DataAttribute.class, new DataAttributeImpl());
		setData(Scripts.class, new ScriptsImpl());
		setData(Style.class, new StyleImpl());
		setData(Attributes.class, new AttributesImpl());
		*/
	}

	@Override
	public Scripts getScripts() {
		if (this.scripts == null) {
			this.scripts = new ScriptsImpl();
		}
		return this.scripts;
	}

	@Override
	public void setScripts(Scripts scripts) {
		this.scripts = scripts;
	}

	@Override
	public Style getStyle() {
		if (this.style == null) {
			this.style = new StyleImpl();
		}
		return this.style;
	}

	@Override
	public void setStyle(Style style) {
		this.style = style;
	}

	@Override
	public Attributes getAttributes() {
		if (this.attributes == null) {
			this.attributes = new AttributesImpl();
		}
		return this.attributes;
	}

	@Override
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	@Override
	public DataAttribute getDataAttribute() {
		if (this.dataAttribute == null) {
			this.dataAttribute = new DataAttributeImpl();
		}
		return this.dataAttribute;
	}

	@Override
	public void setDataAttribute(DataAttribute dataAttribute) {
		this.dataAttribute = dataAttribute;
	}

	@Override
	public Object getInput() {
		return this.input;
	}

	public void setInput(Object input) {
		this.input = input;
	}

	@Override
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		String oldValue = this.id;
		this.id = id;

		if (isChanged(oldValue, id)) {
			notify(this, Notification.SET, Figure.FEATURES.ID, oldValue, id);
		}
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		String oldValue = this.type;
		this.type = type;

		if (isChanged(oldValue, type)) {
			notify(this, Notification.SET, Figure.FEATURES.TYPE, oldValue, type);
		}
	}

	public String getWidget() {
		return this.widget;
	}

	public void setWidget(String widget) {
		String oldValue = this.widget;
		this.widget = widget;

		if (isChanged(oldValue, widget)) {
			notify(this, Notification.SET, Figure.FEATURES.WIDGET, oldValue, widget);
		}
	}

	public int getWidgetStyle() {
		return this.widgetStyle;
	}

	public void setWidgetStyle(int widgetStyle) {
		int oldValue = this.widgetStyle;
		this.widgetStyle = widgetStyle;

		if (oldValue != widgetStyle) {
			notify(this, Notification.SET, Figure.FEATURES.WIDGET_STYLE, oldValue, widgetStyle);
		}
	}

	@Override
	public String getDisplay() {
		return this.display;
	}

	@Override
	public void setDisplay(String display) {
		Object oldValue = this.display;
		this.display = SVG.checkDisplay(display);

		if (isChanged(oldValue, display)) {
			notify(this, Notification.SET, Figure.FEATURES.DISPLAY, oldValue, display);
		}
	}

	@Override
	public boolean isVisible() {
		if ("inline".equals(this.display)) {
			return true;
		}
		if ("none".equals(this.display)) {
			return false;
		}
		return false;
	}

	@Override
	public void setVisible(boolean isVisible) {
		if (isVisible) {
			setDisplay("inline");
		} else {
			setDisplay("none");
		}
	}

	@Override
	public String getVisibility() {
		return this.visibility;
	}

	@Override
	public void setVisibility(String visibility) {
		String oldValue = this.visibility;
		this.visibility = Figure.checkVisibility(visibility);

		if (isChanged(oldValue, visibility)) {
			notify(this, Notification.SET, Figure.FEATURES.VISIBILITY, oldValue, visibility);
		}
	}

	/**
	 * Value: visible | hidden | collapse | inherit
	 * 
	 * Initial: visible
	 * 
	 * Applies to: graphics elements (including the "text" element) and text sub-elements (i.e., "tspan", "tref", "altGlyph", "textPath" and "a")
	 * 
	 * Inherited: yes
	 * 
	 * @param visibility
	 * @return
	 */
	public static String checkVisibility(String visibility) {
		if (!"visible".equals(visibility) && !"hidden".equals(visibility) && !"collapse".equals(visibility) && !"inherit".equals(visibility)) {
			visibility = "visible";
		}
		return visibility;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasBounds() {
		return this.hasBounds;
	}

	/**
	 * 
	 * @param hasBounds
	 */
	public void setHasBounds(boolean hasBounds) {
		boolean oldValue = this.hasBounds;
		this.hasBounds = hasBounds;

		if (oldValue != hasBounds) {
			notify(this, Notification.SET, Figure.FEATURES.HAS_BOUNDS, oldValue, hasBounds);
		}
	}

	// ----------------------------------------------------------------------------------------------------
	// background
	// ----------------------------------------------------------------------------------------------------
	public String getFillColor() {
		return this.fillColor;
	}

	public void setFillColor(String fillColor) {
		Object oldValue = this.fillColor;
		this.fillColor = fillColor;

		if (isChanged(oldValue, fillColor)) {
			notify(this, Notification.SET, Figure.FEATURES.FILL_COLOR, oldValue, fillColor);
		}
	}

	public void setFillColor(int r, int g, int b) {
		setFillColor(ColorUtil.getColorString(r, g, b));
	}

	public void setFillColor(double r, double g, double b) {
		setFillColor(ColorUtil.getColorString(r, g, b));
	}

	public String getFillRule() {
		return this.fillRule;
	}

	public void setFillRule(String fillRule) {
		Object oldValue = this.fillRule;
		this.fillRule = fillRule;

		if (isChanged(oldValue, fillRule)) {
			notify(this, Notification.SET, Figure.FEATURES.FILL_RULE, oldValue, fillRule);
		}
	}

	public double getFillOpacity() {
		return this.fillOpacity;
	}

	public void setFillOpacity(double fillOpacity) {
		double oldValue = this.fillOpacity;
		this.fillOpacity = ColorUtil.checkOpacity(fillOpacity);

		if (isChanged(oldValue, fillOpacity)) {
			notify(this, Notification.SET, Figure.FEATURES.FILL_OPACITY, oldValue, fillOpacity);
		}
	}

	public double getOpacity() {
		return this.opacity;
	}

	public void setOpacity(double opacity) {
		double oldValue = this.opacity;
		this.opacity = ColorUtil.checkOpacity(opacity);

		if (isChanged(oldValue, opacity)) {
			notify(this, Notification.SET, Figure.FEATURES.OPACITY, oldValue, opacity);
		}
	}

	public Pattern getPattern() {
		return this.pattern;
	}

	public void setPattern(Pattern pattern) {
		Pattern oldValue = this.pattern;
		this.pattern = pattern;

		if (isChanged(oldValue, pattern)) {
			notify(this, Notification.SET, Figure.FEATURES.PATTERN, oldValue, pattern);
		}
	}

	/**
	 * 
	 * @return
	 */
	public Filter getFilter() {
		if (this.filter != null && this.filter.isProxy()) {
			Filter resolvedFilter = ProxyHelper.resolveFilter(this, this.filter.getId());
			if (resolvedFilter != null && !resolvedFilter.isProxy()) {
				this.filter = resolvedFilter;
			}
		}
		return this.filter;
	}

	/**
	 * 
	 * @param filter
	 */
	public void setFilter(Filter filter) {
		Filter oldValue = this.filter;
		this.filter = filter;

		if (isChanged(oldValue, filter)) {
			notify(this, Notification.SET, Figure.FEATURES.FILTER, oldValue, filter);
		}
	}

	/**
	 * 
	 * @return
	 */
	public ClipPath getClipPath() {
		if (this.clipPath != null && this.clipPath.isProxy()) {
			ClipPath resolvedClipPath = ProxyHelper.resolveClipPath(this, this.clipPath.getId());
			if (resolvedClipPath != null && !resolvedClipPath.isProxy()) {
				this.clipPath = resolvedClipPath;
			}
		}
		return this.clipPath;
	}

	/**
	 * 
	 * @param clipPath
	 */
	public void setClipPath(ClipPath clipPath) {
		ClipPath oldValue = this.clipPath;
		this.clipPath = clipPath;

		if (isChanged(oldValue, clipPath)) {
			notify(this, Notification.SET, Figure.FEATURES.CLIP_PATH, oldValue, clipPath);
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getClipPathURL() {
		return this.clipPathURL;
	}

	/**
	 * 
	 * @param clipPathURL
	 */
	public void setClipPathURL(String clipPathURL) {
		String oldValue = this.clipPathURL;
		this.clipPathURL = clipPathURL;

		if (isChanged(oldValue, clipPathURL)) {
			notify(this, Notification.SET, Figure.FEATURES.CLIP_PATH_URL, oldValue, clipPathURL);
		}
	}

	// ----------------------------------------------------------------------------------------------------
	// border
	// ----------------------------------------------------------------------------------------------------
	@Override
	public String getStrokeColor() {
		return this.strokeColor;
	}

	@Override
	public void setStrokeColor(String strokeColor) {
		String oldValue = this.strokeColor;
		this.strokeColor = strokeColor;

		if (isChanged(oldValue, this.strokeColor)) {
			notify(this, Notification.SET, Figure.FEATURES.STROKE_COLOR, oldValue, this.strokeColor);
		}
	}

	@Override
	public void setStrokeColor(int r, int g, int b) {
		setStrokeColor(ColorUtil.getColorString(r, g, b));
	}

	@Override
	public void setStrokeColor(double r, double g, double b) {
		setStrokeColor(ColorUtil.getColorString(r, g, b));
	}

	@Override
	public int getStrokeWidth() {
		return this.strokeWidth;
	}

	@Override
	public void setStrokeWidth(int strokeWidth) {
		int oldValue = this.strokeWidth;
		this.strokeWidth = strokeWidth;

		if (isChanged(oldValue, strokeWidth)) {
			notify(this, Notification.SET, Figure.FEATURES.STROKE_WIDTH, oldValue, strokeWidth);
		}
	}

	@Override
	public double getStrokeOpacity() {
		return this.strokeOpacity;
	}

	@Override
	public void setStrokeOpacity(double strokeOpacity) {
		double oldValue = this.strokeOpacity;
		this.strokeOpacity = ColorUtil.checkOpacity(strokeOpacity);

		if (isChanged(oldValue, strokeOpacity)) {
			notify(this, Notification.SET, Figure.FEATURES.STROKE_OPACITY, oldValue, strokeOpacity);
		}
	}

	@Override
	public String getStrokeDasharray() {
		return this.strokeDasharray;
	}

	@Override
	public void setStrokeDasharray(String strokeDasharray) {
		String oldValue = this.strokeDasharray;
		this.strokeDasharray = strokeDasharray;

		if (isChanged(oldValue, strokeDasharray)) {
			notify(this, Notification.SET, Figure.FEATURES.STROKE_DASH_ARRAY, oldValue, strokeDasharray);
		}
	}

	@Override
	public String getStrokeLineCap() {
		return this.strokeLineCap;
	}

	@Override
	public void setStrokeLineCap(String strokeLineCap) {
		String oldValue = this.strokeLineCap;
		this.strokeLineCap = strokeLineCap;

		if (isChanged(oldValue, strokeLineCap)) {
			notify(this, Notification.SET, Figure.FEATURES.STROKE_LINE_CAP, oldValue, strokeLineCap);
		}
	}

	// ----------------------------------------------------------------------------------------------------
	// transform
	// ----------------------------------------------------------------------------------------------------
	public Transform getTransform() {
		return this.transform;
	}

	public void setTransform(Transform transform) {
		Transform oldValue = this.transform;
		this.transform = transform;

		if (isChanged(oldValue, transform)) {
			notify(this, Notification.SET, Figure.FEATURES.TRANSFORM, oldValue, transform);
		}
	}

	// ----------------------------------------------------------------------------------------------------
	// attribute
	// ----------------------------------------------------------------------------------------------------
	public String[] getAttributeNames() {
		return this.attributesMap.keySet().toArray(new String[this.attributesMap.size()]);
	}

	public boolean hasAttribute(String key) {
		if (key == null) {
			return false;
		}
		return this.attributesMap.containsKey(key) && attributesMap.get(key) != null;
	}

	public String getAttribute(String key) {
		if (key == null) {
			return null;
		}
		return this.attributesMap.get(key);
	}

	public void setAttribute(String key, String value) {
		if (key == null) {
			return;
		}
		Object oldValue = getAttribute(key);
		this.attributesMap.put(key, value);

		notify(this, Notification.ADD, Figure.FEATURES.ATTRIBUTE_MAP, new Object[] { key, oldValue }, new Object[] { key, value });
	}

	public void removeAttribute(String key) {
		if (key == null) {
			return;
		}
		Object oldValue = getAttribute(key);
		this.attributesMap.remove(key);

		notify(this, Notification.REMOVE, Figure.FEATURES.ATTRIBUTE_MAP, new Object[] { key, oldValue }, new Object[] { key, null });
	}

	// ----------------------------------------------------------------------------------------------------
	// children
	// ----------------------------------------------------------------------------------------------------
	public Figure getParent() {
		return this.parent;
	}

	public void setParent(Figure parent) {
		Figure oldValue = this.parent;

		if ((oldValue == null && parent == null) || (oldValue != null && oldValue.equals(parent))) {
			return;
		}

		if (oldValue != null && !oldValue.equals(parent)) {
			oldValue.remove(this);
		}

		this.parent = parent;

		if (isChanged(oldValue, this.parent)) {
			notify(this, Notification.SET, Figure.FEATURES.PARENT, oldValue, this.parent);
		}
	}

	public List<IFigure> getChildren() {
		return this.children;
	}

	public boolean contains(IFigure shape) {
		return this.children.contains(shape);
	}

	/**
	 * 
	 * @param shape
	 */
	public void add(IFigure shape) {
		add(shape, -1);
	}

	/**
	 * @param figure
	 * @param index
	 */
	public void add(IFigure figure, int index) {
		if (figure == null) {
			throw new IllegalArgumentException("shape cannot be null");
		}

		IFigure parent = figure.getParent();
		if (this.equals(parent) || this.children.contains(figure)) {
			throw new IllegalArgumentException("This shape already contains the being added shape.");
		} else {
			figure.setParent(this);
		}

		if (index < 0 || index > this.children.size()) {
			this.children.add(figure);
		} else {
			this.children.add(index, figure);
		}

		notify(this, Notification.ADD, Figure.FEATURES.CHILDREN, null, figure);
	}

	/**
	 * 
	 * @param shape
	 */
	public void remove(IFigure shape) {
		if (shape == null) {
			throw new IllegalArgumentException("shape cannot be null");
		}
		if (!this.children.contains(shape)) {
			throw new IllegalArgumentException("shape is not a child of parent shape");
		}
		this.children.remove(shape);

		notify(this, Notification.REMOVE, Figure.FEATURES.CHILDREN, shape, null);
	}

	// ----------------------------------------------------------------------------------------------------
	// Adaptable
	// ----------------------------------------------------------------------------------------------------
	protected List<Adapter> adapters = new ArrayList<Adapter>();
	protected DataAttribute dataMap = new DataAttributeImpl();

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

	/*-
	protected AdapterSupport adapterSupport = new AdapterSupport();
	
	@Override
	public List<Adapter> getAdapters() {
		return this.adapterSupport.getAdapters();
	}
	
	@Override
	public void addAdapter(Adapter adapter) {
		this.adapterSupport.addAdapter(adapter);
	}
	
	@Override
	public void removeAdapter(Adapter adapter) {
		this.adapterSupport.removeAdapter(adapter);
	}
	
	@Override
	public void notify(Object notifier, int eventType, Object feature, Object oldValue, Object newValue) {
		this.adapterSupport.notify(notifier, eventType, feature, oldValue, newValue);
	}
	
	@Override
	public void notify(Notification msg) {
		this.adapterSupport.notify(msg);
	}
	
	@Override
	public void setData(Object value) {
		this.adapterSupport.setData(value);
	}
	
	@Override
	public <T> void setData(Class<T> clazz, T value) {
		this.adapterSupport.setData(clazz, value);
	}
	
	@Override
	public void setData(String key, Object value) {
		this.adapterSupport.setData(key, value);
	}
	
	@Override
	public String[] getDataKeys() {
		return this.adapterSupport.getDataKeys();
	}
	
	@Override
	public Object getData() {
		return this.adapterSupport.getData();
	}
	
	@Override
	public Object getData(String key) {
		return this.adapterSupport.getData(key);
	}
	
	@Override
	public <T> T getData(Class<T> clazz) {
		return this.adapterSupport.getData(clazz);
	}
	
	@Override
	public <T> T getData(String key, Class<T> clazz) {
		return this.adapterSupport.getData(key, clazz);
	}
	*/

	@Override
	public String toString() {
		return "Shape [input=" + input + ", id=" + id + ", type=" + type + ", widget=" + widget + ", visibility=" + visibility + ", display=" + display + ", fillColor=" + fillColor + ", fillRule=" + fillRule + ", fillOpacity=" + fillOpacity + ", opacity=" + opacity + ", pattern=" + pattern + ", filter=" + filter + ", strokeColor=" + strokeColor + ", strokeWidth=" + strokeWidth + ", strokeOpacity=" + strokeOpacity + ", strokeDasharray=" + strokeDasharray + ", transform=" + transform
				// + ", attributesMap=" + attributesMap + ", parent=" + parent + ", children=" + children + ", isProxy=" + isProxy + "]"; // , adapterSupport=" + adapterSupport + "
				+ ", attributesMap=" + attributesMap + ", isProxy=" + isProxy + "]"; // , adapterSupport=" + adapterSupport + "
	}

	// ----------------------------------------------------------------------------------------------------
	// Runtime
	// ----------------------------------------------------------------------------------------------------
	public boolean isProxy() {
		return this.isProxy;
	}

	public void setProxy(boolean isProxy) {
		this.isProxy = isProxy;
	}

	protected boolean isChanged(Object oldValue, Object newValue) {
		if ((oldValue == null && newValue != null) || (oldValue != null && !oldValue.equals(newValue))) {
			return true;
		}
		return false;
	}

	protected boolean isChanged(long oldValue, long newValue) {
		if (oldValue != newValue) {
			return true;
		}
		return false;
	}

	protected boolean isChanged(int oldValue, int newValue) {
		if (oldValue != newValue) {
			return true;
		}
		return false;
	}

	protected boolean isChanged(double oldValue, double newValue) {
		if (oldValue != newValue) {
			return true;
		}
		return false;
	}
}

// ----------------------------------------------------------------------------------------------------
// hashCode, equals, toString
// ----------------------------------------------------------------------------------------------------
// @Override
// public int hashCode() {
// final int prime = 31;
// int result = 1;
// result = prime * result + ((id == null) ? 0 : id.hashCode());
// return result;
// }

// @Override
// public boolean equals(Object obj) {
// if (this == obj)
// return true;
// if (obj == null)
// return false;
// if (getClass() != obj.getClass())
// return false;
// Shape other = (Shape) obj;
// if (id == null) {
// if (other.id != null)
// return false;
// } else if (!id.equals(other.id))
// return false;
// return true;
// }
