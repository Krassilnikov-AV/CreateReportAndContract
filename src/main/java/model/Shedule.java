/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

/**
 * Класс Shedule
 */
public class Shedule {
	private String id;
	private final String group;
	private final String pro;
	private final String audit;
	private final String type;
	private final String tech;

	public Shedule(String pro, String code, String audit, String type, String tech) {
		this.pro = pro;
		this.group = code;
		this.audit = audit;
		this.type = type;
		this.tech = tech;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='id', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=false, isMap=false, isSet=false, isList=false, isPrimitive=false, isString=true, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String', type='java.lang.String', typeQualifiedName='java.lang.String', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getPro() {
		return pro;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getAudit() {
		return audit;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getTech() {
		return tech;
	}

	@Override
	public String toString() {
//		return "Shedule{" +
//			"pro='" + pro + '\'' +
//			", group='" + group + '\'' +
//			", audit='" + audit + '\'' +
//			", type='" + type + '\'' +
//			", tech='" + tech + '\'' +
//			'}';
		return " //код группы: " + group + " //программа: " + pro + " //аудитория: " + audit +
			" //тип занятия: " + type + " //преподаватель: " + tech;
	}
}