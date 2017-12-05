
package io.skysail.server.website.html

import play.twirl.api.Html
import html.main
import io.skysail.server.RepresentationModel

object DocIndexResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[RepresentationModel,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.28*/("""

"""),_display_(/*8.2*/main/*8.6*/ {_display_(Seq[Any](format.raw/*8.8*/("""
  """),format.raw/*9.3*/("""<br><br><br>
  <iframe style="height:800px; width:100%;border:0px solid;" src="/doc/v1/meta.html"></iframe>
""")))}))
      }
    }
  }

  def render(rep:RepresentationModel): play.twirl.api.HtmlFormat.Appendable = apply(rep)

  def f:((RepresentationModel) => play.twirl.api.HtmlFormat.Appendable) = (rep) => apply(rep)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Tue Dec 05 11:16:45 CET 2017
                  SOURCE: C:/git/skysail-server/skysail.server.website/./resources/templates/io/skysail/server/website/DocIndexResource_Get.scala.html
                  HASH: 6e03e16fcd6cd22eeab7cc2b390a181186bd0347
                  MATRIX: 649->193|770->219|798->222|809->226|847->228|876->231
                  LINES: 15->6|20->6|22->8|22->8|22->8|23->9
                  -- GENERATED --
              */
          