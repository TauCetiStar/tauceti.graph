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
package org.tauceti.graph.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stax.StAXResult;

import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.tauceti.graph.BoundedShape;
import org.tauceti.graph.Circle;
import org.tauceti.graph.ClipPath;
import org.tauceti.graph.Definitions;
import org.tauceti.graph.Ellipse;
import org.tauceti.graph.Figure;
import org.tauceti.graph.Filter;
import org.tauceti.graph.FilterComponent;
import org.tauceti.graph.ForeignObject;
import org.tauceti.graph.Group;
import org.tauceti.graph.IFigure;
import org.tauceti.graph.ImageShape;
import org.tauceti.graph.Line;
import org.tauceti.graph.Path;
import org.tauceti.graph.Pattern;
import org.tauceti.graph.Polygon;
import org.tauceti.graph.Polyline;
import org.tauceti.graph.Rect;
import org.tauceti.graph.SVG;
import org.tauceti.graph.ShapeConstants;
import org.tauceti.graph.Text;
import org.tauceti.graph.Use;
import org.tauceti.graph.ViewBox;
import org.tauceti.graph.extensions.Attribute;
import org.tauceti.graph.extensions.Attributes;
import org.tauceti.graph.extensions.SVGExtensionElement;
import org.tauceti.graph.extensions.SVGExtensionElementSerializer;
import org.tauceti.graph.extensions.SVGExtensionRegistry;
import org.tauceti.graph.extensions.ScriptDeclaration;
import org.tauceti.graph.extensions.Style;
import org.tauceti.graph.filter.FeBlend;
import org.tauceti.graph.filter.FeColorMatrix;
import org.tauceti.graph.filter.FeComponentTransfer;
import org.tauceti.graph.filter.FeComposite;
import org.tauceti.graph.filter.FeConvolveMatrix;
import org.tauceti.graph.filter.FeDiffuseLighting;
import org.tauceti.graph.filter.FeDisplacementMap;
import org.tauceti.graph.filter.FeDistantLight;
import org.tauceti.graph.filter.FeDropShadow;
import org.tauceti.graph.filter.FeFlood;
import org.tauceti.graph.filter.FeFunc;
import org.tauceti.graph.filter.FeGaussianBlur;
import org.tauceti.graph.filter.FeImage;
import org.tauceti.graph.filter.FeMerge;
import org.tauceti.graph.filter.FeMergeNode;
import org.tauceti.graph.filter.FeMorphology;
import org.tauceti.graph.filter.FeOffset;
import org.tauceti.graph.filter.FeSpecularLighting;
import org.tauceti.graph.filter.FeTile;
import org.tauceti.graph.filter.FeTurbulence;
import org.tauceti.graph.graphics.BackgroundAttribute;
import org.tauceti.graph.graphics.BorderAttribute;
import org.tauceti.graph.graphics.DataAttribute;
import org.tauceti.graph.graphics.DisplayAttribute;
import org.tauceti.graph.graphics.FontAttribute;
import org.tauceti.graph.graphics.OverflowAttribute;
import org.tauceti.graph.graphics.Point;
import org.tauceti.graph.graphics.Rectangle;
import org.tauceti.graph.graphics.Transform;
import org.tauceti.graph.graphics.TransformAttribute;
import org.tauceti.graph.graphics.VisibilityAttribute;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

//import com.sun.org.apache.xml.internal.serialize.OutputFormat;
//import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * Note: container element - An element which can have graphics elements and other container elements as child elements. Specifically: "a", "defs", "glyph", "g", "marker", "mask", "missing-glyph",
 * "pattern", "svg", "switch" and "symbol".
 * 
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class SVGDOMWriter {

	protected Document document = null;
	protected Figure shape;
	private SVGExtensionRegistry extensionRegistry = SVGExtensionRegistry.getInstance();

	/**
	 * 
	 * @param shape
	 */
	public SVGDOMWriter(Figure shape) {
		this.shape = shape;
	}

	/**
	 * Convert the SVG model to an XML DOM model and then write the DOM model to the output stream. Create String from the output stream and return the String.
	 * 
	 * @param shape
	 * @return
	 */
	public String toXml() {
		String xmlStr = null;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// write1(out);
			write2(out);
			xmlStr = new String(out.toByteArray(), "UTF-8");
			out.close();

			// xmlStr = write3();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlStr;
	}

	/**
	 * Convert the SVG model to an XML DOM model and then write the DOM model to the output stream.
	 * 
	 * Serialization using org.apache.xml.serialize.OutputFormat and org.apache.xml.serialize.XMLSerializer
	 * 
	 * @param out
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public void write1(OutputStream out) throws IOException {
		try {
			// create DOM builder
			DocumentBuilderFactory builderFactory = new DocumentBuilderFactoryImpl();
			builderFactory.setNamespaceAware(true);
			builderFactory.setValidating(false);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			this.document = builder.newDocument();

			// transform the EMF model to the DOM document.
			Element shapeElement = shape2XML(this.shape);
			this.document.appendChild(shapeElement);

			// transform the DOM document to its serialized form.
			OutputFormat format = new OutputFormat(this.document);
			format.setIndenting(true);
			format.setIndent(4);

			// write the DOM model to the output stream
			XMLSerializer serializer = new XMLSerializer(out, format);
			serializer.endPreserving();
			serializer.serialize(this.document);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Serialization using javax.xml.transform.Transformer and javax.xml.stream.XMLStreamWriter.
	 * 
	 * @param out
	 * @throws IOException
	 */
	public void write2(OutputStream out) throws IOException {
		try {
			// create DOM builder
			DocumentBuilderFactory builderFactory = new DocumentBuilderFactoryImpl();
			builderFactory.setNamespaceAware(true);
			builderFactory.setValidating(false);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			this.document = builder.newDocument();

			// transform the EMF model to the DOM document.
			Element shapeElement = shape2XML(this.shape);
			this.document.appendChild(shapeElement);

			// TransformerFactory transformerFactory = TransformerFactory.newInstance();
			// transformerFactory.setAttribute("indent-number", new Integer(2));
			// Transformer transformer = transformerFactory.newTransformer();
			// transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			// transformer.transform(new DOMSource(this.document), new StreamResult(new OutputStreamWriter(out, "utf-8")));

			// This API can preserve ending tag!!!
			XMLStreamWriter writer = XMLOutputFactory.newFactory().createXMLStreamWriter(out);
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			transformer.transform(new DOMSource(this.document), new StAXResult(writer));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Serialization using org.w3c.dom.DOMImplementation, org.w3c.dom.ls.DOMImplementationLS and org.w3c.dom.ls.LSSerializer.
	 * 
	 * @return
	 * @throws IOException
	 */
	public String write3() throws IOException {
		String result = null;
		try {
			// create DOM builder
			DocumentBuilderFactory builderFactory = new DocumentBuilderFactoryImpl();
			builderFactory.setNamespaceAware(true);
			builderFactory.setValidating(false);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			this.document = builder.newDocument();

			// transform the EMF model to the DOM document.
			Element shapeElement = shape2XML(this.shape);
			this.document.appendChild(shapeElement);

			DOMImplementation impl = this.document.getImplementation();
			DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS", "3.0");
			LSSerializer serializer = implLS.createLSSerializer();
			result = serializer.writeToString(this.document);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String toXmlFragment() {
		String xmlStr = null;
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			writeFragment(out);
			xmlStr = new String(out.toByteArray(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlStr;
	}

	/**
	 * Convert the SVG model to an XML DOM model and then write the DOM model to the output stream.
	 */
	@SuppressWarnings("deprecation")
	public void writeFragment(OutputStream out) throws IOException {
		try {
			// create DOM builder
			DocumentBuilderFactory builderFactory = new DocumentBuilderFactoryImpl();
			builderFactory.setNamespaceAware(true);
			builderFactory.setValidating(false);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();

			this.document = builder.newDocument();

			// transform the EMF model to the DOM document.
			Element shapeElement = shape2XML(this.shape);
			this.document.appendChild(shapeElement);

			// transform the DOM document to its serialized form.
			OutputFormat format = new OutputFormat(this.document);
			// format.setIndenting(true);
			// format.setIndent(4);
			format.setOmitXMLDeclaration(true);

			// write the DOM model to the output stream
			XMLSerializer serializer = new XMLSerializer(out, format);
			serializer.serialize(this.document);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Convert a SVG shape to a DOM element
	 * 
	 * @param shape
	 * @return
	 */
	protected Element shape2XML(IFigure shape) {
		Element element = null;

		if (shape instanceof SVG) {
			element = svgToXML((SVG) shape);

		} else if (shape instanceof Definitions) {
			element = definitionsToXML((Definitions) shape);

		} else if (shape instanceof Pattern) {
			element = patternToXML((Pattern) shape);

		} else if (shape instanceof Use) {
			element = useToXML((Use) shape);

		} else if (shape instanceof Filter) {
			element = filterToXML((Filter) shape);

		} else if (shape instanceof FeBlend) {
			element = feBlendToXML((FeBlend) shape);

		} else if (shape instanceof FeColorMatrix) {
			element = feColorMatrixToXML((FeColorMatrix) shape);

		} else if (shape instanceof FeComponentTransfer) {
			element = feComponentTransferToXML((FeComponentTransfer) shape);

		} else if (shape instanceof FeComposite) {
			element = feCompositeToXML((FeComposite) shape);

		} else if (shape instanceof FeConvolveMatrix) {
			element = feConvolveMatrixToXML((FeConvolveMatrix) shape);

		} else if (shape instanceof FeDiffuseLighting) {
			element = feDiffuseLightingToXML((FeDiffuseLighting) shape);

		} else if (shape instanceof FeDisplacementMap) {
			element = feDisplacementMapToXML((FeDisplacementMap) shape);

		} else if (shape instanceof FeDropShadow) {
			element = feDropShadowToXML((FeDropShadow) shape);

		} else if (shape instanceof FeFlood) {
			element = feFloodToXML((FeFlood) shape);

		} else if (shape instanceof FeGaussianBlur) {
			element = feGaussianBlurToXML((FeGaussianBlur) shape);

		} else if (shape instanceof FeImage) {
			element = feImageToXML((FeImage) shape);

		} else if (shape instanceof FeMerge) {
			element = feMergeToXML((FeMerge) shape);

		} else if (shape instanceof FeMorphology) {
			element = feMorphologyToXML((FeMorphology) shape);

		} else if (shape instanceof FeOffset) {
			element = feOffsetToXML((FeOffset) shape);

		} else if (shape instanceof FeSpecularLighting) {
			element = feSpecularLightingToXML((FeSpecularLighting) shape);

		} else if (shape instanceof FeTile) {
			element = feTileToXML((FeTile) shape);

		} else if (shape instanceof FeTurbulence) {
			element = feTurbulenceToXML((FeTurbulence) shape);

		} else if (shape instanceof Text) {
			element = textToXML((Text) shape);

		} else if (shape instanceof Group) {
			element = groupToXML((Group) shape);

		} else if (shape instanceof ClipPath) {
			element = clipPathToXML((ClipPath) shape);

		} else if (shape instanceof Rect) {
			element = rectToXML((Rect) shape);

		} else if (shape instanceof Circle) {
			element = circleToXML((Circle) shape);

		} else if (shape instanceof Ellipse) {
			element = ellipseToXML((Ellipse) shape);

		} else if (shape instanceof Line) {
			element = lineToXML((Line) shape);

		} else if (shape instanceof Path) {
			element = pathToXML((Path) shape);

		} else if (shape instanceof Polyline) {
			element = polylineToXML((Polyline) shape);

		} else if (shape instanceof Polygon) {
			element = polygonToXML((Polygon) shape);

		} else if (shape instanceof ForeignObject) {
			element = foreignObjectToXML((ForeignObject) shape);

		} else if (shape instanceof ImageShape) {
			element = imageToXML((ImageShape) shape);

		} else {
			if (shape == null) {
				System.err.println("SVG shape is null");
			} else {
				System.err.println("SVG shape is not supported [" + shape.getClass().getName() + "] " + shape.toString());
			}
		}

		if (element != null) {
			appendVisibilityAttributes(shape, element);
			appendDataAttributes(shape, element);
			appendScriptDeclarations(shape, element);
			appendStyle(shape, element);
			appendAttributes(shape, element);
		}
		return element;
	}

	/**
	 * Create DOM element for SVG
	 * 
	 * @param svg
	 * @return
	 */
	protected Element svgToXML(SVG svg) {
		Element svgElement = createElement(svg, ShapeConstants.SVG);

		if (svg.getLevel() != null) {
			svgElement.setAttribute(ShapeConstants.LEVEL, svg.getLevel().toString());
		}

		String xmlns = svg.getXmlns();
		if (xmlns != null && !xmlns.isEmpty()) {
			svgElement.setAttribute(ShapeConstants.XMLNS, xmlns);
		}

		String version = svg.getVersion();
		if (version != null && !version.isEmpty()) {
			svgElement.setAttribute(ShapeConstants.VERSION, version);
		}

		serializePrefixes(svg, svgElement);

		if (svg.getBaseProfile() != null) {
			svgElement.setAttribute(ShapeConstants.BASE_PROFILE, svg.getBaseProfile());
		}

		// svgElement.setAttribute("x", String.valueOf(svg.getX())); // + "px"
		// svgElement.setAttribute("y", String.valueOf(svg.getY())); // + "px"
		// svgElement.setAttribute("width", String.valueOf(svg.getWidth())); // + "px"
		// svgElement.setAttribute("height", String.valueOf(svg.getHeight())); // + "px"
		Rectangle bounds = svg.getBounds();
		if (svg.hasBounds() && bounds != null) {
			svgElement.setAttribute(ShapeConstants.X, String.valueOf(bounds.getX()));
			svgElement.setAttribute(ShapeConstants.Y, String.valueOf(bounds.getY()));
			svgElement.setAttribute(ShapeConstants.WIDTH, String.valueOf(bounds.getWidth()));
			svgElement.setAttribute(ShapeConstants.HEIGHT, String.valueOf(bounds.getHeight()));
		}

		ViewBox viewBox = svg.getViewBox();
		if (viewBox != null) {
			svgElement.setAttribute(ShapeConstants.VIEW_BOX, viewBox.getViewBoxString());
		}

		appendFontAttributes(svg, svgElement);
		appendTransformAttributes(svg, svgElement);
		appendBackgroundAttributes(svg, svgElement);

		List<SVGExtensionElement> extensionElements = svg.getExtensionElements();
		if (extensionElements != null) {
			for (SVGExtensionElement extensionElement : extensionElements) {
				Element childElement = extensionElement2XML(svg, extensionElement);
				if (childElement != null) {
					svgElement.appendChild(childElement);
				}
			}
		}

		List<Definitions> defsList = new ArrayList<Definitions>();
		List<Use> uses = new ArrayList<Use>();

		for (IFigure child : svg.getChildren()) {
			if (child instanceof Definitions) {
				defsList.add((Definitions) child);
			} else if (child instanceof Use) {
				uses.add((Use) child);
			}
		}

		// defs
		for (Definitions defsObj : defsList) {
			Element defsElement = definitionsToXML(defsObj);
			if (defsElement != null) {
				svgElement.appendChild(defsElement);
			}
		}

		// children
		for (IFigure child : svg.getChildren()) {
			if (child instanceof Definitions || child instanceof Use) {
				continue;
			}
			Element childElement = shape2XML(child);
			if (childElement != null) {
				svgElement.appendChild(childElement);
			}
		}

		// uses
		for (Use use : uses) {
			Element useElement = useToXML(use);
			if (useElement != null) {
				svgElement.appendChild(useElement);
			}
		}

		return svgElement;
	}

	/**
	 * Create DOM element for SVG extension element.
	 * 
	 * @param parentShape
	 * @param extensionElement
	 * @return
	 */
	protected Element extensionElement2XML(Figure parentShape, SVGExtensionElement extensionElement) {
		Element childElement = null;
		SVGExtensionElementSerializer serializer = extensionRegistry.getExtensionElementSerializer(extensionElement.getElementType());
		if (serializer != null) {
			DocumentFragment fragment = document.createDocumentFragment();
			serializer.marshall(parentShape, fragment, extensionElement, extensionRegistry, this);
			childElement = getFirstChildElement(fragment);
		}
		return childElement;
	}

	/**
	 * 
	 * @param fragment
	 * @return
	 */
	protected Element getFirstChildElement(DocumentFragment fragment) {
		Node child = fragment.getFirstChild();
		while (child != null && !(child instanceof Element)) {
			child = child.getNextSibling();
		}
		// We are out of the loop: either child is null, or it is an element
		if (child != null) {
			return (Element) child;
		}
		throw new IllegalArgumentException("Document Fragment does not contain any Elements");
	}

	/**
	 * Create DOM element for SVG defs
	 * 
	 * @param definitions
	 * @return
	 */
	protected Element definitionsToXML(Definitions definitions) {
		Element defsElement = createElement(definitions, ShapeConstants.DEFS);

		// children shapes
		appendChildren(definitions, defsElement);

		return defsElement;
	}

	/**
	 * Create DOM element for SVG Filter
	 * 
	 * @param filter
	 * @return
	 */
	protected Element filterToXML(Filter filter) {
		Element filterElement = createElement(filter, ShapeConstants.FILTER);

		// x, y, width, height
		if (filter.getX() != null && !filter.getX().isEmpty()) {
			filterElement.setAttribute(ShapeConstants.X, filter.getX());
		}
		if (filter.getY() != null && !filter.getY().isEmpty()) {
			filterElement.setAttribute(ShapeConstants.Y, filter.getY());
		}
		if (filter.getWidth() != null && !filter.getWidth().isEmpty()) {
			filterElement.setAttribute(ShapeConstants.WIDTH, filter.getWidth());
		}
		if (filter.getHeight() != null && !filter.getHeight().isEmpty()) {
			filterElement.setAttribute(ShapeConstants.HEIGHT, filter.getHeight());
		}

		// filterUnits, primitiveUnits, color-interpolation-filters
		if (filter.getFilterUnits() != null && !filter.getFilterUnits().isEmpty()) {
			filterElement.setAttribute(ShapeConstants.FILTER_UNITS, filter.getFilterUnits());
		}
		if (filter.getPrimitiveUnits() != null && !filter.getPrimitiveUnits().isEmpty()) {
			filterElement.setAttribute(ShapeConstants.PRIMITIVE_UNITS, filter.getPrimitiveUnits());
		}
		if (filter.getColorInterpolationFilters() != null && !filter.getColorInterpolationFilters().isEmpty()) {
			filterElement.setAttribute(ShapeConstants.COLOR_INTERPOLATION_FILTERS, filter.getColorInterpolationFilters());
		}

		// children
		for (IFigure child : filter.getChildren()) {
			Element childElement = shape2XML(child);
			if (childElement != null) {
				filterElement.appendChild(childElement);
			}
		}

		return filterElement;
	}

	/**
	 * Update DOM element for FilterComponent to set common attributes.
	 * 
	 * @param filterComponent
	 * @param filterComponentElement
	 */
	protected void appendFilterCommonAttributes(FilterComponent filterComponent, Element filterComponentElement) {
		if (filterComponent == null || filterComponentElement == null) {
			return;
		}

		// x, y, width, height
		if (filterComponent.getX() != null && !filterComponent.getX().isEmpty()) {
			filterComponentElement.setAttribute(ShapeConstants.X, filterComponent.getX());
		}
		if (filterComponent.getY() != null && !filterComponent.getY().isEmpty()) {
			filterComponentElement.setAttribute(ShapeConstants.Y, filterComponent.getY());
		}
		if (filterComponent.getWidth() != null && !filterComponent.getWidth().isEmpty()) {
			filterComponentElement.setAttribute(ShapeConstants.WIDTH, filterComponent.getWidth());
		}
		if (filterComponent.getHeight() != null && !filterComponent.getHeight().isEmpty()) {
			filterComponentElement.setAttribute(ShapeConstants.HEIGHT, filterComponent.getHeight());
		}

		// in, in2, result
		if (filterComponent.getIn() != null && !filterComponent.getIn().isEmpty()) {
			filterComponentElement.setAttribute(ShapeConstants.IN, filterComponent.getIn());
		}
		if (filterComponent.getIn2() != null && !filterComponent.getIn2().isEmpty()) {
			filterComponentElement.setAttribute(ShapeConstants.IN2, filterComponent.getIn2());
		}
		if (filterComponent.getResult() != null && !filterComponent.getResult().isEmpty()) {
			filterComponentElement.setAttribute(ShapeConstants.RESULT, filterComponent.getResult());
		}
	}

	/**
	 * Create DOM element for FeBlend
	 * 
	 * @param feBlend
	 * @return
	 */
	protected Element feBlendToXML(FeBlend feBlend) {
		Element feBlendElement = createElement(feBlend, ShapeConstants.FE_BLEND);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feBlend, feBlendElement);

		// mode
		if (feBlend.getMode() != null && !feBlend.getMode().isEmpty()) {
			feBlendElement.setAttribute(ShapeConstants.MODE, feBlend.getMode());
		}

		return feBlendElement;
	}

	/**
	 * Create DOM element for FeColorMatrix
	 * 
	 * @param feColorMatrix
	 * @return
	 */
	protected Element feColorMatrixToXML(FeColorMatrix feColorMatrix) {
		Element feColorMatrixElement = createElement(feColorMatrix, ShapeConstants.FE_COLOR_MATRIX);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feColorMatrix, feColorMatrixElement);

		// type, value
		if (feColorMatrix.getType() != null && !feColorMatrix.getType().isEmpty()) {
			feColorMatrixElement.setAttribute(ShapeConstants.TYPE, feColorMatrix.getType());
		}
		if (feColorMatrix.getValues() != null && !feColorMatrix.getValues().isEmpty()) {
			feColorMatrixElement.setAttribute(ShapeConstants.VALUES, feColorMatrix.getValues());
		}

		return feColorMatrixElement;
	}

	/**
	 * Create DOM element for FeComponentTransfer
	 * 
	 * @param feComponentTransfer
	 * @return
	 */
	protected Element feComponentTransferToXML(FeComponentTransfer feComponentTransfer) {
		Element feComponentTransferElement = createElement(feComponentTransfer, ShapeConstants.FE_COMPONENT_TRANSFER);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feComponentTransfer, feComponentTransferElement);

		// funcs
		for (IFigure child : feComponentTransfer.getChildren()) {
			if (child instanceof FeFunc) {
				FeFunc feFunc = (FeFunc) child;

				Element feFuncElement = null;
				{
					// feFuncR or feFuncG or feFuncB or feFuncA
					feFuncElement = createElement(feFunc, feFunc.getTagName());

					// type, tableValues
					if (feFunc.getType() != null && !feFunc.getType().isEmpty()) {
						feFuncElement.setAttribute(ShapeConstants.TYPE, feFunc.getType());
					}
					if (feFunc.getTableValues() != null && !feFunc.getTableValues().isEmpty()) {
						feFuncElement.setAttribute(ShapeConstants.TABLE_VALUES, String.valueOf(feFunc.getTableValues()));
					}
				}

				if (feFuncElement != null) {
					feComponentTransferElement.appendChild(feFuncElement);
				}
			}
		}

		return feComponentTransferElement;
	}

	/**
	 * Create DOM element for FeComposite
	 * 
	 * @param feComposite
	 * @return
	 */
	protected Element feCompositeToXML(FeComposite feComposite) {
		Element feCompositeElement = createElement(feComposite, ShapeConstants.FE_COMPOSITE);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feComposite, feCompositeElement);

		// operator
		if (feComposite.getOperator() != null && !feComposite.getOperator().isEmpty()) {
			feCompositeElement.setAttribute(ShapeConstants.OPERATOR, feComposite.getOperator());
		}

		return feCompositeElement;
	}

	/**
	 * Create DOM element for FeConvolveMatrix
	 * 
	 * @param feConvolveMatrix
	 * @return
	 */
	protected Element feConvolveMatrixToXML(FeConvolveMatrix feConvolveMatrix) {
		Element feConvolveMatrixElement = createElement(feConvolveMatrix, ShapeConstants.FE_CONVOLVE_MATRIX);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feConvolveMatrix, feConvolveMatrixElement);

		// order, kernelMatrix
		if (feConvolveMatrix.getOrder() != null && !feConvolveMatrix.getOrder().isEmpty()) {
			feConvolveMatrixElement.setAttribute(ShapeConstants.ORDER, feConvolveMatrix.getOrder());
		}
		if (feConvolveMatrix.getKernelMatrix() != null && !feConvolveMatrix.getKernelMatrix().isEmpty()) {
			feConvolveMatrixElement.setAttribute(ShapeConstants.KERNEL_MATRIX, feConvolveMatrix.getKernelMatrix());
		}

		// divisor, bias, targetX, targetY
		if (feConvolveMatrix.getDivisor() != null) {
			feConvolveMatrixElement.setAttribute(ShapeConstants.DIVISOR, String.valueOf(feConvolveMatrix.getDivisor()));
		}
		if (feConvolveMatrix.getBias() != null) {
			feConvolveMatrixElement.setAttribute(ShapeConstants.BIAS, String.valueOf(feConvolveMatrix.getBias()));
		}
		if (feConvolveMatrix.getTargetX() != null) {
			feConvolveMatrixElement.setAttribute(ShapeConstants.TARGET_X, String.valueOf(feConvolveMatrix.getTargetX()));
		}
		if (feConvolveMatrix.getTargetY() != null) {
			feConvolveMatrixElement.setAttribute(ShapeConstants.TARGET_Y, String.valueOf(feConvolveMatrix.getTargetY()));
		}

		// edgeMode, preserveAlpha
		if (feConvolveMatrix.getEdgeMode() != null && !feConvolveMatrix.getEdgeMode().isEmpty()) {
			feConvolveMatrixElement.setAttribute(ShapeConstants.EDGE_MODE, feConvolveMatrix.getEdgeMode());
		}
		if (feConvolveMatrix.isPreserveAlpha() != null) {
			feConvolveMatrixElement.setAttribute(ShapeConstants.PRESERVE_ALPHA, String.valueOf(feConvolveMatrix.isPreserveAlpha()));
		}

		return feConvolveMatrixElement;
	}

	/**
	 * Create DOM element for FeDiffuseLighting
	 * 
	 * @param feDiffuseLighting
	 * @return
	 */
	protected Element feDiffuseLightingToXML(FeDiffuseLighting feDiffuseLighting) {
		Element feDiffuseLightingElement = createElement(feDiffuseLighting, ShapeConstants.FE_DIFFUSE_LIGHTING);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feDiffuseLighting, feDiffuseLightingElement);

		// surfaceScale, diffuseConstant, lighting-color
		if (feDiffuseLighting.getSurfaceScale() != null) {
			feDiffuseLightingElement.setAttribute(ShapeConstants.SURFACE_SCALE, String.valueOf(feDiffuseLighting.getSurfaceScale()));
		}
		if (feDiffuseLighting.getDiffuseConstant() != null) {
			feDiffuseLightingElement.setAttribute(ShapeConstants.DIFFUSE_CONSTANT, String.valueOf(feDiffuseLighting.getDiffuseConstant()));
		}
		if (feDiffuseLighting.getLightingColor() != null && !feDiffuseLighting.getLightingColor().isEmpty()) {
			feDiffuseLightingElement.setAttribute(ShapeConstants.LIGHTING_COLOR, feDiffuseLighting.getLightingColor());
		}

		// feDistantLight
		for (IFigure child : feDiffuseLighting.getChildren()) {
			if (child instanceof FeDistantLight) {
				FeDistantLight feDistantLight = (FeDistantLight) child;

				// feDistantLight
				Element feDistangLightElement = createElement(feDistantLight, ShapeConstants.FE_DISTANT_LIGHT);
				// azimuth, elevation
				if (feDistantLight.getAzimuth() != null) {
					feDistangLightElement.setAttribute(ShapeConstants.AZIMUTH, String.valueOf(feDistantLight.getAzimuth()));
				}
				if (feDistantLight.getElevation() != null) {
					feDistangLightElement.setAttribute(ShapeConstants.ELEVATION, String.valueOf(feDistantLight.getElevation()));
				}
				feDiffuseLightingElement.appendChild(feDistangLightElement);
			}
		}

		return feDiffuseLightingElement;
	}

	/**
	 * Create DOM element for FeDisplacementMap
	 * 
	 * @param feDisplacementMap
	 * @return
	 */
	protected Element feDisplacementMapToXML(FeDisplacementMap feDisplacementMap) {
		Element feDisplacementMapElement = createElement(feDisplacementMap, ShapeConstants.FE_DISPLACEMENT_MAP);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feDisplacementMap, feDisplacementMapElement);

		// scale, xChannelSelector, yChannelSelector
		if (feDisplacementMap.getScale() != null) {
			feDisplacementMapElement.setAttribute(ShapeConstants.SCALE, String.valueOf(feDisplacementMap.getScale()));
		}
		if (feDisplacementMap.getXChannelSelector() != null && !feDisplacementMap.getXChannelSelector().isEmpty()) {
			feDisplacementMapElement.setAttribute(ShapeConstants.X_CHANNEL_SELECTOR, feDisplacementMap.getXChannelSelector());
		}
		if (feDisplacementMap.getYChannelSelector() != null && !feDisplacementMap.getYChannelSelector().isEmpty()) {
			feDisplacementMapElement.setAttribute(ShapeConstants.Y_CHANNEL_SELECTOR, feDisplacementMap.getYChannelSelector());
		}

		return feDisplacementMapElement;
	}

	/**
	 * Create DOM element for FeDropShadow
	 * 
	 * @param feDropShadow
	 * @return
	 */
	protected Element feDropShadowToXML(FeDropShadow feDropShadow) {
		Element feDropShadowElement = createElement(feDropShadow, ShapeConstants.FE_DROP_SHADOW);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feDropShadow, feDropShadowElement);

		// dx, dy
		if (feDropShadow.getDx() != null) {
			feDropShadowElement.setAttribute(ShapeConstants.DX, String.valueOf(feDropShadow.getDx()));
		}
		if (feDropShadow.getDy() != null) {
			feDropShadowElement.setAttribute(ShapeConstants.DY, String.valueOf(feDropShadow.getDy()));
		}

		// stdDeviation, flood-color, flood-opacity
		if (feDropShadow.getStdDeviation() != null) {
			feDropShadowElement.setAttribute(ShapeConstants.STD_DEVIATION, String.valueOf(feDropShadow.getStdDeviation()));
		}
		if (feDropShadow.getFloodColor() != null && !feDropShadow.getFloodColor().isEmpty()) {
			feDropShadowElement.setAttribute(ShapeConstants.FLOOD_COLOR, feDropShadow.getFloodColor());
		}
		if (feDropShadow.getFloodOpacity() != null) {
			feDropShadowElement.setAttribute(ShapeConstants.FLOOD_OPACITY, String.valueOf(feDropShadow.getFloodOpacity()));
		}

		return feDropShadowElement;
	}

	/**
	 * Create DOM element for FeFlood
	 * 
	 * @param feFlood
	 * @return
	 */
	protected Element feFloodToXML(FeFlood feFlood) {
		Element feFloodElement = createElement(feFlood, ShapeConstants.FE_FLOOD);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feFlood, feFloodElement);

		// flood-color, flood-opacity
		if (feFlood.getFloodColor() != null && !feFlood.getFloodColor().isEmpty()) {
			feFloodElement.setAttribute(ShapeConstants.FLOOD_COLOR, feFlood.getFloodColor());
		}
		if (feFlood.getFloodOpacity() != null) {
			feFloodElement.setAttribute(ShapeConstants.FLOOD_OPACITY, String.valueOf(feFlood.getFloodOpacity()));
		}

		return feFloodElement;
	}

	/**
	 * Create DOM element for FeGaussianBlur
	 * 
	 * @param feGaussianBlur
	 * @return
	 */
	protected Element feGaussianBlurToXML(FeGaussianBlur feGaussianBlur) {
		Element feGaussianBlurElement = createElement(feGaussianBlur, ShapeConstants.FE_GAUSSIAN_BLUR);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feGaussianBlur, feGaussianBlurElement);

		// stdDeviation, edgeMode, value
		if (feGaussianBlur.getStdDeviation() != null && !feGaussianBlur.getStdDeviation().isEmpty()) {
			feGaussianBlurElement.setAttribute(ShapeConstants.STD_DEVIATION, feGaussianBlur.getStdDeviation());
		}
		if (feGaussianBlur.getEdgeMode() != null && !feGaussianBlur.getEdgeMode().isEmpty()) {
			feGaussianBlurElement.setAttribute(ShapeConstants.EDGE_MODE, feGaussianBlur.getEdgeMode());
		}

		return feGaussianBlurElement;
	}

	/**
	 * Create DOM element for FeImage
	 * 
	 * @param feImage
	 * @return
	 */
	protected Element feImageToXML(FeImage feImage) {
		Element feImageElement = createElement(feImage, ShapeConstants.FE_IMAGE);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feImage, feImageElement);

		// href, preserveAspectRatio, crossOrigin
		if (feImage.getHref() != null && !feImage.getHref().isEmpty()) {
			feImageElement.setAttribute(ShapeConstants.HREF, feImage.getHref());
		}
		if (feImage.getPreserveAspectRatio() != null && !feImage.getPreserveAspectRatio().isEmpty()) {
			feImageElement.setAttribute(ShapeConstants.PRESERVE_ASPECT_RATIO, feImage.getPreserveAspectRatio());
		}
		if (feImage.getCrossOrigin() != null && !feImage.getCrossOrigin().isEmpty()) {
			feImageElement.setAttribute(ShapeConstants.CROSS_ORIGIN, feImage.getCrossOrigin());
		}

		return feImageElement;
	}

	/**
	 * Create DOM element for FeMerge
	 * 
	 * @param feMerge
	 * @return
	 */
	protected Element feMergeToXML(FeMerge feMerge) {
		Element feMergeElement = createElement(feMerge, ShapeConstants.FE_MERGE);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feMerge, feMergeElement);

		// feMergeNode
		for (IFigure child : feMerge.getChildren()) {
			if (child instanceof FeMergeNode) {
				FeMergeNode feMergeNode = (FeMergeNode) child;
				// feMergeNode
				Element feMergeNodeElement = createElement(feMergeNode, ShapeConstants.FE_MERGE_NODE);
				// in
				if (feMergeNode.getIn() != null && !feMergeNode.getIn().isEmpty()) {
					feMergeNodeElement.setAttribute(ShapeConstants.IN, feMergeNode.getIn());
				}
				feMergeElement.appendChild(feMergeNodeElement);
			}
		}

		return feMergeElement;
	}

	/**
	 * Create DOM element for FeMorphology
	 * 
	 * @param feMorphology
	 * @return
	 */
	protected Element feMorphologyToXML(FeMorphology feMorphology) {
		Element feMorphologyElement = createElement(feMorphology, ShapeConstants.FE_MORPHOLOGY);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feMorphology, feMorphologyElement);

		// operator, radius
		if (feMorphology.getOperator() != null && !feMorphology.getOperator().isEmpty()) {
			feMorphologyElement.setAttribute(ShapeConstants.OPERATOR, feMorphology.getOperator());
		}
		if (feMorphology.getRadius() != null && !feMorphology.getRadius().isEmpty()) {
			feMorphologyElement.setAttribute(ShapeConstants.RADIUS, feMorphology.getRadius());
		}

		return feMorphologyElement;
	}

	/**
	 * Create DOM element for FeOffset
	 * 
	 * @param feOffset
	 * @return
	 */
	protected Element feOffsetToXML(FeOffset feOffset) {
		Element feOffsetElement = createElement(feOffset, ShapeConstants.FE_OFFSET);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feOffset, feOffsetElement);

		// dx, dy
		if (feOffset.getDx() != null) {
			feOffsetElement.setAttribute(ShapeConstants.DX, String.valueOf(feOffset.getDx()));
		}
		if (feOffset.getDy() != null) {
			feOffsetElement.setAttribute(ShapeConstants.DY, String.valueOf(feOffset.getDy()));
		}

		return feOffsetElement;
	}

	/**
	 * Create DOM element for FeSpecularLighting
	 * 
	 * @param feSpecularLighting
	 * @return
	 */
	protected Element feSpecularLightingToXML(FeSpecularLighting feSpecularLighting) {
		Element feSpecularLightingElement = createElement(feSpecularLighting, ShapeConstants.FE_SPECULAR_LIGHTING);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feSpecularLighting, feSpecularLightingElement);

		// surfaceScale, specularConstant, specularExponent, lighting-color
		if (feSpecularLighting.getSurfaceScale() != null) {
			feSpecularLightingElement.setAttribute(ShapeConstants.SURFACE_SCALE, String.valueOf(feSpecularLighting.getSurfaceScale()));
		}
		if (feSpecularLighting.getSpecularConstant() != null) {
			feSpecularLightingElement.setAttribute(ShapeConstants.SPECULAR_CONSTANT, String.valueOf(feSpecularLighting.getSpecularConstant()));
		}
		if (feSpecularLighting.getSpecularExponent() != null) {
			feSpecularLightingElement.setAttribute(ShapeConstants.SPECULAR_EXPONENT, String.valueOf(feSpecularLighting.getSpecularExponent()));
		}
		if (feSpecularLighting.getLightingColor() != null && !feSpecularLighting.getLightingColor().isEmpty()) {
			feSpecularLightingElement.setAttribute(ShapeConstants.LIGHTING_COLOR, feSpecularLighting.getLightingColor());
		}

		// feDistantLight
		for (IFigure child : feSpecularLighting.getChildren()) {
			if (child instanceof FeDistantLight) {
				FeDistantLight feDistantLight = (FeDistantLight) child;

				// feDistantLight
				Element feDistangLightElement = createElement(feDistantLight, ShapeConstants.FE_DISTANT_LIGHT);
				// azimuth, elevation
				if (feDistantLight.getAzimuth() != null) {
					feDistangLightElement.setAttribute(ShapeConstants.AZIMUTH, String.valueOf(feDistantLight.getAzimuth()));
				}
				if (feDistantLight.getElevation() != null) {
					feDistangLightElement.setAttribute(ShapeConstants.ELEVATION, String.valueOf(feDistantLight.getElevation()));
				}
				feSpecularLightingElement.appendChild(feDistangLightElement);
			}
		}

		return feSpecularLightingElement;
	}

	/**
	 * Create DOM element for FeTile
	 * 
	 * @param feTile
	 * @return
	 */
	protected Element feTileToXML(FeTile feTile) {
		Element feTileElement = createElement(feTile, ShapeConstants.FE_TILE);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feTile, feTileElement);

		return feTileElement;
	}

	/**
	 * Create DOM element for FeTurbulence
	 * 
	 * @param feTurbulence
	 * @return
	 */
	protected Element feTurbulenceToXML(FeTurbulence feTurbulence) {
		Element feTurbulenceElement = createElement(feTurbulence, ShapeConstants.FE_TURBULENCE);

		// x, y, width, height, in, in2, result
		appendFilterCommonAttributes(feTurbulence, feTurbulenceElement);

		// type, baseFrequency, numOctaves, seed, stitchTiles
		if (feTurbulence.getType() != null && !feTurbulence.getType().isEmpty()) {
			feTurbulenceElement.setAttribute(ShapeConstants.TYPE, feTurbulence.getType());
		}
		if (feTurbulence.getBaseFrequency() != null && !feTurbulence.getBaseFrequency().isEmpty()) {
			feTurbulenceElement.setAttribute(ShapeConstants.BASE_FREQUENCY, feTurbulence.getBaseFrequency());
		}
		if (feTurbulence.getNumOctaves() != null) {
			feTurbulenceElement.setAttribute(ShapeConstants.NUM_OCTAVES, String.valueOf(feTurbulence.getNumOctaves()));
		}
		if (feTurbulence.getSeed() != null) {
			feTurbulenceElement.setAttribute(ShapeConstants.SEED, String.valueOf(feTurbulence.getSeed()));
		}
		if (feTurbulence.getStitchTiles() != null && !feTurbulence.getStitchTiles().isEmpty()) {
			feTurbulenceElement.setAttribute(ShapeConstants.STITCH_TILES, feTurbulence.getStitchTiles());
		}

		return feTurbulenceElement;
	}

	/**
	 * Create DOM element for SVG Text
	 * 
	 * @param text
	 * @return
	 */
	protected Element textToXML(Text text) {
		Element textElement = createElement(text, ShapeConstants.TEXT);

		// top-left location
		textElement.setAttribute(ShapeConstants.X, String.valueOf(text.getX()));
		textElement.setAttribute(ShapeConstants.Y, String.valueOf(text.getY()));

		// attributes to tweak the position of the text.
		if (text.getDx() != 0) {
			textElement.setAttribute(ShapeConstants.DX, String.valueOf(text.getDx()));
		}
		if (text.getDy() != 0) {
			textElement.setAttribute(ShapeConstants.DY, String.valueOf(text.getDy()));
		}

		// corner radius
		if (text.getTextAnchor() != null) {
			textElement.setAttribute(ShapeConstants.TEXT_ANCHOR, text.getTextAnchor());
		}
		if (text.getLengthAdjust() != null) {
			textElement.setAttribute(ShapeConstants.LENGTH_ADJUST, text.getLengthAdjust());
		}
		if (text.getTextLength() != null) {
			textElement.setAttribute(ShapeConstants.TEXT_LENGTH, text.getTextLength());
		}

		appendBackgroundAttributes(text, textElement);
		appendBorderAttributes(text, textElement);
		appendTransformAttributes(text, textElement);

		// set text
		textElement.setTextContent((text.getText() != null) ? text.getText() : "");

		return textElement;
	}

	/**
	 * Create DOM element for SVG Group
	 * 
	 * @param group
	 * @return
	 */
	protected Element groupToXML(Group group) {
		Element groupElement = createElement(group, ShapeConstants.G);

		// top-left location and size
		if (group.hasBounds()) {
			groupElement.setAttribute(ShapeConstants.X, String.valueOf(group.getX()));
			groupElement.setAttribute(ShapeConstants.Y, String.valueOf(group.getY()));
			groupElement.setAttribute(ShapeConstants.WIDTH, String.valueOf(group.getWidth()));
			groupElement.setAttribute(ShapeConstants.HEIGHT, String.valueOf(group.getHeight()));
		}

		// common attributes
		appendBackgroundAttributes(group, groupElement);
		appendBorderAttributes(group, groupElement);
		appendTransformAttributes(group, groupElement);

		// children shapes
		appendChildren(group, groupElement);

		return groupElement;
	}

	/**
	 * 
	 * @param clipPath
	 * @return
	 */
	protected Element clipPathToXML(ClipPath clipPath) {
		Element clipPathElement = createElement(clipPath, ShapeConstants.CLIP_PATH);

		// children shapes
		appendChildren(clipPath, clipPathElement);

		return clipPathElement;
	}

	/**
	 * Create DOM element for SVG rect
	 * 
	 * @param rect
	 * @return
	 */
	protected Element rectToXML(Rect rect) {
		Element rectElement = createElement(rect, ShapeConstants.RECT);

		// if ("widget_815__inner_rect".equals(rect.getId())) {
		// System.out.println();
		// System.out.println();
		// }

		// top-left location and size
		rectElement.setAttribute(ShapeConstants.X, String.valueOf(rect.getX()));
		rectElement.setAttribute(ShapeConstants.Y, String.valueOf(rect.getY()));
		rectElement.setAttribute(ShapeConstants.WIDTH, String.valueOf(rect.getWidth()));
		rectElement.setAttribute(ShapeConstants.HEIGHT, String.valueOf(rect.getHeight()));

		// corner radius
		if (rect.getRx() != -1) {
			rectElement.setAttribute(ShapeConstants.RX, String.valueOf(rect.getRx()));
		}
		if (rect.getRy() != -1) {
			rectElement.setAttribute(ShapeConstants.RY, String.valueOf(rect.getRy()));
		}

		// common attributes
		appendBackgroundAttributes(rect, rectElement);
		appendBorderAttributes(rect, rectElement);

		// children shapes
		appendChildren(rect, rectElement);

		return rectElement;
	}

	/**
	 * Create DOM element for SVG circle
	 * 
	 * @param circle
	 * @return
	 */
	protected Element circleToXML(Circle circle) {
		Element circleElement = createElement(circle, ShapeConstants.CIRCLE);

		// center location and radius
		circleElement.setAttribute(ShapeConstants.CX, String.valueOf(circle.getCenterX()));
		circleElement.setAttribute(ShapeConstants.CY, String.valueOf(circle.getCenterY()));
		circleElement.setAttribute(ShapeConstants.R, String.valueOf(circle.getRadius()));

		// common attributes
		appendBackgroundAttributes(circle, circleElement);
		appendBorderAttributes(circle, circleElement);

		return circleElement;
	}

	/**
	 * Create DOM element for SVG ellipse
	 * 
	 * @param ellipse
	 * @return
	 */
	protected Element ellipseToXML(Ellipse ellipse) {
		Element rectElement = createElement(ellipse, ShapeConstants.ELLIPSE);

		// center location
		rectElement.setAttribute(ShapeConstants.CX, String.valueOf(ellipse.getCenterX()));
		rectElement.setAttribute(ShapeConstants.CY, String.valueOf(ellipse.getCenterY()));

		// corner radius
		if (ellipse.getRadiusX() != -1) {
			rectElement.setAttribute(ShapeConstants.RX, String.valueOf(ellipse.getRadiusX()));
		}
		if (ellipse.getRadiusX() != -1) {
			rectElement.setAttribute(ShapeConstants.RY, String.valueOf(ellipse.getRadiusY()));
		}

		// common attributes
		appendBackgroundAttributes(ellipse, rectElement);
		appendBorderAttributes(ellipse, rectElement);

		return rectElement;
	}

	/**
	 * Create DOM element for SVG line
	 * 
	 * @param line
	 * @return
	 */
	protected Element lineToXML(Line line) {
		Element lineElement = createElement(line, ShapeConstants.LINE);

		// start location
		lineElement.setAttribute(ShapeConstants.X1, String.valueOf(line.getX1()));
		lineElement.setAttribute(ShapeConstants.Y1, String.valueOf(line.getY1()));

		// end location
		lineElement.setAttribute(ShapeConstants.X2, String.valueOf(line.getX2()));
		lineElement.setAttribute(ShapeConstants.Y2, String.valueOf(line.getY2()));

		// common attributes
		appendBackgroundAttributes(line, lineElement);
		appendBorderAttributes(line, lineElement);

		return lineElement;
	}

	/**
	 * Create DOM element for SVG path
	 * 
	 * @param path
	 * @return
	 */
	protected Element pathToXML(Path path) {
		Element pathElement = createElement(path, ShapeConstants.PATH);

		// d
		pathElement.setAttribute(ShapeConstants.D, String.valueOf(path.getD()));

		// common attributes
		appendBackgroundAttributes(path, pathElement);
		appendBorderAttributes(path, pathElement);

		return pathElement;
	}

	/**
	 * Create DOM element for SVG polyline
	 * 
	 * @param polyline
	 * @return
	 */
	protected Element polylineToXML(Polyline polyline) {
		Element polylineElement = createElement(polyline, ShapeConstants.POLYLINE);

		// points
		if (polyline.getPoints() != null) {
			StringBuffer pointsValue = new StringBuffer();
			for (int i = 0; i < polyline.getPoints().size(); i++) {
				Point point = polyline.getPoints().get(i);
				if (i > 0) {
					pointsValue.append(" ");
				}
				pointsValue.append(point.getX()).append(",").append(point.getY());
			}

			polylineElement.setAttribute(ShapeConstants.POINTS, pointsValue.toString());
		}

		// common attributes
		appendBackgroundAttributes(polyline, polylineElement);
		appendBorderAttributes(polyline, polylineElement);

		return polylineElement;
	}

	/**
	 * Create DOM element for SVG polygon
	 * 
	 * @param polygon
	 * @return
	 */
	protected Element polygonToXML(Polygon polygon) {
		Element polygonElement = createElement(polygon, ShapeConstants.POLYGON);

		// points
		if (polygon.getPoints() != null) {
			StringBuffer pointsValue = new StringBuffer();
			for (int i = 0; i < polygon.getPoints().size(); i++) {
				Point point = polygon.getPoints().get(i);
				if (i > 0) {
					pointsValue.append(" ");
				}
				pointsValue.append(point.getX()).append(",").append(point.getY());
			}

			polygonElement.setAttribute(ShapeConstants.POINTS, pointsValue.toString());
		}

		// common attributes
		appendBackgroundAttributes(polygon, polygonElement);
		appendBorderAttributes(polygon, polygonElement);

		return polygonElement;
	}

	/**
	 * Create DOM element for SVG foreignObject
	 * 
	 * @param foreignObject
	 * @return
	 */
	protected Element foreignObjectToXML(ForeignObject foreignObject) {
		Element element = createElement(foreignObject, ShapeConstants.FOREIGN_OBJECT);
		// 1. top-left location and size
		element.setAttribute(ShapeConstants.X, String.valueOf(foreignObject.getX()));
		element.setAttribute(ShapeConstants.Y, String.valueOf(foreignObject.getY()));
		element.setAttribute(ShapeConstants.WIDTH, String.valueOf(foreignObject.getWidth()));
		element.setAttribute(ShapeConstants.HEIGHT, String.valueOf(foreignObject.getHeight()));

		// 2. common attributes
		appendBackgroundAttributes(foreignObject, element);
		appendBorderAttributes(foreignObject, element);

		// 3. children shapes
		// ForeignObject contains html contents. Parse the html string to DOM element and append it to the foreignObjectElement.
		// appendChildren(foreignObject, foreignObjectElement);

		// read1. Parse DOM document from string
		// read2. handle org.w3c.dom.DOMException: WRONG_DOCUMENT_ERR: A node is used in a different document than the one that created it
		String htmlContent = foreignObject.getContent();
		Exception ex = null;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(htmlContent));

			Document doc = builder.parse(is);

			Node childElement = doc.getFirstChild();
			if (childElement != null) {
				Node importedChildElement = this.document.importNode(childElement, true);
				element.appendChild(importedChildElement);

				// To handle html widgets with text content containing XML prolog (e.g. <?xml version="1.0" encoding="UTF-8"?>), the XML prolog are removed from
				// the text content by the figure classes, so the xml DocumentBuilder here can parse the it into DOM element successfully as DeferredElement
				// (rather than CDataSection). After the DOM element is created, need to find the DOM element and recover its text content with original text
				// content.
				// String hasXMLProlog = foreignObject.getAttribute("hasXMLProlog");
				// String widgetIdWithXMLProlog = foreignObject.getAttribute("widgetIdWithXMLProlog");
				String postponeTextContent = foreignObject.getAttribute(ShapeConstants.POSTPONE_TEXT_CONTENT);
				String postponeTextContentWidgetId = foreignObject.getAttribute(ShapeConstants.POSTPONE_TEXT_CONTENT_WIDGET_ID);

				if (ShapeConstants.TRUE.equalsIgnoreCase(postponeTextContent) && postponeTextContentWidgetId != null) {
					Element postponeElement = null;

					// find the element (with original text content containing XML prolog) by id
					Stack<Node> stack = new Stack<Node>();
					stack.push(importedChildElement);
					while (!stack.isEmpty()) {
						Node currNode = stack.pop();

						Node currIdAttr = currNode.getAttributes().getNamedItem(ShapeConstants.ID); // id attribute
						if (currIdAttr != null && postponeTextContentWidgetId.equals(currIdAttr.getNodeValue())) {
							postponeElement = (Element) currNode;
							break;
						}

						NodeList childrenNodes = currNode.getChildNodes();
						if (childrenNodes != null) {
							for (int i = 0; i < childrenNodes.getLength(); i++) {
								Node childNode = childrenNodes.item(i);
								stack.push(childNode);
							}
						}
					}

					// NodeList childrenNodes = importedChildElement.getChildNodes();
					// if (childrenNodes != null) {
					// for (int i = 0; i < childrenNodes.getLength(); i++) {
					// Node childNode = childrenNodes.item(i);
					//
					// Node currIdAttr = childNode.getAttributes().getNamedItem("id"); // id attribute
					// if (currIdAttr != null && postponeTextContentWidgetId.equals(currIdAttr.getNodeValue())) {
					// postponeElement = (Element) childNode;
					// break;
					// }
					// }
					// }

					if (postponeElement != null) {
						// String nodeValue = cdataElement.getNodeValue();
						// if (nodeValue != null && nodeValue.startsWith("<![CDATA[") && nodeValue.endsWith("]]>")) {
						// String newNodeValue = nodeValue.substring("<![CDATA[".length(), nodeValue.lastIndexOf("]]>"));
						// cdataElement.setNodeValue(newNodeValue);
						// }
						// String textContent = cdataElement.getTextContent();
						// if (textContent != null && textContent.startsWith("<![CDATA[") && textContent.endsWith("]]>")) {
						// String newTextContent = textContent.substring("<![CDATA[".length(), textContent.lastIndexOf("]]>"));
						// cdataElement.setTextContent(newTextContent);
						// }

						// Recover its original text conent (with XML prolog)
						String postponeTextContentValue = foreignObject.getAttribute(ShapeConstants.POSTPONE_TEXT_CONTENT_VALUE);
						postponeElement.setTextContent(postponeTextContentValue);
					}
				}

				// ForeignObject can have children SVG Shapes that are contained inside a DIV.
				// Put the DIV id in the ForeignObject's "innerContainerId" attribute, so that the DIV element can be found here
				// and the DOM elements serialized from the ForeignObject's children SVG Shapes can be added to the DIV element.
				String innerContainerId = foreignObject.getAttribute(ShapeConstants.INNER_CONTAINER_ID);

				if (innerContainerId != null && !innerContainerId.isEmpty()) {
					Element innerContainerElement = null;

					Stack<Node> stack = new Stack<Node>();
					stack.push(importedChildElement);
					while (!stack.isEmpty()) {
						Node currNode = stack.pop();

						Node currIdAttr = currNode.getAttributes().getNamedItem(ShapeConstants.ID); // id attribute
						if (currIdAttr != null && innerContainerId.equals(currIdAttr.getNodeValue())) {
							innerContainerElement = (Element) currNode;
							break;
						}

						NodeList childrenNodes = currNode.getChildNodes();
						if (childrenNodes != null) {
							for (int i = 0; i < childrenNodes.getLength(); i++) {
								Node childNode = childrenNodes.item(i);
								stack.push(childNode);
							}
						}
					}

					if (innerContainerElement != null) {
						for (IFigure child : foreignObject.getChildren()) {
							Element _childElement = shape2XML(child);
							if (_childElement != null) {
								innerContainerElement.appendChild(_childElement);
							}
						}
					}
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			ex = e;
		} catch (SAXException e) {
			e.printStackTrace();
			ex = e;
		} catch (IOException e) {
			e.printStackTrace();
			ex = e;
		}

		if (ex != null) {
			System.err.println(getClass().getSimpleName() + ".foreignObjectToXML() " + ex.getMessage());
			System.err.println("htmlContent:");
			System.err.println(htmlContent);
			System.err.println();
		}

		return element;
	}

	/**
	 * Create DOM element for SVG use
	 * 
	 * @param use
	 * @return
	 */
	protected Element useToXML(Use use) {
		Element useElement = createElement(use, ShapeConstants.USE);

		// xlink:href
		if (use.getHref() != null) {
			useElement.setAttribute(ShapeConstants.HREF, use.getHref());
		}
		if (use.getShape() != null) {
			useElement.setAttribute(ShapeConstants.XLINK_HREF, "#" + use.getShape().getId());
		}

		// x, y
		if (use.getX() != -1) {
			useElement.setAttribute(ShapeConstants.X, String.valueOf(use.getX()));
		}
		if (use.getY() != -1) {
			useElement.setAttribute(ShapeConstants.Y, String.valueOf(use.getY()));
		}
		// groupElement.setAttribute("width", String.valueOf(use.getWidth()));
		// groupElement.setAttribute("height", String.valueOf(use.getHeight()));

		appendBackgroundAttributes(use, useElement);
		appendBorderAttributes(use, useElement);
		appendTransformAttributes(use, useElement);

		return useElement;
	}

	/**
	 * Create DOM element for SVG pattern
	 * 
	 * @param pattern
	 * @return
	 */
	protected Element patternToXML(Pattern pattern) {
		Element element = createElement(pattern, ShapeConstants.PATTERN);

		// x, y, width, height
		appendBoundAttributes(pattern, element);

		// patternUnits
		if (pattern.getPatternUnits() != null) {
			element.setAttribute(ShapeConstants.PATTERN_UNITS, pattern.getPatternUnits());
		}

		// common attributes
		appendBackgroundAttributes(pattern, element);
		appendBorderAttributes(pattern, element);

		// children shapes
		appendChildren(pattern, element);

		return element;
	}

	/**
	 * Create DOM element for SVG image
	 * 
	 * @param imageShape
	 * @return
	 */
	protected Element imageToXML(ImageShape imageShape) {
		Element element = createElement(imageShape, ShapeConstants.IMAGE);

		// x, y, width, height
		appendBoundAttributes(imageShape, element);

		// xlink:href
		if (imageShape.getHref() != null) {
			// element.setAttribute("xlink:href", image.getHref());
			element.setAttribute(ShapeConstants.HREF, imageShape.getHref());
		}

		// preserveAspectRatio
		if (imageShape.getPreserveAspectRatio() != null) {
			element.setAttribute(ShapeConstants.PRESERVE_ASPECT_RATIO, imageShape.getPreserveAspectRatio());
		}

		return element;
	}

	/**
	 * Create DOM element for a Shape
	 * 
	 * @param shape
	 * @param tagName
	 * @return
	 */
	protected Element createElement(Figure shape, String tagName) {
		Element newElement = null;

		String namespaceURI = SVGConstants.XMLNS_SVG;
		List<String> prefixes = NSMapUtil.getNamespaceMap(shape).getReverse(namespaceURI);
		if (!prefixes.isEmpty() && !prefixes.get(0).equals("")) {
			newElement = this.document.createElementNS(namespaceURI, prefixes.get(0) + ":" + tagName);
		} else {
			newElement = this.document.createElement(tagName);
		}

		if (newElement != null) {
			if (shape.getId() != null) {
				newElement.setAttribute(ShapeConstants.ID, shape.getId());
			}
			if (shape.getType() != null) {
				// newElement.setAttribute("type", shape.getType());
				// newElement.setAttributeNS(SVGConstants.EUROPA_SVG_NAMESPACE, SVGConstants.EUROPA_DATA_PREFIX + ":type", shape.getType());
			}

			// Customized attributes
			if (shape.getWidget() != null) {
				newElement.setAttribute(ShapeConstants.CUSTOM__WIDGET, shape.getWidget());
				// newElement.setAttributeNS(SVGConstants.EUROPA_SVG_NAMESPACE, SVGConstants.EUROPA_DATA_PREFIX + ":widget", shape.getWidget());
				if (shape.getWidgetStyle() > 0) {
					newElement.setAttribute(ShapeConstants.CUSTOM__WIDGET_STYLE, String.valueOf(shape.getWidgetStyle()));
				}
				newElement.setAttribute(ShapeConstants.CUSTOM__FIGURE, shape.getClass().getSimpleName()); // test only
			}

			// filter
			Filter filter = shape.getFilter();
			if (filter != null) {
				String filterId = filter.getId();
				if (filterId != null && !filterId.isEmpty()) {
					newElement.setAttribute(ShapeConstants.FILTER, "url(#" + filterId + ")");
				}
			}

			// clip-path
			String clipPathURL = shape.getClipPathURL();
			if (clipPathURL != null && !clipPathURL.isEmpty()) {
				newElement.setAttribute(ShapeConstants.ATTR__CLIP_PATH, clipPathURL);

			} else {
				ClipPath clipPath = shape.getClipPath();
				if (clipPath != null && !clipPath.isProxy()) {
					String clipPathId = clipPath.getId();
					if (clipPathId != null && !clipPathId.isEmpty()) {
						newElement.setAttribute(ShapeConstants.ATTR__CLIP_PATH, "url(#" + clipPathId + ")");
					}
				}
			}
		}

		return newElement;
	}

	/**
	 * 
	 * @param shape
	 * @param shapeElement
	 */
	protected void serializePrefixes(Figure shape, Element shapeElement) {
		NSMap nsMap = NSMapUtil.getNamespaceMap(shape);
		if (!nsMap.isEmpty()) {
			for (Map.Entry<String, String> entry : nsMap.entrySet()) {
				String prefix = entry.getKey();
				String namespace = entry.getValue();
				if (prefix.length() == 0) {
					shapeElement.setAttributeNS(SVGConstants.XMLNS_URI_2000, "xmlns", namespace); //$NON-NLS-1$
				} else {
					shapeElement.setAttributeNS(SVGConstants.XMLNS_URI_2000, "xmlns:" + prefix, namespace); //$NON-NLS-1$
				}
			}
		}
	}

	/**
	 * 
	 * @param boundedShape
	 * @param element
	 */
	protected void appendBoundAttributes(BoundedShape boundedShape, Element element) {
		if (boundedShape == null || element == null) {
			return;
		}
		element.setAttribute(ShapeConstants.X, String.valueOf(boundedShape.getX()));
		element.setAttribute(ShapeConstants.Y, String.valueOf(boundedShape.getY()));
		element.setAttribute(ShapeConstants.WIDTH, String.valueOf(boundedShape.getWidth()));
		element.setAttribute(ShapeConstants.HEIGHT, String.valueOf(boundedShape.getHeight()));
	}

	/**
	 * Append display and visibility attributes
	 * 
	 * @param shape
	 * @param shapeElement
	 */
	protected void appendVisibilityAttributes(IFigure shape, Element shapeElement) {
		/*
		 * display
		 * 
		 * Value: inline | block | list-item | run-in | compact | marker | table | inline-table | table-row-group | table-header-group | table-footer-group | table-row | table-column-group | table-column |
		 * table-cell | table-caption | none | inherit
		 * 
		 * Initial: inline
		 * 
		 * Applies to: "svg", "g", "switch", "a", "foreignObject", graphics elements (including the "text" element) and text sub-elements (i.e., "tspan", "tref", "altGlyph", "textPath")
		 * 
		 * Inherited: no
		 */
		if (shape instanceof DisplayAttribute && ((DisplayAttribute) shape).getDisplay() != null) {
			shapeElement.setAttribute(ShapeConstants.DISPLAY, ((DisplayAttribute) shape).getDisplay());
		}

		/*
		 * visibility
		 * 
		 * Value: visible | hidden | collapse | inherit
		 * 
		 * Initial: visible
		 * 
		 * Applies to: graphics elements (including the "text" element) and text sub-elements (i.e., "tspan", "tref", "altGlyph", "textPath" and "a")
		 * 
		 * Inherited: yes
		 */
		if (shape instanceof VisibilityAttribute && ((VisibilityAttribute) shape).getVisibility() != null) {
			shapeElement.setAttribute(ShapeConstants.VISIBILITY, ((VisibilityAttribute) shape).getVisibility());
		}

		/*
		 * overflow
		 * 
		 * Value: auto | visible | scroll | hidden
		 */
		if (shape instanceof OverflowAttribute) {
			String overflow = ((OverflowAttribute) shape).getOverflow();
			if (OverflowAttribute.AUTO.equals(overflow) //
					|| OverflowAttribute.VISIBLE.equals(overflow) //
					|| OverflowAttribute.SCROLL.equals(overflow) //
					|| OverflowAttribute.HIDDEN.equals(overflow) //
			) {
				shapeElement.setAttribute(ShapeConstants.OVERFLOW, overflow);
			}
		}
	}

	/**
	 * 
	 * @param shape
	 * @param shapeElement
	 */
	protected void appendDataAttributes(IFigure shape, Element shapeElement) {
		String prefix = NSMapUtil.getPrefix(shape, SVGConstants.TAUCETI_NAMESPACE, SVGConstants.TAUCETI_PREFIX);

		DataAttribute dataAttribute = shape.getDataAttribute();
		String[] dataKeys = shape.getDataKeys();
		if (dataKeys != null) {
			for (String key : dataKeys) {
				Object value = dataAttribute.getData(key);
				if (value != null) {
					// shapeElement.setAttributeNS(SVGConstants.EUROPA_DATA_NAMESPACE_DATA, "xmlns:" + key, value.toString());
					shapeElement.setAttribute(prefix + ":" + key, value.toString());
				}
			}
		}
	}

	/**
	 * Append attributes for font
	 * 
	 * @param fontAttribute
	 * @param shapeElement
	 */
	protected void appendFontAttributes(FontAttribute fontAttribute, Element shapeElement) {
		// font name
		if (fontAttribute.getFontFamily() != null) {
			shapeElement.setAttribute(ShapeConstants.FONT_FAMILY, fontAttribute.getFontFamily());
		}

		// font size
		if (fontAttribute.getFontSize() > 0) {
			shapeElement.setAttribute(ShapeConstants.FONT_SIZE, String.valueOf(fontAttribute.getFontSize()) + ShapeConstants.PX);
		}
	}

	/**
	 * Append attributes for background color
	 * 
	 * @param bgAttribute
	 * @param shapeElement
	 */
	protected void appendBackgroundAttributes(BackgroundAttribute bgAttribute, Element shapeElement) {
		// opacity
		if (bgAttribute.getOpacity() != -1) {
			shapeElement.setAttribute(ShapeConstants.OPACITY, String.valueOf(bgAttribute.getOpacity()));
		}

		// background color
		if (bgAttribute.getFillColor() != null) {
			shapeElement.setAttribute(ShapeConstants.FILL, bgAttribute.getFillColor());
		}

		if (bgAttribute.getPattern() != null) {
			shapeElement.setAttribute(ShapeConstants.FILL, "url(#" + bgAttribute.getPattern().getId() + ")");
		}

		// rule for applying background color
		if (bgAttribute.getFillRule() != null) {
			shapeElement.setAttribute(ShapeConstants.FILL_RULE, bgAttribute.getFillRule());
		}

		// fill opacity
		if (bgAttribute.getFillOpacity() != -1) {
			shapeElement.setAttribute(ShapeConstants.FILL_OPACITY, String.valueOf(bgAttribute.getFillOpacity()));
		}
	}

	/**
	 * Append attributes for border color (or line color)
	 * 
	 * @param borderAttribute
	 * @param xmlStr
	 */
	protected void appendBorderAttributes(BorderAttribute borderAttribute, Element shapeElement) {
		// border color
		if (borderAttribute.getStrokeColor() != null) {
			shapeElement.setAttribute(ShapeConstants.STROKE, borderAttribute.getStrokeColor());
		}

		// border width
		if (borderAttribute.getStrokeWidth() >= 0) {
			shapeElement.setAttribute(ShapeConstants.STROKE_WIDTH, borderAttribute.getStrokeWidth() + ShapeConstants.PX);
		}

		// stroke opacity
		if (borderAttribute.getStrokeOpacity() != -1) {
			shapeElement.setAttribute(ShapeConstants.STROKE_OPACITY, String.valueOf(borderAttribute.getStrokeOpacity()));
		}

		// border dash
		if (borderAttribute.getStrokeDasharray() != null) {
			shapeElement.setAttribute(ShapeConstants.STROKE_DASHARRAY, borderAttribute.getStrokeDasharray());
		}

		// stroke-linecap (butt, round, square)
		if (borderAttribute.getStrokeLineCap() != null) {
			shapeElement.setAttribute(ShapeConstants.STROKE_LINECAP, borderAttribute.getStrokeLineCap());
		}
	}

	/**
	 * Append attributes for Transform
	 * 
	 * @param transformAttribute
	 * @param shapeElement
	 */
	protected void appendTransformAttributes(TransformAttribute transformAttribute, Element shapeElement) {
		Transform transform = transformAttribute.getTransform();
		if (transform != null) {
			StringBuffer sb = new StringBuffer();

			// matrix
			if (transform.getMatrix() != null) {
				sb.append(" matrix(").append(transform.getMatrix()).append(")");
			}

			// translate
			if (transform.getTranslateX() != 0 || transform.getTranslateY() != 0) {
				sb.append(" translate(").append(transform.getTranslateX() + "," + transform.getTranslateY()).append(")");
			}

			// rotate
			if (transform.getRotate() != 0) {
				sb.append(" rotate(").append(transform.getRotate()).append(")");
			}

			// scale
			if (transform.getScaleX() != 1 || transform.getScaleY() != 1) {
				if (transform.getScaleX() == transform.getScaleY()) {
					sb.append(" scale(").append(transform.getScaleX()).append(")");
				} else {
					sb.append(" scale(").append(transform.getScaleX() + "," + transform.getScaleY()).append(")");
				}
			}

			// skewX
			if (transform.getSkewX() != 0) {
				sb.append(" skewX(").append(transform.getSkewX()).append(")");
			}

			// skewY
			if (transform.getSkewY() != 0) {
				sb.append(" skewY(").append(transform.getSkewY()).append(")");
			}

			String transformValue = sb.toString();
			if (transformValue.startsWith(" ") && transformValue.length() > 1) {
				transformValue = transformValue.substring(1);
			}

			if (!transformValue.isEmpty()) {
				shapeElement.setAttribute(ShapeConstants.TRANSFORM, transformValue);
			}
		}
	}

	/**
	 * 
	 * @param shape
	 * @param shapeElement
	 */
	protected void appendScriptDeclarations(IFigure shape, Element shapeElement) {
		List<ScriptDeclaration> scriptDeclarations = shape.getScripts().getScriptDeclarations();
		for (ScriptDeclaration scriptDeclaration : scriptDeclarations) {
			String eventName = scriptDeclaration.getEventName();
			String function = scriptDeclaration.getFunction();
			if (eventName != null && function != null) {
				shapeElement.setAttribute(eventName, function);
			}
		}
	}

	/**
	 * 
	 * @param shape
	 * @param shapeElement
	 */
	protected void appendStyle(IFigure shape, Element shapeElement) {
		Style style = shape.getStyle();
		if (style != null) {
			String name = style.getName();

			String styleValue = "";
			String propertiesString = style.getPropertiesString();
			if (propertiesString != null) {
				styleValue += propertiesString;
			}
			String cssContent = style.getContent();
			if (cssContent != null) {
				styleValue += cssContent;
			}

			// approach1
			if (!styleValue.isEmpty()) {
				shapeElement.setAttribute(name, styleValue);
			}

			// approach2
			// Attr attr = document.createAttribute(name);
			// attr.setValue(propertiesString);
			// shapeElement.setAttributeNode(attr);
		}
	}

	/**
	 * 
	 * @param shape
	 * @param shapeElement
	 */
	protected void appendAttributes(IFigure shape, Element shapeElement) {
		Attributes attributesObj = shape.getAttributes();
		if (attributesObj != null) {
			for (Attribute attribute : attributesObj.getAttributes()) {
				String attrName = attribute.getName();
				Object attrValue = attribute.getValue();
				if (attrName != null && attrValue != null) {
					// if (shape instanceof SVG) {
					// System.out.println();
					// System.out.println();
					// }
					shapeElement.setAttribute(attrName, String.valueOf(attrValue));
				}
			}
		}
	}

	/**
	 * Append DOM elements of children Shapes of a Shape to the DOM element of the Shape
	 * 
	 * @param shape
	 * @param shapeElement
	 */
	protected void appendChildren(Figure shape, Element shapeElement) {
		if (shape != null && shapeElement != null) {
			for (IFigure child : shape.getChildren()) {
				Element childElement = shape2XML(child);
				if (childElement != null) {
					shapeElement.appendChild(childElement);
				}
			}
		}
	}
}
