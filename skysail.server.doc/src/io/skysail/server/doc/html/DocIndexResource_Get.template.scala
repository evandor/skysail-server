
package io.skysail.server.doc.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object DocIndexResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[RepresentationModel,ResponseEventBase,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.57*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""
  """),format.raw/*9.3*/("""<br><br><br>
  <iframe style="height:800px; width:100%;border:0px solid;" src="/doc/v1/meta.html"></iframe>
""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase): play.twirl.api.HtmlFormat.Appendable = apply(rep,response)

  def f:((RepresentationModel,ResponseEventBase) => play.twirl.api.HtmlFormat.Appendable) = (rep,response) => apply(rep,response)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Jan 14 08:37:56 CET 2018
                  SOURCE: /Users/carsten/git/skysail-server/skysail.server.doc/./src/io/skysail/server/doc/DocIndexResource_Get.scala.html
                  HASH: 36b4c53e349b33c7184cb7c6c7e0bf02a4c87052
                  MATRIX: 706->193|856->248|884->251|906->265|945->267|974->270
                  LINES: 16->6|21->6|23->8|23->8|23->8|24->9
                  -- GENERATED --
              */
          