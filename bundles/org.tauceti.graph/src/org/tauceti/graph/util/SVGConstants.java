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

import javax.xml.namespace.QName;

/**
 * @author Yang Yang <yangyang4j@gmail.com>
 */
public class SVGConstants {

	public static String XMLNS_URI_2000 				= "http://www.w3.org/2000/xmlns/";
	public static String XMLNS_SVG_PREFIX 				= "svg";
	public static String XMLNS_SVG 						= "http://www.w3.org/2000/svg";
	public static String VERSION_1_1 					= "1.1";

	public static String XLINK_PREFIX 					= "xlink";
	public static String XLINK_NAMESPACE 				= "http://www.w3.org/1999/xlink";

	public static String TAUCETI_PREFIX 				= "tauceti";
	public static String TAUCETI_NAMESPACE 				= "http://www.tauceti.org";

	public static String BASE_PROFILE_FULL 				= "full";
	public static String FILL_RULE_NONZERO 				= "nonzero"; // default value for fill rule
	public static String FILL_RULE_EVENODD 				= "evenodd";

	public static String TEXT_ANCHOR_START 				= "start";
	public static String TEXT_ANCHOR_MIDDLE 			= "middle";
	public static String TEXT_ANCHOR_END 				= "end";

	public static String PATTERN_USER_SPACE_ON_USE 		= "userSpaceOnUse";

	public static String SCRIPT_DEFINITION_LOCALNAME 	= "ScriptDefinition";
	public static QName SCRIPT_DEFINITION_QNAME 		= new QName(TAUCETI_NAMESPACE, SCRIPT_DEFINITION_LOCALNAME);

	public static String SCRIPT_DECLARATION_LOCALNAME 	= "ScriptDeclaration";
	public static QName SCRIPT_DECLARATION_QNAME 		= new QName(TAUCETI_NAMESPACE, SCRIPT_DECLARATION_LOCALNAME);

	public static String STYLE_DEFINITION_LOCALNAME 	= "StyleDefinition";
	public static QName STYLE_DEFINITION_QNAME 			= new QName(TAUCETI_NAMESPACE, STYLE_DEFINITION_LOCALNAME);

	public static String ATTRIBUTE_LOCALNAME 			= "Attribute";
	public static QName ATTRIBUTE_QNAME 				= new QName(TAUCETI_NAMESPACE, ATTRIBUTE_LOCALNAME);

	public static String LINE_CAP__BUTT 				= "butt";
	public static String LINE_CAP__ROUND 				= "round";
	public static String LINE_CAP__SQUARE 				= "square";

}
