package io.skysail.domain.model

import scala.annotation.meta.field
import scala.beans.BeanProperty
import akka.http.scaladsl.server.PathMatcher
import io.skysail.domain.Resource

object LinkModel2 {
//  def fromLinkheader(l: String): LinkModel = {
//    require(l != null, "the linkheader string must not be empty")
//    val parts = l.split(";")
//    val uriPart = parts(0).trim().replace("<","").replace(">", "")
//    val linkModel = new LinkModel(context = "", path = uriPart, resource = null)
//    parts.foreach { part => parsePart(part, linkModel) }
//    linkModel
//  }
//
//  def parsePart(part: String, linkModel: LinkModel):Unit = {
//    val keyValue = part.split("=");
//    if (keyValue.length != 2) {
//      return
//      //throw new IllegalArgumentException();
//    }
//    keyValue(0).trim() match {
//      case "title" =>  linkModel.setTitle(keyValue(1).replace("\"", ""))
//      case "refId" =>  linkModel.setRefId(keyValue(1).replace("\"", ""))
//      case _ => 
//    }
//  }
}

case class LinkModel2(
    val context: String,
    val pathMatcher: PathMatcher[Unit]
    //@BeanProperty rat: ResourceAssociationType = null
    //resource: ResourceDefinition[_] = null
    //@BeanProperty val resourceClass: Class[_ <: ResourceDefinition[_]] = null
    ) {

  @BeanProperty val relation = LinkRelation.ALTERNATE//if (resource != null) resource.getLinkRelation() else LinkRelation.ALTERNATE
  @BeanProperty val verbs = Set()//if (resource != null) resource.getVerbs() else Set()
  @BeanProperty var title = determineTitle()
  @BeanProperty val alt: String = "-"
  @BeanProperty val needsAuth: Boolean = false
  //@BeanProperty val linkRole: LinkRole = LinkRole.DEFAULT
  @BeanProperty var refId: String = _
  @BeanProperty var cls: Class[_] = _

  def getUri() = context //+ path

  //override def toString() = s"'{path}': {resourceClass} ($rat) [title: $getTitle()]"

  def asLinkheaderElement(): String = {
    val sb = new StringBuilder().append("<").append(getUri()).append(">");
    sb.append("; rel=\"").append(relation.getName()).append("\"");
    if (getTitle() != null) {
      sb.append("; title=\"").append(getTitle()).append("\"");
    }
    if (getRefId() != null) {
      sb.append("; refId=\"").append(getRefId()).append("\"");
    }
    sb.append("; verbs=\"")
      //.append(verbs.map(verb => verb.getName()).mkString(",")).append("\"");
    return sb.toString();
  }

  def determineTitle(): String = {
//    if (resource == null) {
//      return "unknown.."
//    }
//    val title = resource.getFromContext(ResourceContextId.LINK_TITLE)
//    if (title == null) "unknown" else title
    "unknown..."
  }

}