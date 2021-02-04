/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package query;

import java.util.*;

/**
 * Класс Shedule
 */
public class Shedule {

	public Shedule(List<String> pro) {
		pro = new LinkedList<>();
	}
	public List<String> code;

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<String> getCode() {
		return code;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='code', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String>', type='java.util.List<java.lang.String>', typeQualifiedName='java.util.List<java.lang.String>', isModifierStatic=false, isModifierPublic=true, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=false, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setCode(List<String> code) {
		this.code = code;
	}

	public List<String> pro;

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public List<String> getPro() {
		return pro;
	}


	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='pro', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=true, isMap=false, isSet=false, isList=true, isPrimitive=false, isString=false, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String>', type='java.util.List<java.lang.String>', typeQualifiedName='java.util.List<java.lang.String>', isModifierStatic=false, isModifierPublic=true, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=false, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setPro(List<String> pro) {
		this.pro = pro;
	}
}