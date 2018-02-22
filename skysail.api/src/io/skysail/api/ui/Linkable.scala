package io.skysail.api.ui

trait Linkable {
  def _links: Seq[Link]
  
  //def forRelation(rel: String) = _links.filter(_.rel == rel).headOption.map(_.toHtml()).getOrElse("")

}
