/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package model;

/**
 * Класс SheduleSearch
 */
public class SheduleSearch {
	private final String group;
	private final String pro;
	private String dateStart;
	private String timeStart;
	private String dateFinish;
	private String timeFinish;
	private final String audit;
	private final String type;
	private final String tech;


	public SheduleSearch(String group, String pro,
						 String dateStart, String timeStart,
						 String dateFinish, String timeFinish,
						 String audit, String type, String tech) {
		this.group = group;
		this.pro = pro;
		this.dateStart = dateStart;
		this.timeStart = timeStart;
		this.dateFinish = dateFinish;
		this.timeFinish = timeFinish;
		this.audit = audit;
		this.type = type;
		this.tech = tech;
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
	public String getPro() {
		return pro;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getDateStart() {
		return dateStart;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='dateStart', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=false, isMap=false, isSet=false, isList=false, isPrimitive=false, isString=true, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String', type='java.lang.String', typeQualifiedName='java.lang.String', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getTimeStart() {
		return timeStart;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='timeStart', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=false, isMap=false, isSet=false, isList=false, isPrimitive=false, isString=true, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String', type='java.lang.String', typeQualifiedName='java.lang.String', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getDateFinish() {
		return dateFinish;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='dateFinish', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=false, isMap=false, isSet=false, isList=false, isPrimitive=false, isString=true, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String', type='java.lang.String', typeQualifiedName='java.lang.String', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setDateFinish(String dateFinish) {
		this.dateFinish = dateFinish;
	}

	/**
	 * Возвращает
	 *
	 * @return
	 */
	public String getTimeFinish() {
		return timeFinish;
	}

	/**
	 * Устанавливает
	 *
	 * @param AbstractElement{name='timeFinish', isArray=false, isPrimitiveArray=false, isObjectArray=false, isStringArray=false, isCollection=false, isMap=false, isSet=false, isList=false, isPrimitive=false, isString=true, isNumeric=false, isObject=true, isDate=false, isCalendar=false, isBoolean=false, isLong=false, isFloat=false, isDouble=false, isVoid=false, isChar=false, isByte=false, isShort=false, typeName='String', type='java.lang.String', typeQualifiedName='java.lang.String', isModifierStatic=false, isModifierPublic=false, isModifierProtected=false, isModifierPackageLocal=false, isModifierPrivate=true, isModifierFinal=false} ::: FieldElement{isConstant=false, isEnum=false, isModifierTransient=false, isModifierVolatile=false}
	 */
	public void setTimeFinish(String timeFinish) {
		this.timeFinish = timeFinish;
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
		return "SheduleSearch{" +
			"group='" + group + '\'' +
			", pro='" + pro + '\'' +
			", dateStart='" + dateStart + '\'' +
			", timeStart='" + timeStart + '\'' +
			", dateFinish='" + dateFinish + '\'' +
			", timeFinish='" + timeFinish + '\'' +
			", audit='" + audit + '\'' +
			", type='" + type + '\'' +
			", tech='" + tech + '\'' +
			'}';
	}
}