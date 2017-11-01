package io.skysail.domain.model

import java.lang.reflect.Field
import java.lang.reflect.Type
import java.util.Collection

case class MemberModel(symbol: reflect.runtime.universe.MethodSymbol) {
  
 // require(f != null, "you must provide a non-null field to construct a FieldModel")

  val name = symbol.fullName
  
  val `type` = symbol.typeSignature.resultType.toString()
  
  override def toString() = {
    s"Name: ${symbol.fullName}, Annotations: ${symbol.annotations}, ${symbol.isSynthetic}, ${symbol.typeSignature.resultType}"
  }
  
  //def getInputType(): String = f.getAnnotation(classOf[io.skysail.core.html.Field]).inputType().name();

//  def isMandatory(): Boolean = {
//    val notNullAnnotation = f.getAnnotation(classOf[javax.validation.constraints.NotNull]);
//    if (notNullAnnotation != null) {
//      return true;
//    }
//    val sizeAnnotation = f.getAnnotation(classOf[javax.validation.constraints.Size]);
//    if (sizeAnnotation != null) {
//      if (sizeAnnotation.min() > 0) {
//        return true;
//      }
//    }
//    return false;
//  }
//  
//  //override def toString() = s"""${this.getClass.getSimpleName}(inputType: $getInputType, mandatory: $isMandatory)"""
//
//  private def getEntityType() = {
//    if (classOf[Collection[_]].isAssignableFrom(f.getType())) 
//      null//ScalaReflectionUtils.getParameterizedType(f);
//    else
//      null
//  }
}