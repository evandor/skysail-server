
package io.skysail.server.doc.html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object DocIndexResource_Get extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[RepresentationModel,ResponseEventBase,Any,play.twirl.api.HtmlFormat.Appendable] {

  /*************************************
* Home page.                        *
*                                   *
* @param msg The message to display *
*************************************/
  def apply/*6.2*/(rep: RepresentationModel, response: ResponseEventBase, o: Any):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*6.65*/("""

"""),_display_(/*8.2*/main(response)/*8.16*/ {_display_(Seq[Any](format.raw/*8.18*/("""
  """),format.raw/*9.3*/("""<br><br><br>
  <iframe style="height:800px; width:100%;border:0px solid;" src="/doc/v1/meta.html"></iframe>
""")))}))
      }
    }
  }

  def render(rep:RepresentationModel,response:ResponseEventBase,o:Any): play.twirl.api.HtmlFormat.Appendable = apply(rep,response,o)

  def f:((RepresentationModel,ResponseEventBase,Any) => play.twirl.api.HtmlFormat.Appendable) = (rep,response,o) => apply(rep,response,o)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Wed Feb 07 14:07:55 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server.doc/./src/io/skysail/server/doc/DocIndexResource_Get.scala.html
                  HASH: f3830bfb051882c75f4daa7f15d80a28220dddea
                  MATRIX: 710->193|868->256|896->259|918->273|957->275|986->278
                  LINES: 16->6|21->6|23->8|23->8|23->8|24->9
                  -- GENERATED --
              */
          