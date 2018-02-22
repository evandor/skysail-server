package io.skysail.api.ui

//https://www.iana.org/assignments/link-relations/link-relations.xhtml

abstract class Link(val rel: String, val href: String, val target: String, val title: String, val style: String) {
  def toHtml(): String
  def toHtml(id: String): String

  def theStyle() = if (style == "") "" else s" style='$style'"
  def theTitle() = if (title == "") "" else s" title='$title'"
  def theClass() = if (style == "") "" else s" class='$style'"
  
}

case class IconLink(
                     override val rel: String,
                     icon: String,
                     override val href: String,
                     override val style: String = "",
                     override val title: String = "",
                     override val target: String = "_self")

  extends Link(rel, href, target, title, style) {

  def toHtml(): String = {
    s"<a href='$href' target='$target'${theStyle}${theTitle}><i class='$icon'></i></a>"
  }

  def toHtml(id: String): String = {
    val link = href.replace("${id}", id)
    s"<a href='$link' target='$target'${theStyle}${theTitle}><i class='$icon'></i></a>"
  }

}

case class ButtonLink(
                     override val rel: String,
                     text: String,
                     override val href: String,
                     override val style: String = "",
                     override val title: String = "",
                     override val target: String = "_self")

  extends Link(rel, href, target, title, style) {

  def toHtml(): String = {
    s"<a href='$href'${theClass} role='button' aria-pressed='true'>$text</a>"
  }

  def toHtml(id: String): String = {
    val link = href.replace("${id}", id)
    s"<a href='$link'${theClass} role='button' aria-pressed='true'>$text</a>"
  }

}

case class TextLink(
                     override val rel: String,
                     text: String,
                     override val href: String,
                     override val style: String = "",
                     override val title: String = "",
                     override val target: String = "_self")

  extends Link(rel, href, target, title, style) {

  def toHtml(): String = s"<a href='$href' target='$target'${theStyle}${theTitle}>$text</a>"

  def toHtml(id: String): String = {
    val link = href.replace("${id}", id)
    s"<a href='$link' target='$target'${theStyle}${theTitle}>$text</a>"
  }

}